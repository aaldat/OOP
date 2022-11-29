package libraryMgmt;

@SuppressWarnings ("serial")
public class LMException extends Exception {
public LMException (String reason) {
	super (reason);
	//System.out.println(reason);
	}
}
