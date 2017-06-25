package olszewski.filip.ga.rockets;

public class MainWindowController {

	private SimulatorListener listener;

	public void startSimulation(Vector2d panelSize, Integer populationSize, Integer lifespan,
			double mutationRate) {

		Thread simThread = new Thread(new Runnable() {

			@Override
			public void run() {
				Simulator simulator = new Simulator(panelSize, populationSize, lifespan, mutationRate);
				simulator.setListener(listener);
				simulator.start();
			}
		});
		simThread.start();
	}

	public MainWindowController(SimulatorListener listener) {
		this.listener = listener;
	}

}
