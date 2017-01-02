package ch.hepia.it.nqueens.core;

import ch.hepia.it.nqueens.game.State;

import java.util.ArrayList;
import java.util.HashSet;

public class Solver {
	public static State bruteForceSolve(int size){
		HashSet<State> visited = new HashSet<>();
		State current = new State(size);

		while(!visited.contains(current)){
			if (current.conflicts() == 0){
				return current;
			}
			current = getMinimalConflictState(current.neighbours());
		}
		return null;
	}

	private static State getMinimalConflictState(ArrayList<State> states){
		int min = Integer.MAX_VALUE;
		State best = states.get(0);
		for (State st : states) {
			int temp = st.conflicts();
			if(temp < min){
				min = temp;
				best = st;
			}
		}
		return best;
	}
}
