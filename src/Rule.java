
public class Rule {
private int ruleNum;			//ruleNum to be static???

public Rule(int ruleNum) {
	if (ruleNum < 0) {
		this.ruleNum = 0;				//formatting
	} else if (ruleNum > 255) {
		this.ruleNum = 255;
	} else {
		this.ruleNum = ruleNum;
	}
}
	
	public int getRuleNum() {
		return this.ruleNum;
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
		String binaryNeighborhood = neighborhoodToBinary(neighborhood);	
		String bitstring = intToBinary(ruleNum);
		
		for(int j = 7; j >= 0; j--) {
			String binaryJ = intToBinary(j);
			if(binaryJ.equals(binaryNeighborhood)) {	//found at binary 2
				return (charToBoolean(bitstring.charAt(7-j)));	//7-2 = 5	//returns the boolean value associated with the next step of that neighborhood
			}
		}
		return false;
	}
	
	
	public Generation evolve(Generation gen) {
		
	}
	
	
	//HELPER METHODS:
	
	public String arrayToString(int[] array) {	//helper method
		String arrayString = "";
		for(int x: array) {arrayString += x;}				//?? for loops same format as else statements?
		return arrayString;
	}
	
	
	public String intToBinary(int value) {		//helper method, changes given int value into a binary string of length 3 (intended to be used on number 0-7 only
		String binaryString = Integer.toBinaryString(value);		
		binaryString = String.format("%3s", binaryString);
		binaryString = binaryString.replaceAll(" ", "0");
		return binaryString;
	}
	
	
	public boolean charToBoolean(char character) {		//helper method
			if(character == '0') {System.out.println("false");return false;}
			System.out.println("true");
			return true;
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
