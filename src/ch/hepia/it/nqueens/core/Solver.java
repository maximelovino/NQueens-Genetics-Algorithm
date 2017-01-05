package ch.hepia.it.nqueens.core;

import ch.hepia.it.nqueens.game.State;

import java.util.*;

public class Solver {
	private static final int MAX_GENERATIONS = 5000;
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
		Random rnd = new Random();
		ArrayList<State> population = generateInitialPopulation(size, 1000);
		int gens = 1;

		while (gens<MAX_GENERATIONS){
			// This is how you sort the states according to their conflicts
			Collections.sort(population, Comparator.comparing(o -> o.conflicts()));
			if (population.get(0).conflicts() == 0){
				return population.get(0);
			}

			ArrayList<State> futureParents = new ArrayList<>(population.subList(0,(int)(0.5*population.size())));
			ArrayList<State> nextPop = new ArrayList<>();
			for (int i = 0; i < futureParents.size(); i++) {
				for (int j = i+1; j < futureParents.size() ; j++) {
					nextPop.addAll(cross(futureParents.get(i),futureParents.get(j)));
				}
			}
			for (int i = 0; i < nextPop.size(); i++) {
				if (rnd.nextBoolean()) nextPop.get(i).mutate();
			}
			population = nextPop;
			gens++;
		}

		return null;
	}

	private static ArrayList<State> generateInitialPopulation (int size, int cnt) {
		ArrayList<State> toReturn = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			toReturn.add(State.getRandomState(size));
		}
		return toReturn;
	}

	public static ArrayList<State> cross (State a, State b) {
		Random rnd = new Random();
		ArrayList<Integer> range1 = new ArrayList<>();

		for (int i = 0; i < a.getSize(); i++) range1.add(i);
		ArrayList<Integer> range2 = new ArrayList<>(range1);

		State c1 = new State(a.getSize());
		State c2 = new State(a.getSize());
		int[] c1State = new int[a.getSize()];
		int[] c2State = new int[a.getSize()];

		int start = rnd.nextInt(a.getSize());
		int cnt = rnd.nextInt(a.getSize()-start);

		for (int i = start; i < start+cnt; i++) {
			int val1 = a.get(i);
			int val2 = b.get(i);
			c1State[i] = val1;
			c2State[i] = val2;
			range1.remove(Integer.valueOf(val1));
			range2.remove(Integer.valueOf(val2));
		}
		int tempIdx1 = start+cnt;
		int tempIdx2 = tempIdx1;
		for (int i = 0; i < b.getSize(); i++) {
			int temp1 = b.get(i);
			int temp2 = a.get(i);
			if (range1.contains(temp1)){
				c1State[tempIdx1] = temp1;
				range1.remove(Integer.valueOf(temp1));
				tempIdx1++;
				tempIdx1 %= a.getSize();
			}
			if (range2.contains(temp2)){
				c2State[tempIdx2] = temp2;
				range2.remove(Integer.valueOf(temp2));
				tempIdx2++;
				tempIdx2 %= a.getSize();
			}
		}
		c1.setState(c1State);
		c2.setState(c2State);
		ArrayList<State> toReturn = new ArrayList<>();
		toReturn.add(c1);
		toReturn.add(c2);
		return toReturn;
	}
}
