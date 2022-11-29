package delivery;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Restaurant implements Comparable<Restaurant>{
	private String name;
	private String category;
	private Map<String, Dish> dishes = new HashMap<>();
	private List<Integer> ratings = new LinkedList<>();
	//OR
	//private double totRating;
	//private long numRating;
	
	public Restaurant(String name, String category) {
		this.name = name;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public void addDish(String nameDish, float price) throws DeliveryException{
		if(dishes.containsKey(nameDish)) {
			throw new DeliveryException("Piatto gia' esistente nel ristorante: " + nameDish);
		}
		Dish d = new Dish(nameDish, price);
		dishes.put(nameDish, d);
	}
	
	List<String> dishesInRange(float minPrice, float maxPrice) {
		return dishes.values().stream() 						//prendo il valore della Mappa
				.filter(p -> p.inRange(minPrice, maxPrice))		//filtro i piatti contenuti nel range
				.map(Dish::getName)								//prendo i nomi di questi piatti
				.collect(Collectors.toList());					//faccio una lista con questi nomi
	}
	
	Collection<Dish> getDishes() {
		return dishes.values();
	}

	@Override
	public int compareTo(Restaurant o) {
		return this.name.compareTo(o.name);
	}

	Dish getDish(String dishName) {
		return dishes.get(dishName);
	}

	public void addRating(int rating) {
		ratings.add(rating);
		//OR
		//totRating += rating;
		//numRating++;
	}
	
	public double ratingAverage() {
		return ratings.stream()
				.mapToInt(r -> r)
				.average()
				.orElse(-1);
	}
	
	
}
