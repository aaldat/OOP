package discounts;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
	
	List<PurchaseLine> lines = new ArrayList<>();
	private double purchaseAmount = 0;
	private double purchaseDiscount = 0;
	int id;
	
	void addLine(PurchaseLine pl) {
		lines.add(pl);
	}
	
	void setId(int id) {
		this.id = id;
	}

	public List<PurchaseLine> getLines() {
		return lines;
	}

	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public double getPurchaseDiscount() {
		return purchaseDiscount;
	}

	public int getId() {
		return id;
	}
	
	void setAmount(boolean yesDiscount) {
		if (!yesDiscount) {
			purchaseAmount = getAmount();
			purchaseDiscount = 0;
		} else {
			purchaseAmount = getAmountWithDiscounts();
			purchaseDiscount = getAmount() - purchaseAmount;
		}
	}

	private double getAmount() {
		double amount = 0;
		for (PurchaseLine l: lines) {
			Product product = l.getProduct();
			amount += l.getQuantity() * product.getPrice();	
		}
		return amount;
	}

	private double getAmountWithDiscounts() {
		double amount = 0;
		for (PurchaseLine l: lines) {
			Product product = l.getProduct();
			amount += l.getQuantity() * (product.getPrice() * 
					(1 - product.getCategory().getDiscount()/100.0));		
		}
		return amount;
	}
	
	int getQuantity() {
		return lines.stream()
				.mapToInt(PurchaseLine::getQuantity)
				.sum();
	}
	

}
