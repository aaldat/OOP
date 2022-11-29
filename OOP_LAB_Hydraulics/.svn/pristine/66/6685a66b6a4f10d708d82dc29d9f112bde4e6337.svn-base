package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends ElementExt {

	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
	}
	
	@Override
	public void connect(Element elem) {
		//non deve succedere nulla
	}

	@Override
	public void simulate(double flow, SimulationObserver observer) {
		observer.notifyFlow("Sink", getName(), flow, SimulationObserver.NO_FLOW);
	}

	@Override
	protected void layout(StringBuffer buffer) {
		buffer.append("[" + getName() + "]Sink");
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
