import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddToInventory {

	public static void main(String args[]) throws IOException {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Item Name: ");
		String addItem = input.nextLine();
		System.out.println("Item Price: ");
		double addPrice = input.nextDouble();
		DecimalFormat df = new DecimalFormat("#.00");
		
		
		try (FileWriter f = new FileWriter("InventoryList.txt", true);
				BufferedWriter b = new BufferedWriter(f);
				PrintWriter p = new PrintWriter(b);) {
			
			p.println(addItem + "\t\t" + df.format(addPrice));
			System.out.println("\nItem successfully added to inventory");
			
		} catch (IOException i) {
			i.printStackTrace();
		}
		

		
	}
}
