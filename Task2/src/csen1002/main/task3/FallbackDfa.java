package csen1002.main.task3;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Ahmed Essam
 * @id 49-2174
 * @labNumber 10
 */

public class FallbackDfa {
	ArrayList<Transition> changes;
	Stack<Integer> stack;
	int beginningS;
	ArrayList<Integer> finalS;
	String output;

	public FallbackDfa(String fdfa) {
		this.changes = new ArrayList<Transition>();
		this.stack = new Stack<Integer>();
		this.finalS = new ArrayList<Integer>();
		this.output = "";
		String[] parsedInput = fdfa.split("#");
		this.beginningS = Integer.parseInt(parsedInput[3]);
		this.addfinalS(parsedInput[4]);
		this.addChanges(parsedInput[2]);
	}

	private void addChanges(String changes) {
		String[] listTransitions = changes.split(";");
		for (String transition : listTransitions) {
			String[] components = transition.split(",");
			Transition newTransition = new Transition(Integer.parseInt(components[0]), components[1].charAt(0),
					Integer.parseInt(components[2]));
			this.changes.add(newTransition);
		}
	}

	private void addfinalS(String input) {
		String[] states = input.split(";");
		for (String state : states) {
			this.finalS.add(Integer.parseInt(state));
		}
	}

	public String run(String input) {
		int l = 0;
		int r = 0;
		boolean isStringFinished = false;
		while (!isStringFinished) {
			int firstPop = -1;
			this.stack.push(this.beginningS);
			while (l < input.length()) {
				this.stack.push(this.findState(input.charAt(l), this.stack.peek()));
				l++;
			}
			while (!this.stack.isEmpty()) {
				int poppedState = this.stack.pop();
				if (firstPop == -1) {
					firstPop = poppedState;
				}
				if (this.finalS.contains(poppedState) || this.stack.isEmpty()) {
					String newString;
					if (l <= r) {
						newString = input.substring(r);
						isStringFinished = true;
					} else {
						newString = input.substring(r, l);
					}
					this.output += newString + "," + (this.stack.isEmpty() ? firstPop : poppedState) + ";";
					l--;
					this.stack.clear();
					break;
				}
				l--;
			}
			l++;
			r = l;
			if (l >= input.length()) {
				isStringFinished = true;
			}
		}
		String output = this.output.substring(0, this.output.length() - 1);
		return output;
	}

	private int findState(char c, int startState) {
		for (Transition transition : this.changes) {
			if (transition.start == startState && c == transition.transition) {
				return transition.end;
			}
		}
		return -1;
	}
}

class Transition {
	int start;
	char transition;
	int end;

	public Transition(int start, char transition, int end) {
		this.start = start;
		this.transition = transition;
		this.end = end;
	}
}