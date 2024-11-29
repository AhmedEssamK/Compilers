package csen1002.main.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Write your info here
 * 
 * @name Ahmed Essam
 * @id 49-2174
 * @labNumber 10
 */

public class RegExToNfa {
	/**
	 * Constructs an NFA corresponding to a regular expression based on Thompson's
	 * construction
	 * 
	 * @param input The alphabet and the regular expression in postfix notation for
	 *              which the NFA is to be constructed
	 */

	public String A;
	public String R;
	Stack<List<String[]>> stateStack = new Stack<>();

	public RegExToNfa(String input) {
		int size = input.length();
		A = "";
		R = "";
		for (int i = 0; i < size; i++) {
			if (input.charAt(i) != '#') {
				String A1 = String.valueOf(input.charAt(i));
				A = A.concat(A1);
			} else {
				R = input.substring(i + 1);
				break;
			}
		}
	}

	/**
	 * @return Returns a formatted string representation of the NFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		String answer = "";
		String Q = "";
		String T = "";
		String I = "";
		String F = "";
		int Qcounter = 0;
		int size = R.length();

		for (int i = 0; i < size; i++) {
			if (R.charAt(i) == 'e') {
				Q += Integer.toString(Qcounter) + ';' + Integer.toString(Qcounter + 1) + ';';
				T += Integer.toString(Qcounter) + ";e;" + Integer.toString(Qcounter + 1) + ',';
				List<String[]> eStates = new ArrayList<>();
				eStates.add(new String[] { Integer.toString(Qcounter), Integer.toString(Qcounter + 1) });
				stateStack.push(eStates);
				// I = Integer.toString(Qcounter);
				// F = Integer.toString(Qcounter + 1);
				Qcounter += 2;
			} else if (Character.isLetter(R.charAt(i))) {
				Q += Integer.toString(Qcounter) + ';' + Integer.toString(Qcounter + 1) + ';';
				T += Integer.toString(Qcounter) + ';' + R.charAt(i) + ';' + Integer.toString(Qcounter + 1) + ',';
				List<String[]> charStates = new ArrayList<>();
				charStates.add(new String[] { Integer.toString(Qcounter), Integer.toString(Qcounter + 1) });
				stateStack.push(charStates);
				Qcounter += 2;
			} else if (R.charAt(i) == '|') {
				List<String[]> secondStates = stateStack.pop();
				List<String[]> firstStates = stateStack.pop();

				String[] firstState = firstStates.get(0);
				String[] secondState = secondStates.get(0);

				stateStack.push(new ArrayList<>());
				stateStack.peek().add(new String[] { Integer.toString(Qcounter), Integer.toString(Qcounter + 1) });

				Q += Integer.toString(Qcounter) + ';' + Integer.toString(Qcounter + 1) + ';';

				T += Integer.toString(Qcounter) + ";e;" + firstState[0] + ",";
				T += Integer.toString(Qcounter) + ";e;" + secondState[0] + ",";
				T += firstState[1] + ";e;" + Integer.toString(Qcounter + 1) + ",";
				T += secondState[1] + ";e;" + Integer.toString(Qcounter + 1) + ",";

				Qcounter += 2;
			} else if (R.charAt(i) == '*') {
				List<String[]> lastStates = stateStack.pop();

				String[] lastState = lastStates.get(0);

				stateStack.push(new ArrayList<>());
				stateStack.peek().add(new String[] { Integer.toString(Qcounter), Integer.toString(Qcounter + 1) });

				Q += Integer.toString(Qcounter) + ';' + Integer.toString(Qcounter + 1) + ';';

				T += lastState[1] + ";e;" + lastState[0] + ",";
				T += Integer.toString(Qcounter) + ";e;" + lastState[0] + ",";
				T += Integer.toString(Qcounter) + ";e;" + Integer.toString(Qcounter + 1) + ",";
				T += lastState[1] + ";e;" + Integer.toString(Qcounter + 1) + ",";

				Qcounter += 2;
			} else if (R.charAt(i) == '.') {
				List<String[]> secondStates = stateStack.pop();
				List<String[]> firstStates = stateStack.pop();

				String[] firstState = firstStates.get(0);
				String[] secondState = secondStates.get(0);

				stateStack.push(new ArrayList<>());
				stateStack.peek().add(new String[] { firstState[0], secondState[1] });

				// Update the transitions to replace occurrences of secondState[0] with
				// firstState[1]
				T = T.replace(secondState[0] + ";", firstState[1] + ";");
				Q = Q.replace(secondState[0] + ";", "");

				// Qcounter += 1;
			}
		}

		Q = Q.substring(0, Q.length() - 1);
		T = T.substring(0, T.length() - 1);

		ArrayList<String> transitionsList = new ArrayList<>(Arrays.asList(T.split(",")));
		List<Change> transitionsObjectsList = new ArrayList<>();
		for (String transition : transitionsList) {
			String[] parts = transition.split(";");
			Change t = new Change(parts[0], parts[1], parts[2]);
			transitionsObjectsList.add(t);
		}
		Collections.sort(transitionsObjectsList);

		T = "";

		for (Change t : transitionsObjectsList) {
			T += t.beginningS + ";" + t.transition + ";" + t.endingS + ",";
		}

		T = T.substring(0, T.length() - 1);

		List<String[]> IFState = stateStack.pop();

		String[] state = IFState.get(0);

		I = state[0];

		F = state[1];

		String T2 = "";
		for (int z = 0; z < T.length(); z++) {
			if (T.charAt(z) == ';') {
				T2 += ',';
			} else if (T.charAt(z) == ',') {
				T2 += ';';
			} else {
				T2 += T.charAt(z);
			}
		}

		answer = Q + '#' + A + '#' + T2 + '#' + I + '#' + F;
		// System.out.println(T);
		return answer;
	}

	public static void main(String[] args) {
		RegExToNfa nfa = new RegExToNfa("a;b#eab*|.a.*");
		System.out.println(nfa.toString());
	}

	public static class Change implements Comparable<Change> {
		int beginningS;
		String transition;
		int endingS;

		public Change(String beginningS, String transition, String endingS) {
			this.beginningS = Integer.parseInt(beginningS);
			this.transition = transition;
			this.endingS = Integer.parseInt(endingS);
		}

		@Override
		public int compareTo(Change other) {

			int result = Integer.compare(this.beginningS, other.beginningS);
			if (result == 0) {
				result = this.transition.compareTo(other.transition);
				if (result == 0) {
					result = Integer.compare(this.endingS, other.endingS);
				}
			}
			return result;
		}
	}
}