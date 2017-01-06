package ch.hepia.it.nqueens;

import ch.hepia.it.nqueens.core.Solver;
import ch.hepia.it.nqueens.game.State;
import ch.hepia.it.nqueens.gui.ChessView;

import javax.swing.*;
import java.awt.*;

public class NQueens {
	public static void main (String[] args) {
		JFrame frame = new JFrame("NQueens problem");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Integer size = Integer.valueOf(JOptionPane.showInputDialog("What size of the NQueens problem would you like to solve?","8"));

		JPanel header = new JPanel(new FlowLayout());
		JButton bruteforceSolve = new JButton("Bruteforce solve");
		JButton geneticSolve = new JButton("Genetic solve");
		header.add(bruteforceSolve);
		header.add(geneticSolve);




		ChessView view = new ChessView(size);

		frame.getContentPane().setLayout(new BorderLayout());

		frame.getContentPane().add(view,BorderLayout.CENTER);
		frame.getContentPane().add(header, BorderLayout.PAGE_START);
		frame.pack();
		frame.setSize(new Dimension(1000,1000));
		frame.setVisible(true);



		geneticSolve.addActionListener(e -> {
			State solution = Solver.geneticSolve(size);
			view.setState(solution);
		});

		bruteforceSolve.addActionListener(e -> {
			State solution = Solver.bruteForceSolve(size);
			view.setState(solution);
		});



/*		long startTime = System.nanoTime();
		State solution = method == 0 ? Solver.bruteForceSolve(size) : Solver.geneticSolve(size);
		long endTime = System.nanoTime();
		double elapsed = (endTime - startTime) / 1e9;
		if (solution != null){
			System.out.println("The solution is: "+solution);
		}else{
			System.out.println("No solution was found");
		}
		System.out.println("This took " + elapsed + " seconds");
		view.setState(solution);*/
	}
}
