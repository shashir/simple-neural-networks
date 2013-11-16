import java.util.*;

public class Driver {
	public static void main(String[] args) {
		
		List<BackPropInput> inputLayer = new ArrayList<BackPropInput>();
		List<HiddenBackPropNeuron> hiddenLayer = new ArrayList<HiddenBackPropNeuron>();
		OutputBackPropNeuron outputNeuron = new OutputBackPropNeuron();

		Random generator = new Random();

		// Bias is 0th neuron in inputLayer
		for (int i = 0; i <= 4; i++) {
			inputLayer.add(new BackPropInput());
		}

		// Attach bias to outputNeuron
		outputNeuron.incomingSynapse(inputLayer.get(0),
				(generator.nextDouble() * 2.0) - 1);

		// Attach all input neurons to the hiddenLayer neurons
		for (int i = 0; i < 4; i++) {
			hiddenLayer.add(new HiddenBackPropNeuron());
			for (int j = 0; j <= 4; j++) {
				hiddenLayer.get(i).incomingSynapse(inputLayer.get(j),
						(generator.nextDouble() * 2.0) - 1);
			}
			outputNeuron.incomingSynapse(hiddenLayer.get(i),
					(generator.nextDouble() * 2.0) - 1);
		}
		
		
		
		ArrayList<ArrayList<Double>> inputList = new ArrayList<ArrayList<Double>>();

		// Set up list of inputs
		for (int i = 0; i < 16; i++) {
			ArrayList<Double> point = new ArrayList<Double>();
			point.add(1.0);
			point.add((i >= 8) ? 1.0 : 0.0);
			point.add((i / 4 % 2 == 1) ? 1.0 : 0.0);
			point.add((i / 2 % 2 == 1) ? 1.0 : 0.0);
			point.add((i % 2 == 1) ? 1.0 : 0.0);
			point
					.add(((i / 4 % 2 == 1) ^ (i >= 8) ^ (i / 2 % 2 == 1) ^ (i % 2 == 1)) ? 1.0
							: 0.0);
			// System.out.println(point);
			inputList.add(point);
		}
		
		
		
		boolean pass = false;
		int epochs = 0;
		double exitError = 0.05;
		double eta = 0.50;
		inputLayer.get(0).set(1.0);
		double alpha = 0.9;

		while (pass == false) {

			// Permute the list of inputs
			for (int i = 0; i < 16; i++) {
				Collections.swap(inputList, i, i + generator.nextInt(16 - i));
			}

			pass = true;
			for (int i = 0; i < 16; i++) {
				// Upload input values into input neurons
				inputLayer.get(1).set(inputList.get(i).get(1));
				inputLayer.get(2).set(inputList.get(i).get(2));
				inputLayer.get(3).set(inputList.get(i).get(3));
				inputLayer.get(4).set(inputList.get(i).get(4));

				if (Math.abs(outputNeuron.output() - inputList.get(i).get(5)) <= exitError) {
					if (epochs % 10000 == 0 && i == 1) {
						System.out.println("Epoch "
								+ (epochs + 1)
								+ ": Input "
								+ inputLayer
								+ " passed with output "
								+ outputNeuron.output()
								+ " where desired was "
								+ inputList.get(i).get(5)
								+ " and error was "
								+ Math.abs(outputNeuron.output()
										- inputList.get(i).get(5)));
					}
				} else {
					pass = false;
					if (epochs % 10000 == 0 && i == 1) {
						System.out.println("Epoch "
								+ (epochs + 1)
								+ ": Input "
								+ inputLayer
								+ " failed with output "
								+ outputNeuron.output()
								+ " where desired was "
								+ inputList.get(i).get(5)
								+ " and error was "
								+ Math.abs(outputNeuron.output()
										- inputList.get(i).get(5)));
					}
					
					outputNeuron.applyUpdates(eta, alpha, inputList.get(i).get(5) - outputNeuron.output());			
					for (HiddenBackPropNeuron neuron : hiddenLayer) {
						neuron.applyUpdates(eta, alpha, inputList.get(i).get(5) - outputNeuron.output());
					}
					
				}

			}
			epochs++;
		}

		System.out.println("Convergence reached in " + epochs + " epochs!");

		for (int i = 0; i < 16; i++) {
			// Upload input values into input neurons
			inputLayer.get(1).set(inputList.get(i).get(1));
			inputLayer.get(2).set(inputList.get(i).get(2));
			inputLayer.get(3).set(inputList.get(i).get(3));
			inputLayer.get(4).set(inputList.get(i).get(4));

			if (Math.abs(outputNeuron.output() - inputList.get(i).get(5)) <= exitError) {
				System.out.println("Epoch "
						+ (epochs + 1)
						+ ": Input "
						+ inputLayer
						+ " passed with output "
						+ outputNeuron.output()
						+ " where desired was "
						+ inputList.get(i).get(5)
						+ " and error was "
						+ Math.abs(outputNeuron.output()
								- inputList.get(i).get(5)));
			}

		}

		
		
		
	}

}
