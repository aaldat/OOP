package diet;

public class WorkingHours implements Comparable<WorkingHours> {
	
	private Time open, close;
	
	public WorkingHours(String open, String close) {
		this.open = new Time(open);
		this.close = new Time(close);
	}
	
	public Time getOpen() {
		return open;
	}
	
	public Time getClose() {
		return close;
	}

	public boolean includes(Time t) {
		return open.compareTo(t) <= 0 && close.compareTo(t) >= 0;
	}
	
	@Override
	public int compareTo(WorkingHours o) {
		return open.compareTo(o.getOpen());
	}
	
	@Override
	public String toString() {
		return open.toString() + " - " + close.toString();
	}
	
}
