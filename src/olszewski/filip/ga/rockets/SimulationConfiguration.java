package olszewski.filip.ga.rockets;

import java.util.List;

import olszewski.filip.ga.rockets.physics.Rectangle2d;
import olszewski.filip.ga.rockets.physics.Vector2d;

public class SimulationConfiguration {

	Vector2d target;
	Vector2d panelSize;
	Integer populationSize;
	Integer lifespan;
	double mutationRate;
	float rocketMaxVelocity;
	List<Rectangle2d> obstacles;

	public SimulationConfiguration(Vector2d target, Vector2d panelSize, Integer populationSize, Integer lifespan,
			double mutationRate,
			float rocketMaxVelocity, List<Rectangle2d> obstacles) {
		super();
		this.target = target;
		this.panelSize = panelSize;
		this.populationSize = populationSize;
		this.lifespan = lifespan;
		this.mutationRate = mutationRate;
		this.rocketMaxVelocity = rocketMaxVelocity;
		this.obstacles = obstacles;
	}

}
