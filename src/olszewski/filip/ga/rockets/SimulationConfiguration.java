package olszewski.filip.ga.rockets;

public class SimulationConfiguration {

	Vector2d target;
	Vector2d panelSize;
	Integer populationSize;
	Integer lifespan;
	double mutationRate;
	float rocketMaxVelocity;

	public SimulationConfiguration(Vector2d target, Vector2d panelSize, Integer populationSize, Integer lifespan,
			double mutationRate,
			float rocketMaxVelocity) {
		super();
		this.target = target;
		this.panelSize = panelSize;
		this.populationSize = populationSize;
		this.lifespan = lifespan;
		this.mutationRate = mutationRate;
		this.rocketMaxVelocity = rocketMaxVelocity;
	}

}
