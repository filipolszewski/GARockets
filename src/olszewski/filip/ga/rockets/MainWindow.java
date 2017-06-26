package olszewski.filip.ga.rockets;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements SimulatorListener {

	private JPanel mainPanel;
	private RocketPanel rocketsPanel;
	private MainWindowController controller;
	private JTextField popSizeField;
	private JTextField lifespanField;
	private JTextField mutationField;
	private JTextField velocityField;

	public MainWindow() {
		createController();
		configureWindow();
		createComponents();
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
		JPanel optionsPanel = new JPanel(new GridLayout(2, 5, 10, 10));
		optionsPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
		mainPanel.add(optionsPanel, BorderLayout.PAGE_START);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newRocketData(null);
				controller.onStartSimulation(getSimulationConfiguration());
			}
		});
		optionsPanel.add(startButton);

		JLabel popSizeLabel = new JLabel("Population Size: ");
		popSizeField = new JTextField();
		popSizeField.setText("300");

		JLabel lifespanLabel = new JLabel("Lifespan: ");
		lifespanField = new JTextField();
		lifespanField.setText("200");

		JLabel mutationLabel = new JLabel("Mutation Rate: ");
		mutationField = new JTextField();
		mutationField.setText("0.01");

		JLabel velocityLabel = new JLabel("Maximum Velocity: ");
		velocityField = new JTextField();
		velocityField.setText("10");

		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.stopSimulation();
			}
		});

		optionsPanel.add(startButton);
		optionsPanel.add(popSizeLabel);
		optionsPanel.add(popSizeField);
		optionsPanel.add(lifespanLabel);
		optionsPanel.add(lifespanField);
		optionsPanel.add(stopButton);
		optionsPanel.add(mutationLabel);
		optionsPanel.add(mutationField);
		optionsPanel.add(velocityLabel);
		optionsPanel.add(velocityField);
	}

	protected SimulationConfiguration getSimulationConfiguration() {
		Vector2d panelSize = rocketsPanel.getSizeAsVector2d();
		Integer populationSize = Integer.parseInt(popSizeField.getText());
		Integer lifespan = Integer.parseInt(lifespanField.getText());
		double mutationRate = Double.parseDouble(mutationField.getText());
		float maxVelocity = Float.parseFloat(velocityField.getText());
		return new SimulationConfiguration(null, panelSize, populationSize, lifespan, mutationRate, maxVelocity);
	}

	private void createRocketsPanel() {
		rocketsPanel = new RocketPanel();
		mainPanel.add(rocketsPanel, BorderLayout.CENTER);
	}

	@Override
	public void newRocketData(GenerationData data) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				rocketsPanel.setRocketData(data);
				rocketsPanel.paintComponent(rocketsPanel.getGraphics());
				repaint();
			}
		});
	}

}
