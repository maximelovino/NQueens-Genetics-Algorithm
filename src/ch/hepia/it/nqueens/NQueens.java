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
		State solution = Solver.bruteForceSolve(size);
		System.out.println(solution);
	}
}
