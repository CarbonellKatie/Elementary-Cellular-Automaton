/**
 * representation of a Rule object that contains a ruleNum and methods to evolve a Generation object to the next state
 * @author Katherine Carbonell
 * @version 0.1
 *
 */
public class Rule {
	private static int ruleNum; 

	/**
	 * Rule constructor, creates a new Rule object and checks to make sure ruleNum given is within the bounds [0, 255]
	 * if outside bounds, ruleNum becomes closest valid number
	 * @param ruleNumP	value user wants to set ruleNum to 
	 */
	public Rule(int ruleNumP) {
		if (ruleNumP < 0) {
			ruleNum = 0; // formatting
		} else if (ruleNumP > 255) {
			ruleNum = 255;
		} else {
			ruleNum = ruleNumP;
		}
	}
	
/**
 * ruleNum accessor
 * @return	returns ruleNum associated with Rule object the method was called on
 */
	public int getRuleNum() {
		return ruleNum;
	}

	/**
	 * finds the neighborhood of a specified index in a specified generation in the format {left-neighbor, index, right-neighbor}
	 * @param idx	index of cell you want to get the neighborhood of
	 * @param gen	generation of cell you want to get the neighborhood of
	 * @return		returns a boolean array of the cell states of the neighborhood of the cell at the given index
	 */
	public static boolean[] getNeighborhood(int idx, Generation gen) {
		boolean leftNeighborState;
		boolean cellIdxState = gen.getState(idx);
		boolean rightNeighborState;
		boolean[] neighborhoodArray = new boolean[3];

		if (idx == gen.size() - 1) {
			rightNeighborState = gen.getState(0);
		} else {
			rightNeighborState = gen.getState(idx + 1);
		}

		if (idx == 0) {
			leftNeighborState = gen.getState(gen.size() - 1);
		} else {
			leftNeighborState = gen.getState(idx - 1);
		}

		neighborhoodArray[0] = leftNeighborState;
		neighborhoodArray[1] = cellIdxState;
		neighborhoodArray[2] = rightNeighborState;
		return neighborhoodArray;
	}

	/**
	 * given the neighborhood of a cell, evolve that cell to the next state
	 * @param neighborhood	neighborhood you want evolved
	 * @return	returns the evolved state of the center cell of the neighborhood
	 */
	public boolean evolve(boolean[] neighborhood) { // input: neighborhood array given {true, false, false}
		String binaryNeighborhood = neighborhoodToBinary(neighborhood); // changes neighborhood to a string of 1s and 0s
		String bitstring = intToBinary2(ruleNum); // changes rule number to binary string correctly formatted
		for (int j = 7; j >= 0; j--) {
			String binaryJ = intToBinary1(j);
			if (binaryJ.equals(binaryNeighborhood)) { // found at binary 2
				return (charToBoolean(bitstring.charAt(7 - j))); // 7-2 = 5 //returns the boolean value associated with
																	// the next step of that neighborhood
			}
		}
		return false;
	}

	/**
	 * evolves an entire generation object to the next state
	 * @param gen	generation object you want evolved
	 * @return	returns the next step in the Generation's evolution
	 */
	public Generation evolve(Generation gen) {
		String str = "";
		for (int i = 0; i < gen.size(); i++) {
			boolean nextState = evolve(getNeighborhood(i, gen));
			System.out.println(nextState);
			str += booleanToString(nextState);
		}
		Generation nextGen = new Generation(str, '1');
		return nextGen;
	}

	// HELPER METHODS:

	/**
	 * helper method, converts an array of ints to a String containing the array's values
	 * @param array		array to be converted to a String
	 * @return			returns String representation of int array with no spaces or commas between elements
	 */
	public String arrayToString(int[] array) { // helper method
		String arrayString = "";
		for (int x : array) {
			arrayString += x;
		} // ?? for loops same format as else statements?
		return arrayString;
	}

	/**
	 * helper method, changes given int value into a binary string of length 3 (padded on left side with 0s if length is
	 * less than 3,
	 *  (intended to be used on number 0-7 only)
	 * @param value		value you want converted to binary
	 * @return			returns the number as a binary string 3 characters long		
	 */
	public String intToBinary1(int value) { 
		String binaryString = Integer.toBinaryString(value);
		binaryString = String.format("%3s", binaryString);
		binaryString = binaryString.replaceAll(" ", "0");
		return binaryString;
	}

	/**
	 * helper method, changes given int value into a binary string of length 8 (padded on left side with 0s if length is
	 * less than 8,
	 *  (intended to be used on number greater than 7)
	 * @param value		int value you want converted to binary
	 * @return			returns String representation of value in binary
	 */
	public String intToBinary2(int value) {
		String binaryString = Integer.toBinaryString(value);
		binaryString = String.format("%8s", binaryString);
		binaryString = binaryString.replaceAll(" ", "0");
		return binaryString;
	}

	/**
	 * helper method, intended to convert a char value of 0 or 1 into boolean, with 0 being false and 1 being true
	 * @param character		character you want converted into boolean
	 * @return				boolean value associated with given character
	 */
	public boolean charToBoolean(char character) { // helper method
		if (character == '0') {
			return false;
		}
		return true;
	}

	/**
	 * helper method to convert boolean values to 0 or 1 (as Strings)
	 * @param bool	boolean value you want converted to String
	 * @return		String representation of boolean value
	 */
	public String booleanToString(boolean bool) {
		if (bool == true) {
			return "1";
		} else {
			return "0";
		}
	}
	
	/**
	 * helper method, changes boolean neighborhood array into 1s and 0s using false = 0; true = 1
	 * @param neighborhood		neighborhood you want converted into 1s and 0s
	 * @return					returns String representation of neighborhood containing 1s and 0s
	 */
	public String neighborhoodToBinary(boolean[] neighborhood) { 
		
		String binaryNeighborhood = "";
		for (int i = 0; i < neighborhood.length; i++) { // changing neighborhood array into binary form 0 = false; 1 =
														// true {0, 1, 1}
			if (neighborhood[i] == true) {
				binaryNeighborhood += "1";
			} 
			else {
				binaryNeighborhood += "0";
			}
		}
		return binaryNeighborhood;
	}

}
