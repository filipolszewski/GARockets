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

	public void start() {
		Population population = new Population(target, panelSize, populationSize, lifespan, mutationRate);
		while (true) {

			population.setCycle(0);
			for (int i = 1; i <= lifespan; i++) {
				notifyListener(population.getGenerationData());
				population.updatePositions(i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			notifyListener(population.getGenerationData());
			population.createNewGeneration();
		}


	}

	private void notifyListener(GenerationData data) {
		if (listener != null) {
			listener.newRocketData(data);
		}
	}

}
