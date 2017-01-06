package ch.hepia.it.nqueens.gui;

import ch.hepia.it.nqueens.game.State;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class ChessView extends JPanel {
	private State state;
	private static final int BORDER_SIZE = 10;
	private JButton[] buttons;
	private boolean[] queens;
	private int size;

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
		syncButtonsWithState();
	}

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

	public void setState (State state) {
		this.state = state;
		syncButtonsWithState();
	}

	private void printTrace (int index) {
		if (!queens[index]) return;
		clearAllTraces();
		System.out.println(index);
		int divide = index / this.size;
		int mod = index % this.size;

		for (int i = 0; i < buttons.length; i++) {
			if (i / this.size == divide || i % this.size == mod || Math.abs(i / this.size - divide) == Math.abs(i % this.size - mod)) {
				buttons[i].setBackground(i == index ? Color.GREEN : Color.RED);
				buttons[i].setOpaque(true);
			}
		}

	}

	private void clearAllTraces () {
		for (JButton b : buttons) {
			b.setBackground(Color.white);
			b.setOpaque(false);
		}
	}
}
