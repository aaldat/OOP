package example;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
import jobOffers.*;

public class TestApp {
	
	@Test
	public void test() throws JOException {
		JobOffers jo = new JobOffers();	
		int j = 0;
		List<String> list = null;
		
	//R1	
		System.out.println("R1");
		j = jo.addSkills ("java", "python", "swEng", "mvc");
		assertEquals(4, j); 
		j = jo.addSkills ("projectMgmt", "userEx", "projectMgmt", "mvc");
		assertEquals(6, j);

		j = jo.addPosition("juniorProgrammer", "python:5", "java:8");
		assertEquals(6, j); // 5 + 8 = 13, 13/2 = 6 

		try {
			j = jo.addPosition("juniorProgrammer", "python:5", "java:8");
			fail("Expected exception"); // duplicated position
		} catch (JOException ex) {}	
		
		j = jo.addPosition("internA", "python:5", "java:8");
		assertEquals(6, j);

	//R2	
		System.out.println("R2");
		j = jo.addCandidate("John", "java");
		assertEquals(1, j);
	
		j = jo.addCandidate("Mary", "python", "java");
		
		try {
			j = jo.addCandidate("Jim", "C++", "java");
			fail("Expected exception"); // unknown skill C++
		} catch (JOException ex) {}	
		
		j = jo.addCandidate("Linda", "java", "python");

		try {
			j = jo.addCandidate("Jack", "java", "python");
		} catch (JOException ex) {fail("no exception expected");}	

		list = jo.addApplications("Mary", "juniorProgrammer", "internA");
		assertEquals( "[Mary:internA, Mary:juniorProgrammer]",
					  list.toString());

		try {
			list = jo.addApplications("John", "internA");
			fail("Expected exception"); // missing skill python
		} catch (JOException ex) {}
		
		list = jo.addApplications("Linda", "juniorProgrammer", "internA");
		assertEquals("[Linda:internA, Linda:juniorProgrammer]",
					   list.toString());
		
		TreeMap<String, List<String>> map = jo.getCandidatesForPositions(); 
		assertEquals("{internA=[Linda, Mary], juniorProgrammer=[Linda, Mary]}", 
					   map.toString());
		
	//R3
		System.out.println("R3");
		j = jo.addConsultant("James", "python", "java");
		assertEquals(2, j);
		
		j = jo.addConsultant("Susan", "mvc", "java");
		assertEquals(2, j);

		try {
			j = jo.addConsultant("James", "mvc", "java");
			fail("Expected exception"); // duplicated consultant
		} catch (JOException ex) {}
		
		j = jo.addRatings ("James", "John", "java:8");
		assertEquals(8, j);
		
		try {
			j = jo.addRatings ("Susan", "Mary", "java:8");
			fail("Expected exception"); //consultant's skills do not include candidate's skills
		} catch (JOException ex) {}
		
		try {
			j = jo.addRatings ("James", "Linda", "java:8", "python:11");
			fail("Expected exception"); // 11 is out of range
		} catch (JOException ex) {}
		
		
	//R4
		System.out.println("R4");
		jo.addApplications ("Jack", "juniorProgrammer", "internA");
		j = jo.addRatings ("James", "Jack", "python:5", "java:9");
		list = jo.discardApplications();
		assertEquals( "[Linda:internA, Linda:juniorProgrammer, Mary:internA, Mary:juniorProgrammer]",
						list.toString()); 
		// no ratings for Linda and Mary
		
		j = jo.addRatings ("James", "Linda", "java:4", "python:7"); 
		assertEquals(5, j); //average rating 11/2 = 5
		
		j = jo.addRatings ("James", "Mary", "java:8", "python:9"); 
		assertEquals(8, j); //average rating 17/2 = 8
		
		list = jo.discardApplications();
		//rating java:4 lower than level java:8
		assertEquals("[Linda:internA, Linda:juniorProgrammer]", list.toString());

		List<String> wList = jo.getEligibleCandidates("juniorProgrammer");
		assertEquals("[Jack, Mary]", wList.toString());
	}


}
