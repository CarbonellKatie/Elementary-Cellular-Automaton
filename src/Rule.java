
public class Rule {
private static int ruleNum;			//ruleNum to be static???

public Rule(int ruleNumP) {
	if (ruleNumP < 0) {
		ruleNum = 0;				//formatting
	} else if (ruleNumP > 255) {
		ruleNum = 255;
	} else {
		ruleNum = ruleNumP;
	}
}
	
	public int getRuleNum() {
		return ruleNum;
	}
	
	public static boolean[] getNeighborhood(int idx, Generation gen) {
		boolean leftNeighborState;
		boolean cellIdxState = gen.getState(idx);
		boolean rightNeighborState;
		boolean[] neighborhoodArray = new boolean[3];
		
		if(idx == gen.size() - 1) {
			rightNeighborState = gen.getState(0);
		}
		else { rightNeighborState = gen.getState(idx + 1); }
		
		if(idx == 0) {
			leftNeighborState = gen.getState(gen.size() - 1);
		}
		else { leftNeighborState = gen.getState(idx - 1); }
		
		neighborhoodArray[0] = leftNeighborState;
		neighborhoodArray[1] = cellIdxState;
		neighborhoodArray[2] = rightNeighborState;
		return neighborhoodArray;
	}
	
	
	public boolean evolve(boolean[] neighborhood) {	//input: neighborhood array  given {true, false, false}
		String binaryNeighborhood = neighborhoodToBinary(neighborhood);	//changes neighborhood to a string of 1s and 0s 
		String bitstring = intToBinary2(ruleNum);	//changes rule number to binary string correctly formatted
		for(int j = 7; j >= 0; j--) {
			String binaryJ = intToBinary1(j);
			if(binaryJ.equals(binaryNeighborhood)) {	//found at binary 2
				return (charToBoolean(bitstring.charAt(7-j)));	//7-2 = 5	//returns the boolean value associated with the next step of that neighborhood
			}
		}
		return false;
	}
	
	
	public Generation evolve(Generation gen) {
		String str = "";
		for(int i = 0; i < gen.size(); i++) {
		boolean nextState = evolve(getNeighborhood(i, gen));
		System.out.println(nextState);
		 str += booleanToString(nextState);
		
		
		
		}
		Generation nextGen = new Generation(str, '1');
		return nextGen;
	}
	
	
	//HELPER METHODS:
	
	public String arrayToString(int[] array) {	//helper method
		String arrayString = "";
		for(int x: array) {arrayString += x;}				//?? for loops same format as else statements?
		return arrayString;
	}
	
	
	public String intToBinary1(int value) {		//helper method, changes given int value into a binary string of length 3 (intended to be used on number 0-7 only
		String binaryString = Integer.toBinaryString(value);		
		binaryString = String.format("%3s", binaryString);
		binaryString = binaryString.replaceAll(" ", "0");
		return binaryString;
	}
	
	public String intToBinary2(int value) {
		String binaryString = Integer.toBinaryString(value);		
		binaryString = String.format("%8s", binaryString);
		binaryString = binaryString.replaceAll(" ", "0");
		return binaryString;
	}
	
	
	public boolean charToBoolean(char character) {		//helper method
			if(character == '0') {return false;}
			return true;
		}
	
	public String booleanToString(boolean bool) {
		if(bool == true) {
			return "1";
		}
		else {return "0";}
	}
	
	
	
	public String neighborhoodToBinary(boolean[] neighborhood) {	//helper method to change boolean neighborhood array into 1s and 0s
		String binaryNeighborhood = "";
		for(int i = 0; i < neighborhood.length; i++) {			//changing neighborhood array into binary form 0 = false; 1 = true {0, 1, 1}
			if(neighborhood[i] == true) {binaryNeighborhood += "1";}		//???? If statements acceptable in this form?
			else {binaryNeighborhood += "0";}
		}
		return binaryNeighborhood;
	}
	

}
