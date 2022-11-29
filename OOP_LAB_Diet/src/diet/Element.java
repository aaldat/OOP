package diet;

public abstract class Element implements NutritionalElement {

	private String name;
	private double calories, proteins, carbs, fat;
	
	public Element(String name, double calories,
					   double proteins, double carbs,
					   double fat) {
	    this.name = name;
		this.calories = calories;
		this.proteins = proteins;
		this.carbs = carbs;
		this.fat = fat;	   
	}
	
	public String getName() {
		return name;
	}

	public double getCalories() {
		return calories;
	}

	public double getProteins() {
		return proteins;
	}

	public double getCarbs() {
		return carbs;
	}

	public double getFat() {
		return fat;
	}

}
