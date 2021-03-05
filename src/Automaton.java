import java.util.ArrayList;
import java.util.StringJoiner;
public class Automaton {
public char trueSymbol;
public char falseSymbol;
private Rule rule;
private int steps = 0;

private ArrayList<Generation> generations = new ArrayList<Generation>();
	public Automaton(int ruleNum, Generation initial) {
		trueSymbol = '0';
		falseSymbol = '1';
		rule = new Rule(ruleNum);
		generations.add(initial);
	}

	public Automaton(String filename){
		rule = new Rule(6);
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
	return generations.size();
}

public String toString() {
	String str = "";
	StringJoiner strJ = new StringJoiner("");
	for(int i = 0; i < generations.size(); i++) {
		str = (generations.get(i)).getStates(trueSymbol, falseSymbol);
		strJ.add(str);
		strJ.add(System.lineSeparator());
	}
	return strJ.toString();
	
}
}