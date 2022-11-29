package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystemExt extends HSystem{
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		StringBuffer buffer = new StringBuffer();
		for(Element e : elements) {
			if(e instanceof Source) {
				e.layout(buffer);
			}
		}
		return buffer.toString();
	}
	
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public boolean deleteElement(String name) {
		Element toDelete = null;
		for(Element e : elements) {
			if(e != null) {
				if(e.getName().equals(name)) {
					toDelete = e;
					break;
				}
			}
		}
		if(toDelete == null) {
			return false;
		}
		if(!toDelete.isDeletable()) {
			return false;
		}
		if(toDelete.input != null) {
			toDelete.input.connect(toDelete.getOutput());
		}
		Element[] newElements = new Element[elements.length];
		int i = 0;
		for(Element e : elements) {
			if(e != null) {
				if(e != toDelete) {
					newElements[i++] = e;
				}
			} else {
				break;
			}
		}
		elements = newElements;
		return true;
		
	}

	/**
	 * starts the simulation of the system; if enableMaxFlowCheck is true,
	 * checks also the elements maximum flows against the input flow
	 */
	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		for(Element e : elements) {
			if(e instanceof Source) {
				e.simulate(SimulationObserver.NO_FLOW, observer, enableMaxFlowCheck);
			}
		}
	}
	
}
