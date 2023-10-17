package Lab2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class DFS implements ISearchAlgo {

	// Depth-First Search
	public static Node depthFirstSearch(Node node, String goalLabel) {
		Set<String> visited = new HashSet<>();
		return depthFirstSearchRecursive(node, goalLabel, visited);
	}

	private static Node depthFirstSearchRecursive(Node current, String goalLabel, Set<String> visited) {
		if (current.getLabel().equals(goalLabel)) {
			return current; // Goal found
		}

		visited.add(current.getLabel());

		for (Node child : current.getChildrenNodes()) {
			if (!visited.contains(child.getLabel())) {
				child.setParent(current);
				Node result = depthFirstSearchRecursive(child, goalLabel, visited);
				if (result != null) {
					return result;
				}
			}
		}

		return null; // Goal not found
	}

	@Override
	public Node execute(Node root, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);
		List<Node> explored = new ArrayList<Node>();
		while (!frontier.isEmpty()) {
			Node current = frontier.remove();
			explored.add(current);
			System.out.print(current.getLabel() + " ");
			if (current.getLabel().equals(goal)) {
				return current;
			}

			List<Node> children = current.getChildrenNodes();
			for (int i = 0; i < children.size(); i++) {
				Node n = children.get(i);
				if (!explored.contains(n) && !frontier.contains(n)) {
					frontier.add(n);
					n.setParent(current);
				}
			}

		}
		System.out.println();
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {

		Queue<Node> frontier = new LinkedList<Node>();
		frontier.add(root);
		List<Node> explored = new ArrayList<Node>();
		boolean started = false;
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			explored.add(current);
			System.out.print(current.getLabel() + "\t");
			if (current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				explored.clear();

			} else if (current.getLabel().equals(goal) && started) {
				return current;
			}

			List<Node> children = current.getChildrenNodes();
			for (int i = 0; i < children.size(); i++) {
				Node n = children.get(i);
				if (!explored.contains(n) && !frontier.contains(n)) {
					frontier.add(n);
					if (started)
						n.setParent(current);
				}
			}
		}
		
		

		System.out.println();
		return null;
	}

}
