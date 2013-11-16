/**
 * This interface defines the interaction with the activation function of any
 * type of neuron.
 * 
 * 
 * 
 * @author shashir
 * 
 */
public interface ActivationFunction {

	/**
	 * 
	 * A single variable function R --> R.
	 * 
	 * @param v
	 *            linear combination of neuron's inputs wrt weights.
	 * @return result of neuron's contemplations.
	 */
	double evaluate(double v);
	
	/**
	 * 
	 * Derivative of activation function.
	 * 
	 * @param v
	 *			input
	 * @return result of differentiation at v
	 */
	double derivative(double v);

}
