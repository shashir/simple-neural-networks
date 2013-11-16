import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.awt.geom.Point2D;

/**
 * This class implements the BackPropNeuron in a very weak way in order to
 * simulate the basic neuron functions which are later implemented in full by
 * child classes.. The update and delta methods do nothing since there are no
 * incoming weights to update.
 * 
 * 
 * @author shashir
 * 
 */
public abstract class BackPropNeuronAbstractImplementation implements
		BackPropNeuron {

	/**
	 * This records all incoming neurons.
	 */
	private Map<BackPropNeuron, Point2D.Double> incomingNeurons = 
		new HashMap<BackPropNeuron, Point2D.Double>();

	/**
	 * This records all neurons which are backward synapsed to this instance.
	 */
	private Set<BackPropNeuron> backNeurons = new HashSet<BackPropNeuron>();
	
	/**
	 * Store delta the local gradient here.
	 */
	private double delta = 0.0;
	
	@Override
	public final double getDelta() {
		return delta;
	}
	
	@Override
	public final void setDelta(double value) {
		delta = value;
	}
	
	@Override
	public final String toString() {
		return java.lang.Double.toString(output());
	}

	@Override
	public abstract double output();

	/**
	 * Weighted sum of the incoming neurons' outputs and weights.
	 * 
	 * @return weighted sum of outputs and weights coming in.
	 */
	public final double weightedSum() {
		double sum = 0;

		for (Map.Entry<BackPropNeuron, Point2D.Double> entry : incomingNeurons
				.entrySet()) {
			sum = sum + entry.getKey().output() * entry.getValue().y;
		}
		return sum;
	}

	@Override
	public abstract void applyUpdates(double eta, double alpha, double error);

	@Override
	public final Set<BackPropNeuron> getBackNeurons() {
		return new HashSet<BackPropNeuron>(backNeurons);
	}

	@Override
	public final Set<BackPropNeuron> getIncomingNeurons() {
		return new HashSet<BackPropNeuron>(incomingNeurons.keySet());
	}

	@Override
	public final double getIncomingWeight(BackPropNeuron neuron) {
			return incomingNeurons.get(neuron).y;
	}


	@Override
	public final double getOldIncomingWeight(BackPropNeuron incoming) {
			return incomingNeurons.get(incoming).x;
	}

	@Override
	public final void removeIncomingSynapse(BackPropNeuron incoming) {
		incomingNeurons.remove(incoming);
		if (incoming.getBackNeurons().contains(this)) {
			incoming.removeBackwardSynapse(this);
		}
	}

	@Override
	public final void removeBackwardSynapse(BackPropNeuron outgoing) {
		backNeurons.remove(outgoing);
		if (outgoing.getIncomingNeurons().contains(this)) {
			outgoing.removeIncomingSynapse(this);
		}
	}

	@Override
	public final void incomingSynapse(BackPropNeuron incoming, double weight) {

		if (!incomingNeurons.containsKey(incoming)) {
			incomingNeurons.put(incoming, new Point2D.Double(weight, weight));
		} else {
			incomingNeurons.put(incoming, new Point2D.Double(incomingNeurons
					.get(incoming).y, weight));
		}
		
		if (!incoming.getBackNeurons().contains(this)) {
			incoming.backwardSynapse(this);
		}
	}

	@Override
	public final void backwardSynapse(BackPropNeuron neuron) {
		backNeurons.add(neuron);
	}

}
