package lab4;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> openSet = new PriorityQueue<>(new GreedyComparator(goal));
		Set<Node> closedSet = new HashSet<>();

		openSet.add(root);
		while (!openSet.isEmpty()) {
			Node current = openSet.poll();

			if (current.getLabel().equals(goal)) {
				return current; // Goal node found
			}

			closedSet.add(current);

			for (Node neighbor : current.getChildrenNodes()) {
				if (closedSet.contains(neighbor)) {
					continue; // Skip nodes in the closed set
				}

				if (!openSet.contains(neighbor)) {
					openSet.add(neighbor);
				}
			}
		}

		return null; // No path to the goal
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		return null;
	}

}
