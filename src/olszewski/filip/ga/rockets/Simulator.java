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
		target = new Vector2d(panelSize.getX() / 2, panelSize.getX() - 30);
	}

	public void setListener(SimulatorListener listener) {
		this.listener = listener;
	}

	public void start() {
		Population population = new Population(target, panelSize, populationSize, lifespan, mutationRate);



	}

	private void notifyListener(RocketData data) {
		if (listener != null) {
			listener.newRocketData(data);
		}
	}

}
