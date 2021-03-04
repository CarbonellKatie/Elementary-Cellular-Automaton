import java.util.ArrayList;
public class Automaton {
private char trueSymbol;
private char falseSymbol;
private Rule rule;
private int steps = 0;

private ArrayList<Generation> generations = new ArrayList<Generation>();
	public Automaton(int ruleNum, Generation initial) {
		trueSymbol = '0';
		falseSymbol = '1';
		Rule rule = new Rule(ruleNum);
		generations.add(initial);
	}

	public Automaton(String filename){
		
	}

public int getRuleNum() {
	return rule.getRuleNum();
}

public void evolve(int numSteps) {
	Generation gen = generations.get(0);
	if(steps <= 0) {
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
}