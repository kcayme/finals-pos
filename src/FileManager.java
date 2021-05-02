import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FileManager {
	private JFrame messageFrame;
	private File file;
	private JTable inventoryTable;
	
	public FileManager(String fileName, JTable table) {
		this.setFile(fileName);
		this.setTable(table);
	}
	public void setFile(String fileName) {
		file = new File(fileName);
	}
	public void setTable(JTable table) {
		inventoryTable = table;
	}
	public File getFile() {
		return file;
	}
	public JTable getTable() {
		return inventoryTable;
	}
	public void createFile() {
		if(file.exists()) {
			try {
				this.readFile();
			}
			catch (Exception e2) {
				JOptionPane.showMessageDialog(messageFrame, "Failed to load inventory data.", "Error", JOptionPane.ERROR_MESSAGE);
				e2.printStackTrace();
			}
		}
		else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(messageFrame, "Failed to create new file.", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	public void readFile() {
		DefaultTableModel model = (DefaultTableModel)inventoryTable.getModel();
		FileReader reader;
		try {
			reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String content = br.readLine();
			while(content != null) {
				//splits the comma-separated values into array of Strings
				String[] rowData = content.split(",");
				//adds the array of String to respective data column
				Object[] data = {rowData[0],rowData[1],rowData[2],rowData[3],Integer.parseInt(rowData[4]),
						Double.parseDouble(rowData[5])};
				//add the object array to a row in JTable via DefaultTableMOdel object
				model.addRow(data);
				//traverses to the next line
				content = br.readLine();
			}
			br.close();
			reader.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(messageFrame, "Failed to read file.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void updateFile(JTable newTable) {
		File tempFile = new File("temp.csv");
		//FileWriter and reader are instantiated to be utilized by the Buffered objects below
		FileWriter writer;
		this.setTable(newTable);
		try {
			writer = new FileWriter(tempFile);
			//BufferedWriter is used to save large amount of data
			BufferedWriter bw = new BufferedWriter(writer);
			for(int i=0; i< inventoryTable.getRowCount(); i++) {
				for(int j=0; j<inventoryTable.getColumnCount(); j++) {
					bw.write(inventoryTable.getValueAt(i, j).toString() + ",");
				}
				bw.newLine();
			}
			bw.close();
			writer.close();
			this.getFile().delete();
			tempFile.renameTo(file);
			this.setFile(tempFile.getName());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(messageFrame, "Inventory failed to save.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
