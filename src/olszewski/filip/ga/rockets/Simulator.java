package olszewski.filip.ga.rockets;

public class Simulator {

	private Integer populationSize;
	private Integer lifespan;
	private double mutationRate;
	private Vector2d panelSize;
	private Vector2d target;

	private SimulatorListener listener;

	public Simulator(Vector2d panelSize, Integer populationSize, Integer lifespan, double mutationRate) {
		this.populationSize = populationSize;
		this.lifespan = lifespan;
		this.mutationRate = mutationRate;
		this.panelSize = panelSize;
		calculateTarget();
	}

	/**
	 * Calculates the target position based on RocketPanel's size.
	 * 
	 * Editable!
	 * 
	 */
	private void calculateTarget() {
		target = new Vector2d(panelSize.getX() / 2, panelSize.getY() - 30);
		System.out.println(target);
	}

	public void setListener(SimulatorListener listener) {
		this.listener = listener;
	}

	/**
	 * The most important method performing the whole simulation - the main loop
	 * iterating with next generations, and inside loop iterating over next
	 * cycles of rocket's flights.
	 */
	public void start() {
		Population population = new Population(target, panelSize, populationSize, lifespan, mutationRate);
		while (true) {

			population.setCycle(0);
			for (int i = 1; i <= lifespan; i++) {
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

	private void notifyListener(GenerationData data) {
		if (listener != null) {
			listener.newRocketData(data);
		}
	}

}
