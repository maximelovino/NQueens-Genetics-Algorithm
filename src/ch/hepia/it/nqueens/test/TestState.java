package ch.hepia.it.nqueens.test;

import ch.hepia.it.nqueens.core.Solver;
import ch.hepia.it.nqueens.game.State;

public class TestState {
	public static void main (String[] args) {
		System.out.println("Putting some random states");
		for (int i = 0; i < 10; i++) {
			System.out.println(State.getRandomState(8));
		}
	}
}
