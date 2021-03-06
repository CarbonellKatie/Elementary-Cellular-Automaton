import java.util.Arrays;

/** representation of a Generation object composed of a boolean array of cell states
 * @author Katherine Carbonell
 * @verion 0.1
 */
public class Generation {
	private boolean[] cellStates;
	
/**
 * Generation object constructor which creates a new Generation object with the given boolean values 
 * as cell states, creates a new Generation with a single cell state, which is set to false if input is null or 
 * constructor is given no input
 * @param cells		initial values of each cell of the Generation object
 */
	public Generation(boolean... cells) {
		if (cells == null || cells.length == 0) {
			invalidParam();
			return;
		}
		cellStates = new boolean[cells.length];
		for (int i = 0; i < cells.length; i++) {
			cellStates[i] = cells[i];
		}
	}

	/**
	 * Generation constructor that creates a new Generation object, filling the cellStates array with the values in String states,
	 * determines state of cell using trueSymbol
	 * @param states		string of desired cell states
	 * @param trueSymbol	if found, in states string, the value of the cell at that index will be set to true, otherwise false
	 */
	public Generation(String states, char trueSymbol) {
		if (states == null || states.equals("")) {
			invalidParam();
			return;
		}
		cellStates = new boolean[states.length()];
		for (int i = 0; i < states.length(); i++) {
			if (states.charAt(i) == trueSymbol) {
				this.cellStates[i] = true;
			} else {
				this.cellStates[i] = false;
			}
		}
	}

	/**
	 * cellStates accessor, returns cell state at given index
	 * @param idx	index of cellState you want returned
	 * @return		returns the state of the cell (true or false) at the given index
	 */
	public boolean getState(int idx) {
		return cellStates[idx];
	}
	
	/**
	 * 	cellStates accessor, gives the user every state of every cell in the Generation object
	 * @return	returns a copy of the cellStates array
	*/
	public boolean[] getStates() {
		return Arrays.copyOf(cellStates, cellStates.length);
	}

	/**
	 * gives the String representation of all cell states in a Generation object, using falseSymbol and trueSymbol
	 * @param falseSymbol	symbol the user wants put in the string if a cell state is false
	 * @param trueSymbol	symbol the user wants put in the string if a cell state is true
	 * @return				returns a string containing every cell state in the Generation object, if the state is true
	 * 						the String will contain the trueSymbol at that index, false = falseSymbol at that index
	 */
	public String getStates(char falseSymbol, char trueSymbol) {
		String cellStatesString = "";
		for (boolean b : cellStates) {
			if (b == true) {
				cellStatesString += trueSymbol;
			} else {
				cellStatesString += falseSymbol;
			}
		}
		return cellStatesString;
	}

	/**
	 * size accessor
	 * @return	returns the length of the cellStates array, or the number of cells in that Generation object
	 */
	public int size() {
		return cellStates.length;
	}
	
	/**
	 * helper method, creates a new Generation object with one cell set to false 
	 */
	private void invalidParam() { // helper method to create a new Generation object w/ one cell in the off state
		cellStates = new boolean[1];
		cellStates[0] = false;
	}

}
