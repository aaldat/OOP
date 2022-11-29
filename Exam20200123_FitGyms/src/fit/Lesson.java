package fit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lesson {
	
	private String gym, activity;
	private int maxAttendees, day, time;
	private String[] instructors;
	private String instructorname = null;
	private List<Customer> attendees = new ArrayList<>();
	
	public Lesson(String gymnname, int day, int time, String activity, int maxattendees, String[] allowedinstructors) {
		this.gym = gymnname;
		this.day = day;
		this.time = time;
		this.activity = activity;
		this.maxAttendees = maxattendees;
		this.instructors = allowedinstructors;
	}

	public String getGym() {
		return gym;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getTime()
	{
		return time;
	}
	public String getActivity() {
		return activity;
	}

	public int getMaxAttendees() {
		return maxAttendees;
	}
	
	public int getNumAttendees() {
		return attendees.size();
	}

	public String[] getInstructors() {
		return instructors;
	}
	
	public String getInstructorName() {
		return instructorname;
	}
	
	public void addReservation(Customer c) throws FitException {
		if(attendees.size() < maxAttendees) {
			if(attendees.contains(c)) throw new FitException("Il cliente risulta gia' prenotato alla lezione.");
			attendees.add(c);
		} else {
			throw new FitException("Non sono disponibili altri posti per questa lezione.");
		}
	}
	
	public boolean isReserved(int customerid) {
		return attendees.stream().anyMatch(t -> { //dice se il cliente ha prenotato o meno la lezione.
			return t.getCode()==customerid;
			}
		);
	}
	
	public void addLessonGiven(String instructor) throws FitException {
		if(!Arrays.asList(instructors).contains(instructor)) throw new FitException("L'istruttore " + instructor + " non e' associato alla lezione.");
		this.instructorname = instructor;
	}
	
	

}
