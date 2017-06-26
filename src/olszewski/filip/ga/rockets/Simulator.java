package olszewski.filip.ga.rockets;

public class Simulator {

	private SimulationConfiguration config;

	private SimulatorListener listener;

	public Simulator(SimulationConfiguration config) {
		config.target = calculateTarget(config.panelSize);
		this.config = config;
	}

	/**
	 * Calculates the target position based on RocketPanel's size.
	 * 
	 * Editable!
	 * 
	 */
	private Vector2d calculateTarget(Vector2d panelSize) {
		return new Vector2d(panelSize.getX() / 2, panelSize.getY() - 30);
	}


	/**
	 * The most important method performing the whole simulation - the main loop
	 * iterating with next generations, and inside loop iterating over next
	 * cycles of rocket's flights.
	 */
	public void start() {
		Population population = new Population(config);

		while (true) {
			population.setCycle(0);

			for (int i = 1; i <= config.lifespan; i++) {
				GenerationData data = population.getGenerationData();
				if (data.allFinished) {
					break;
				}
				notifyListener(data);
				population.updatePositions(i);
				delaySimulation(50);
			}
			delaySimulation(1000);
			notifyListener(population.getGenerationData());
			population.createNewGeneration();
		}


	}

	private void delaySimulation(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setListener(SimulatorListener listener) {
		this.listener = listener;
	}

	private void notifyListener(GenerationData data) {
		if (listener != null) {
			listener.newRocketData(data);
		}
	}

}
