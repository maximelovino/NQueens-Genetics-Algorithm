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

		// ############ HEADER CREATION ##################
		JPanel header = new JPanel(new FlowLayout());
		JButton bruteforceSolve = new JButton("Bruteforce solve");
		JButton geneticSolve = new JButton("Genetic solve");
		JButton clearCoverage = new JButton("Clear all coverage display");
		header.add(bruteforceSolve);
		header.add(geneticSolve);
		header.add(clearCoverage);

		ChessView view = new ChessView(size);

		// ############ ADDING TO FRAME ##################
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(view,BorderLayout.CENTER);
		frame.getContentPane().add(header, BorderLayout.PAGE_START);
		frame.pack();
		frame.setSize(new Dimension(1000,1000));
		frame.setVisible(true);

		// ############ LISTENERS AND HANDLERS ##################
		geneticSolve.addActionListener(e -> {
			long start = System.nanoTime();
			State solution = Solver.geneticSolve(size);
			long end = System.nanoTime();
			System.out.println("The process took "+ (end-start)/1e9 + " seconds");
			view.setState(solution);
		});

		bruteforceSolve.addActionListener(e -> {
			long start = System.nanoTime();
			State solution = Solver.bruteForceSolve(size);
			long end = System.nanoTime();
			System.out.println("The process took "+ (end-start)/1e9 + " seconds");
			view.setState(solution);
		});

		clearCoverage.addActionListener(e -> {
			view.clearAllTraces();
		});
	}
}
