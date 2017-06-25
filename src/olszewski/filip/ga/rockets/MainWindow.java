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
		start();
	}

	public void start() {
		setVisible(true);

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
	public void newRocketData(RocketData data) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				rocketsPanel.setRocketData(data);
			}
		});
	}

}
