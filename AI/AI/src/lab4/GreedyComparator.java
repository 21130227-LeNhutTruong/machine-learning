package lab4;

import java.util.Comparator;

public class GreedyComparator implements Comparator<Node> {
    private String goal;

    public GreedyComparator(String goal) {
        this.goal = goal;
    }

    @Override
    public int compare(Node node1, Node node2) {
        double h1 = node1.getH(goal);
        double h2 = node2.getH(goal);

        // So sánh theo giá trị heuristic. Ưu tiên giá trị heuristic thấp hơn.
        if (h1 < h2) {
            return -1;
        } else if (h1 > h2) {
            return 1;
        } else {
            return 0;
        }
    }
}
