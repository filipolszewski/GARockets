package olszewski.filip.ga.rockets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RocketPanel extends JPanel {

	private Graphics2D g2;
	private int xSize;
	private int ySize;
	private Vector2d target;
	private GenerationData rocketData;

	public RocketPanel(Vector2d target) {
		this.target = target;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		xSize = this.getWidth();
		ySize = this.getHeight();

		if (target != null) {
			drawTarget();
		}
		if (rocketData != null) {
			drawRockets();
		}
	}

	private void drawRockets() {
		// TODO Auto-generated method stub

	}

	private void drawTarget() {
		// TODO Auto-generated method stub

	}

	public void setRocketData(GenerationData data) {
		// TODO Auto-generated method stub

	}

}
