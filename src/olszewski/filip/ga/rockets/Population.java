package olszewski.filip.ga.rockets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Population {

	private Integer size;
	private Integer lifespan;
	private double mutationRate;
	private Vector2d target;
	private Vector2d panelSize;
	private Integer cycle;

	private Integer generation;
	private List<Rocket> population = new ArrayList<>();


	public Population(Vector2d target, Vector2d panelSize, Integer size, Integer lifespan, double mutationRate) {
		this.target = target;
		this.panelSize = panelSize;
		this.size = size;
		this.lifespan = lifespan;
		this.mutationRate = mutationRate;
		generation = 1;
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
		this.cycle = cycle;
		for (int i = 0; i < size; i++) {
			population.get(i).update(cycle - 1, target);
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
		long fitnessSum = calculateFitnessSum();
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			matingPool.add(pickRocket(fitnessSum, r));
		}
		return matingPool;
	}

	private Rocket pickRocket(long fitnessSum, Random r) {
		long point = ThreadLocalRandom.current().nextLong(fitnessSum);
		System.out.println(point);
		for (int i = 0; i < size; i++) {
			Rocket rocket = getPopulation().get(i);
			point += rocket.getFitness();
			if (point >= fitnessSum) {
				return rocket;
			}
		}
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

	private long calculateFitnessSum() {
		long fitnessSum = 0;
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

	public GenerationData getGenerationData() {
		return new GenerationData(population, generation, cycle, size, mutationRate, calculatesuccessCount(), target,
				allFinished());
	}

	private boolean allFinished() {
		for (int i = 0; i < size; i++) {
			Rocket rocket = getPopulation().get(i);
			boolean finished = rocket.crushed() || rocket.targetReached();
			if (!finished) {
				return false;
			}
		}
		return true;
	}

	private Integer calculatesuccessCount() {
		Integer count = 0;
		for (int i = 0; i < size; i++) {
			if (getPopulation().get(i).targetReached()) {
				count++;
			}
		}
		return count;
	}

	public void setCycle(int i) {
		this.cycle = i;
	}

}
