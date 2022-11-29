package mountainhuts;

public class AltitudeRange {
	
	private int minValue, maxValue;
	
	public AltitudeRange(int minValue, int maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	public AltitudeRange(String range) {
		String[] values = range.split("-");
		this.minValue = Integer.parseInt(values[0]);
		this.maxValue = Integer.parseInt(values[1]);
	}

	public int getMinValue() {
		return minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}
	
	public boolean includes(int altitude) {
		return minValue < altitude && altitude <= maxValue;
	}

	@Override
	public String toString() {
		return minValue + "-" + maxValue;
	}
	
	
	
}
