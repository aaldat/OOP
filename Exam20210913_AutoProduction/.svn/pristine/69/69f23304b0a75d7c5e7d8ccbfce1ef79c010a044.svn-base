package it.polito.oop.production;

//import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Storage {
	
	private String name;
	private int capacity, space = 0;
	private List<String> models = new LinkedList<>();
	
	public Storage(String name, int capacity) {
		super();
		this.name = name;
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public int getSpace() {
		return space;
	}
	
	public void addModel(String code) throws BrandException {
		if(space+1 > capacity) throw new BrandException();
		models.add(code);
		space++;
	}
	
	public void deleteModel(String code) throws BrandException {
		if(space == 0) throw new BrandException();
		models.remove(code);
		space--;
	}

	public Map<String, Long> getStorageSummary() {
		return models.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				;
	}
	
	
}
