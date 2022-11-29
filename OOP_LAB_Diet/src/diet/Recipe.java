package diet;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	
	private String name;
	private Food food;
	private double weigth = 0.0;
	
	private List<Ingredient> ingredients = new LinkedList<Ingredient>();

	private static class Ingredient{
		final NutritionalElement ne;
		final double qty;
		public Ingredient(NutritionalElement ne, double qty) {
			this.ne = ne;
			this.qty = qty;
		}
		
	}
    
	public Recipe(String name, Food food) {
		this.name = name;
		this.food = food;
	}

	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe addIngredient(String material, double quantity) {
		NutritionalElement raw = food.getRawMaterial(material);
		Ingredient ing = new Ingredient(raw, quantity);
		ingredients.add(ing);
		weigth += quantity;
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getCalories() {
		double cals = 0.0;
		for(Ingredient i : ingredients) {
			cals += i.ne.getCalories(i.qty);
		}
		return cals * 100/ weigth;
	}

	@Override
	public double getProteins() {
		double prots = 0.0;
		for(Ingredient i : ingredients) {
			prots += i.ne.getProteins(i.qty);
		}
		return prots * 100/ weigth;
	}

	@Override
	public double getCarbs() {
		double carbs = 0.0;
		for(Ingredient i : ingredients) {
			carbs += i.ne.getCarbs(i.qty);
		}
		return carbs * 100/ weigth;
	}

	@Override
	public double getFat() {
		double fats = 0.0;
		for(Ingredient i : ingredients) {
			fats += i.ne.getFat(i.qty);
		}
		return fats * 100/ weigth;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}
	
	
	/**
	 * Returns the ingredients composing the recipe.
	 * 
	 * A string that contains all the ingredients, one per per line, 
	 * using the following format:
	 * {@code "Material : ###.#"} where <i>Material</i> is the name of the 
	 * raw material and <i>###.#</i> is the relative quantity. 
	 * 
	 * Lines are all terminated with character {@code '\n'} and the ingredients 
	 * must appear in the same order they have been added to the recipe.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Ingredient i : ingredients) {
			sb.append(i.ne.getName())
			  .append(" : ")
			  .append(String.format("%03.1f", i.qty))
			  .append('\n');
		}
		return sb.toString();
	}
}
