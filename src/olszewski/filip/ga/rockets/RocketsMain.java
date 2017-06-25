package olszewski.filip.ga.rockets;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * 
 * @author Filip Olszewski
 *
 */
public class RocketsMain {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				RocketWindow mainWindow = new RocketWindow();
				mainWindow.start();
			}
		});
	}

}
