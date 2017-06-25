package olszewski.filip.ga.rockets;

import java.util.List;

public class GenerationData {

	List<Rocket> rockets;
	Integer generation;
	Integer lifecycle;
	Integer populationSize;
	double mutationRate;
	Integer successCount;

	public GenerationData(List<Rocket> rockets, Integer generation, Integer lifecycle, Integer populationSize,
			double mutationRate, Integer successCount) {
		this.rockets = rockets;
		this.generation = generation;
		this.lifecycle = lifecycle;
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.successCount = successCount;
	}

}
