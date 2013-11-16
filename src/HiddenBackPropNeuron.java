/**
 * Implementation of the hidden neuron as required for the backpropagation
 * algorithm. Here, the <code>delta</code> and <code>update</code> methods are
 * completely implemented.
 * 
 * @author shashir
 */
public class HiddenBackPropNeuron extends BackPropNeuronAbstractImplementation {

	/**
	 * Set the activation function as required for backpropagation.
	 */
	private ActivationFunction phi = new LogisticSigmoid();

	/**
	 * This constructor simply sets up the abstract class's basic methods.
	 */
	public HiddenBackPropNeuron() {
		super();
	}

	@Override
	public final double output() {
		return phi.evaluate(weightedSum());
	}

	@Override
	public final void applyUpdates(double eta, double alpha, double error) {
		// First compute and set delta
		double delta = 0.0;
		for (BackPropNeuron neuron : getBackNeurons()) {
			delta = delta + neuron.getDelta()
					* neuron.getOldIncomingWeight(this);
		}

		delta = delta * phi.derivative(weightedSum());

		this.setDelta(delta);

		// Next compute weight updates
		for (BackPropNeuron incoming : getIncomingNeurons()) {
			incomingSynapse(
					incoming,
					getIncomingWeight(incoming)
							+ alpha
							* (getIncomingWeight(incoming) 
							- getOldIncomingWeight(incoming))
							+ eta * getDelta() * incoming.output());
		}
	}

}
