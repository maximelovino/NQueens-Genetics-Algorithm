package ch.hepia.it.nqueens.test;

import ch.hepia.it.nqueens.core.Solver;
import ch.hepia.it.nqueens.game.State;

public class TestSolver {
	public static void main (String[] args) {
		State st = Solver.geneticSolve(10);
		if (st != null){
			System.out.println("The solution is: "+ st);
		}else{
			System.out.println("You suck mate");
		}
	}
}
