package diet;

public class RawMaterial extends Element {
	
	public RawMaterial(String name, double calories,
					   double proteins, double carbs,
					   double fat) {
	      super(name, calories, proteins, carbs, fat);
	}

	public boolean per100g() {
		return true;
	}

}
