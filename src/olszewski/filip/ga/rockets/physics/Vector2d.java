package olszewski.filip.ga.rockets.physics;

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

	public static Vector2d createRandom(float genelimitX, float genelimitY) {
		Random r = new Random();
		return new Vector2d(r.nextFloat() * genelimitX, r.nextFloat() * genelimitY).mul(2)
				.substract(new Vector2d(genelimitX, genelimitY));
	}

	public Vector2d add(Vector2d v2) {
		float newX = this.x + v2.getX();
		float newY = this.y + v2.getY();
		setX(newX);
		setY(newY);
		return this;
	}

	public Vector2d substract(Vector2d v2) {
		float newX = this.x - v2.getX();
		float newY = this.y - v2.getY();
		setX(newX);
		setY(newY);
		return this;
	}

	public Vector2d mul(float mul) {
		setX(this.x * mul);
		setY(this.y * mul);
		return this;
	}

	public float distance(Vector2d v2) {
		return (float) Math.pow(Math.pow(this.x - v2.x, 2) + Math.pow(this.y - v2.y, 2), 1.0 / 2.0);
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
		setY(this.y);
	}

	@Override
	public String toString() {
		return x + " " + y;
	}

}
