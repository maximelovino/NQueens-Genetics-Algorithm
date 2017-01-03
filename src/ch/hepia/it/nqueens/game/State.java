package ch.hepia.it.nqueens.game;

import java.util.ArrayList;
import java.util.Random;

public class State {
	private int[] state;

	public State (int size) {
		this.state = new int[size];
		for (int i = 0; i < size; i++) {
			this.state[i] = i;
		}
	}

	public State (State state) {
		this.state = new int[state.getSize()];
		for (int i = 0; i < this.state.length; i++) {
			this.state[i] = state.get(i);
		}
	}

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

	public void setState (int[] state) {
		this.state = state;
	}

	public int getSize(){
		return this.state.length;
	}

	public int get(int idx){
		return this.state[idx];
	}

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

	@Override
	public String toString () {
		String str = "[";
		for (int i = 0; i < this.state.length; i++) {
			str+= this.state[i];
			str += i == this.state.length-1 ? "]" : ",";
		}
		return str;
	}

	public ArrayList<State> neighbours(){
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

	private void swap (int i, int j) {
		int temp = this.state[i];
		this.state[i] = this.state[j];
		this.state[j] = temp;
	}

	@Override
	public int hashCode () {
		String str = "";
		for (int i = 0; i < this.state.length; i++) {
			str += state[i];
		}
		return str.hashCode();
	}

	public void mutate(){
		ArrayList<State> neighbours = this.neighbours();
		Random rnd = new Random();
		this.setState(neighbours.get(rnd.nextInt(neighbours.size())).state);
	}
}
