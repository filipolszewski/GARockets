package olszewski.filip.ga.rockets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RocketDNA {

	private static final int mutatedGenesNumber = 5;
	private static final float geneLimit = (float) 0.4;
	private Integer size;
	private List<Vector2d> genes = new ArrayList<>();

	/**
	 * Constructor which initialize the genes with random vectors.
	 * 
	 * @param size
	 */
	public RocketDNA(Integer size) {
		this.size = size;
		for (int i = 0; i < size; i++) {
			genes.add(Vector2d.createRandom(geneLimit, geneLimit));
		}
	}

	/**
	 * Changes genes to new, random ones.
	 */
	public void mutate() {
		Random r = new Random();
		for (int i = 0; i < mutatedGenesNumber; i++) {
			genes.set(r.nextInt(size), Vector2d.createRandom(geneLimit, geneLimit));
		}
	}

	public Vector2d getGeneAt(Integer i) {
		return genes.get(i);
	}

	public Vector2d setGene(Integer pos, Vector2d gene) {
		return genes.set(pos, gene);
	}

	
}
