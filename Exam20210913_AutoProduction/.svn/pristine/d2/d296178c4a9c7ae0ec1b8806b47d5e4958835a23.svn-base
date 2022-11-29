package it.polito.oop.production;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Facade class used to interact with the system.
 *
 */
public class Carmaker {
	
	private Map<String, Model> models = new HashMap<>();
	private Map<String, Factory> factories = new HashMap<>();
	private Map<String, Storage> storages = new HashMap<>();
	private float ISmin, ISmax;

	/** unique code for diesel engine **/
	public static final int DIESEL = 0;
	/** unique code for gasoline engine **/
	public static final int GASOLINE = 1;
	/** unique code for gpl engine **/
	public static final int GPL = 2;
	/** unique code for electric engine **/
	public static final int ELECTRIC = 3;

	
	// **************** R1 ********************************* //

	/**
	 * Add a new model to the brand factory.
	 * 
	 * Models are uniquely identified by a code
	 * 
	 * @param code 	code
	 * @param name  name
	 * @param year	year of introduction in the market
	 * @param displacement  displacement of the engine in cc
	 * @param enginetype	the engine type (e.g., gasoline, diesel, electric)
	 * @return {@code false} if code is duplicate, 
	*/
	public boolean addModel(String code, String name,  int year, float displacement, int enginetype) {
		Model m = new Model(code, name, year, displacement, enginetype);
		if(models.containsKey(m.getCode())) {
			return false;
		} else {
			models.put(m.getCode(), m);
			return true;
		}
	}
	
	/**
	 * Count the number of models produced by the brand
	 * 
	 * @return models count
	 */
	public int countModels() {
		return models.size();
	}
	
	/**
	 * Retrieves information about a model.
	 * Information is formatted as code, name, year, displacement, enginetype
	 * separate by {@code ','} (comma).
	 * 	 
	 * @param code code of the searched model
	 * @return info about the model
	 */
	public String getModel(String code) {
		if(!models.containsKey(code)) return null;
		return models.get(code).toString();
	}
	
	
	/**
	 * Retrieves the list of codes of active models.
	 * Active models not older than 10 years with respect to the execution time.
	 * 	 
	 * @return a list of codes of the active models
	 */
	public List<String> getActiveModels() {
		return models.values().stream()
				.filter(m -> m.getYear() > (java.time.LocalDate.now().getYear() - 10))
				.map(Model::getCode)
				.collect(Collectors.toList());
	}
	
	//private final static Pattern re = Pattern.compile("(?<code>[0-9a-zA-Z]+) * (?<name>[a-zA-Z]+) * (?<year>[0-9]+) * (?<displacement>[0-9]+) * (?<engineType>[0-9]+))");
	
	/**
	 * Loads a list of models from a file.
	 * @param Filename path of the file
	 * @throws IOException in case of IO problems
	 */
	public void loadFromFile(String Filename) throws IOException  {
//		Reader rd = new FileReader(Filename);
//		BufferedReader r = new BufferedReader(rd);
//		String line;
//		while((line = r.readLine()) != null) {
//			Matcher m = re.matcher(line);
//			if(m.find()) {
//				try {
//					if(!models.containsKey(m.group("code"))) {
//						addModel(m.group("code"), m.group("name"), Integer.parseInt(m.group("year")), Float.parseFloat(m.group("displacement")), Integer.parseInt(m.group("engineType")));
//					}
//				} catch(NumberFormatException e) {
//					//ok
//				}
//			}
//		}
//		r.close();
		
		Reader rd = new FileReader(Filename);
		BufferedReader r = new BufferedReader(rd);
		String line;
		while((line = r.readLine()) != null) {
			String[] el = line.split(" ");
			try {
				if(!models.containsKey(el[0])) {
					addModel(el[0], el[1], Integer.parseInt(el[2]), Float.parseFloat(el[3]), Integer.parseInt(el[4]));
				}
			} catch(NumberFormatException e) {
				//ok
			}
		}
		r.close();
	}
	
	// **************** R2 ********************************* //

	
	
	/**
	 * Creates a new factory given its name. Throws Brand Exception if the name of the factory already exists.
	 * 
	 * @param name the unique name of the factory.
	 * @throws BrandException
	 */
	public void buildFactory(String name) throws BrandException {
		Factory f = new Factory(name);
		if(factories.containsKey(name)) throw new BrandException();
		factories.put(name, f);
	}
	
	
	
	/**
	 * Returns a list of the factory names. The list is empty if no factories are created.
	 * @return A list of factory names.
	 */
	public List<String> getFactories() {
		return factories.values().stream()
				.map(Factory::getName)
				.collect(Collectors.toList());
	}
	
	
	/**
	 * Create a set of production lines for a factory.
	 * Each production line is identified by name,capacity and type of engines it can handle.
	 * 
	 * @param fname The factory name
	 * @parm  line	An array of strings. Each string identifies a production line.
	 * 
	 * @throws BrandException if factory name is not defined or line specification is malformed
	 */
	public void setProductionLines (String fname, String... line) throws BrandException {
		if(!factories.containsKey(fname)) {
			throw new BrandException();
		}
		Factory f = factories.get(fname);
		f.addProductionLine(line);
	}
	
