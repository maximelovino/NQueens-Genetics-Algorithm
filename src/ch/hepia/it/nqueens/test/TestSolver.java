package ch.hepia.it.nqueens.test;

import ch.hepia.it.nqueens.core.Solver;
import ch.hepia.it.nqueens.game.State;

public class TestSolver {
	public static void main (String[] args) {
		State st = State.getRandomState(8);
		System.out.println(st);
		st.mutate();
		System.out.println(st);
		System.out.println("Genetic solving");
		Solver.geneticSolve(8);
	}
}
