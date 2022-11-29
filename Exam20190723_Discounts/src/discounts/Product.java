package discounts;

import java.util.ArrayList;
import java.util.List;

public class Product {
	
	private String code;
	private Category category;
	private Double price;
	List<PurchaseLine> lines = new ArrayList<>();
	
	public Product(Category category, String code, Double price) {
		this.category = category;
		this.code = code;
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public String getCode() {
		return code;
	}

	public Double getPrice() {
		return price;
	}
	
	public void addLine(PurchaseLine pl) {
		lines.add(pl);
	}
	
	int getNofUnits() {
		return lines.stream()
				.mapToInt(PurchaseLine::getQuantity)
				.sum();
	}

}
