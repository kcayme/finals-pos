import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateFile {

	public static void main (String[] args) {
		try {
			File inventory = new File("InventoryList.txt");
			if (inventory.createNewFile()) {
				System.out.println("File successfully created: " + inventory.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occured.");
			e.printStackTrace();
			}
	}
}
