package hydraulic;

import static hydraulic.SimulationObserver.exists;

import java.util.Arrays;

/**
 * Sample implementation of {@link SimulationObserver} interface.
 * This simulation observer simply prints out the notification info
 * and keeps count of how many notification it receive.
 * 
 * This class is intended to be used for debugging purposes.
 *  
 */
public class PrintingObserver implements SimulationObserver {
	
	private int numElements;
	private int countNotifications = 0;
	
	public PrintingObserver(HSystem sys) {
		numElements = sys.getElements().length;
	}
	
	@Override
	public void notifyFlow(String type, String name, double inFlow, double... outFlow) {
		countNotifications++;
		int pct = 100 * countNotifications / numElements;
		System.out.println(pct +"% : " + type + " " + name + ": ");
		if(exists(inFlow)) System.out.println("\t-> in flow=" + inFlow);
		if(exists(outFlow)) System.out.println("\t<- out flow=" + Arrays.toString(outFlow));
	}
	
	public int getCount() {
		return countNotifications;
	}
}