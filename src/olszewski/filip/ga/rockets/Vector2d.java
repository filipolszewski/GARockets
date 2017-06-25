package olszewski.filip.ga.rockets;

import java.util.Random;

/**
 * 2D Vector class with a configurable limit of coordinates values. Operates on
 * float values.
 * 
 * @author Filip
 *
 */
public class Vector2d {

	private float x;
	private float y;
	private float limit = Integer.MAX_VALUE;
	private float limitX = Integer.MAX_VALUE;
	private float limitY = Integer.MAX_VALUE;
	
	public Vector2d() {
		this.x = 0;
		this.y = 0;
	}

	public Vector2d(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public static Vector2d createRandom(int boundX, int boundY) {
		Random r = new Random();
		return new Vector2d(r.nextInt(boundX), r.nextInt(boundY));
	}

	public void add(Vector2d v2) {
		float newX = this.x + v2.getX();
		float newY = this.y + v2.getY();
		setX(newX);
		setY(newY);
	}

	public void substract(Vector2d v2) {
		float newX = this.x - v2.getX();
		float newY = this.y - v2.getY();
		setX(newX);
		setY(newY);

	}

	public void mul(float mul) {
		setX(this.x * mul);
		setY(this.y * mul);
	}

	public void setLimit(float limit) {
		this.limit = limit;
		setX(this.x);
		setY(this.y);
	}

	public float getX() {
		return x;
	}

	public void setX(float newX) {
		if (Math.abs(newX) > limitX) {
			this.x = limitX * (int) Math.signum(newX);
		} else {
			this.x = newX;
		}
	}

	public float getY() {
		return y;
	}

	public void setY(float newY) {
		if (Math.abs(newY) > limitY) {
			this.y = limitY * (int) Math.signum(newY);
		} else {
			this.y = newY;
		}
	}

	public void setLimitForX(float lim) {
		this.limitX = lim;
		setX(this.x);
	}

	public void setLimitForY(float lim) {
		this.limitY = lim;
		setX(this.x);
	}

}
