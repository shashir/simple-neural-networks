import java.util.Set;

/**
 * The BackPropNeuron interface extends the Neuron interface in such a way as to
 * integrate the learning algorithm known as backpropagation. Backpropagation
 * involves two passes of computation across all neurons: the forward pass
 * (which aims to calculate output), and the backward pass (which performs
 * weight updates). The forward pass is accomplished by the
 * <code>output()</code> method over the regularly synapsed neurons. The
 * backward pass requires management of "backward synapses" which propagate
 * delta-rule parameters backward in order to calculate the weight updates. Some
 * new methods associated with tracking "backward synapses" are featured.
 * 
 * 
 * @author shashir
 * 
 */
public interface BackPropNeuron extends Neuron<BackPropNeuron> {

	/**
	 * This method returns the local gradient which is used by the update method
	 * to determine the direction and amount to update the weights.
	 * 
	 * @return the local gradient
	 */
	double getDelta();

	/**
	 * This method sets the computed the local gradient which is used by the
	 * update method to determine the direction and amount to update the
	 * weights.
	 * 
	 * @param value 
	 * 			the local gradient
	 */
	void setDelta(double value);

	/**
	 * Calculates the weight updates. Update all weights to the calculated
	 * weight updates.
	 * 
	 * @param eta
	 * 			the learning rate
	 * @param alpha
	 * 			the momentum constant
	 * @param error
	 * 			store the error
	 */
	void applyUpdates(double eta, double alpha, double error);

	/**
	 * List of "backward synapsed" neurons.
	 * 
	 * @return List of outgoing neurons
	 */
	Set<BackPropNeuron> getBackNeurons();

	/**
	 * Performs a backward synapse.
	 * 
	 * @param backward
	 *            neuron which will be backward synapsed to this
	 */
	void backwardSynapse(BackPropNeuron backward);

	/**
	 * Remove backward synapse.
	 * 
	 * @param backward
	 *            neuron to remove backward synapse from
	 */
	void removeBackwardSynapse(BackPropNeuron backward);

}
