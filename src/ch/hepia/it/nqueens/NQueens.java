package ch.hepia.it.nqueens;

import ch.hepia.it.nqueens.core.Solver;
import ch.hepia.it.nqueens.game.State;

import java.util.Scanner;

public class NQueens {
	public static void main (String[] args) {
		int size;
		Scanner sc = new Scanner(System.in);
		System.out.println("What size of the NQueens problem do you want to solve?");
		try {
			size = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Your number was not parsable, we set the size to 8");
			size = 8;
		}

		System.out.println("What method do you want to use? \n 0. Bruteforce \n 1. Genetic method");
		int method;
		try {
			method = sc.nextInt();
		} catch (Exception e) {
			System.out.println("Your number was not parsable, we set the method to genetics");
			method = 1;
		}
		if (method != 1 && method != 0){
			System.out.println("This method doesn't exist. We set the method to genetics");
			method = 1;
		}
		long startTime = System.nanoTime();
		State solution = method == 0 ? Solver.bruteForceSolve(size) : Solver.geneticSolve(size);
		long endTime = System.nanoTime();
		double elapsed = (endTime - startTime) / 1e9;
		if (solution != null){
			System.out.println("The solution is: "+solution);
		}else{
			System.out.println("No solution was found");
		}
		System.out.println("This took " + elapsed + " seconds");
	}
}
