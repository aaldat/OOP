package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends ElementExt {

	private boolean open;
	
	public Tap(String name) {
		super(name);
	}
	
	/**
	 * Defines whether the tap is open or closed.
	 * 
	 * @param open  opening level
	 */
	public void setOpen(boolean open){
		this.open = open;
	}

	@Override
	public void simulate(double flow, SimulationObserver observer) {
		if(open) {
			observer.notifyFlow("Tap", getName(), flow, flow);
			getOutput().simulate(flow, observer);
		} else {
			observer.notifyFlow("Tap", getName(), flow, 0.0);
			getOutput().simulate(0.0, observer);
		}
		
		
	}

	@Override
	protected void layout(StringBuffer buffer) {
		buffer.append("[" + getName() + "]Tap -> ");
		if(getOutput() != null) {
			getOutput().layout(buffer);
		} else {
			buffer.append("*");
		}
		
	}

	@Override
	public boolean isDeletable() {
		return true;
	}

	@Override
	protected void simulate(double Flow, SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		// TODO Auto-generated method stub
		
	}

}
