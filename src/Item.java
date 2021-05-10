import java.util.HashMap;

import javax.swing.JTable;
/*
======================================================================
 CLASS NAME  : Item
 DESCRIPTION : Contains the details of the item such as name, brand, price, stocks, category, then generates a corresponding SKU for the product and adds it to the JTable 
 COPYRIGHT   : April 29, 2021
 REVISION HISTORY
 Date:               			By:          				Description:
 April 29, 2021			Amber Brinette U. Lim			Creation of Class Constructor, data fields, and methods. Testing of Item Class				
 May 1, 2021			Karl Jensen F. Cayme			Modification of class methods and data fields and addition of setSKU method with HashMap
======================================================================
*/
public class Item {
	private String SKU;
	private String brand;
	private String name;
	protected double price;
	private String category;
	private int stocks;
	private JTable table;
	
	public static void main(String args[]) {

	}
	public Item() {
	}
	
	public Item(String brandInput, String nameInput, String categoryInput, double priceInput, 
			int stocksInput, JTable inventory) {
		setTable(inventory);
		setBrand(brandInput);
		setName(nameInput);
		setPrice(priceInput);
		setCategory(categoryInput);
		setstocks(stocksInput);
		setSKU();
	}
	
	public String getSKU() {
		return SKU;
	}
	public String getBrand() {
		return brand;
	}
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getstocks() {
		return stocks;
	}
	public JTable getTable() {
		return table;
	}
	
	public void setTable(JTable tableInput) {
		table = tableInput;
	}
	
	private void setSKU() {
		String name = this.getName();
		String brand = this.getBrand();
		
		//if brand and product name are too short, repeat the its name and brand
		if(this.getBrand().length() < 3 || this.getName().length() < 3) {
			StringBuilder modifiedBrand = new StringBuilder(name);
			StringBuilder modifiedName = new StringBuilder(brand);
			for(int i=0; i<3; i++) {
				modifiedBrand.append(this.getBrand());
				modifiedName.append(this.getName());
			}
			name = modifiedName.toString();
			brand = modifiedBrand.toString();
		}

		String SKU = brand.substring(0,3)+ '-' + name.substring(0,3) + '-' + this.getCategory().substring(0,3);
		
		//create a HashMap to check duplicates of SKU
		HashMap<String,Integer> map = this.createMap();
		//append additional numbering of the SKU if it has duplicate on the first 9 characters excluding '-'
		if(map.get(SKU) != null) {
			int num = map.get(SKU)+1;
			SKU = SKU + "-" + num;
		}
		this.SKU = SKU;
	}
	
	public HashMap<String,Integer> createMap() {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0; i<table.getRowCount(); i++) {
			//use substring method to ensure that the checked SKU is the first 9 characters excluding '-'
			String baseSKU = table.getValueAt(i, 0).toString().substring(0, 11);
			//add the baseSKU in the map if it is not yet added 
			if(!map.containsKey(baseSKU)) {
				map.put(baseSKU, 1);
			}
			//increment the corresponding value of the SKU in the map
			else {
				map.put(baseSKU, map.get(baseSKU) + 1);
			}
		}
		return map;
	}
	
	public void setBrand(String brandInput) {
		this.brand = brandInput.toUpperCase().trim();
	}
	
	public void setName(String nameInput) {
		this.name = nameInput.toUpperCase().trim();
	}
	
	public void setPrice(double priceInput) {
		this.price = priceInput;
	}
	
	public void setCategory(String categoryInput) {
		this.category = categoryInput.toUpperCase().trim();
	}
	
	public void setstocks(int stocksInput) {
		this.stocks = stocksInput;
	}
	
}
