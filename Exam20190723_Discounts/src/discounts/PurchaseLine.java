package discounts;

public class PurchaseLine {
	
	private int quantity;
	private Product product;
	
	PurchaseLine(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public Product getProduct() {
		return product;
	}

}
