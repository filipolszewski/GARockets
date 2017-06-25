package olszewski.filip.ga.rockets;

import java.util.Random;

public class Rocket {

	private Vector2d position;
	private Vector2d velocity;
	private Vector2d acceleration;
	private Integer lifespan;
	private RocketDNA dna;
	private Integer fitness;
	private Vector2d panelSize;
	private boolean crushed = false;
	private boolean reachedTarget = false;

	private static final float MAX_VELOCITY = 25;

	public Rocket(Integer lifespan, Vector2d panelSize) {
		this.position = new Vector2d(panelSize.getX() / 2, 3);
		this.velocity = new Vector2d(0, 0);
		this.acceleration = new Vector2d(0, 0);
		this.lifespan = lifespan;
		this.dna = new RocketDNA(lifespan);
		this.panelSize = panelSize;
		position.setLimitForX(panelSize.getX());
		position.setLimitForY(panelSize.getY());
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
		
		if ((position.getX() < 0 || position.getX() >= panelSize.getX())
				|| (position.getY() < 0 || position.getY() >= panelSize.getY())) {
			this.crushed = true;
		}

		if ((Math.abs(position.getX() - target.getX()) < 2) && (Math.abs(position.getY() - target.getY()) < 2)) {
			this.reachedTarget = true;
		}
	}

	public void calculateFitness(Vector2d targetPosition) {
		fitness = 0;
		fitness += (int) (Math.pow(300 - (targetPosition.getX() - this.position.getX()), 2));
		fitness += (int) (Math.pow(550 - (targetPosition.getY() - this.position.getY()), 2));
		if (crushed) {
			fitness /= 10;
		}
		if (reachedTarget) {
			fitness *= 10;
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

	public Integer getFitness() {
		return fitness;
	}

	public void setFitness(Integer fitness) {
		this.fitness = fitness;
	}

	public boolean targetReached() {
		return reachedTarget;
	}

	public boolean crushed() {
		return crushed;
	}

}
