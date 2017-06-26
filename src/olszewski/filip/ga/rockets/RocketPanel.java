package olszewski.filip.ga.rockets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RocketPanel extends JPanel {

	private Graphics2D g2;
	private GenerationData rocketData;

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (rocketData != null) {
			drawTarget();
			drawRockets();
			drawInfo();
		}

	}

	private void drawInfo() {
		g2.setColor(Color.BLACK);
		g2.drawString("Generation: " + rocketData.generation, 5, 40);
		g2.drawString("Cycle: " + rocketData.lifecycle, 5, 55);
		g2.drawString("Population size: " + rocketData.populationSize, 5, 70);
		g2.drawString("Successes: " + rocketData.successCount, 5, 85);
	}

	private void drawTarget() {
		g2.setColor(Color.BLACK);
		Vector2d target = rocketData.target;
		g2.fillRect((int) target.getX() - 5, (int) target.getY() - 5, 10, 10);
	}

	private void drawRockets() {
		g2.setColor(Color.RED);
		List<Rocket> rockets = rocketData.rockets;
		for (int i = 0; i < rocketData.rockets.size(); i++) {
			Rocket rocket = rockets.get(i);
			if (!rocket.crushed()) {
				drawRocket(rockets.get(i));
			}
		}
	}

	private void drawRocket(Rocket rocket) {
		Vector2d pos = rocket.getPosition();
		g2.fillRect((int) pos.getX() - 2, (int) pos.getY() - 2, 4, 4);
	}

	public void setRocketData(GenerationData data) {
		this.rocketData = data;
	}

	public Vector2d getSizeAsVector2d() {
		return new Vector2d((float) getSize().getWidth(), (float) getSize().getHeight());
	}

}
