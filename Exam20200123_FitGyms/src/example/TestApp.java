package example;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import fit.*;

public class TestApp {	
	
@Test
public void test() throws FitException {
	
	// R1
	
	Fit f = new Fit();
	f.addGymn("MacFit Polito");
	f.addGymn("MacFit Bresica");
	f.addGymn("MacFit Unito");
		
	int ng = f.getNumGymns();
	assertEquals ("Wrong numnber of gyms", 3, ng);
	
	try {
		f.addGymn("MacFit Polito");
		fail("Duplicate gym name should not be allowed");
	} catch(Exception ex){} //ok

	
	// R2
	
	f.addLessons("MacFit Polito", // Polito gym
	             "Aerobics",       // Activity is aerobics
	             10,               // 10 people max per class
	             "1.8,1.9,2.8",    // Mon 8:00-9:00, Mon 9:00-10:00, Tue 8:00-9:00
	             "Stefano", "Marco"// trainers are Stefano and Marco
	             );
	f.addLessons("MacFit Polito", "Step", 5, "4.8,5.10","Giorgio","Stefano");
	f.addLessons("MacFit Unito", "Calisthenics", 10,"6.20","Giovanni","Luca");

    try {
        f.addLessons("MacFit Polito", "Power lifting", 4, "1.8","Andrea");
        fail("Slot on Monday at 8:00 is already taken, should not be allowed");
    } catch(Exception ex){} //ok

    	
	
	// R3
	
	int one = f.addCustomer("Lorenzo Bianchi");
	int two = f.addCustomer("Cristiano Rossi");
	assertEquals("Lorenzo Bianchi", f.getCustomer(one));
	assertEquals("Cristiano Rossi", f.getCustomer(two));
	assertEquals("The first customerId should be 1", 1, one);

	
	// R4
	
	f.placeReservation(one, "MacFit Polito", Fit.MONDAY, 9);
	f.placeReservation(one, "MacFit Polito", Fit.THURSDAY, 8);
	assertEquals("Wrong number of lessons for customer Bianchi", 2, f.getNumLessons(one));
	
	
	// R5
	
	f.addLessonGiven("MacFit Polito", Fit.MONDAY, 8, "Stefano");
	f.addLessonGiven("MacFit Polito", Fit.THURSDAY, 8, "Stefano");
	f.addLessonGiven("MacFit Polito", Fit.FRIDAY, 10, "Giorgio");

	assertEquals("Wrong number of lessons for Stefano", 2, f.getNumLessonsGiven("MacFit Polito", "Stefano"));
	assertEquals("Wrong number of lessons for Giorgio", 1, f.getNumLessonsGiven("MacFit Polito", "Giorgio"));

	
	// R6
	
	assertEquals("Wrong gym identified as most active","MacFit Polito",f.mostActiveGymn());

	Map<String, Integer> m = f.totalLessonsPerGymn();
	assertEquals("Numero errato di lezioni per palestra Polito", 5, m.get("MacFit Polito").intValue());
	assertEquals("Numero errato di lezioni per palestra Bresica", 0, m.get("MacFit Bresica").intValue());
	assertEquals("Numero errato di lezioni per palestra Unito", 1, m.get("MacFit Unito").intValue());
	
	SortedMap<Integer, List<String>> m1 = f.slotsPerNofParticipants("MacFit Polito");
	List<String> ll = m1.get(0);
	assertTrue("The list of slots with zero participants is missing 1.8", ll.contains("1.8"));
	assertTrue("The list of slots with zero participants is missing 2.8", ll.contains("2.8"));
	assertTrue("The list of slots with zero participants is missing 5.10", ll.contains("5.10"));
	ll = m1.get(1);
	assertTrue("The list of slots with one participant is missing 1.9", ll.contains("1.9"));
	assertTrue("The list of slots with one participant is missing 4.8", ll.contains("4.8"));

}
}
