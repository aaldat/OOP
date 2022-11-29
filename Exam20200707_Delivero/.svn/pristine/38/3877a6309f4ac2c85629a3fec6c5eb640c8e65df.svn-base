package delivery;

import java.util.LinkedList;
import java.util.List;

public class Order {
	private int code, deliveryTime, deliveryDistance;
	private List<OrderLine> lines = new LinkedList<>();
	private String customer;
	private Restaurant restaurant;
	private boolean assigned;
	
	
	
	public Order(int code, String[] dishNames, int[] quantities, String customerName, Restaurant r, int deliveryTime, int deliveryDistance) {
		this.code = code;
		this.customer = customerName;
		this.restaurant = r;
		this.deliveryTime = deliveryTime;
		this.deliveryDistance = deliveryDistance;
		this.assigned = false;
		for(int i = 0; i < dishNames.length; i++) {
			Dish d = r.getDish(dishNames[i]);
			OrderLine l = new OrderLine(d, quantities[i]);
			lines.add(l);
		}
	}

	public int getCode() {
		return code;
	}
	
	public boolean assigned() {
		return assigned;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public int getDistance() {
		return deliveryDistance;
	}

	public void setAssigned() {
		assigned = true;
	}
	
	public String getCategory() {
		return restaurant.getCategory();
	}

}
