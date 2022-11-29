package discounts;

import java.util.ArrayList;
import java.util.List;

public class Card {

	private int code;
	private String user;
	private List<Purchase> purchases = new ArrayList<>();
	
	public Card(Integer code, String user) {
		this.code = code;
		this.user = user;
	}

	public int getCode() {
		return code;
	}

	public String getUser() {
		return user;
	}
	
	public int getNofPurchases() {
		return purchases.size();
	}
	
	public void addPurchase(Purchase p) {
		purchases.add(p);
	}
	
	public int getAmountPurchases() {
		return (int) Math.round(purchases.stream()
								.mapToDouble(Purchase::getPurchaseAmount)
								.sum());
	}
	
	public int getAmountDiscount() {
		if(code == 0) {
			return 0;
		} else {
			return (int) Math.round(purchases.stream()
					.mapToDouble(Purchase::getPurchaseDiscount)
					.sum());
		}
	}
	
}
