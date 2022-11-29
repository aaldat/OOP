package fit;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Gym {
	
	private String name;
	private SortedMap<String, Lesson> lessons = new TreeMap<>();
	
	public Gym(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addLesson(Lesson l) throws FitException {
		if(lessons.containsKey(l.getDay() + "." + l.getTime())) throw new FitException("E' gia' presente una lezione a questo orario: " + l.getDay() + "." + l.getTime());
		lessons.put(l.getDay() + "." + l.getTime(), l);
	}
	
	public int numLessons() {
		return lessons.size();
	}
	
	public Lesson getLesson(String slot) {
		return lessons.get(slot);
	}
	
	public int getNumReservation(int customerid) { 			//mi ritorna il numero di lezioni che il cliente ha prenotato in UNA palestra.
		return (int) lessons.values().stream() 				//prendi una lista di lezioni in questa palestra
				.filter(p -> p.isReserved(customerid))		//filtra tutte le lezioni che il cliente ha prenotato
				.count(); 									//conta tutte le lezioni che il cliente ha prenotato
	}
	
	public int getNumLessonsGiven(String instructor) {
		return (int)lessons.values().stream()
				.map(l -> l.getInstructorName())		 //mi restituisce una lista di nomi ripetuti di istruttori che hanno fatto lezione
				.filter(i -> {
						if (i != null) {
							return i.equals(instructor); //filtra tutti quelli che hanno lo stesso nome dato dell'istruttore
						} else {
							return false;
						}
						})
				.count() 								 //conta tutti quelli che hanno lo stesso nome dell'istruttore
				;
	}
	
	public SortedMap<Integer, List<String>> slotsPerNofParticipants () {
		return lessons.entrySet().stream()
				.collect(Collectors.groupingBy(										//raggruppamelo per
						e -> e.getValue().getNumAttendees(),						//per ogni lezione prendi il numero di clienti frequentanti la lezione (chiave)
						TreeMap::new,												//in un nuovo TreeMap
						Collectors.mapping(Map.Entry::getKey, Collectors.toList())	//prendi la lista degli slot della lezione e mettimelo come valore della map
						));
	}

}
