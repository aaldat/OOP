package supermarket;

@SuppressWarnings("serial")
public class SMException extends Exception {
	SMException (String reason) {
		super(reason);
	}
}
