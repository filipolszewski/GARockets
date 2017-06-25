package olszewski.filip.ga.rockets;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements SimulatorListener {

	private JPanel mainPanel;
	private RocketPanel rocketsPanel;
	private MainWindowController controller;

	public MainWindow() {
		createController();
		configureWindow();
		createComponents();
	}

	public void start() {
		setVisible(true);
		Vector2d panelSize = new Vector2d((float) rocketsPanel.getSize().getWidth(),
				(float) rocketsPanel.getSize().getHeight());
		controller.startSimulation(panelSize, 300, 200, 0.02);
	}

	private void createController() {
		controller = new MainWindowController(this);
	}

	private void configureWindow() {
		setTitle("Genetic Algorithm - Smart Rockets");
		setSize(600, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createComponents() {
		mainPanel = new JPanel(new BorderLayout(5, 5));
		createOptionsPanel();
		createRocketsPanel();
		add(mainPanel);
	}

	private void createOptionsPanel() {
		JPanel optionsPanel = new JPanel();
		mainPanel.add(optionsPanel, BorderLayout.LINE_START);
	}

	private void createRocketsPanel() {
		rocketsPanel = new RocketPanel(null);
		mainPanel.add(rocketsPanel, BorderLayout.CENTER);
	}

	@Override
	public void newRocketData(GenerationData data) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
//				rocketsPanel.setRocketData(data);
				System.out.println("Racket 10, cycle " + data.lifecycle + ", generation " + data.generation);
				System.out.println("Position: " + data.rockets.get(10).getPosition().toString());
				System.out.println("Velocity: " + data.rockets.get(10).getVelocity().toString());
				System.out.println("Acceleration: " + data.rockets.get(10).getAcceleration().toString());
				System.out.println("Reached target: " + data.rockets.get(10).targetReached());
				System.out.println("Crushed: " + data.rockets.get(10).crushed());
				System.out.println();
			}
		});
	}

}
