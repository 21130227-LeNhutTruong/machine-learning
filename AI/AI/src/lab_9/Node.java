package lab_9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
	private List<Integer> data = new ArrayList<Integer>();

	public void add(Integer val) {
		this.data.add(val);
	}

	public void addAll(List<Integer> data) {
		this.data.addAll(data);
	}

	// Get children of the current nodes
	public List<Node> getSuccessors() {
		List<Node> re = new ArrayList<>();
		data.sort(DESCOMPARATOR);
		for (int i = 0; i < data.size(); i++) {
			int a = data.get(i);
			for (int j = 1; j < a/2; j++) {
				if(j != (a-i)) {
					Node n = new Node();
					n.add(j);
					n.add(a-j);
					for (int k = 0; k < data.size(); k++) {
						if(k!=i) {
							n.add(data.get(k));
							if(!re.contains(n)) {
								re.add(n);
							}
						}
					}
				}
			}
		}
	}

	// Check whether a node is terminal or not
	public boolean isTerminal() {
		// A node is terminal if the tokens of each pile cannot be divided
		for (int tokens : data) {
			if (tokens % 2 != 0) {
				return true; // If any pile cannot be divided, the node is terminal
			}
		}
		return false;

	}

	public static final Comparator<Integer> DESCOMPARATOR = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};

	@Override
	public String toString() {
		Collections.sort(this.data, DESCOMPARATOR);
		return this.data.toString();
	}

}
