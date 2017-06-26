package olszewski.filip.ga.rockets.physics;

public class Rectangle2d {

	private Integer xSize;
	private Integer ySize;
	private Vector2d startPoint;
	private Vector2d endPoint;

	public Rectangle2d(Vector2d startPoint, Vector2d endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		xSize = (int) Math.abs(endPoint.getX() - startPoint.getX());
		ySize = (int) Math.abs(endPoint.getY() - startPoint.getY());
	}

	public boolean collision(Vector2d position, int margin) {
		float x = position.getX();
		float y = position.getY();
		return ((x > startPoint.getX() - margin) && (y > startPoint.getY() - margin) && (x < endPoint.getX() + margin)
				&& (y < endPoint.getY() + margin));
	}

	public Integer getxSize() {
		return xSize;
	}

	public Integer getySize() {
		return ySize;
	}

	public Vector2d getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Vector2d startPoint) {
		this.startPoint = startPoint;
	}

	public Vector2d getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Vector2d endPoint) {
		this.endPoint = endPoint;
	}

}
