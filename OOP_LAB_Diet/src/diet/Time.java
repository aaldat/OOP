package diet;

public class Time implements Comparable<Time>{

	private int h, m;
	
	Time(String time) {
		String[] hm = time.split(":");
		h = Integer.parseInt(hm[0]);
		m = Integer.parseInt(hm[1]);
	}
	
	Time(int h, int m) {
		this.h = h;
		this.m = m;
	}

	@Override
	public int compareTo(Time o) {
		return 60*h + m - (60 * o.h + o.m);
	}
	
	@Override
	public String toString() {
		return String.format("%02d:%02d", h, m);
	}
	
}
