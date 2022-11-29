package discounts;

import java.util.HashMap;
import java.util.Map;

public class Category {
	
	private String name;
	private int discount;
	private Map<String, Product> products = new HashMap<>();
	
	public Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDiscount() {
		return discount;
	}
	
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public void addProduct(Product p) {
		products.put(p.getCode(), p);
	}
	
	public Map<String, Product> getProductsOfCategory() {
		return products;
	}

}
