import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SortTextFile {

	public static void main(String[] args) {
		BufferedReader r = null;
		BufferedWriter w = null;
		
		ArrayList<String> lines = new ArrayList<String>();
		try {
			r = new BufferedReader(new FileReader("InventoryList.txt"));
			String currentLine = r.readLine();
			
			while(currentLine != null) {
				lines.add(currentLine);
				currentLine = r.readLine();
			}
			
			Collections.sort(lines);
			w = new BufferedWriter(new FileWriter("InventoryList.txt"));
			
			for(String line : lines) {
				w.write(line);
				w.newLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(r != null) {
					r.close();
				}
				
				if(w != null) {
					w.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
