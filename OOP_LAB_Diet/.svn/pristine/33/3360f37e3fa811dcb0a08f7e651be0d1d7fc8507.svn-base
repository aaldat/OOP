package diet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {
	
	private Map<String, Restaurant> restaurants = new TreeMap<String, Restaurant>();
	private Collection<User> users = new ArrayList<User>();
	
	/**
	 * Adds a new restaurant to the take-away system
	 * 
	 * @param r the restaurant to be added
	 */
	public void addRestaurant(Restaurant r) {
		restaurants.put(r.getName(), r);
	}
	
	/**
	 * Returns the collections of restaurants
	 * 
	 * @return collection of added restaurants
	 */
	public Collection<String> restaurants() {
		return restaurants.keySet();
	}
	
	/**
	 * Define a new user
	 * 
	 * @param firstName first name of the user
	 * @param lastName  last name of the user
	 * @param email     email
	 * @param phoneNumber telephone number
	 * @return
	 */
	public User registerUser(String firstName, String lastName, String email, String phoneNumber) {
		User u = new User(firstName, lastName, email, phoneNumber);
		users.add(u);
		return u;
	}
	
	/**
	 * Gets the collection of registered users
	 * 
	 * @return the collection of users
	 */
	public Collection<User> users(){
		ArrayList<User> u = new ArrayList<User>(users);
		Collections.sort(u);
		return u;
	}
	
	/**
	 * Create a new order by a user to a given restaurant.
	 * 
	 * The order is initially empty and is characterized
	 * by a desired delivery time. 
	 * 
	 * @param user				user object
	 * @param restaurantName	restaurant name
	 * @param h					delivery time hour
	 * @param m					delivery time minutes
	 * @return
	 */
	public Order createOrder(User user, String restaurantName, int h, int m) {
		Restaurant restaurant =  restaurants.get(restaurantName);
		Order o = new Order(restaurant, user, h, m);
		restaurant.addOrder(o);
		user.addOrder(o);
		return o;
	}
	
	/**
	 * Retrieves the collection of restaurant that are open
	 * at the given time.
	 * 
	 * @param time time to check open
	 * 
	 * @return collection of restaurants
	 */
	public Collection<Restaurant> openedRestaurants(String time){
		ArrayList<Restaurant> opened = new ArrayList<Restaurant>();
		Time lt = new Time(time);
		for(Restaurant r : restaurants.values()) {
			if(r.checkTime(lt) == lt) {
				opened.add(r);
			}
		}
		Collections.sort(opened);
		return opened;
	}

	
	
}
