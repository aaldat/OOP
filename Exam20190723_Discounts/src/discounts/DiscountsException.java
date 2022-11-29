package discounts;
@SuppressWarnings("serial")
public class DiscountsException extends Exception {
    public DiscountsException() {
        super("No description");
    }
	public DiscountsException(String reason) {
		super(reason);
	}
}
