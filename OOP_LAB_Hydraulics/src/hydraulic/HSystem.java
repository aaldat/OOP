package hydraulic;

import java.util.Arrays;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	
	private static final int SIZE = 100;
	protected Element[] elements = new Element[SIZE];
	private int nextElement = 0;
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public void addElement(Element elem){
		elements[nextElement++] = elem;
	}
	
	/**
	 * returns the element added so far to the system.
	 * If no element is present in the system an empty array (length==0) is returned.
	 * 
	 * @return an array of the elements added to the hydraulic system
	 */
	public Element[] getElements(){
		return Arrays.copyOf(elements, nextElement);
	}
	
	/**
	 * starts the simulation of the system
	 */
	public void simulate(SimulationObserver observer){
		for(Element e : elements) {
			if(e instanceof Source) {
				e.simulate(SimulationObserver.NO_FLOW, observer);
			}
		}
	}

}
