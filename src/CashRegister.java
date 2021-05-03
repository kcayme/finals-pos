import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CashRegister {
 
File cashregisterfile = new File("cashregister.txt");
static float cashOnHand = 0;


	public static void main(String args[])
	{
		CashRegister test = new CashRegister(10);
	}



	public CashRegister(float CashIn)
	{
		
		if(cashregisterfile.exists())
			{
				ReadFile();
				acceptAmount(CashIn, 8);
				AppendFile();
			}
		else
			{
				CreateFile();
				ReadFile();
				acceptAmount(CashIn, 8);
				AppendFile();
			}
			 
			
		acceptAmount(CashIn, 8);
		
	}
	
	
	public static float getCurrentBalance()
	{    
		ReadFile();
		return cashOnHand;
	}
	
	public void acceptAmount(float amountIn, float cost)
	{
		cashOnHand += amountIn;
		
		if(amountIn >= cost)
		{
			float change = amountIn - cost;
			
			JOptionPane.showMessageDialog(null, "Successful, Please get your change: Php " + change);
			cashOnHand -= change;
		}
		if(amountIn<cost)
		{
			float change = amountIn;
			JOptionPane.showMessageDialog(null, "Failed Purchase! Please get your money: Php " + change);
			cashOnHand -= change;
		}
	}
	

	private static void ReadFile()
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
	
	private void AppendFile()
	{
		try (FileWriter f = new FileWriter("cashRegister.txt");
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) 
			 {
			
				p.println(cashOnHand + ",Cash On Hand");
				
			
			 } catch (IOException i) {i.printStackTrace();}
	}
	
	private void CreateFile()
	{
		try (FileWriter f = new FileWriter("cashRegister.txt");
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) 
			 {
				
				float initialvalue = 5; 
				p.println(initialvalue + ",Cash On Hand;");
				System.out.println("\nFile Created");
			
			 } catch (IOException i) {i.printStackTrace();}
		 
	}
	
	
	
	
	
}

