package ch.hepia.it.nqueens.game;

import java.util.ArrayList;
import java.util.Random;

/**
 *	Class representing a State of the chessboard
 *	We use a 1D array to store the line where the queen is present at each column
 */
public class State {
	private int[] state;
	private static Random rnd = new Random();

	/**
	 * Default constructor for state (State 0,1,2,...,size)
	 * @param size	The size of the state
	 */
	public State (int size) {
		this.state = new int[size];
		for (int i = 0; i < size; i++) {
			this.state[i] = i;
		}
	}

	/**
	 * Copy constructor for State
	 * @param state	The state we want to copy
	 */
	public State (State state) {
		this.state = new int[state.getSize()];
		for (int i = 0; i < this.state.length; i++) {
			this.state[i] = state.get(i);
		}
	}

	/**
	 * Function that returns a random state
	 * @param size	The size of the state
	 * @return	A random state
	 */
	public static State getRandomState (int size){
		Random rnd = new Random();
		State st = new State(size);
		int[] random = new int[size];
		ArrayList<Integer> range = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			range.add(i);
		}

		for (int i = 0; i < random.length ; i++) {
			int temp = rnd.nextInt(range.size());
			random[i] = range.remove(temp);
		}
		st.setState(random);
		return st;
	}

	/**
	 * Setter for the state
	 * @param state	The state array we want to set
	 */
	public void setState (int[] state) {
		this.state = state;
	}

	/**
	 * @return	The size of the state
	 */
	public int getSize(){
		return this.state.length;
	}

	/**
	 * @param idx	The index we're looking for
	 * @return	The value of the state at that index
	 */
	public int get(int idx){
		return this.state[idx];
	}

	/**
	 * This is the fitness function of our state
	 * @return	The number of conflicts of the state
	 */
	public int conflicts(){
		int conflicts = 0;
		for (int i = 0; i < this.state.length; i++) {
			int tempVal = this.state[i];
			for (int j = i+1; j < this.state.length; j++) {
				if (this.state[j] == tempVal || this.state[j] == tempVal-(j-i) || this.state[j] == tempVal+(j-i)){
					conflicts++;
				}
			}
		}
		return conflicts;
	}

	/**
	 * @return	The string representation of the state
	 */
	@Override
	public String toString () {
		String str = "[";
		for (int i = 0; i < this.state.length; i++) {
			str+= this.state[i];
			str += i == this.state.length-1 ? "]" : ",";
		}
		return str;
	}

	/**
	 * @return	The list of neighbors of the state, meaning the states obtained by swapping two columns
	 */
	public ArrayList<State> neighbors (){
		ArrayList<State> toReturn = new ArrayList<>();
		for (int i = 0; i < this.state.length; i++) {
			for (int j = i+1; j < this.state.length; j++) {
				State newState = new State(this);
				newState.swap(i,j);
				toReturn.add(newState);
			}
		}
		return toReturn;
	}

	/**
	 * Method to swap two columns of the state
	 * @param i	The first column
	 * @param j	The second column
	 */
	private void swap (int i, int j) {
		int temp = this.state[i];
		this.state[i] = this.state[j];
		this.state[j] = temp;
	}

	/**
	 * @return	The hashcode of the state
	 */
	@Override
	public int hashCode () {
		String str = "";
		for (int i = 0; i < this.state.length; i++) {
			str += state[i];
		}
		return str.hashCode();
	}

	/**
	 * Method to mutate a state, meaning swapping two random columns
	 */
	public void mutate(){
		int first = rnd.nextInt(state.length);
		int second;
		do second = rnd.nextInt(state.length); while (second == first);
		this.swap(first,second);
	}
}
