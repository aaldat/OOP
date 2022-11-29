package diet;

import java.util.LinkedList;
import java.util.List;

/**
 * Represent a take-away system user
 *  
 */
public class User implements Comparable<User>{
	
	private String lastName, firstName, email, phone;
	private List<Order> orderHistory = new LinkedList<Order>();
	
	public User(String firstName, String lastName, String email, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}
		
	/**
	 * get user's last name
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * get user's first name
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * get user's email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * get user's phone number
	 * @return  phone number
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * change user's email
	 * @param email new email
	 */
	public void SetEmail(String email) {
		this.email = email;
	}
	
	/**
	 * change user's phone number
	 * @param phone new phone number
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	@Override
	public int compareTo(User o) {
		int last = this.lastName.compareTo(o.getLastName());
		if(last == 0) {
			return this.firstName.compareTo(o.getFirstName());
		}
		return last;
	}

	public void addOrder(Order o) {
		orderHistory.add(o);
	}
	
}
