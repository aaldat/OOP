package diet;

public class Product extends Element {
	
	public Product(String name, double calories,
					   double proteins, double carbs,
					   double fat) {
		super(name, calories, proteins, carbs, fat);   
	}

	public boolean per100g() {
		return false;
	}

}
