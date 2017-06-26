package olszewski.filip.ga.rockets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import olszewski.filip.ga.rockets.physics.Rectangle2d;
import olszewski.filip.ga.rockets.physics.Vector2d;

public class Population {

	private Integer size;
	private Integer lifespan;
	private double mutationRate;
	private Vector2d target;
	private Vector2d panelSize;
	private float rocketMaxVelocity;

	private Integer cycle;

	private Integer generation;
	private List<Rocket> population = new ArrayList<>();
	private List<Rectangle2d> obstacles;


	public Population(SimulationConfiguration config) {
		this.target = config.target;
		this.panelSize = config.panelSize;
		this.size = config.populationSize;
		this.lifespan = config.lifespan;
		this.mutationRate = config.mutationRate;
		this.rocketMaxVelocity = config.rocketMaxVelocity;
		this.obstacles = config.obstacles;
		generation = 1;
		createRandomPopulation(size);
	}

	/**
	 * Creates population of Rockets of given size with random DNA
	 * 
	 * @param size
	 *            number of Rockets in population
	 */
	private void createRandomPopulation(Integer size) {
		for (int i = 0; i < size; i++) {
			getPopulation().add(new Rocket(lifespan, panelSize, rocketMaxVelocity));
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
			population.get(i).update(cycle - 1, target, obstacles);
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

	/**
	 * 
	 * @return Mating Pool - population in which the probability of picking an
	 *         element (amount of this element instances in the pool) is
	 *         proportional to its fitness.
	 */
	private List<Rocket> generateMatingPool() {
		List<Rocket> matingPool = new ArrayList<>();
		long fitnessSum = calculateFitnessSum();
		for (int i = 0; i < size; i++) {
			matingPool.add(pickRocket(fitnessSum));
		}
		return matingPool;
	}

	/**
	 * Returns an element from population with a statistical distribution
	 * imitating the natural selection process.
	 * 
	 * There are couple of ways of programming that method. One is using an
	 * accept-reject algorithm where it picks random element, but accepts it
	 * with a chance based on its fitness.
	 * 
	 * This method is using an algorithm, which picks a random number from <0,
	 * fitnessSum> and starts adding up values(fitness values of elements) to
	 * this number until it gets higher than fitnessSum - it returns an element
	 * that was currently in the loop.
	 * 
	 * @param fitnessSum
	 *            Sum of the fitness values of all elements in population
	 * 
	 * @return element from population, where probability of returning a rocket
	 *         is proportional to its fitness
	 */
	private Rocket pickRocket(long fitnessSum) {
		long point = ThreadLocalRandom.current().nextLong(fitnessSum);
		for (int i = 0; i < size; i++) {
			Rocket rocket = getPopulation().get(i);
			point += rocket.getFitness();
			if (point >= fitnessSum) {
				return rocket;
			}
		}
		return null;
	}

	/**
	 * Generates new population from the mating pool, performing multiple
	 * crossovers and possible mutations.
	 * 
	 * @param matingPool
	 * @return new generation
	 */
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
		return new GenerationData(population, generation, cycle, size, mutationRate, calculateSuccessCount(), target,
				obstacles, allFinished());
	}

	/**
	 * Checks whether all rockets either crashed or reached the target.
	 * 
	 * @return
	 */
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

	/**
	 * Calculates how many rockets reached the target
	 * 
	 * @return
	 */
	private Integer calculateSuccessCount() {
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
