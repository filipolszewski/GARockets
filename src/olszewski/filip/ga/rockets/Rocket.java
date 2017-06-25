package olszewski.filip.ga.rockets;

public class Rocket {

	private Vector2d position;
	private Vector2d velocity;
	private Vector2d acceleration;
	private Integer lifespan;
	private RocketDNA dna;
	private Integer fitness;

	public Rocket(Integer lifespan) {
		this.position = new Vector2d(0, 0);
		this.velocity = new Vector2d(0, 0);
		this.acceleration = new Vector2d(0, 0);
		this.lifespan = lifespan;
		this.dna = new RocketDNA(lifespan);
	}

	public void applyForce(Vector2d force) {
		this.acceleration.add(force);
	}

	public void update() {

	}

	public void calculateFitness(Vector2d targetPosition) {
		fitness = 0;
		fitness += (int) (Math.pow((targetPosition.getX() - this.position.getX()), 2));
		fitness += (int) (Math.pow((targetPosition.getY() - this.position.getY()), 2));
	}

	public Rocket crossoverWith(Rocket parentB) {
		// TODO Auto-generated method stub
		return null;
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


}
