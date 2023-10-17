package Lab2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class UniformCostSearchAlgo implements ISearchAlgo {

	private static class NodeComparator implements Comparator<Node> {
		@Override
		public int compare(Node node1, Node node2) {
			return Double.compare(node1.getPathCost(), node2.getPathCost());

		}
	}

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparator());
		Set<String> explored = new HashSet<>();
		root.setPathCost(0);
		frontier.add(root);

		while (!frontier.isEmpty()) {
			Node current = frontier.poll();

			if (current.getLabel().equals(goal)) {
				return current; // Goal found
			}

			explored.add(current.getLabel());

			for (Edge edge : current.getChildren()) {
				Node child = edge.getEnd();
				double newPathCost = current.getPathCost() + edge.getWeight();

				if (!explored.contains(child.getLabel()) && !frontier.contains(child)) {
					child.setParent(current);
					child.setPathCost(newPathCost);
					frontier.add(child);
				} else if (frontier.contains(child) && newPathCost < child.getPathCost()) {
					child.setParent(current);
					child.setPathCost(newPathCost);

					frontier.remove();
					frontier.add(child);

				}
			}
		}

		return null;
	}

	@Override
	public Node execute(Node root, String startLabel, String goalLabel) {
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new NodeComparator());
		Set<String> explored = new HashSet();

		// Tìm nút bắt đầu bằng cách kiểm tra label
		Node startNode = null;
		for (Node node : priorityQueue) {
			if (node.getLabel().equals(startLabel)) {
				startNode = node;
				break;
			}
		}

		if (startNode == null) {
			return null; // Không tìm thấy nút bắt đầu
		}

		startNode.setPathCost(0);
		priorityQueue.add(startNode);

		while (!priorityQueue.isEmpty()) {
			Node current = priorityQueue.poll();

			if (current.getLabel().equals(goalLabel)) {
				return current; // Tìm thấy nút mục tiêu
			}

			explored.add(current.getLabel());

			for (Edge edge : current.getChildren()) {
				Node child = edge.getEnd();
				double newPathCost = current.getPathCost() + edge.getWeight();

				if (!explored.contains(child.getLabel()) && !priorityQueue.contains(child)) {
					child.setParent(current);
					child.setPathCost(newPathCost);
					priorityQueue.add(child);
				} else if (priorityQueue.contains(child) && newPathCost < child.getPathCost()) {
					// Nếu tìm thấy một đường đi ngắn hơn, cập nhật chi phí đường đi của con
					child.setParent(current);
					child.setPathCost(newPathCost);

					// Cập nhật hàng đợi ưu tiên bằng cách loại bỏ và thêm lại con
					priorityQueue.remove(child);
					priorityQueue.add(child);
				}
			}
		}

		return null; // Nút mục tiêu không được tìm thấy
	}

	public static Node BreadthFirstSearchAlgo(Node root, String goalLabel) {
		Queue<Node> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();

		queue.add(root);
		visited.add(root.getLabel());

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.getLabel().equals(goalLabel)) {
				return current; // Goal found
			}

			for (Node child : current.getChildrenNodes()) {
				if (!visited.contains(child.getLabel())) {
					child.setParent(current);
					queue.add(child);
					visited.add(child.getLabel());
				}
			}
		}
		return null; // Goal not found
	}

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

	public static List<String> printPath(Node node) {
		if (node != null) {
			List<String> result = new ArrayList<String>();
			result.add(node.getLabel());
			Node tmp;
			while ((tmp = node.getParent()) != null) {

				result.add(tmp.getLabel());
				node = tmp;
			}
			Collections.reverse(result);
			return result;
		} else
			return new ArrayList<String>();
	}

	public static void main(String[] args) {
		Node nodeS = new Node("S");
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		Node nodeF = new Node("F");
		Node nodeG = new Node("G");
		Node nodeH = new Node("H");
		nodeS.addEdge(nodeA, 5);
		nodeS.addEdge(nodeB, 2);
		nodeS.addEdge(nodeC, 4);
		nodeA.addEdge(nodeD, 9);
		nodeA.addEdge(nodeE, 4);
		nodeB.addEdge(nodeG, 6);
		nodeC.addEdge(nodeF, 2);
		nodeD.addEdge(nodeH, 7);
		nodeE.addEdge(nodeG, 6);
		nodeF.addEdge(nodeG, 1);
		ISearchAlgo algo1 = new BFS();
		ISearchAlgo algo2 = new DFS();

		Node result = algo1.execute(nodeS, "G");
		Node result2 = algo2.execute(nodeS, "G");

		Node result3 = algo1.execute(nodeS, "S", "G");
		Node result4 = algo2.execute(nodeS, "S", "G");
	}

}
