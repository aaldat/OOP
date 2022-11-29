import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.regex.Pattern;

import org.junit.Test;

import clinic.Clinic;
import clinic.NoSuchDoctor;
import clinic.NoSuchPatient;

public class ExampleTest {

	@Test
	public void test() throws NoSuchPatient, NoSuchDoctor, FileNotFoundException, IOException {
		Clinic clinic = new Clinic();

		clinic.addPatient("Alice", "Green", "ALCGRN");
		clinic.addPatient("Robert", "Smith", "RBTSMT");
		clinic.addPatient("Steve", "Moore", "STVMRE");
		clinic.addPatient("Susan", "Madison", "SSNMDS");
		
		String alice = clinic.getPatient("ALCGRN");
		Pattern p = Pattern.compile("green\\s+alice\\s+\\(\\s*ALCGRN\\s*\\)",Pattern.CASE_INSENSITIVE);
		assertNotNull("Missing patient",alice);
		assertTrue("Wrong patient format: " + alice,p.matcher(alice).matches());
		assertTrue("Wrong patient format: " + alice,alice.matches("Green\\s+Alice\\s+\\(\\s*ALCGRN\\s*\\)"));

		
		clinic.addDoctor("George", "Sun","SNUGRG", 14,"Physician");
		clinic.addDoctor("Kate", "Love", "LVOKTA",86,"Physician");
		
		String kate = clinic.getDoctor(86);
		assertNotNull("Missing doctor",kate);
		assertTrue("Missing doctor's id",kate.contains("86"));
		assertTrue("Missing doctor's specialization",kate.contains("Physician"));

		clinic.assignPatientToDoctor("SSNMDS", 86);
		clinic.assignPatientToDoctor("ALCGRN", 14);
		clinic.assignPatientToDoctor("RBTSMT", 14);
		clinic.assignPatientToDoctor("STVMRE", 14);

		int susanDoc = clinic.getAssignedDoctor("SSNMDS");
		assertEquals("Wrong doctor for Susan",86,susanDoc);

		Collection<String> patients = clinic.getAssignedPatients(14);

		assertNotNull("Missing George's patients",patients);
		
		try {
			clinic.getAssignedPatients(-1);
			fail("Invalid id should raise an exception");
		} catch (Exception ex) {
			// ok
		}
		
		int n = clinic.loadData(new FileReader("data.txt"));
		assertEquals("Wrong number of lines",n,3);
		
		String gio = clinic.getPatient("ALCGRN");
		
		Collection<Integer> busy = clinic.busyDoctors();
		
		assertNotNull("Missing busy doctors",busy);
		assertEquals("Too many busy doctors detected",1,busy.size());
		assertTrue("Missing busy doctor",busy.contains(14));
	}

}
