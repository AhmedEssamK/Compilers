package csen1002.main.task6;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Mayar Khaled
 * @id 49-0556
 * @labNumber 22
 */

public class CfgFirstFollow {

	ArrayList<String> variablesList;
	ArrayList<String> terminalsList;
	ArrayList<Rule> rulesList;

	public CfgFirstFollow(String cfg) {
		// ----------start of input string parsing part and variable
		// assignment-----------
		String[] parts = cfg.split("#");
		// ------ parsing of variables----------
		ArrayList<String> variablesList = new ArrayList<>();
		String variables = parts[0];
		String[] variablesL = variables.split(";");
		for (String curVariable : variablesL) {
			variablesList.add(curVariable.charAt(0) + "");
		}

		// ------ parsing of terminals----------
		ArrayList<String> terminalsList = new ArrayList<>();
		String terminals = parts[1];
		String[] terminalsL = terminals.split(";");
		for (String curTerminal : terminalsL) {
			terminalsList.add(curTerminal.charAt(0) + "");
		}

		// ------ parsing of rules----------
		ArrayList<Rule> rulesList = new ArrayList<Rule>();
		String rulesE = parts[2];
		String[] ruleParts = rulesE.split(";");
		for (String rulePart : ruleParts) {
			String[] ruleComponents = rulePart.split("/");
			String variable = ruleComponents[0].charAt(0) + "";
			String[] rules = ruleComponents[1].split(",");
			Set<String> ruleList = new LinkedHashSet<>(Arrays.asList(rules));
			Rule rule = new Rule(variable, ruleList);
			rulesList.add(rule);
		}

		for (Rule rule : rulesList) {
			System.out.println(rule);
		}

		this.rulesList = rulesList;
		this.terminalsList = terminalsList;
		this.variablesList = variablesList;

	}

