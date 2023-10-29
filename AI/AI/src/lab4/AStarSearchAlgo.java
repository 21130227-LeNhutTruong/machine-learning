package lab4;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		PriorityQueue<Node> openSet = new PriorityQueue<>(new AStarComparator(goal));
		Set<Node> closedSet = new HashSet<>();

		root.setG(0); // Set the initial cost to reach the root node to 0.
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

				// Calculate the cost to reach the neighbor from the current node.
				double tentativeG = current.getG() + current.getEdgeCost(neighbor, current);

				if (!openSet.contains(neighbor) || tentativeG < neighbor.getG()) {
					neighbor.setParent(current);
					neighbor.setG(tentativeG);
					if (!openSet.contains(neighbor)) {
						openSet.add(neighbor);
					}
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
