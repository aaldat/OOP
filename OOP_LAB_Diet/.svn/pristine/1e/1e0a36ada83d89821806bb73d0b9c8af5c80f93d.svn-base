package diet;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents an order in the take-away system
 */
public class Order{
	
	private Restaurant restaurant;
	private User user;
	private Time deliveryTime;
	private OrderStatus orderStatus;
	private PaymentMethod paymentMethod;
	private SortedMap<Menu, Integer> menuOrder;
	
	public Order(Restaurant restaurant, User user, int h, int m) {
		this.orderStatus = OrderStatus.ORDERED;
		this.paymentMethod = PaymentMethod.CASH;
		this.restaurant = restaurant;
		this.user = user;
		this.deliveryTime = restaurant.checkTime(new Time(h, m));
		this.menuOrder = new TreeMap<Menu, Integer>(Comparator.comparing(Menu::getName));
	}
 
	/**
	 * Defines the possible order status
	 */
	public enum OrderStatus {
		ORDERED, READY, DELIVERED;
	}
	/**
	 * Defines the possible valid payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD;
	}
		
	/**
	 * Total order price
	 * @return order price
	 */
	public double Price() {
		return -1.0;
	}
	
	/**
	 * define payment method
	 * 
	 * @param method payment method
	 */
	public void setPaymentMethod(PaymentMethod method) {
		this.paymentMethod = method;
	}
	
	/**
	 * get payment method
	 * 
	 * @return payment method
	 */
	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}
	
	/**
	 * change order status
	 * @param newStatus order status
	 */
	public void setStatus(OrderStatus newStatus) {
		this.orderStatus = newStatus;
	}
	
	public Restaurant getRestaurant() {
		return this.restaurant;
	}
	
	/**
	 * get current order status
	 * @return order status
	 */
	public OrderStatus getStatus(){
		return this.orderStatus;
	}
	
	/**
	 * Add a new menu with the relative order to the order.
	 * The menu must be defined in the {@link Food} object
	 * associated the restaurant that created the order.
	 * 
	 * @param menu     name of the menu
	 * @param quantity quantity of the menu
	 * @return this order to enable method chaining
	 */
	public Order addMenus(String menu, int quantity) {
		Menu m = restaurant.getMenu(menu);
		menuOrder.put(m, quantity);
		return this;
	}
	
	User getUser() {
		return user;
	}
	
	Time getDeliveryTime() {
		return this.deliveryTime;
	}
	
	/**
	 * Converts to a string as:
	 * <pre>
	 * RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
	 * 	MENU_NAME_1->MENU_QUANTITY_1
	 * 	...
	 * 	MENU_NAME_k->MENU_QUANTITY_k
	 * </pre>
	 */
	@Override
	public String toString() {
		StringBuffer bf = new StringBuffer();
		bf.append(restaurant.getName())
		.append(", ")
		.append(user)
		.append(" : (")
		.append(deliveryTime)
		.append("):\n");
		for(Map.Entry<Menu, Integer> m : menuOrder.entrySet()) {
			bf.append("\t")
			.append(m.getKey().getName())
			.append("->")
			.append(m.getValue().toString())
			.append("\n");
		}
		return bf.toString();
	}
	
}
