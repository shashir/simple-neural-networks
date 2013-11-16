import java.util.Set;

/**
 * The Neuron interface outlines the basic methods associated with most models
 * for artificial neurons. Any particular neuron model (McCulloch-Pitts, Linear
 * Neuron, Rosenblatt's Perceptron) can be implemented using the appropriate
 * activation function (and possible additional helper methods). Here T denotes
 * the type of neuron.
 * 
 * @author shashir
 * @param <T>
 * 			the type of neuron which will be specified by implementer
 */
public interface Neuron<T> {

	/**
	 * Add an incoming neuron of type T with its weight or update previously
	 * added incoming neuron's weight.
	 * 
	 * @param incoming
	 *            neuron of type T that is incoming
	 * @param weight
	 *            weight to associate with the incoming neuron
	 */
	void incomingSynapse(T incoming, double weight);

	/**
	 * Remove incoming connection to a neuron of type T.
	 * 
	 * @param incoming
	 *            incoming of type T to remove
	 */
	void removeIncomingSynapse(T incoming);

	/**
	 * The evaluation of the activation function phi() at the linear combination
	 * v of weights and outputs of incoming neurons.
	 * 
	 * @return phi(v)
	 */
	double output();

	/**
	 * Get the weight associated with an incoming neuron of type T attached to this.
	 * 
	 * @param incoming
	 *            incoming neuron of type T whose weight to locate.
	 * @return weight of incoming neuron
	 */
	double getIncomingWeight(T incoming);
	
	/**
	 * Get the previous weight associated with a neuron attached to this.
	 * 
	 * @param incoming
	 *            neuron whose previous weight to locate.
	 * @return previous weight of neuron
	 */
	double getOldIncomingWeight(T incoming);

	/**
	 * List of incoming neurons of type T.
	 * 
	 * @return List of incoming neurons of type T
	 */
	Set<T> getIncomingNeurons();

	/**
	 * String of output.
	 * 
	 * @return String of output
	 */
	String toString();

}
