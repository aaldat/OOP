package hydraulic;

import java.util.Arrays;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends ElementExt {

	private final static int OUT = 2;
	protected Element[] outputs;
	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name);
		outputs = new Element[OUT];
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
        return Arrays.copyOf(outputs, OUT);
    }

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){
		if(noutput < 2) {
			outputs[noutput] = elem;
			elem.input = this;
		}
	}

	@Override
	public void simulate(double flow, SimulationObserver observer) {
		double outFlow = flow / 2;
		observer.notifyFlow("Split", getName(), flow, outFlow, outFlow);
		outputs[0].simulate(outFlow, observer);
		outputs[1].simulate(outFlow, observer);
	}

	@Override
	protected void layout(StringBuffer buffer) {
		buffer.append("[" + getName() + "]" + this.getClass().getSimpleName() + " ");
		int bufferLength = buffer.length();
		for(int i=0; i<outputs.length; i++) {
			if(outputs[i] != null) {
				if(i > 0) {
					buffer.append("\n");
					buffer.append(" ".repeat(bufferLength));
					buffer.append("|\n");
					buffer.append(" ".repeat(bufferLength));
				}
				buffer.append("+-> ");
				outputs[i].layout(buffer);
			}
			
		}
	}

	@Override
	public boolean isDeletable() {
		if(outputs.length == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void simulate(double Flow, SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		// TODO Auto-generated method stub
		
	}
}
