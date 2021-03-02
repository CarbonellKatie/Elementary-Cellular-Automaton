
public class Rule {
private int ruleNum;			//ruleNum to be static???

	public Rule(int ruleNum){
	if(ruleNum < 0) {
		this.ruleNum = 0;
	}
	else if(ruleNum > 255) {
		this.ruleNum = 255;
	}
	else {
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
	
	public boolean evolve(boolean[] neighborhood) {	//input: neighborhood array  given {true, false, true}
		String binaryNeighborhood = neighborhoodToBinary(neighborhood);	// 011
		String ruleNumBinary = Integer.toBinaryString(ruleNum);
		ruleNumBinary = String.format("%8s", ruleNumBinary);
		int foundAt = -1;
		for(int i = 0; i < neighborhood.length; i++) {
		for(int j = 7; j >= 0; j--) {
			if(Integer.toBinaryString(j) == binaryNeighborhood) {
				foundAt = j;
				neighborhood[i] = ruleNumBinary.charAt(7-j);		//gives back 0
				break;
			}
		}
		
	}
	public String arrayToString(int[] array) {	//returns "011"
	
		String arrayString = "";
		for(int x: array) {
			arrayString += x;
		}
		return arrayString;
	}
	
	
	public String neighborhoodToBinary(boolean[] neighborhood) {	//helper method to change boolean neighborhood array into 1s and 0s
		String binaryNeighborhood = "";
		
		for(int i = 0; i < neighborhood.length; i++) {			//changing neighborhood array into binary form 0 = false; 1 = true {0, 1, 1}
			if(neighborhood[i] == true) {binaryNeighborhood += 1;}		//???? If statements acceptable in this form?
			else if(neighborhood[i] == false) {binaryNeighborhood += 0;}
		}
		return binaryNeighborhood;
	}

}
