package Lab_5;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearchAlgo implements IPuzzleAlgo {

	@Override
	public Node execute(Puzzle model) {
		Node initialState = model.getInitialState();
		Node goalState = model.getGoalState();

		// Sử dụng PriorityQueue để duyệt các trạng thái theo giá trị h(n) + g(n)
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>(PuzzleUtils.HeuristicComparatorByF);

		initialState.setH(model.computeH2(initialState));
		initialState.setG(0); // Khởi tạo số bước g(n) ban đầu là 0
		priorityQueue.add(initialState);

		Set<Node> visited = new HashSet<>();

		while (!priorityQueue.isEmpty()) {
			Node current = priorityQueue.poll();

			if (current.equals(goalState)) {
				return current; // Trạng thái giải pháp đã được tìm thấy
			}

			visited.add(current);

			List<Node> successors = model.getSuccessors(current);

			for (Node successor : successors) {
				if (!visited.contains(successor)) {
					successor.setG(current.getG() + 1); // Cập nhật số bước g(n)
					successor.setH(model.computeH2(successor));
					priorityQueue.add(successor);
				}
			}
		}

		return null; // Không tìm thấy giải pháp
	}

}
