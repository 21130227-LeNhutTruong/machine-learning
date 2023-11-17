package Lab_7;

public class Test {
	public static void main(String[] args) {
		GA_NQueenAlgo geneticAlgorithm = new GA_NQueenAlgo();

		// Kiểm thử phương thức execute
		Node solution = geneticAlgorithm.execute();
		System.out.println("Solution found: ");
		solution.displayBoard();


	}
}
