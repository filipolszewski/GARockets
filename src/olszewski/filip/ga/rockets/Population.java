package olszewski.filip.ga.rockets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Population {

	// These are the parameters to play with.
	private Integer size;
	private Integer lifespan;
	private double mutationRate;
	private Vector2d target;
	private Vector2d panelSize;

	private Integer generation;
	private List<Rocket> population = new ArrayList<>();

	public Population(Vector2d target, Vector2d panelSize, Integer size, Integer lifespan, double mutationRate) {
		this.target = target;
		this.panelSize = panelSize;
		this.size = size;
		this.lifespan = lifespan;
		this.mutationRate = mutationRate;
		generation = 0;
		createRandomPopulation(size);
	}

	private void createRandomPopulation(Integer size) {
		for (int i = 0; i < size; i++) {
			getPopulation().add(new Rocket(lifespan, panelSize));
		}
	}

	/**
	 * Calculates new positions and new speeds of rockets, then applies new
	 * forces - updates the state of objects after one cycle.
	 * 
	 */
	public void updatePositions(Integer cycle) {
		for (int i = 0; i < size; i++) {
			population.get(i).update(cycle, target);
		}
	}

	/**
	 * Performs a cycle of natural selection, creating a new population out of
	 * the current one, based on the fitness values of elements.
	 */
	public void createNewGeneration() {
		calculateFitness();

		List<Rocket> matingPool = generateMatingPool();

		population = generateNewPopulation(matingPool);
		generation++;
	}

	private void calculateFitness() {
		for (int i = 0; i < size; i++) {
			getPopulation().get(i).calculateFitness(target);
		}
	}

	private List<Rocket> generateMatingPool() {
		List<Rocket> matingPool = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			matingPool.add(pickRocket());
		}
		return matingPool;
	}

	private Rocket pickRocket() {
		Integer maxFitness = calculateFitnessSum();
		Random r = new Random();
		Integer point = r.nextInt(maxFitness);
		for (int i = 0; i < size; i++) {
			Rocket rocket = getPopulation().get(i);
			point += rocket.getFitness();
			if (point >= maxFitness) {
				return rocket;
			}
		}
		System.out.println("Error - No rocket picked! - implementation error..");
		return null;
	}

	private List<Rocket> generateNewPopulation(List<Rocket> matingPool) {
		List<Rocket> newPopulation = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			Rocket parentA = matingPool.get(r.nextInt(size));
			Rocket parentB = matingPool.get(r.nextInt(size));
			Rocket child = parentA.crossoverWith(parentB);
			if (r.nextFloat() < mutationRate) {
				child.mutate();
			}
			newPopulation.add(child);
		}
		return newPopulation;
	}

	private Integer calculateFitnessSum() {
		Integer fitnessSum = 0;
		for (int i = 0; i < size; i++) {
			fitnessSum += getPopulation().get(i).getFitness();
		}
		return fitnessSum;
	}

	public List<Rocket> getPopulation() {
		return population;
	}

	public Integer getGeneration() {
		return generation;
	}

}
