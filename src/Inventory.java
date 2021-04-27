import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Inventory {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Item Name: ");
		String nameInput = input.nextLine();
		
		System.out.println("Item Price: ");
		double priceInput = input.nextDouble();
		DecimalFormat df = new DecimalFormat("#.00");
		
		System.out.println("Item Category: ");
		String categoryInput = input.nextLine();
		
		System.out.println("Original Stock Number: ");
		int originalStocksInput = input.nextInt();
		
		System.out.println("Remaining Stock Number: ");
		int remainingStocksInput = input.nextInt();
		
		Item add = new Item();
		add.setName(nameInput);
		add.setPrice(df.format(priceInput));
		add.setCategory(categoryInput);
		add.setOriginalStocks(originalStocksInput);
		add.setremainingStocks(remainingStocksInput);
		
		try (FileWriter f = new FileWriter("InventoryList.txt", true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) {
			
			p.println(nameInput + "\t\t\t\t" + df.format(priceInput) + "\t\t\t" 
					+ categoryInput + "\t\t\t\t" + originalStocksInput + "\t\t\t"
					+ remainingStocksInput);
			System.out.println("\nItem successfully added to inventory");
			
		} catch (IOException i) {
			i.printStackTrace();
		}
		
		input.close();
	}
	
}
