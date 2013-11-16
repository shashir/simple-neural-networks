/**
 * This class implements the BackPropNeuron in a very weak way in order to
 * simulate the "input" neurons which are used to test values within a network.
 * The update and delta methods do nothing since there are no incoming weights
 * to update.
 * 
 * 
 * @author shashir
 * 
 */
public class BackPropInput extends BackPropNeuronAbstractImplementation {
	
	/**
	 * Basic constructor for input neuron.
	 */
	public BackPropInput() {
		super();
	}

	/**
	 * The value that this input neuron always returns.
	 */
	private double value = 1.0;

	/**
	 * The only way to set input values.
	 * 
	 * @param value
	 *            the value to update this.value with
	 */
	public final void set(double value) {
		this.value = value;
	}
	
	
	@Override
	public final double output() {
		return value;
	}
	
	/**
	 * Does nothing of value. Just ceremonial.
	 * 
	 * @param eta
	 * 			the learning rate
	 * @param alpha
	 * 			the momentum constant
	 * @param error
	 * 			store the error
	 */
	@Override
	public void applyUpdates(double eta, double alpha, double error) {

	}

}
