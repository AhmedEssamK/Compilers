package csen1002.main.task8;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Ahmed Essam
 * @id 49-2174
 * @labNumber 10
 */
// finallll
public class CfgLl1Parser {

	public Map<String, List<String>> grammarMap = new HashMap<>();
	public Map<String, List<String>> firstMap = new HashMap<>();
	public Map<String, Map<String, List<String>>> pTable = new HashMap<>();

	public CfgLl1Parser(String input) {
		String[] cfg = input.split("#");
		String[] vars = cfg[0].split(";");
		String[] terms = cfg[1].split(";");
		String[] gomal = cfg[2].split(";");
		String[] first = cfg[3].split(";");
		String[] foll = cfg[4].split(";");

		ebdaaGMap(gomal);
		ebdaaFMap(first);
		ebdaaTable(vars, terms);
		ebdaaTableFeehaE(vars, terms);
		ebdaaTableFeehaf(vars, terms, first, foll);
	}

	public void initMap(String[] data, Map<String, List<String>> map) {
		for (String entry : data) {
			String[] parts = faraaelydakhel(entry);
			if (parts.length == 2) {
				String key = parts[0];
				List<String> values = faraaelValues(parts[1]);
				map.put(key, values);
			}
		}
	}

	public String[] faraaelydakhel(String entry) {
		return entry.split("/", 2);
	}

	public List<String> faraaelValues(String valuesPart) {
		String[] valuesArray = valuesPart.split(",");
		return new ArrayList<>(Arrays.asList(valuesArray));
	}

	public void ebdaaGMap(String[] gomal) {
		initMap(gomal, grammarMap);
	}

	public void ebdaaFMap(String[] first) {
		initMap(first, firstMap);
	}

	public void ebdaaTable(String[] vars, String[] terms) {
		for (String terminal : terms) {
			List<String> a1 = new ArrayList<>();
			for (String var : vars) {
				pTable.put(var, new HashMap<>());
				pTable.get(var).put(terminal, a1);
			}
		}
	}

	public void ebdaaTableFeehaE(String[] vars, String[] terms) {
		List<String> a0 = new ArrayList<>();
		for (String var : vars) {
			Map<String, List<String>> mapMoakat = new HashMap<>();
			mapMoakat.put("$", a0);
			for (String terminal : terms) {
				List<String> a5 = new ArrayList<>();
				mapMoakat.put(terminal, a5);
			}
			pTable.put(var, mapMoakat);
		}
	}

	public void ebdaaTableFeehaf(String[] vars, String[] terms, String[] first,
			String[] foll) {
		for (String var : vars) {
			Map<String, List<String>> varTable = eemelInitvarTable(terms);
			List<String> fSymbols = firstMap.get(var);
			eemelfinals(var, fSymbols, varTable, foll);
			pTable.put(var, varTable);
		}
		System.out.println(pTable);
	}

	public Map<String, List<String>> eemelInitvarTable(String[] terms) {
		Map<String, List<String>> table = new HashMap<>();
		table.put("$", new ArrayList<>());
		for (String terminal : terms) {
			table.put(terminal, new ArrayList<>());
		}
		return table;
	}

	public void eemelfinals(String var, List<String> fSymbols, Map<String, List<String>> varTable, String[] foll) {
		int index = 0;
		for (String fSymbol : fSymbols) {
			if (fSymbol.contains("e")) {
				eemelE(var, varTable, foll);
			} else {
				zawedProds(var, index, fSymbol, varTable);
			}
			index++;
		}
	}

	public void eemelE(String var, Map<String, List<String>> varTable, String[] foll) {
		for (String follEntry : foll) {
			if (follEntry.startsWith(var)) {
				String folls = follEntry.substring(2);
				for (char follSymbol : folls.toCharArray()) {
					varTable.get(String.valueOf(follSymbol)).add("e");
				}
			}
		}
	}