	/**
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {

		ArrayList<String> variablesList = new ArrayList<>();
		ArrayList<String> terminalsList = new ArrayList<>();
		ArrayList<Rule> rulesList = new ArrayList<>();

		variablesList = this.variablesList;
		terminalsList = this.terminalsList;
		rulesList = this.rulesList;

		// ArrayList<String> firstList = new ArrayList<>();
		// Set<String> firstHash = new LinkedHashSet<>();
		LinkedHashMap<String, Set<String>> firstHashMap = new LinkedHashMap<>();

		for (String terminal : terminalsList) {
			Set<String> terminalSet = new HashSet<>();
			terminalSet.add(terminal);
			firstHashMap.put(terminal, terminalSet);
		}

		for (String variable : variablesList) {
			firstHashMap.put(variable, new HashSet<>());
		}

		// for (Map.Entry<String, Set<String>> entry : firstHashMap.entrySet()) {
		// System.out.println(entry.getKey() + ": " + entry.getValue());
		// }

		boolean changed = true;

		do {
			changed = false; // Reset the flag for each iteration
			for (int i = 0; i < variablesList.size(); i++) {

				String curVariable = variablesList.get(i);
				Set<String> curFirstList = new HashSet<>(firstHashMap.get(curVariable));
				Set<String> curFirstListStart = new HashSet<>(firstHashMap.get(curVariable));

				// System.out.println("curVariable: "+curVariable);
				// System.out.println("curFirstList: "+curFirstList);
				// System.out.println("curFirstListStart: "+curFirstListStart);
				// Compute the first set for the current variable
				for (int j = 0; j < rulesList.size(); j++) {
					Rule curRule = rulesList.get(j);
					if (curRule.getVariable().equals(curVariable)) {
						for (String curRuleWithinTheRuleStrings : curRule.getRules()) {

							// boolean test2=checkBeforeOccurrence2(curRuleWithinTheRuleStrings, symbol,
							// firstHashMap);
							// if(m>curRuleWithinTheRuleStrings.length()) {
							// System.out.println("check");
							// if(test2) {
							// curFirstList.add("e");
							// }
							// }
							boolean epsilonInAllFirst = true;
							for (int l = 0; l < curRuleWithinTheRuleStrings.length(); l++) {
								char symbol = curRuleWithinTheRuleStrings.charAt(0);
								if (Character.isLowerCase(symbol)) {
									epsilonInAllFirst = false;
									break;
								}
							}
							for (int l = 0; l < curRuleWithinTheRuleStrings.length(); l++) {
								char symbol = curRuleWithinTheRuleStrings.charAt(l);
								// System.out.println(symbol+"symbolsymbol");
								if (symbol == 'e') {
									epsilonInAllFirst = true;
									break;
								}
								Set<String> curFirstV = new HashSet<>(firstHashMap.get(symbol + ""));
								if (!("e".equals(symbol + ""))) {
									if (!curFirstV.contains("e")) {
										epsilonInAllFirst = false;
										break;
									}
								}

							}

							if (epsilonInAllFirst && !curFirstList.contains("e")) {
								curFirstList.add("e");
								changed = true;
							}

							for (int m = 0; m < curRuleWithinTheRuleStrings.length(); m++) {
								boolean test = false;
								// System.out.println("curRuleWithinTheRuleStrings:
								// "+curRuleWithinTheRuleStrings);
								char symbol = curRuleWithinTheRuleStrings.charAt(m);
								if (Character.isLowerCase(symbol)) {
									test = checkBeforeOccurrence(curRuleWithinTheRuleStrings, symbol, firstHashMap);
								}

								if (((Character.isLowerCase(symbol) && m == 0) || test)) {
									// Terminal
									// System.out.println(symbol + " is a terminal");
									curFirstList.add(symbol + "");
									break;
								}
								// && (!curVariable.equals(symbol+""))
								if ((Character.isUpperCase(symbol) && m == 0) || test) {
									// if(!curVariable.equals(symbol+"")) {
									// Variable
									// System.out.println(symbol + " is a variable");
									Set<String> curFirstV = new HashSet<>(firstHashMap.get(symbol + ""));
									// System.out.println("curFirstV"+curFirstV);
									for (String s : curFirstV) {

										if (!s.equals("e") && terminalsList.contains(s)
												&& (!variablesList.contains(s))) {
											// System.out.println("entered e flag");
											// System.out.println("sssssssssssss"+s);
											curFirstList.add(s + "");
											// break;
											// boolean eflag=true;
										}
									}
								}

							}
						}
					}
				}

				// System.out.println("curFirstListStart at the end"+curFirstListStart);
				// System.out.println("curFirstList at the end"+curFirstList);
				if (!curFirstListStart.equals(curFirstList)) {
					firstHashMap.put(curVariable, curFirstList);
					changed = true; // Mark that a change has occurred
					// System.out.println("new iteration");
				}

			}
		} while (changed); // Continue until there are no more changes)

		// for (Map.Entry<String, Set<String>> entry : firstHashMap.entrySet()) {
		// System.out.println(entry.getKey() + ": " + entry.getValue());
		// }

		StringBuilder result = new StringBuilder();
		for (Map.Entry<String, Set<String>> entry : firstHashMap.entrySet()) {
			String key = entry.getKey();
			if (key.length() == 1 && Character.isUpperCase(key.charAt(0))) {
				result.append(key).append("/");
				List<String> sortedFirsts = new ArrayList<>(entry.getValue());
				Collections.sort(sortedFirsts);
				for (String first : sortedFirsts) {
					// if(!variablesList.contains(first)) {
					result.append(first);
					// }

				}
				result.append(";");
			}
		}

		result.deleteCharAt(result.length() - 1);
		System.out.println("hwnak" + result);
		return result.toString();
	}

	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 * 
	 */
	public String follow() {

		LinkedHashMap<String, Set<String>> firstHashMap = new LinkedHashMap<>();
		String firstResult = this.first();
		// Split the firstResult string into key-value pairs
		String[] pairs = firstResult.split(";");
		for (String pair : pairs) {
			String[] keyValue = pair.split("/");
			if (keyValue.length == 2) {
				String key = keyValue[0];
				String[] values = keyValue[1].split(",");
				Set<String> valueSet = new HashSet<>();
				for (String value : values) {
					for (char c : value.toCharArray()) {
						valueSet.add(String.valueOf(c));
					}
				}
				firstHashMap.put(key, valueSet);
			}
		}
		for (String terminal : terminalsList) {
			Set<String> terminalSet = new HashSet<>();
			terminalSet.add(terminal);
			firstHashMap.put(terminal, terminalSet);
		}

		for (Map.Entry<String, Set<String>> entry : firstHashMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue() + "follow");
		}

