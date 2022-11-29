package it.polito.oop.production;

public class Model {

	private String code, name;
	private int year, engineType;
	private float displacement;
	private float is;
	
	public Model(String code, String name, int year, float displacement, int engineType) {
		this.code = code;
		this.name = name;
		this.year = year;
		this.displacement = displacement;
		this.engineType = engineType;
	}
	
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public int getYear() {
		return year;
	}
	public int getEngineType() {
		return engineType;
	}
	public float getDisplacement() {
		return displacement;
	}
	
	//IS = enginetype × 100 / (currentyear - modelyear)
	
	public float getIS() {
		is = (engineType*100)/(java.time.LocalDate.now().getYear() - year);
		return is;
	}
	
	@Override
	public String toString() {
		return code + "," + name + "," + year + "," + displacement + "," + engineType;
	}
	
	
	
	
	
	
	
	
	
}
