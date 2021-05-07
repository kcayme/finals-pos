import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuManager implements FileHandling {
	private JFrame messageFrame;
	private File file;
	private JTable table;
	
	public MenuManager(String fileName, JTable menuTable) {
		this.setFile(fileName);
		this.setTable(menuTable);
	}
	
	public void setFile(String fileName) {
		file = new File(fileName);
	}
	
	public void setTable(JTable table) {
		this.table = table;
	}
	
	@Override
	public void readFile() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		FileReader reader;
		try {
			reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String content = br.readLine();
			while(content != null) {
				//splits the comma-separated values into array of Strings
				String[] rowData = content.split(",");
				String status = "";
				Double price = Double.parseDouble(rowData[5]);
				int stocks = Integer.parseInt(rowData[4]);
				if( stocks > 0) {
					status = "IN STOCK";
				}
				else {
					status = "OUT OF STOCK";
				}
				Object[] data = {rowData[1],rowData[2],rowData[3],price,status};
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
	
	public void updateCheckout(JTable checkoutTable) {
		DefaultTableModel model = (DefaultTableModel)checkoutTable.getModel();
		FileReader reader;
		try {
			reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String content = br.readLine();
			ArrayList<String> list = new ArrayList<String>();
			while(content != null) {
				String[] rowData = content.split(",");
				if(!list.contains(rowData[2])) {
					list.add(rowData[2]);
				}
				content = br.readLine();
			}
			br.close();
			reader.close();
			for(int i=0; i<checkoutTable.getRowCount(); i++) {
				if(!list.contains(checkoutTable.getValueAt(i, 0))){
					model.removeRow(i);
				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(messageFrame, "Failed to refresh checkout.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		FileReader reader2;
		try {
			reader2 = new FileReader(file);
			BufferedReader br2 = new BufferedReader(reader2);
			String content = br2 .readLine();
			
			while(content != null) {
				String[] rowData = content.split(",");
				String name = rowData[2];
				int row = getTableIndex(name,checkoutTable);
				if(row != -1) {
					int inventoryStocks = Integer.parseInt(rowData[4]);
					double inventoryPrice = Double.parseDouble(rowData[5]);
					int itemCheckoutStock = Integer.parseInt(checkoutTable.getValueAt(row, 2).toString());
					if(inventoryStocks != 0) {
						if(inventoryStocks < itemCheckoutStock) {
							checkoutTable.setValueAt(inventoryStocks, row, 2);
						}
						checkoutTable.setValueAt(inventoryPrice, row, 1);
						int updatedStock = Integer.parseInt(checkoutTable.getValueAt(row, 2).toString());
						double updatePrice = Double.parseDouble(checkoutTable.getValueAt(row, 1).toString());
						double updateSubtotal = updatedStock * updatePrice;
						checkoutTable.setValueAt(updateSubtotal, row, 3);
					}
					else {
						model.removeRow(row);
					}
				}
				content = br2 .readLine();
			}
			br2.close();
			reader2.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(messageFrame, "Failed to update checkout.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void updateFile() {
		File tempFile = new File("temp.csv");
		//FileWriter and reader are instantiated to be utilized by the Buffered objects below
		FileWriter writer;
		FileReader reader;
		try {
			writer = new FileWriter(tempFile);
			reader = new FileReader(file);
			//reading the inventoryData while writing on the temp file
			BufferedReader br = new BufferedReader(reader);
			BufferedWriter bw = new BufferedWriter(writer);
			
			String content = br.readLine();
			
			while(content != null) {
				//splits the comma-separated values into String array
				String[] rowData = content.split(",");
				//gets the product name located at index 2
				String name = rowData[2];
				//calls getTableIndex method to check if the product is in the checkoutTable by getting the row index
				int row = getTableIndex(name);
				//if it is in the checkout, update the stock value of that product
				if(row != -1) {
					int currentStock = Integer.parseInt(rowData[4]);
					int deductStock = Integer.parseInt(table.getValueAt(row, 2).toString());
					int updatedStock = currentStock - deductStock;
					rowData[4] = String.valueOf(updatedStock);
					for(int i=0; i<rowData.length; i++) {
						bw.write(rowData[i]+",");
					}
				}
				//else, copy directly the content from inventoryData to tempfile
				else {
					bw.write(content);
				}
				bw.newLine();
				//read next line of inventoryData
				content = br.readLine();
			}
			//both reader and writers
			br.close();
			reader.close();
			bw.close();
			writer.close();
			//delete the old inventoryData
			this.getFile().delete();
			//rename temp file to inventoryData
			tempFile.renameTo(file);
			//set the new inventoryData as the new file in this object
			this.setFile(tempFile.getName());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(messageFrame, "Inventory failed to save.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

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
	
	public File getFile() {
		return file;
	}
	
	public int checkStocks(String name) {
		int inventoryStock = 0;
		FileReader reader;
		try {
			reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			String content = br.readLine();
			while(content != null) {
				//splits the comma-separated values into array of Strings
				String[] rowData = content.split(",");
				String productName = rowData[2];
				if(productName.equals(name)) {
					inventoryStock = Integer.parseInt(rowData[4]);
					break;
				}
				//traverses to the next line
				content = br.readLine();
			}
			br.close();
			reader.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(messageFrame, "Failed to check stocks.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return inventoryStock;
	}
	
	public int getTableIndex(String name) {
		int inTable = -1;
		for (int i=0; i < table.getRowCount(); i++) {
			if(name.equals(table.getValueAt(i, 0))) {
				inTable = i;
			}
		}
		
		return inTable;
	}
	
	public int getTableIndex(String name, JTable referenceTable) {
		int inTable = -1;
		
		for(int i=0; i < referenceTable.getRowCount(); i++) {
			if(name.equals(referenceTable.getValueAt(i, 0).toString())) {
				inTable = i;
			}
		}
		
		return inTable;
	}
	public double getTotal(JTable table) {
		double total = 0;
		
		for(int i=0; i<table.getRowCount(); i++) {
			total += Double.parseDouble(table.getValueAt(i, 3).toString());
		}
		return total;
	}
	

}
