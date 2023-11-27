package Lab_8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlphaBetaSearchAlgo implements ISearchAlgo {
	private List<String> cutNodes = new ArrayList<>();

	// function ALPHA-BETA-SEARCH(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state, Integer.MIN_VALUE , Integer.MAX_VALUE)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		// Enter your code here
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int maxValue = maxValue(node, alpha, beta);
		System.out.println("Max-Value result: " + maxValue);

		System.out.println("Nodes cut during Alpha-Beta search: " + cutNodes);
	}

	// function MAX-VALUE(state, alpha, beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- MIN_VALUE;
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s, alpha, beta))
	// if v >= beta then return v
	// alpha <- MAX(alpha, v)
	// return v

	public int maxValue(Node node, int alpha, int beta) {
		if (terminalTest(node)) {
			return utility(node);
		}

		Collections.sort(node.getChildren(), Node.LabelComparator);

		int v = Integer.MIN_VALUE;
		for (Node child : node.getChildren()) {
			v = Math.max(v, minValue(child, alpha, beta));
			if (v >= beta) {
				cutNodes.add(child.getLabel());
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		return v;
	}
	// function MIN-VALUE(state, alpha , beta) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s, alpha , beta))
	// if v <= alpha then return v
	// beta <- MIN(beta ,v)
	// return v

	public int minValue(Node node, int alpha, int beta) {
		// Enter your code here
		if (terminalTest(node)) {
			return utility(node);
		}

		Collections.sort(node.getChildren(), Node.LabelComparator);

		int v = Integer.MAX_VALUE;
		for (Node child : node.getChildren()) {
			v = Math.min(v, maxValue(child, alpha, beta));
			if (v <= alpha) {
				cutNodes.add(child.getLabel());
				return v;
			}
			beta = Math.min(beta, v);
		}
		return v;
	}

	// Replace with your actual implementation
	private boolean terminalTest(Node node) {
		return node.isTerminal();
	}

	// Replace with your actual implementation
	private int utility(Node node) {
		return node.getValue();
	}

	public static void main(String[] args) {
		AlphaBetaSearchAlgo alphaBetaSearch = new AlphaBetaSearchAlgo();

		Node root = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D", 0);
		Node e = new Node("E");
		Node f = new Node("F");
		Node g = new Node("G", -5);
		Node h = new Node("H", 3);
		Node i = new Node("I", 8);
		Node j = new Node("J");
		Node k = new Node("K");
		Node l = new Node("L", 2);
		Node m = new Node("M");
		Node n = new Node("N", 4);
		Node o = new Node("O");
		Node p = new Node("P", 9);
		Node q = new Node("Q", -6);
		Node r = new Node("R", 0);
		Node s = new Node("S", 3);
		Node t = new Node("T", 5);
		Node u = new Node("U", -7);
		Node v = new Node("V", -9);
		Node w = new Node("W", -3);
		Node x = new Node("X", -5);

		root.addChild(b);
		root.addChild(c);
		root.addChild(d);
		root.addChild(e);
		b.addChild(f);
		b.addChild(g);
		c.addChild(h);
		c.addChild(i);
		c.addChild(j);
		e.addChild(k);
		e.addChild(l);
		e.addChild(m);
		f.addChild(n);
		f.addChild(o);
		j.addChild(p);
		j.addChild(q);
		j.addChild(r);
		k.addChild(s);
		k.addChild(t);
		m.addChild(u);
		m.addChild(v);
		o.addChild(w);
		o.addChild(x);

		alphaBetaSearch.execute(root);
	}
}
