package ch.hepia.it.nqueens.test;

import ch.hepia.it.nqueens.core.Solver;
import ch.hepia.it.nqueens.game.State;

public class TestSolver {
	public static void main (String[] args) {
		State a = State.getRandomState(8);
		State b = State.getRandomState(8);
		System.out.println(a+" + " + b + " => " + Solver.cross(a,b));
	}
}
