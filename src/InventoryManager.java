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
/*
======================================================================
 CLASS NAME  : InventoryManager
 DESCRIPTION : Responsible for the File Handling of the items in the JTable of InventoryGUI
 COPYRIGHT   : May 1, 2021
 REVISION HISTORY
 Date:               			By:          				Description:
 May 1, 2021			Karl Jensen F. Cayme			Creation of Class constructor, data fields, and methods	
 May 2, 2021			Karl Jensen F. Cayme			Editing of createFile and readFile methods	
 May 4, 2021			Karl Jensen F. Cayme			Editing of updateFile method

======================================================================
*/
public class InventoryManager implements FileHandling{
	private JFrame messageFrame;
	private File file;
	private JTable inventoryTable;
	
	public InventoryManager(String fileName, JTable table) {
		this.setFile(fileName);
		this.setTable(table);
	}
	/**
	======================================================================
	METHOD : setFile
	DESCRIPTION : sets the File to be used for file handling
	======================================================================
	*/
	public void setFile(String fileName) {
		file = new File(fileName);
	}
	/**
	======================================================================
	METHOD : setTable
	DESCRIPTION : sets the table which will be used for updating item list and reading the file
	======================================================================
	*/
	public void setTable(JTable table) {
		inventoryTable = table;
	}
	/**
	======================================================================
	METHOD : getFile() 
	DESCRIPTION : returns the File used in this class
	======================================================================
	*/
	public File getFile() {
		return file;
	}
	/**
	======================================================================
	METHOD : getTable() 
	DESCRIPTION : returns the JTable used in this class
	======================================================================
	*/
	public JTable getTable() {
		return inventoryTable;
	}
	/**
	======================================================================
	METHOD : createFile
	DESCRIPTION : creates a file to store the item list or calls readFile method to read the existing data
	PRE-CONDITION : file path must be valid
	POST-CONDITION : the file is either created or read
	======================================================================
	*/
	@Override
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
	/**
	======================================================================
	METHOD : readFile
	DESCRIPTION : reads the data from a csv file and puts it in the JTable
	======================================================================
	*/
	@Override
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
	/**
	======================================================================
	METHOD : updateFile
	DESCRIPTION : replaces the old file with a new that contains the updated list
	======================================================================
	*/
	@Override
	public void updateFile() {
		File tempFile = new File("temp.csv");
		//FileWriter and reader are instantiated to be utilized by the Buffered objects below
		FileWriter writer;
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
	/**
	======================================================================
	METHOD : exists
	DESCRIPTION : checks the JTable in this class if the passed brand and name already exists in it
	======================================================================
	*/
	public boolean exists(String name, String brand) {
		boolean exists = false;
		for(int i=0; i<this.getTable().getRowCount(); i++) {
			if(name.equals(this.getTable().getValueAt(i, 2)) && brand.equals(this.getTable().getValueAt(i, 1))) {
				exists = true;
			}
		}
		return exists;
	}
}
