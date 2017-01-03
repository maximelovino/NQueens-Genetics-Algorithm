package ch.hepia.it.nqueens;

import ch.hepia.it.nqueens.core.Solver;
import ch.hepia.it.nqueens.game.State;

import java.util.Scanner;

public class NQueens {
	public static void main (String[] args) {
		int size;
		if (args.length == 1){
			size = Integer.valueOf(args[0]);
		}else{
			Scanner sc = new Scanner(System.in);
			System.out.println("What size of the NQueens problem do you want to solve?");
			size = sc.nextInt();
		}
		long startTime = System.nanoTime();
		State solution = Solver.bruteForceSolve(size);
		long endTime = System.nanoTime();
		double elapsed = (endTime-startTime) / 1e9;
		System.out.println(solution);
		System.out.println("This took "+elapsed+" seconds");
	}
}
