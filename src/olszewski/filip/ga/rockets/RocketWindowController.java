package olszewski.filip.ga.rockets;

public class RocketWindowController {

	private RocketListener listener;

	public void startSimulation(Vector2d panelSize, Integer populationSize, Integer lifespan,
			double mutationRate) {

		Thread simThread = new Thread(new Runnable() {

			@Override
			public void run() {
				RocketSimulator simulator = new RocketSimulator(panelSize, populationSize, lifespan, mutationRate);
				simulator.setListener(listener);
				simulator.start();
			}
		});
		simThread.start();
	}

	public RocketWindowController(RocketListener listener) {
		this.listener = listener;
	}

}
