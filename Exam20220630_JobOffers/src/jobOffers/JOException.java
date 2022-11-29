package jobOffers;
@SuppressWarnings ("serial")
public class JOException extends Exception {
	public JOException (String reason) {
		super (reason);
		System.out.println(reason);
	}
}
