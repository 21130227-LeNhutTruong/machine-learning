package lab_9;

public class MinimaxAlgo {
	public void execute(Node node) {
		System.out.println("Starting Minimax algorithm...");

		int v = maxValue(node);

		System.out.println("Value at the root node: " + v);
		System.out.println("Minimax algorithm completed.");

	}

	// function MAX-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MIN_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MAX(v, MIN-VALUE(s))
	// return v
	public int maxValue(Node node) {
		if (node.isTerminal()) {
			int utilityValue = utility(node);
			System.out.println("Terminal node utility: " + utilityValue);
			return utilityValue;
		}

		int v = Integer.MIN_VALUE;
		for (Node successor : node.getSuccessors()) {
			System.out.println("Evaluating MAX successor: " + successor);
			v = Math.max(v, minValue(successor));
		}
		System.out.println("MAX node value: " + v);
		return v;

	}

	// function MIN-VALUE(state) returns a utility value
	// if TERMINAL-TEST(state) then return UTILITY(state)
	// v <- Integer.MAX_VALUE
	// for each s in SUCCESSORS(state) do
	// v <- MIN(v, MAX-VALUE(s))
	// return v
	public int minValue(Node node) {
		if (node.isTerminal()) {
			return utility(node);
		}

		int v = Integer.MAX_VALUE;
		for (Node successor : node.getSuccessors()) {
			v = Math.min(v, maxValue(successor));
		}
		return v;
	}

	private int utility(Node node) {
		// Replace this with your utility function for terminal nodes
		return 0;
	}
}
