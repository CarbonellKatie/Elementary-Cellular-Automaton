import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Automaton {
public char trueSymbol;
public char falseSymbol;
private Rule rule;
private int steps = 0;

private ArrayList<Generation> generations = new ArrayList<Generation>();
	
	public Automaton(int ruleNum, Generation initial) {
		trueSymbol = '1';
		falseSymbol = '0';
		rule = new Rule(ruleNum);
		generations.add(initial);
	}

	public Automaton(String filename) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String currInfoString = br.readLine();
		while(currInfoString != null) {
		rule = new Rule(Integer.parseInt(currInfoString));
		currInfoString = br.readLine();
		falseSymbol = currInfoString.charAt(0);
		trueSymbol = currInfoString.charAt(2);
		currInfoString = br.readLine();
		Generation gen = new Generation(currInfoString, trueSymbol);
		generations.add(gen);
		currInfoString = br.readLine();
	}
	}

public int getRuleNum() {
	return rule.getRuleNum();
}

public void evolve(int numSteps) {
	if(generations.size() == 0) {
		return;
	}
	Generation gen = generations.get(generations.size() - 1);
	if(gen == null || numSteps <= 0) {
		return;
	}
	steps += numSteps;

	for(int i = 0; i < numSteps; i++) {
	gen = rule.evolve(gen);
	generations.add(gen);
	}
}

public Generation getGeneration(int stepNum) {
	if(stepNum > this.steps) {
		int temp = stepNum - steps;
		evolve(temp);
	}
	return generations.get(stepNum);
}

public int getTotalSteps() {
	return generations.size() - 1;
}

public String toString() {
	String str = "";
	StringJoiner strJ = new StringJoiner(System.lineSeparator());
	for(int i = 0; i < generations.size(); i++) {
		str = (generations.get(i)).getStates(falseSymbol, trueSymbol);			//flipped these
		strJ.add(str);
	}
	return strJ.toString();
	
}
}