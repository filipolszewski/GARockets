package olszewski.filip.ga.rockets;

public class MainWindowController {

	private SimulatorListener listener;
	private Simulator simulator;


	public void onStartSimulation(SimulationConfiguration config) {

		simulator = new Simulator(config);
		simulator.setListener(listener);
		simulator.start();
	}

	public void stopSimulation() {
		simulator.stopSimulation();
	}

	public MainWindowController(SimulatorListener listener) {
		this.listener = listener;
	}

}
