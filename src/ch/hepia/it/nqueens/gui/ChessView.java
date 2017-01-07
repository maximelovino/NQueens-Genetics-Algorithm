package ch.hepia.it.nqueens.gui;

import ch.hepia.it.nqueens.game.State;

import javax.swing.*;
import java.awt.*;

/**
 * Class for the view of our chessboard
 */
public class ChessView extends JPanel {
	private State state;
	private JButton[] buttons;
	private boolean[] queens;
	private int size;

	/**
	 * Main constructor for our view
	 * We initialize with the size of the problem but a null state, since the solution is not computed yet
	 * @param size	The size of our view
	 */
	public ChessView (int size) {
		super(new GridLayout(size, size));
		this.size = size;
		this.state = null;
		buttons = new JButton[size * size];
		queens = new boolean[size * size];
		for (int i = 0; i < buttons.length; i++) {
			queens[i] = false;
			buttons[i] = new JButton(new ImageIcon(""));
			buttons[i].setName(String.valueOf(i));
			buttons[i].addActionListener(e -> {
				Integer idx = Integer.valueOf(((JButton) e.getSource()).getName());
				printTrace(idx);

			});
			this.add(buttons[i]);
		}
	}

	/**
	 * Method to sync the buttons of the view with the state
	 */
	public void syncButtonsWithState () {
		if (state == null) return;
		for (int i = 0; i < this.size; i++) {
			int temp = this.state.get(i);
			queens[temp * this.size + i] = true;
		}

		for (int i = 0; i < buttons.length; i++) {
			if (queens[i])
				buttons[i].setIcon(new ImageIcon(new ImageIcon("assets/queen.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
			else
				buttons[i].setIcon(new ImageIcon(""));
		}
	}

	/**
	 * Method to set a new state for the view
	 * @param state	The new state
	 */
	public void setState (State state) {
		this.state = state;
		syncButtonsWithState();
	}

	/**
	 * Method that displays the coverage of a queen on the GUI
	 * @param index	The index of the clicked cell, we will display coverage only if queen
	 */
	private void printTrace (int index) {
		if (!queens[index]) return;
		clearAllTraces();
		int divide = index / this.size;
		int mod = index % this.size;

		for (int i = 0; i < buttons.length; i++) {
			if (i / this.size == divide || i % this.size == mod || Math.abs(i / this.size - divide) == Math.abs(i % this.size - mod)) {
				buttons[i].setBackground(i == index ? Color.GREEN : Color.RED);
				buttons[i].setOpaque(true);
			}
		}

	}

	/**
	 * Method to clear all coverage displayed
	 */
	public void clearAllTraces () {
		for (JButton b : buttons) {
			b.setBackground(Color.white);
			b.setOpaque(false);
		}
	}
}
