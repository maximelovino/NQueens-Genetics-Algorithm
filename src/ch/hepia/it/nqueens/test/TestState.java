package ch.hepia.it.nqueens.test;

import ch.hepia.it.nqueens.game.State;

public class TestState {
	public static void main (String[] args) {
		State st = new State(8);
		System.out.println(st);
		System.out.println(st.conflicts());

		int[] solution = {1,3,5,7,2,0,6,4};
		st.setState(solution);
		System.out.println(st);
		System.out.println(st.conflicts());
	}
}
