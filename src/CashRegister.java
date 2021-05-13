import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTable;
/*
======================================================================
 CLASS NAME  : CashRegister
 DESCRIPTION : Deals with Logic on Transaction Process. Create or updates file which to store values money earn after transaction.
 COPYRIGHT   : April 30, 2021
 REVISION HISTORY
 Date:               			By:          				Description:
 April 30, 2021			Miguel Edwin P. Salubre			Creation of the class and Logic. 
 May 1, 2021			Miguel Edwin P. Salubre			Updated the Logic with implementation of FileHandling to Store values.
 May 3, 2021			Karl Jensen F. Cayme			Updated the Logic on Constructor and method 
 May 4, 2021			Karl Jensen F. Cayme			Added method setManager for JTable to Update the Inventory from transaction.				
======================================================================
*/
public class CashRegister implements FileHandling{
 
	File cashregisterfile = new File("cashregister.txt");
	static double cashOnHand = 0;
	MenuManager manager;
	boolean transactionMade;
	
	public CashRegister(double payment, double cost, JTable table, String fileName) {
		if(!cashregisterfile.exists())
		{
			createFile();
		}
		setManager(fileName, table);
		readFile();	
		acceptAmount(payment, cost);
		updateFile();
	}
	
	public CashRegister() {
		if(!cashregisterfile.exists())
		{
			createFile();
		}
		readFile();	
		updateFile();
	}
	/**
	======================================================================
	METHOD : getCurrentBalance
	DESCRIPTION : Returns the value to the method that calls this method.
	PRE-CONDITION : method has to be called or invoked from the class that call this method
	POST-CONDITION : returns value CashOnHand.
	======================================================================
	*/
	public static double getCurrentBalance()
	{    
		return cashOnHand;
	}
	/**
	======================================================================
	METHOD : setTransactionMade
	DESCRIPTION : sets the truth value of variable transactionMade
	PRE-CONDITION : a transaction is completed
	POST-CONDITION : variable transactionMade is then true
	======================================================================
	*/
	public void setTransactionMade(boolean value) {
		this.transactionMade = value;
	}
	/**
	======================================================================
	METHOD : getTransactionMade
	DESCRIPTION : returns the value of transactionMade
	======================================================================
	*/
	public boolean getTransactionMade() {
		return transactionMade;
	}
	/**
	======================================================================
	METHOD : acceptAmount
	DESCRIPTION : Logic of Transaction from Customer. This is where the money is received and 
		      determination of change to Cashier.
	PRE-CONDITION : The amount from customer has identified, the cost and 
			JTable needed from Inventory
	POST-CONDITION : Prompts the user of the transaction has failed or not, tells also the change
			 successful transaction
	======================================================================
	*/
	public void acceptAmount(double amountIn, double cost)
	{
		if(amountIn <= cashOnHand) {
			if(amountIn >= cost)
			{
				cashOnHand += amountIn;
				double change = amountIn - cost;
				cashOnHand -= change;
				manager.updateFile();
				this.setTransactionMade(true);
				JOptionPane.showMessageDialog(null, "Successful, Please get your change: Php " + change);
			}
			else{
				this.setTransactionMade(false);
				JOptionPane.showMessageDialog(null, "Failed Purchase! Please get your money: Php " + amountIn);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Not enough change.");
		}
		
	}
	/**
	======================================================================
	METHOD : 
	DESCRIPTION : Reads the value Cash on Hand from File.
	PRE-CONDITION : method has to be called or invoked from the class that call this method
	POST-CONDITION : Cash On Hand data field in class is updated.
	======================================================================
	*/
	@Override
	public void readFile()
	{
		try
		{
			Scanner sc = new Scanner(new File("cashregister.txt"));
			sc.useDelimiter("[,\0]");
			while(sc.hasNext())
			{
				cashOnHand = sc.nextFloat();
				sc.close();
				break;
			}
			
		} 
		catch (FileNotFoundException e)
			{
				System.out.println("File has not Read");
				e.printStackTrace();
			}
	}
	/**
	======================================================================
	METHOD : updateFile
	DESCRIPTION : Overwrites the File with new Values of Cash On Hand.
	PRE-CONDITION : method has to be called or invoked from the class that call this method.
	POST-CONDITION : Cash On Hand data field in File is updated.
	======================================================================
	*/
	@Override
	public void updateFile()
	{
		try (FileWriter f = new FileWriter("cashRegister.txt");
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) 
			 {
	
				p.println(cashOnHand + ",Cash On Hand");
				
			
			 } catch (IOException i) {i.printStackTrace();}
	}
	/**
	======================================================================
	METHOD : createFile
	DESCRIPTION : Create the File when File for storing Cash on Hand does not exist.
	PRE-CONDITION : When file for storing Cash on Hand does not exist
	POST-CONDITION : Creation of File for storing Cash On Hand is Created.
	======================================================================
	*/
	@Override
	public void createFile()
	{
		try (FileWriter f = new FileWriter("cashRegister.txt");
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) 
			 {
				float initialvalue = 100000; 
				p.println(initialvalue + ",Cash On Hand;");
			
			 } catch (IOException i) {i.printStackTrace();}
		 
	}
	/**
	======================================================================
	METHOD : setManager
	DESCRIPTION : Initializes a MenuManager Object.
	PRE-CONDITION : Requires the filename and JTable of the Inventory
	POST-CONDITION : an MenuManager Object is created
	======================================================================
	*/
	public void setManager(String fileName, JTable table) {
		manager = new MenuManager(fileName,table);
	}
	
}

