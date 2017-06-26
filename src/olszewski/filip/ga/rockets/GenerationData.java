package olszewski.filip.ga.rockets;

import java.util.List;

import olszewski.filip.ga.rockets.physics.Rectangle2d;
import olszewski.filip.ga.rockets.physics.Vector2d;

public class GenerationData {

	List<Rocket> rockets;
	Integer generation;
	Integer lifecycle;
	Integer populationSize;
	double mutationRate;
	Integer successCount;
	Vector2d target;
	boolean allFinished;
	List<Rectangle2d> obstacles;

	public GenerationData(List<Rocket> rockets, Integer generation, Integer lifecycle, Integer populationSize,
			double mutationRate, Integer successCount, Vector2d target, List<Rectangle2d> obstacles,
			boolean allFinished) {
		this.rockets = rockets;
		this.generation = generation;
		this.lifecycle = lifecycle;
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.successCount = successCount;
		this.target = target;
		this.obstacles = obstacles;
		this.allFinished = allFinished;
	}

}
