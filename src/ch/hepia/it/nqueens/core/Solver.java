package ch.hepia.it.nqueens.core;

import ch.hepia.it.nqueens.game.State;

import java.util.*;

public class Solver {
	public static State bruteForceSolve (int size) {
		HashSet<State> visited = new HashSet<>();
		State current = new State(size);

		while (!visited.contains(current)) {
			if (current.conflicts() == 0) {
				return current;
			}
			visited.add(current);
			current = getMinimalConflictState(current.neighbours());
		}
		return null;
	}

	private static State getMinimalConflictState (ArrayList<State> states) {
		int min = Integer.MAX_VALUE;
		State best = states.get(0);
		for (State st : states) {
			int temp = st.conflicts();
			if (temp < min) {
				min = temp;
				best = st;
			}
		}
		return best;
	}

	public static State geneticSolve (int size) {
		ArrayList<State> initialPopulation = generateInitialPopulation(size, 10);
		System.out.println(initialPopulation);

		// This is how you sort the states according to their conflicts
		Collections.sort(initialPopulation, Comparator.comparing(o -> o.conflicts()));


		System.out.println(initialPopulation);
		return null;
	}

	private static ArrayList<State> generateInitialPopulation (int size, int cnt) {
		ArrayList<State> toReturn = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			toReturn.add(State.getRandomState(size));
		}
		return toReturn;
	}

	private static State cross (State a, State b) {
		return null;
	}
}
