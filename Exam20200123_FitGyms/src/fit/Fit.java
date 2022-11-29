package fit;

import java.util.*;
import java.util.stream.Collectors;


public class Fit {
	
	private Map<String, Gym> gyms = new HashMap<>();
	private Map<Integer, Customer> customers = new HashMap<>();
	private int codeCustomer = 1;
    
    public static int MONDAY    = 1;
    public static int TUESDAY   = 2;
    public static int WEDNESDAY = 3;
    public static int THURSDAY  = 4;
    public static int FRIDAY    = 5;
    public static int SATURDAY  = 6;
    public static int SUNDAY    = 7;
    
	public Fit() {
	
	}
	// R1 
	
	public void addGymn (String name) throws FitException {
		if(gyms.containsKey(name)) throw new FitException("Esiste gia' una palestra con questo nome: " + name);
		Gym g = new Gym(name);
		gyms.put(name, g);
	}
	
	public int getNumGymns() {
		return gyms.size();
	}
	
	//R2

	public void addLessons (String gymnname, 
	                        String activity, 
	                        int maxattendees, 
	                        String slots, 
	                        String ...allowedinstructors) throws FitException{
		String slotsArray[] = slots.split(",");
		for(String s : slotsArray) {
			String slot[] = s.split("\\.");
			int day = Integer.parseInt(slot[0]);
			int timeSlot = Integer.parseInt(slot[1]);
			if (day < 1 || day > 7 || timeSlot < 8 || timeSlot > 21) {
				throw new FitException("Giorno o orario sbagliato.");
			} else {
				if(!gyms.containsKey(gymnname)) {
					throw new FitException("Il nome della palestra non e' corretto: " + gymnname);
				} else {
					Lesson l = new Lesson (gymnname, day, timeSlot, activity, maxattendees, allowedinstructors);
					gyms.get(gymnname).addLesson(l);
				}
				
			}
		}
		
	}
	
	//R3
	public int addCustomer(String name) {
		Customer c = new Customer(codeCustomer++, name);
		customers.put(c.getCode(), c);
		return c.getCode();
	}
	
	public String getCustomer (int customerid) throws FitException{
		if(!customers.containsKey(customerid)) throw new FitException("Il cliente " + customerid + " non esiste.");
	    return customers.get(customerid).getName();
	}
	
	//R4
	
	public void placeReservation(int customerid, String gymnname, int day, int slot) throws FitException{
		if(!customers.containsKey(customerid)) throw new FitException("Il cliente " + customerid + " non esiste.");
		if(!gyms.containsKey(gymnname)) throw new FitException("La palestra " + gymnname + " non esiste.");
		if (day < 1 || day > 7 || slot < 8 || slot > 21) throw new FitException("Giorno o orario sbagliato.");
		gyms.get(gymnname).getLesson(day + "." + slot).addReservation(customers.get(customerid));
	}
	
	public int getNumLessons(int customerid) {
		return gyms.values().stream()
				.mapToInt(g -> g.getNumReservation(customerid)) //mi ritorna il numero di lezioni che ha prenotato il cliente per ogni palestra.
				.sum();											//me le somma tutte.
	}
	
	
	//R5
	
	public void addLessonGiven (String gymnname, int day, int slot, String instructor) throws FitException{
		if(!gyms.containsKey(gymnname)) throw new FitException("La palestra " + gymnname + " non esiste.");
		if (day < 1 || day > 7 || slot < 8 || slot > 21) throw new FitException("Giorno o orario sbagliato.");
		gyms.get(gymnname).getLesson(day + "." + slot).addLessonGiven(instructor);
	}
	
	public int getNumLessonsGiven (String gymnname, String instructor) throws FitException {
		if(!gyms.containsKey(gymnname)) throw new FitException("La palestra " + gymnname + " non esiste.");
	    return gyms.get(gymnname).getNumLessonsGiven(instructor);
	}
	//R6
	
	public String mostActiveGymn() {
		Gym g = gyms.values().stream()												//una lista di palestre
				.max((g1,g2) -> Integer.compare(g1.numLessons(), g2.numLessons()))	//compara il numero di lezioni di g1 e di g2, e calcola il massimo
				.get();																//restituiscimi la palestra con più lezioni
		return g.getName();
	}
	
	public Map<String, Integer> totalLessonsPerGymn() {		
		return gyms.values().stream()
				.collect(Collectors.toMap(Gym::getName, Gym::numLessons)); //rifammi una mappa che per ogni nome di palestra ho il numero di lezioni.
	}
	
	public SortedMap<Integer, List<String>> slotsPerNofParticipants(String gymnname) throws FitException{
		if(!gyms.containsKey(gymnname)) throw new FitException("La palestra " + gymnname + " non esiste.");
	    return gyms.get(gymnname).slotsPerNofParticipants();
	}
	

	
	
	
	


}
