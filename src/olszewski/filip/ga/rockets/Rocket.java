package olszewski.filip.ga.rockets;

import java.util.List;
import java.util.Random;

import olszewski.filip.ga.rockets.physics.Rectangle2d;
import olszewski.filip.ga.rockets.physics.Vector2d;

public class Rocket {

	private Vector2d position;
	private Vector2d velocity;
	private Vector2d acceleration;

	private Integer lifespan;
	private RocketDNA dna;
	private long fitness;
	private Vector2d panelSize;
	private boolean crushed = false;
	// private Integer timeToReach;
	private boolean reachedTarget = false;

	private float maxVelocity;
	private Integer timeToReach;

	/**
	 * 
	 * @param lifespan
	 *            time of life counted in simulation cycles. Used as a RocketDNA
	 *            size.
	 * @param panelSize
	 */
	public Rocket(Integer lifespan, Vector2d panelSize, float maxVelocity) {
		this.lifespan = lifespan;
		this.panelSize = panelSize;
		this.maxVelocity = maxVelocity;
		initRocket();
	}

	/**
	 * 
	 * Initializing the physical vectors with limiting the values, creating a
	 * random DNA.
	 */
	private void initRocket() {
		this.dna = new RocketDNA(lifespan);
		this.position = new Vector2d(panelSize.getX() / 2, 30);
		this.velocity = new Vector2d(0, 0);
		this.acceleration = new Vector2d(0, 0);

		velocity.setLimitForX(maxVelocity);
		velocity.setLimitForY(maxVelocity);

		acceleration.setLimitForX(maxVelocity);
		acceleration.setLimitForY(maxVelocity);

		position.setLimitForX(panelSize.getX() - 2);
		position.setLimitForY(panelSize.getY() - 2);
	}

	public void applyForce(Vector2d force) {
		this.acceleration.add(force);
	}

	/**
	 * Updates the physical state in next cycle and checks if it crushed or
	 * reached the target
	 * 
	 * @param cycle
	 *            cycle indicator used to determine which gene of DNA should be
	 *            used on this update
	 * @param target
	 *            target coordinates to check if rocket reached it
	 * @param obstacles
	 */
	public void update(Integer cycle, Vector2d target, List<Rectangle2d> obstacles) {
		
		if (reachedTarget || crushed) {
			return;
		}

		position.add(velocity);
		velocity.add(acceleration);
		applyForce(dna.getGeneAt(cycle));
		
		if (position.distance(target) < 5) {
			this.reachedTarget = true;
			this.timeToReach = cycle;
		}
		crushed = isCrushed(panelSize, obstacles);
	}

	private boolean isCrushed(Vector2d panelSize, List<Rectangle2d> obstacles) {
		if ((position.getX() < 2 || position.getX() >= panelSize.getX() - 2)
				|| (position.getY() < 2 || position.getY() >= panelSize.getY() - 2)) {
			return true;
		}
		for (Rectangle2d obstacle : obstacles) {
			if (obstacle.collision(position, 2)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * Calculates the fitness function for the element. Returned value depends
	 * on the closest distance to target achieved during flight, the crushed and
	 * reachedTarget flags. The shorter time in which rocket reached the target,
	 * the more fitness.
	 * 
	 * @param target
	 */
	public void calculateFitness(Vector2d target) {
		fitness = 0;
		fitness += (int) Math.pow(Math.abs(panelSize.distance(new Vector2d()) - target.distance(position)), 1);
		if (crushed) {
			fitness /= 7;
		}
		 if (reachedTarget) {
			fitness += (lifespan - timeToReach);
		 }
	}

	/**
	 * Performs crossover with another instance, randomly sharing its DNA.
	 * 
	 * @param parentB
	 * @return
	 */
	public Rocket crossoverWith(Rocket parentB) {
		Integer dnaSize = lifespan;
		Rocket child = new Rocket(dnaSize, panelSize, maxVelocity);
		RocketDNA newDNA = new RocketDNA(dnaSize);
		Random r = new Random();
		Integer distributionPoint = r.nextInt(dnaSize);

		for (int i = 0; i < distributionPoint; i++) {
			newDNA.setGene(i, dna.getGeneAt(i));
		}
		for (int i = distributionPoint; i < dnaSize; i++) {
			newDNA.setGene(i, parentB.dna.getGeneAt(i));
		}
		// for (int i = 0; i < dnaSize / 2; i++) {
		// newDNA.setGene(i, dna.getGeneAt(i));
		// newDNA.setGene(i + 1, parentB.dna.getGeneAt(i + 1));
		// }

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
