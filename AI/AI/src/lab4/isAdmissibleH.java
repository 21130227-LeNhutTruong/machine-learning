package lab4;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class isAdmissibleH {

	public boolean isAdmissibleH(Node root, String goal) {
		Set<Node> visited = new HashSet<>();
		Stack<Node> stack = new Stack<>();

		stack.push(root);

		while (!stack.isEmpty()) {
			Node current = stack.pop();

			if (visited.contains(current)) {
				continue; // Skip nodes that have already been visited.
			}

			visited.add(current);

			// Calculate the actual cost from the current node to the goal.
			double actualCost = 0;
			Node temp = current;
			while (temp != null) {
				if (temp.getLabel().equals(goal)) {
					break;
				}

				// Find the edge connecting temp to its parent node.
				for (Edge edge : temp.getParent().getChildren()) {
					if (edge.getEnd() == temp) {
						actualCost += edge.getWeight();
						break;
					}
				}

				temp = temp.getParent();
			}

			// Check if the heuristic value is greater than the actual cost.
			if (current.getH() > actualCost) {
				return false; // Heuristic does not satisfy the admissibility condition.
			}

			// Add children of the current node to the stack.
			for (Node child : current.getChildrenNodes()) {
				stack.push(child);
			}
		}

		return true; // All nodes satisfy the admissibility condition.
	}
}
