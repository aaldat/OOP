package hydraulic;

import java.util.Arrays;

/**
 * Represents a multisplit element, an extension of the Split that allows many outputs
 * 
 * During the simulation each downstream element will
 * receive a stream that is determined by the proportions.
 */

public class Multisplit extends Split {

	private int numOutput;
	private double[] proportions;
	
	/**
	 * Constructor
	 * @param name
	 * @param numOutput
	 */
	public Multisplit(String name, int numOutput) {
		super(name); //you can edit also this line
		this.numOutput = numOutput;
		outputs = new Element[numOutput];
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
    	if(outputs == null) {
    		return null;
    	} else {
    		return Arrays.copyOf(outputs, numOutput);
    	} 
    }

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){
		outputs[noutput] = elem;
		this.proportions = new double[noutput];
		elem.input = this;
	}
	
	/**
	 * Define the proportion of the output flows w.r.t. the input flow.
	 * 
	 * The sum of the proportions should be 1.0 and 
	 * the number of proportions should be equals to the number of outputs.
	 * Otherwise a check would detect an error.
	 * 
	 * @param proportions the proportions of flow for each output
	 */
	public void setProportions(double... proportions) {
		this.proportions = proportions;
	}
	
	@Override
	public void simulate(double flow, SimulationObserver observer) {
		double[] outFlows = new double[numOutput];
		for(int i=0; i<numOutput; ++i) {
			outFlows[i] = flow*proportions[i];
		}
		observer.notifyFlow("Multisplit", getName(), flow, outFlows);
		for(int i=0; i<numOutput; ++i) {
			if(outputs[i]!=null)
				outputs[i].simulate(outFlows[i], observer);
		}
	}
}
