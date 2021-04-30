import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class CashRegister {

File myObj = new File("CashRegister.txt");
static float cashOnHand = 0;
	
	CashRegister()
	{
		 if(myObj.exists())
		 {
			 createFile();
		 }	 
		 else
		 {
			 
			 try (FileWriter f = new FileWriter("CashRegister.txt", true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) 
			 {
			
				p.println("Cash On Hand" + "\t\t\t"+ cashOnHand);
				System.out.println("\nValue successfully value updated");
			
			 } catch (IOException i) {i.printStackTrace();}
		 }
		
	}
	
	
	public static float getCurrentBalance()
	{
		try 
		{
		      File myObj = new File("CashRegister.txt");
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		   } catch (FileNotFoundException e) 
				{
			      System.out.println("An error occurred.");
			      e.printStackTrace();
				}
		  
		
		return cashOnHand;
	}
	
	public void acceptAmount(int amountIn, int cost)
	{
		cashOnHand += amountIn;
		
		if(amountIn >= cost)
		{
			int change = amountIn - cost;
			
			JOptionPane.showMessageDialog(null, "Successful, Please get your change: Php " + change);
			cashOnHand -= change;
		}
		if(amountIn<cost)
		{
			int change = amountIn;
			JOptionPane.showMessageDialog(null, "Failed Purchase! Please get your money: Php " + change);
			cashOnHand -= change;
		}
	}
	
	public void createFile() 
	{
		  
		try
		{
		      
		  if (myObj.createNewFile()) 
		  {
		    System.out.println("File created: " + myObj.getName());
		  } else 
		  {
		    System.out.println("File already exists.");
		  }
		} catch (IOException e) 
			{
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  
	}
}

