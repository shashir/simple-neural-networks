/**
 * This is a very powerful non-linear activation function with asymptotic
 * behavior akin to the step function, but still smooth.
 * 
 * @author shashir
 * 
 */
public class LogisticSigmoid implements ActivationFunction {

	@Override
	public final double evaluate(double v) {
		return 1 / (1 + Math.exp(-1 * v));
	}

	/**
	 * Calculate the derivative.
	 * 
	 * @param v
	 * 			the independent variable's value
	 * @return derivative of the logistic sigmoid at v
	 */
	public final double derivative(double v) {
		return evaluate(v) * (1 - evaluate(v));
	}

}
