
public class Item {
	private String name;
	protected String price;
	private String category;
	private int originalStocks;
	private int remainingStocks;
	
	
	public Item() {
	}
	
	public Item(String nameInput, String priceInput, String categoryInput,
			int originalStocksInput, int remainingStocksInput) {
		setName(nameInput);
		setPrice(priceInput);
		setCategory(categoryInput);
		setOriginalStocks(originalStocksInput);
		setremainingStocks(remainingStocksInput);
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getCategory() {
		return category;
	}
	
	public int getOriginalStocks() {
		return originalStocks;
	}
	
	public int getRemainingStocks() {
		return remainingStocks;
	}
	
	public void setName(String nameInput) {
		this.name = nameInput;
	}
	
	public void setPrice(String priceInput) {
		this.price = priceInput;
	}
	
	public void setCategory(String categoryInput) {
		this.category = categoryInput;
	}
	
	public void setOriginalStocks(int originalStocksInput) {
		this.originalStocks = originalStocksInput;
	}
	
	public void setremainingStocks(int remainingStocksInput) {
		this.remainingStocks = remainingStocksInput;
	}
	
}
