import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class CashRegister implements FileHandling{
 
	File cashregisterfile = new File("cashregister.txt");
	static double cashOnHand = 0;
	MenuManager manager;
	
	public CashRegister(double payment, double cost, JTable table, String fileName) {
		if(!cashregisterfile.exists())
		{
			createFile();
		}
		setManager(fileName, table);
		readFile();	
		acceptAmount(payment, cost, table);
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
	
	public static double getCurrentBalance()
	{    
		return cashOnHand;
	}
	
	public void acceptAmount(double amountIn, double cost, JTable table)
	{
		if(amountIn >= cost)
		{
			cashOnHand += amountIn;
			double change = amountIn - cost;
			cashOnHand -= change;
			manager.updateFile();
			JOptionPane.showMessageDialog(null, "Successful, Please get your change: Php " + change);
		}
		else{
			JOptionPane.showMessageDialog(null, "Failed Purchase! Please get your money: Php " + amountIn);
		}
	}
	
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
	
	public void setManager(String fileName, JTable table) {
		manager = new MenuManager(fileName,table);
	}
	
}

