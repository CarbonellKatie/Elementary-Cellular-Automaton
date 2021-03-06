import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * representation of an Automaton that contains a trueSymbol, falseSymbol, an ArrayList of Generation objects, and a Rule object
 * @author Katherine Carbonell
 * @version 0.1
 */
public class Automaton {
	public char trueSymbol;
	public char falseSymbol;
	private Rule rule;
	private int steps = 0;

	private ArrayList<Generation> generations = new ArrayList<Generation>();

	/**
	 * Automaton constructor, creates a new Automaton using a rule number to create a new Rule object and an initial
	 * Generation object, which is assigned to generations[0]
	 * @param ruleNum	rule number you want to use to create new Rule object
	 * @param initial	initial Generation object to be place at generations[0]
	 */
	public Automaton(int ruleNum, Generation initial) {
		trueSymbol = '1';
		falseSymbol = '0';
		rule = new Rule(ruleNum);
		generations.add(initial);
	}
	
	/**
	 * constructs an Automaton from a file of information containing the rule number for a rule object, true/false symbols,
	 * and the states of a generation
	 * @param filename					name of file you want to read info from
	 * @throws FileNotFoundException	throws if file cannot be found
	 * @throws IOException				throws if there is an error reading input from file
	 */
	public Automaton(String filename) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String currInfoString = br.readLine();
		while (currInfoString != null) {
			rule = new Rule(Integer.parseInt(currInfoString));
			currInfoString = br.readLine();
			falseSymbol = currInfoString.charAt(0);
			trueSymbol = currInfoString.charAt(2);
			currInfoString = br.readLine();
			Generation gen = new Generation(currInfoString, trueSymbol);
			generations.add(gen);
			currInfoString = br.readLine();
		}
		br.close();
	}
	
	/**
	 * ruleNum accessor
	 * @return	returns ruleNum of Rule object being used to evolve the automaton
	 */
	public int getRuleNum() {
		return rule.getRuleNum();
	}

	/**
	 * evolves the generation in the automaton a given number of steps
	 * @param numSteps	number of steps you want to evolve the generation
	 */
	public void evolve(int numSteps) {
		if (generations.size() == 0) {
			return;
		}
		Generation gen = generations.get(generations.size() - 1);
		if (gen == null || numSteps <= 0) {
			return;
		}
		steps += numSteps;

		for (int i = 0; i < numSteps; i++) {
			gen = rule.evolve(gen);
			generations.add(gen);
		}
	}

	/**
	 * gives the user the generation object found at the specified step number
	 * @param stepNum	step number of the generation you want 
	 * @return			returns the generation object found at stepNum
	 */
	public Generation getGeneration(int stepNum) {
		if (stepNum > steps) {
			int temp = stepNum - steps;
			evolve(temp);
		}
		return generations.get(stepNum);
	}

	/**
	 * accessor for the size of the generations ArrayList
	 * @return	returns the size of the generations arraylist for that Automaton
	 */
	public int getTotalSteps() {
		return generations.size() - 1;
	}

	/**
	 * toString method to convert an automaton to a string containing the states of each generation in the automaton, 
	 * each generation's information on a separate line
	 */
	public String toString() {
		String str = "";
		StringJoiner strJ = new StringJoiner(System.lineSeparator());
		for (int i = 0; i < generations.size(); i++) {
			str = (generations.get(i)).getStates(falseSymbol, trueSymbol); 
			strJ.add(str);
		}
		return strJ.toString();
	}

	/**
	 * saves the current evolution to a file with the given filename
	 * @param filename		file you want to save the evolution to
	 * @throws IOException	throws if there is an error reading input from file
	 */
	public void saveEvolution(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.write(this.toString());
		bw.close();
	}
}