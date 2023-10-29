package lab4;

import java.util.Comparator;

public class AStarComparator implements Comparator<Node> {

	  private String goal;

	    public AStarComparator(String goal) {
	        this.goal = goal;
	    }

	    @Override
	    public int compare(Node node1, Node node2) {
	        double f1 = node1.getG() + node1.getH(goal);
	        double f2 = node2.getG() + node2.getH(goal);

	        // So sánh theo giá trị tổng f (g + h). Ưu tiên giá trị tổng f thấp hơn.
	        if (f1 < f2) {
	            return -1;
	        } else if (f1 > f2) {
	            return 1;
	        } else {
	            return 0;
	        }
	    }

}