	public void zawedProds(String var, int productionIndex, String fSymbol,
			Map<String, List<String>> varTable) {
		for (char symbol : fSymbol.toCharArray()) {
			List<String> productions = new ArrayList<>();
			productions.add(grammarMap.get(var).get(productionIndex));
			varTable.put(String.valueOf(symbol), productions);
		}
	}

	public String parse(String input) {
		StringBuilder elyhayetbeny = new StringBuilder(input);
		elyhayetbeny.append("$");
		String elyetbana = elyhayetbeny.toString();

		Deque<String> elStack = initelStack();
		List<String> elyhayetlaa = initelyetbana();

		int ind = 0;
		while (!elStack.isEmpty()) {
			String elStackTop = elStack.pop();
			String currentInput = hatelChar(elyetbana, ind);

			if (akhro(elStackTop, currentInput)) {
				if (!currentInput.equals("$")) {
					elyhayetlaa.add("ERROR");
				}
				break;
			}

			if (Character.isUpperCase(elStackTop.charAt(0))) {
				if (!eemelElNT(elStackTop, currentInput, elStack, elyhayetlaa)) {
					break;
				}
			} else if (!eemelT(elStackTop, currentInput, elyhayetlaa)) {
				break;
			}

			ind = updateind(elStackTop, currentInput, ind);
		}

		return akherString(elyhayetlaa);
	}

	public Deque<String> initelStack() {
		Deque<String> elStack = new ArrayDeque<>();
		elStack.push("$");
		elStack.push("S");
		return elStack;
	}

	public List<String> initelyetbana() {
		List<String> elyhayetlaa = new ArrayList<>();
		elyhayetlaa.add("S");
		return elyhayetlaa;
	}

	public String hatelChar(String input, int index) {
		return index < input.length() ? input.charAt(index) + "" : "$";
	}

	public boolean akhro(String elStackTop, String currentInput) {
		return elStackTop.equals("$") && !currentInput.equals("$");
	}

	public boolean eemelElNT(String nonTerminal, String input, Deque<String> elStack,
			List<String> elyhayetlaa) {
		Map<String, List<String>> possibleProductions = pTable.get(nonTerminal);
		if (possibleProductions != null && possibleProductions.containsKey(input)) {
			return eemelProd(nonTerminal, input, possibleProductions.get(input), elStack, elyhayetlaa);
		}
		elyhayetlaa.add("ERROR");
		return false;
	}

	public boolean eemelProd(String nonTerminal, String input, List<String> productions, Deque<String> elStack,
			List<String> elyhayetlaa) {
		if (!productions.isEmpty()) {
			String production = productions.get(0);
			updateelyhayetlaaAndelStack(nonTerminal, production, elyhayetlaa, elStack);
			return true;
		}
		elyhayetlaa.add("ERROR");
		return false;
	}

	public void updateelyhayetlaaAndelStack(String nonTerminal, String production, List<String> elyhayetlaa,
			Deque<String> elStack) {
		if (production.equals("e")) {
			elyhayetlaa.add(elyhayetlaa.get(elyhayetlaa.size() - 1).replaceFirst(nonTerminal, ""));
		} else {
			elyhayetlaa.add(elyhayetlaa.get(elyhayetlaa.size() - 1).replaceFirst(nonTerminal, production));

			String reversedProduction = new StringBuilder(production).reverse().toString();

			for (char ch : reversedProduction.toCharArray()) {
				elStack.push(String.valueOf(ch));
			}
		}
	}

	public boolean eemelT(String terminal, String input, List<String> elyhayetlaa) {
		if (terminal.equals(input)) {
			return true;
		}
		elyhayetlaa.add("ERROR");
		return false;
	}

	public int updateind(String elStackTop, String currentInput, int currentind) {
		if (Character.isLowerCase(elStackTop.charAt(0)) && elStackTop.equals(currentInput)) {
			return currentind + 1;
		}
		return currentind;
	}

	public String akherString(List<String> elyhayetlaa) {
		return String.join(";", elyhayetlaa);
	}

}
