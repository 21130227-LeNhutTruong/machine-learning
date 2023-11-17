package Lab_7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GA_NQueenAlgo {
	public static final int POP_SIZE = 100;// Population size
	public static final double MUTATION_RATE = 0.03;
	public static final int MAX_ITERATIONS = 1000;
	List<Node> population = new ArrayList<Node>();
	Random rd = new Random();

	// initialize the individuals of the population
	public void initPopulation() {
		for (int i = 0; i < POP_SIZE; i++) {
			Node ni = new Node();
			ni.generateBoard();
			population.add(ni);
		}
	}

	public Node execute() {
		// Initialize population
		initPopulation();
		int k = 0;
		while (k++ < MAX_ITERATIONS) {
			List<Node> newPopulation = new ArrayList<>();
			for (int i = 0; i < POP_SIZE; i++) {

				Node x = getParentByRandomSelection();
				Node y = getParentByRandomSelection();

				Node child = reproduce(x, y);
				if (rd.nextDouble() < MUTATION_RATE)
					mutate(child);
				if (child.getH() == 0)
					return child;
				newPopulation.add(child);
			}
			population = newPopulation;
		}
		Collections.sort(population);
		return population.get(0);
	}

	// Mutate an individual by selecting a random Queen and
	// move it to a random row.
	public void mutate(Node node) {
		int i = rd.nextInt(Node.N);
		int row = rd.nextInt(Node.N);

		node.setRow(i, row);
	}

	// Crossover x and y to reproduce a child
	public Node reproduce(Node x, Node y) {

		Node child = new Node();
		child.generateBoard();
		int c = rd.nextInt(Node.N);
		for (int i = 0; i < c; i++) {
			child.setRow(i, x.getRow(i));
		}
		for (int i = c + 1; i < Node.N; i++) {
			child.setRow(i, y.getRow(i));
		}

		return child;
	}

	// Select K individuals from the population at random and
	// select the best out of these to become a parent.
	public Node getParentByTournamentSelection() {

		// Kích thước của giải đấu (số lượng cá thể tham gia giải đấu)
		int kichThuocGiaiDau = 5;

		// Chọn ngẫu nhiên k cá thể từ quần thể để tham gia giải đấu
		List<Node> giaiDauCacThe = new ArrayList<>();
		for (int i = 0; i < kichThuocGiaiDau; i++) {
			int viTriNgauNhien = rd.nextInt(population.size());
			giaiDauCacThe.add(population.get(viTriNgauNhien));
		}

		// Chọn cá thể có fitness tốt nhất từ giải đấu
		Node chaMeChonDuoc = Collections.min(giaiDauCacThe);

		return chaMeChonDuoc;
	}

	// Select a random parent from the population
	public Node getParentByRandomSelection() {

		// Chọn ngẫu nhiên một cá thể từ quần thể
		int viTriNgauNhien = rd.nextInt(population.size());
		Node chaMeDuocChon = population.get(viTriNgauNhien);

		return chaMeDuocChon;
	}

}