	/**
	 * Returns a map reporting for each engine type the yearly production capacity of a factory.
	 * 
	 * @param fname factory name
	 * @return A map of the yearly productions
	 * @throws BrandException if factory name is not defined or it has no lines
	 */
	public Map<Integer, Integer> estimateYearlyProduction(String fname) throws BrandException {
		if(!factories.containsKey(fname)) throw new BrandException();
		return factories.get(fname).estimatedYearlyProducyion();
	}

	// **************** R3 ********************************* //

	
	/**
	 * Creates a new storage for the car maker
	 * 
	 * @param name		Name of the storage
	 * @param capacity	Capacity (number of cars) of the storage
	 * @throws BrandException if name already defined or capacity &le; 0
	 */
	public void buildStorage (String name, int capacity) throws BrandException {
		Storage s = new Storage(name, capacity);
		if(storages.containsKey(name) || capacity < 0) throw new BrandException();
		storages.put(name, s);
	}
	
	/**
	 * Retrieves the names of the available storages. 
	 * The list is empty if no storage is available
	 * 
	 * @return List<String> list of storage names
	 */
	public List<String> getStorageList() {
		return storages.values().stream()
				.map(Storage::getName)
				.collect(Collectors.toList());
	}
	
	/**
	 * Add a car to the storage if possible
	 * 
	 * @param sname		storage name
	 * @param model		car model
	 * 
	 * @throws BrandException if storage or model not defined or storage is full
	 */
	public void storeCar(String sname, String model) throws BrandException {
		if(!models.containsKey(model) || !storages.containsKey(sname)) throw new BrandException();
		storages.get(sname).addModel(model);
	}
	
	/**
	 * Remove a car to the storage if possible.
	 * 
	 * @param sname		Storage name
	 * @param model		Car model
	 * @throws BrandException  if storage or model not defined or storage is empty
	 */
	public void removeCar(String sname, String model) throws BrandException {
		if(!models.containsKey(model) || !storages.containsKey(sname)) throw new BrandException();
		storages.get(sname).deleteModel(model);
	}
	
	/**
	 * Generates a summary of the storage.
	 * 
	 * @param sname		storage name
	 * @return map of models vs. quantities
	 * @throws BrandException if storage is not defined
	 */
	public Map<String,Integer> getStorageSummary(String sname) throws BrandException {
		if(!storages.containsKey(sname)) throw new BrandException();
		Map<String, Long> summary = storages.get(sname).getStorageSummary();
		Map<String, Integer> newMap = summary.entrySet().stream()
			     						.collect(Collectors.toMap(Map.Entry::getKey, e -> (Integer)e.getValue().intValue()));
		return newMap;
	}
	
	// **************** R4 ********************************* //
	

	/**
	 * Sets the thresholds for the sustainability level.
	 * 
	 * @param ismin	lower threshold
	 * @param ismax upper threshold
	 */
	public void setISThresholds (float ismin, float ismax) {
		this.ISmin = ismin;
		this.ISmax = ismax;
	}
	
	
	
	
	/**
	 * Retrieves the models classified in the given Sustainability class.
	 * 
	 * @param islevel sustainability level, 0:low 1:medium 2:high
	 * @return the list of model names in the class
	 */
	public List<String> getModelsSustainability(int islevel) {
		List<String> list0 = new LinkedList<>();
		List<String> list1 = new LinkedList<>();
		List<String> list2 = new LinkedList<>();
		for(Model m : models.values()) {
			if(m.getIS() < ISmin) {
				list0.add(m.getName());
			} else if (m.getIS() >= ISmin && m.getIS() <= ISmax) {
				list1.add(m.getName());
			} else {
				list2.add(m.getName());
			}
		}
		
		if(islevel == 0) {
			return list0;
		} else if(islevel == 1) {
			return list1;
		} else {
			return list2;
		}
	}
	
	
	/**
	 * Computes the Carmaker Sustainability level
	 * 
	 * @return sustainability index
	 */
	public float getCarMakerSustainability() {
		float sum = 0;
		for(Model m : models.values()) {
			System.out.println(m.getIS());
			sum += m.getIS();
		}
		return sum/models.size();
	}
	
	// **************** R5 ********************************* //

	/**
	 * Generates an allocation production plan
	 * 
	 * @param request allocation request string
	 * @return {@code true} is planning was successful
	 */
	public boolean plan(String request) {
		return false;
	}
	
	
	
	/**
	 * Returns the capacity of a line
	 * 
	 * @param fname factory name
	 * @param lname line name
	 * @return total capacity of the line
	 */
	public int getLineCapacity(String fname, String lname) {
		return -1;
	}
	
	/**
	 * Returns the allocated capacity of a line
	 * @param fname factory name
	 * @param lname line name
	 * @return already allocated capacity for the line
	 */
	public int getLineAllocatedCapacity(String fname, String lname) {
		return -1;
	}
	
	
	
	// **************** R6 ********************************* //
	
	/**
	 * compute the proportion of lines that are fully allocated
	 * (i.e. allocated capacity == total capacity) as a result
	 * of previous calls to method {@link #plan}
	 * 
	 * @return proportion of lines fully allocated
	 */
	public float linesfullyAllocated() {
		return (float)-1.0;
	}

	/**
	 * compute the proportion of lines that are unused
	 * (i.e. allocated capacity == 0) as a result
	 * of previous calls to method {@link #plan}
	 * 
	 * @return proportion of unused lines
	 */
	public float unusuedLines() {
		return (float)-1.0;
	}
}
