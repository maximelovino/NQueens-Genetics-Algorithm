package ch.hepia.it.nqueens.test;

import ch.hepia.it.nqueens.core.Solver;
import ch.hepia.it.nqueens.game.State;

public class TestState {
	public static void main (String[] args) {
		System.out.println("computing");
		State solution = Solver.bruteForceSolve(8);
		if (solution != null){
			System.out.println(solution);
			System.out.println(solution.conflicts());
		}
	}
}
