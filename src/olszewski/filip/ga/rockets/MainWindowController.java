package olszewski.filip.ga.rockets;

public class MainWindowController {

	private SimulatorListener listener;
	private boolean simulationRunning = false;
	private Thread simThread;

	/**
	 * Makes new thread, which starts the Simulator object.
	 * 
	 */
	public void onStartSimulation(SimulationConfiguration config) {

		if (!simulationRunning) {
			simThread = new Thread(new Runnable() {

				@Override
				public void run() {
					Simulator simulator = new Simulator(config);
					simulator.setListener(listener);
					simulator.start();
				}
			});
			simThread.start();
			simulationRunning = true;
		}
	}

	public void stopSimulation() {
		// TODO - stopping the simulation (some synchronized work to do here)
	}

	public MainWindowController(SimulatorListener listener) {
		this.listener = listener;
	}

}