		ArrayList<String> variablesList = new ArrayList<>();
		ArrayList<String> terminalsList = new ArrayList<>();
		ArrayList<Rule> rulesList = new ArrayList<>();

		variablesList = this.variablesList;
		terminalsList = this.terminalsList;
		rulesList = this.rulesList;

		LinkedHashMap<String, Set<String>> followHashMap = new LinkedHashMap<>();

		for (String variable : variablesList) {
			if (variable.equals("S")) {
				Set<String> startFollowSet = new HashSet<>();
				startFollowSet.add("$");
				followHashMap.put(variable, startFollowSet);
			} else {
				followHashMap.put(variable, new HashSet<>());
			}

		}

		for (Map.Entry<String, Set<String>> entry : followHashMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		for (int k = 0; k < 30; k++) {

			boolean changed = true;
			while (changed) {
				changed = false;
				for (int i = 0; i < rulesList.size(); i++) {
					Rule curRule = rulesList.get(i);
					String curVariable = curRule.getVariable();
					Set<String> followOfCurVariable = new HashSet<>(followHashMap.get(curVariable));
					// followHashMap.get(curVariable); // follow(A)
					Set<String> curRules = curRule.getRules();
					System.out.println("curVariablecurVariable" + curVariable);
					System.out.println("followOfCurVariable" + followOfCurVariable);
					System.out.println("curRules" + curRules);
					for (String curStringRule : curRules) {
						for (int m = 0; m < curStringRule.length(); m++) {
							if (!Character.isLowerCase(curStringRule.charAt(m))) {
								System.out.println("curStringRule: " + curStringRule);
								char symbol = curStringRule.charAt(m);
								System.out.println("symbolsymbolsymbol" + symbol);
								Set<String> followOfCurVariableInRule = followHashMap.get(symbol + ""); // follow(A)
								// new HashSet<>(followHashMap.get(curVariable));
								if (m + 1 < curStringRule.length()) {

									char nextSymbol = curStringRule.charAt(m + 1);
									System.out.println("nextSymbolnextSymbol" + nextSymbol);
									System.out.println("firstHashMap" + firstHashMap);
									// Set<String> followOfNextCharInRule = followHashMap.get(nextSymbol+""); //
									// follow(BetA)
									System.out.println(
											"firstHashMap.get(nextSymbol+\"\")" + firstHashMap.get(nextSymbol + ""));
									Set<String> firstOfNextCharInRule = firstHashMap.get(nextSymbol + ""); // first(BetA)
									if (Character.isLowerCase(nextSymbol)) {
										System.out.println("here");
										followOfCurVariableInRule.add(nextSymbol + ""); // Terminal symbol
										System.out.println(followOfCurVariableInRule + "followOfCurVariableInRule");
										// changed=true;
										continue;
									} else {
										// Set<String> firstOfNextSymbol = firstHashMap.get(nextSymbol + ""); // First
										// of next symbol
										followOfCurVariableInRule.addAll(firstOfNextCharInRule);
										if (firstOfNextCharInRule.contains("e")) {
											followOfCurVariableInRule.remove("e");
											// followOfCurVariableInRule.addAll(followOfCurVariableInRule); // Add
											// follow(A)
											// changed=true;
										}
										continue;
									}
								} else {
									System.out.println("curVariablecurVariable" + curVariable);
									System.out.println("followOfCurVariablefollowOfCurVariable" + followOfCurVariable);
									for (String s : followOfCurVariable) {
										followOfCurVariableInRule.add(s);
									}

									// followOfCurVariableInRule.addAll(followOfCurVariable); // Add follow(A)
								}
							}

						}
					}

				}

			}

		}

		for (Map.Entry<String, Set<String>> entry : followHashMap.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		StringBuilder result = new StringBuilder();
		for (Map.Entry<String, Set<String>> entry : followHashMap.entrySet()) {
			String key = entry.getKey();
			if (key.length() == 1 && Character.isUpperCase(key.charAt(0))) {
				result.append(key).append("/");
				List<String> sortedFollows = new ArrayList<>(entry.getValue());
				Collections.sort(sortedFollows);
				for (String follow : sortedFollows) {
					result.append(follow);
				}
				result.append(";");
			}
		}

		result.deleteCharAt(result.length() - 1);
		System.out.println(result);
		return result.toString();
	}

	public static void main(String[] args) {
		CfgFirstFollow test = new CfgFirstFollow(
				"S;Z;R;D;E;U;O#i;l;m;p;q;u;x#S/x,E;Z/EOl,RRO,U;R/SxDx,ZSxUp,U,Ux,Z,S;D/iZpZ,UxO,R,e;E/ZZOq,Ox;U/uOE,pEZmO,e,U;O/U,p,m,ORE");
		// "S;P;I;T;W;X;C#g;h;l;t;v;x;z#S/gXCPI,SvI;P/g,gThCX,W;I/Sv,XXIC,S;T/XTWx,TCT,WP,hITXS,SCTXh,lWt;W/IWWX,vCXT,e,X,S,I;X/xCx,z,e,X;C/vXX,xXCXX,PITx,zX,SlPv"
		// "S;T;L#a;b;c;d;i#S/ScT,T;T/aSb,iaLb,e;L/SdL,S"
		test.first();
		// test.follow();
	}

	boolean checkBeforeOccurrence(String str, char targetChar, Map<String, Set<String>> firstHashMap) {
		String substringBeforeTarget = str.substring(0, str.indexOf(targetChar));
		if (substringBeforeTarget.length() == 0) {
			return false;
		}
		System.out.println("Substring before target: " + substringBeforeTarget);
		for (char c : substringBeforeTarget.toCharArray()) {
			System.out.println("Checking character: " + c);
			if (Character.isLowerCase(c)) {
				System.out.println("Terminal symbol found: " + c);
				return false; // Terminal symbol
			}
			String variable = String.valueOf(c);
			System.out.println("Checking variable: " + variable);
			Set<String> firstSet = firstHashMap.get(variable);
			System.out.println("First set of " + variable + ": " + firstSet);
			if (firstSet.contains("e")) {
				System.out.println("Variable " + variable + " derives epsilon.");
				continue; // Variable derives epsilon, check next character
			} else {
				System.out.println("Variable " + variable + " does not derive epsilon.");
				return false; // Variable does not derive epsilon
			}
		}
		return true; // All variables before the target char derive epsilon
	}

	// boolean checkBeforeOccurrence2(String str, char targetChar, Map<String,
	// Set<String>> firstHashMap) {
	// String substringBeforeTarget = str.substring(0, str.indexOf(targetChar));
	// if(substringBeforeTarget.length()==0 && Character.isLowerCase(targetChar)) {
	// return true;
	// }
	// System.out.println("Substring before target: " + substringBeforeTarget);
	// for (char c : substringBeforeTarget.toCharArray()) {
	// System.out.println("Checking character: " + c);
	// if (Character.isLowerCase(c)) {
	// System.out.println("Terminal symbol found: " + c);
	// return false; // Terminal symbol
	// }
	// String variable = String.valueOf(c);
	// System.out.println("Checking variable: " + variable);
	// Set<String> firstSet = firstHashMap.get(variable);
	// System.out.println("First set of " + variable + ": " + firstSet);
	// if (firstSet.contains("e")) {
	// System.out.println("Variable " + variable + " derives epsilon.");
	// continue; // Variable derives epsilon, check next character
	// } else {
	// System.out.println("Variable " + variable + " does not derive epsilon.");
	// return false; // Variable does not derive epsilon
	// }
	// }
	// return true; // All variables before the target char derive epsilon
	// }

	public class Rule {
		String variable;
		Set<String> rules;

		public Rule(String variable, Set<String> rules) {
			this.variable = variable;
			this.rules = rules;
		}

		public String getVariable() {
			return variable;
		}

		public Set<String> getRules() {
			return rules;
		}

		public void setRules(Set<String> rules) {
			this.rules = rules;
		}

		@Override
		public String toString() {
			return variable + "/" + rules.toString();
		}
	}

}