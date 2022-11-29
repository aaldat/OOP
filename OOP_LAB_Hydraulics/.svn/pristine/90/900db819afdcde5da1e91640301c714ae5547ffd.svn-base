package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * The status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends ElementExt {
	
	private double flow = Double.NaN;
	
	public Source(String name) {
		super(name);
	}

	/**
	 * defines the flow produced by the source
	 * 
	 * @param flow
	 */
	public void setFlow(double flow){
		this.flow = flow;
	}

	@Override
	public void simulate(double flow, SimulationObserver observer) {
		observer.notifyFlow("Source", getName(), SimulationObserver.NO_FLOW, this.flow);
		getOutput().simulate(this.flow, observer);
	}

	@Override
	protected void layout(StringBuffer buffer) {
		buffer.append("[" + getName() + "]Source -> ");
		if(getOutput() != null) {
			getOutput().layout(buffer);
		} else {
			buffer.append("*");
		}
		
	}

	@Override
	public boolean isDeletable() {
		return false;
	}

	@Override
	protected void simulate(double flow, SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		if(enableMaxFlowCheck) {
			if(this.flow > maxFlow) {
				observer.notifyFlowError("Source", getName(), this.flow, maxFlow);
			}
		}
		observer.notifyFlow("Source", getName(), SimulationObserver.NO_FLOW, this.flow);
		getOutput().simulate(this.flow, observer, enableMaxFlowCheck);
		
	}

	
}
