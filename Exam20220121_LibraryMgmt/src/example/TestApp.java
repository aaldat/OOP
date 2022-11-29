package example;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;
import libraryMgmt.*;
import java.time.*;

public class TestApp {
	//@SuppressWarnings("unused")	

	@Test
	public void test() throws LMException {
		LibraryMgmt lm = new LibraryMgmt();
		int j = 0;
		String string = null;
		String result = null;
		LocalDate currentDate = null;

		//R0
		lm.setCurrentDate(LocalDate.parse ("2022-01-21"));
		LocalDate localDate = lm.getCurrentDate();
		assertEquals("2022-01-21", localDate.toString());

		//R1

		string = lm.addBook("title5",  3,  "auth4", "auth2");
		assertEquals(string, "1:3");

		string = lm.addBook("title1",  3,  "auth1", "auth2");
		assertEquals(string, "4:6");

		try {
			string = lm.addBook("title1",  4,  "auth3", "auth4");
			fail("Expected exception"); 
		} catch (LMException ex) {}

		string = lm.addBook("title2",  2,  "auth1", "auth3");
		assertEquals(string, "7:8");

		string = lm.addBook("title3",  1,  "auth1", "auth4");
		assertEquals(string, "9:9");

		
		string = lm.addUser("user1",  2,  10);
		assertEquals(string, "user1:2:10");

		try {
			string = lm.addUser("user1",  2,  10);
			fail("Expected exception"); 
		} catch (LMException ex) {}

		string = lm.addUser("user2",  2,  12);
		assertEquals(string, "user2:2:12"); 

		//R2
		lm.addDays(10);
		currentDate = lm.getCurrentDate();  //2022-01-31
		assertEquals("2022-01-31", currentDate.toString());

		j = lm.addLoan("user1", "title1"); //loanIndex 1  dueDate 2022-02-10
		assertEquals(j, 1);

		string = lm.getLoanInfo(1);
		result = "user1:1:4:2022-02-10:ongoing";
		assertEquals(result, string);

		j = lm.addLoan("user2", "title3"); //loanIndex 2 dueDate 2021-02-12
		assertEquals(j, 2);

		LocalDate date = lm.closeLoan (1);
		assertEquals(date, currentDate);

		string = lm.getLoanInfo(1);
		result = "user1:1:4:2022-02-10:closed";
		assertEquals(result, string);

		j = lm.addLoan("user1", "title5"); //loanIndex 3  dueDate 2022-02-10 current 2022-01-31
		assertEquals(j, 3);

		string = lm.getLoanInfo(3);
		result = "user1:3:1:2022-02-10:ongoing";
		assertEquals(result, string);

		lm.addDays(10);
		lm.addLoan("user1", "title5"); //loanIndex 4  dueDate 2022-02-20
		lm.addDays(10);
		currentDate = lm.getCurrentDate();  //2022-02-20
		assertEquals("2022-02-20", currentDate.toString());

		string = lm.getLoanInfo(3);
		result = "user1:3:1:2022-02-10:overdue";
		assertEquals(result, string);

		j = lm.numberOfBooks("user1");
		assertEquals(j, 2);

		//R3
		TreeMap<String, ArrayList<String>> map = lm.authorsByTitle();
		result = "{title1=[auth1, auth2], title2=[auth1, auth3], title3=[auth1, auth4], title5=[auth2, auth4]}";
		assertEquals(result, map.toString());

		TreeMap<String, Integer> map2 = lm.numberOfTotalLoansByUser();
		result = "{user1=3, user2=1}";
		assertEquals(result, map2.toString());

		//R4
//		lm.closeLoan (3);
//		
//		double avgOverdue = lm.averageDelay("user1");
//		assertEquals("Wrong overdue average",5.0,avgOverdue, 0.1);
//		
//		List<Integer> overdue = lm.dailyOverdue();
//		assertNotNull("Missing daily overdue list", overdue);
//		assertEquals("One daily overdue expected",1,overdue.size());
//		
//		
//		long nAvail = lm.availableVolumes("title3");
//		assertEquals("There should be no volume available for title 3",0,nAvail);
	}

}
