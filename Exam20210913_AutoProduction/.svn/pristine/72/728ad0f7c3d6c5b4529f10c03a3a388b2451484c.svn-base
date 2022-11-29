package it.polito.oop.production;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Factory {

	private String name;
	private Map<String, ProductionLine> plList = new HashMap<>();
	
	public Factory(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void addProductionLine(String... line) throws BrandException{
		try {
			for(int i = 0; i < line.length; i++) {
				String[] lines = line[i].split(":");
				if(Integer.parseInt(lines[1]) < 0 || Integer.parseInt(lines[2]) < 0 || Integer.parseInt(lines[2]) > 3) throw new BrandException();
				if(plList.containsKey(lines[0])) {
					ProductionLine p = plList.get(lines[0]);
					p.setCapacity(Integer.parseInt(lines[1]));
				}
				ProductionLine pl = new ProductionLine(lines[0], Integer.parseInt(lines[1]), Integer.parseInt(lines[2]));
				plList.put(pl.getName(), pl);
			}
		} catch(NumberFormatException e) {
			//ok
		}
		
	}

	public Map<Integer, Integer> estimatedYearlyProducyion() throws BrandException {
		if(plList == null) throw new BrandException();
		return plList.values().stream()
				.filter(pl -> pl != null)
				.collect(Collectors.groupingBy(ProductionLine::getEngineType, Collectors.summingInt(ProductionLine::getCapacity)))
				;
	}
	
}
