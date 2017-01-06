package ch.hepia.it.nqueens.gui;

import ch.hepia.it.nqueens.game.State;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class ChessView extends JPanel{
	private State state;
	private static final int BORDER_SIZE = 10;
	private JButton[] buttons;

	public ChessView (State state, int size) {
		super(new GridLayout(size,size));
		this.state = state;
		buttons = new JButton[size*size];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(new ImageIcon(""));
			this.add(buttons[i]);
		}
		syncButtonsWithState();
	}

	public void syncButtonsWithState () {
		if (state == null) return;

		HashSet<Integer> queensInButtons = new HashSet<>();
		for (int i = 0; i < this.state.getSize(); i++) {
			int temp = this.state.get(i);
			queensInButtons.add(temp * this.state.getSize()+i);
		}

		for (int i = 0; i < buttons.length; i++) {
			if (queensInButtons.contains(Integer.valueOf(i)))
				buttons[i].setIcon(new ImageIcon(new ImageIcon("assets/queen.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
			else
				buttons[i].setIcon(new ImageIcon(""));
		}
	}
	public void setState(State state){
		if (this.state == null || state.getSize() != this.state.getSize()) setNewSize(state.getSize());
		this.state = state;
		syncButtonsWithState();
	}

	private void setNewSize (int size){
		this.removeAll();
		buttons = new JButton[size*size];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(new ImageIcon(""));
			this.add(buttons[i]);
		}
	}
}
