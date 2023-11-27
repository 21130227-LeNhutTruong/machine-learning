package Lab_8;

import java.util.Collections;
import java.util.List;

public class MiniMaxSearchAlgo implements ISearchAlgo {

	// function MINIMAX-DECISION(state) returns an action
	// inputs: state, current state in game
	// v <- MAX-VALUE(state)
	// return the action in SUCCESSORS(state) with value v
	@Override
	public void execute(Node node) {
		// Enter your code here
		int maxValue = maxValue(node);
		System.out.println("Max-Value : " + maxValue);
	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		// Enter your code here
		if (terminalTest(node)) {
			return utility(node);
		}
		
		   Collections.sort(node.getChildren(), Node.LabelComparator);
		int v = Integer.MIN_VALUE;
		for (Node child : node.getChildren()) {
			v = Math.max(v, minValue(child));
		}
		return v;
	}
	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v

	public int minValue(Node node) {
		if (terminalTest(node)) {
			return utility(node);
		}
		 Collections.sort(node.getChildren(), Node.LabelComparator);
		int v = Integer.MAX_VALUE;
		for (Node child : node.getChildren()) {
			v = Math.min(v, maxValue(child));
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
		MiniMaxSearchAlgo minimax = new MiniMaxSearchAlgo();

		Node root = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D",0);
		Node e = new Node("E");
		Node f = new Node("F");
		Node g = new Node("G",-5);
		Node h = new Node("H",3);
		Node i = new Node("I",8);
		Node j = new Node("J");
		Node k = new Node("K");
		Node l = new Node("L",2);
		Node m = new Node("M");
		Node n = new Node("N", 4);
		Node o = new Node("O");
		Node p = new Node("P",9);
		Node q = new Node("Q",-6);
		Node r = new Node("R",0);
		Node s = new Node("S",3);
		Node t = new Node("T",5);
		Node u = new Node("U",-7);
		Node v = new Node("V",-9);
		Node w = new Node("W",-3);
		Node x = new Node("X",-5);

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

		minimax.execute(root);
		

	}
}
