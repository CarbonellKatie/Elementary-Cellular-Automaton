import java.util.Arrays;

public class Generation {
	private boolean[] cellStates;

	public Generation(boolean... cells) {
		if(cells == null || cells.length == 0) {
			invalidParam();
			return; }									// is it ok to put these brackets on the same line?
		cellStates = new boolean[cells.length];
		for(int i = 0; i < cells.length; i++) {
			cellStates[i] = cells[i];
		}
	}
	
	public Generation(String states, char trueSymbol) {
		if(states == null || states.equals("")) {
			invalidParam();	
			return; }
		cellStates = new boolean[states.length()];
		for(int i = 0; i < states.length(); i++) {
			if(states.charAt(i) == trueSymbol) {
				this.cellStates[i] = true; }
			else { this.cellStates[i] = false; }				//brackets on same line?
		}
	}
	
	public boolean getState(int idx) {
		return cellStates[idx];
	}
	
	public boolean[] getStates() {
		return Arrays.copyOf(cellStates, cellStates.length);
	}
	
	public String getStates(char falseSymbol, char trueSymbol) {
		String cellStatesString = "";
		for(boolean b: cellStates) {
			if(b == true) {
				cellStatesString += trueSymbol;
			}
			else { cellStatesString += falseSymbol; }
		}
		return cellStatesString;
	}
	
	public int size() {
		return cellStates.length;
	}
	
	
	
	
	
	
	
	
	
	private void invalidParam() {		//helper method to create a new Generation object w/ one cell in the off state
		cellStates = new boolean[1];
		cellStates[0] = false;
	}
	

}
