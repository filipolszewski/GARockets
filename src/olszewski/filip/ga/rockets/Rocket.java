package olszewski.filip.ga.rockets;

import java.util.Random;

public class Rocket {

	private Vector2d position;
	private Vector2d velocity;
	private Vector2d acceleration;
	private Integer lifespan;
	private RocketDNA dna;
	private long fitness;
	private Vector2d panelSize;
	private boolean crushed = false;
	private Integer timeToReach;
	private boolean reachedTarget = false;

	private static final float MAX_VELOCITY = 10;

	public Rocket(Integer lifespan, Vector2d panelSize) {
		this.position = new Vector2d(panelSize.getX() / 2, 30);
		this.velocity = new Vector2d(0, 0);
		this.acceleration = new Vector2d(0, 0);
		this.lifespan = lifespan;
		this.dna = new RocketDNA(lifespan);
		this.panelSize = panelSize;
		position.setLimitForX(panelSize.getX() - 2);
		position.setLimitForY(panelSize.getY() - 2);
		velocity.setLimitForX(MAX_VELOCITY);
		velocity.setLimitForY(MAX_VELOCITY);
	}

	public void applyForce(Vector2d force) {
		this.acceleration.add(force);
	}

	public void update(Integer cycle, Vector2d target) {
		
		if (reachedTarget || crushed) {
			return;
		}

		position.add(velocity);
		velocity.add(acceleration);
		applyForce(dna.getGeneAt(cycle));
		
		if ((position.getX() < 2 || position.getX() >= panelSize.getX() - 2)
				|| (position.getY() < 2 || position.getY() >= panelSize.getY() - 2)) {

			this.crushed = true;
		}

		if ((Math.abs(position.getX() - target.getX()) < 3) && (Math.abs(position.getY() - target.getY()) < 3)) {
			this.reachedTarget = true;
			timeToReach = cycle;
		}
	}

	public void calculateFitness(Vector2d target) {
		fitness = 0;
		fitness += (int) (Math.pow(300 - (Math.abs(target.getX() - this.position.getX())), 2));
		fitness += (int) (Math.pow(542 - (Math.abs(target.getY() - this.position.getY())), 2));
		if (crushed) {
			fitness /= 10;
		}
		if (reachedTarget) {
			fitness *= 5;
			fitness += (int) Math.pow(lifespan - timeToReach, 2);
		}
		if (fitness <= 0) {
			fitness = 1;
		}
	}

	public Rocket crossoverWith(Rocket parentB) {
		Integer dnaSize = lifespan;
		Rocket child = new Rocket(dnaSize, panelSize);
		RocketDNA newDNA = new RocketDNA(dnaSize);
		Random r = new Random();
		Integer distributionPoint = r.nextInt(dnaSize);

		for (int i = 0; i < distributionPoint; i++) {
			newDNA.setGene(i, dna.getGeneAt(i));
		}
		for (int i = distributionPoint; i < dnaSize; i++) {
			newDNA.setGene(i, parentB.dna.getGeneAt(i));
		}
		child.setDna(newDNA);
		return child;
	}

	public void mutate() {
		dna.mutate();
	}

	public Vector2d getPosition() {
		return position;
	}

	public void setPosition(Vector2d position) {
		this.position = position;
	}

	public Vector2d getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2d velocity) {
		this.velocity = velocity;
	}

	public Vector2d getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(Vector2d acceleration) {
		this.acceleration = acceleration;
	}

	public Integer getLifespan() {
		return lifespan;
	}

	public void setLifespan(Integer lifespan) {
		this.lifespan = lifespan;
	}

	public RocketDNA getDna() {
		return dna;
	}

	public void setDna(RocketDNA dna) {
		this.dna = dna;
	}

	public long getFitness() {
		return fitness;
	}

	public void setFitness(long fitness) {
		this.fitness = fitness;
	}

	public boolean targetReached() {
		return reachedTarget;
	}

	public boolean crushed() {
		return crushed;
	}

}
