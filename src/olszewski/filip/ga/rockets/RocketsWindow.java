package olszewski.filip.ga.rockets;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class RocketsWindow extends JFrame {

	public RocketsWindow() {
		createController();
		configureWindow();
		createComponents();
	}

	private void createController() {
		// TODO Auto-generated method stub

	}

	private void configureWindow() {
		setTitle("Genetic Algorithm - Smart Rockets");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createComponents() {
		// TODO Auto-generated method stub

	}

	public void start() {
		setVisible(true);
	}

}
