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
/*
======================================================================
 CLASS NAME  : MenuManager
 DESCRIPTION : Responsible for the File Handling done in the MainPOS Class such as updating the menu and checkout table whenever inventory table is accessed and updating the database when a transaction is made. 
 COPYRIGHT   : May 5, 2021
 REVISION HISTORY
 Date:               			By:          				Description:
 May 5, 2021			Karl Jensen F. Cayme			Creation of Class constructor, data fields, and methods	
 May 7, 2021			Karl Jensen F. Cayme			Addition of getStocks, getTotal and getTableIndex methods
 May 8, 2021			Karl Jensen F. Cayme			Debugging of updateCheckout method.

======================================================================
*/
public class MenuManager implements FileHandling {
	private JFrame messageFrame;
	private File file;
	private JTable table;
	
	public MenuManager(String fileName, JTable menuTable) {
		this.setFile(fileName);
		this.setTable(menuTable);
	}
	/**
	======================================================================
	METHOD : setFile
	DESCRIPTION : sets the file to be used in this class
	======================================================================
	*/
	public void setFile(String fileName) {
		file = new File(fileName);
	}
	/**
	======================================================================
	METHOD : setTable
	DESCRIPTION : sets the JTable to be used for reading and updating data
	======================================================================
	*/
	public void setTable(JTable table) {
		this.table = table;
	}
	/**
	======================================================================
	METHOD : readFile
	DESCRIPTION : reads a csv file and places the contents into the JTable
	======================================================================
	*/
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
	/**
	======================================================================
	METHOD : updateCheckout
	DESCRIPTION : updates the item details in the checkout JTable based on the updated item list
	======================================================================
	*/
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
	/**
	======================================================================
	METHOD : updateFile
	DESCRIPTION : updates the csv with the changes in item stocks
	======================================================================
	*/
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
	/**
	======================================================================
	METHOD : createFile
	DESCRIPTION : creates a file based on the given file name if does not exist yet, otherwise read the existing file
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
	METHOD : getFile
	DESCRIPTION : returns the file used in this class
	======================================================================
	*/
	public File getFile() {
		return file;
	}
	/**
	======================================================================
	METHOD : checkStocks
	DESCRIPTION : returns the stocks of the passed product name
	======================================================================
	*/
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
	/**
	======================================================================
	METHOD : getTableIndex
	DESCRIPTION : returns the row index in JTable based on the passed product name
	======================================================================
	*/
	public int getTableIndex(String name) {
		int inTable = -1;
		for (int i=0; i < table.getRowCount(); i++) {
			if(name.equals(table.getValueAt(i, 0))) {
				inTable = i;
			}
		}
		
		return inTable;
	}
	/**
	======================================================================
	METHOD : getTableIndex
	DESCRIPTION : returns the row index of the passed product name from the passed JTable 
	======================================================================
	*/
	public int getTableIndex(String name, JTable referenceTable) {
		int inTable = -1;
		
		for(int i=0; i < referenceTable.getRowCount(); i++) {
			if(name.equals(referenceTable.getValueAt(i, 0).toString())) {
				inTable = i;
			}
		}
		
		return inTable;
	}
	/**
	======================================================================
	METHOD : getTotal
	DESCRIPTION : calculates the total price of the passed JTable
	======================================================================
	*/
	public double getTotal(JTable table) {
		double total = 0;
		
		for(int i=0; i<table.getRowCount(); i++) {
			total += Double.parseDouble(table.getValueAt(i, 3).toString());
		}
		return total;
	}
	

}
