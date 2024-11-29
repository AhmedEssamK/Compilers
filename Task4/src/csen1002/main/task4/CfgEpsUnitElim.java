package csen1002.main.task4;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Ahmed Essam
 * @id 49-2174
 * @labNumber 10
 */

public class CfgEpsUnitElim {

	public String V = "";
	public String T = "";
	public String R = "";
	public Map<Character, String> vRmap;
	public Map<Character, ArrayList<String>> sRmap = new HashMap<>();

	Set<Character> gottenChars = new HashSet<>();
	ArrayList<Character> finals = new ArrayList<Character>();
	ArrayList<Character> characters = new ArrayList<Character>();

	public static boolean workOnRules(Map<Character, ArrayList<String>> sRmap) {
		boolean loopAgain = false;

		for (Map.Entry<Character, ArrayList<String>> entry : sRmap.entrySet()) {
			ArrayList<String> outputs = entry.getValue();

			outputs.replaceAll(rule -> {
				if (rule.length() == 1 && Character.isUpperCase(rule.charAt(0))) {
					char charLookfor = rule.charAt(0);
					ArrayList<String> ruleLookfor = sRmap.get(charLookfor);

					return String.join(",", ruleLookfor);
				} else {
					return rule;
				}
			});

			for (String rule : outputs) {
				if (rule.length() == 1 && Character.isUpperCase(rule.charAt(0))) {
					loopAgain = true;
					break;
				}
			}
		}

		return loopAgain;
	}

	public CfgEpsUnitElim(String cfg) {

		String[] parts = cfg.split("#");

		V = parts[0];
		T = parts[1];
		R = parts[2];
		makeCFGready(cfg);
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < V.length(); i++) {
			char key = V.charAt(i);
			ArrayList<String> values = sRmap.getOrDefault(key, new ArrayList<>());

			values.sort(String::compareTo);

			if (!values.isEmpty()) {

				stringBuilder.append(key);

				stringBuilder.append("/");

				stringBuilder.append(String.join(",", values));

				stringBuilder.append(";");
			}
		}

		if (stringBuilder.length() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}

		return V + "#" + T + "#" + stringBuilder;
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {

		this.vRmap = returnVRules();

	}

	private void makeCFGready(String cfg) {
		Map<Character, String> vRmap = new HashMap<>();

		String[] rules = R.split(";");

		for (String rule : rules) {

			String[] parts = rule.split("/");
			char variable = parts[0].charAt(0);
			String variableRules = parts[1];

			vRmap.put(variable, variableRules);
		}

		for (Map.Entry<Character, String> entry : vRmap.entrySet()) {
			char key = entry.getKey();
			String value = entry.getValue();
			String[] rulesArray = value.split(",");
			ArrayList<String> ruless = new ArrayList<>(Arrays.asList(rulesArray));
			sRmap.put(key, ruless);
		}
	}

	public void workEpsRules(Map<Character, ArrayList<String>> sRmap) {
		ArrayList<Character> finals = new ArrayList<>();

		for (Map.Entry<Character, ArrayList<String>> entry : sRmap.entrySet()) {
			char character = entry.getKey();
			ArrayList<String> ruless = entry.getValue();

			for (int i = 0; i < ruless.size(); i++) {
				String rule = ruless.get(i);

				if (rule.equals("e")) {
					finals.add(character);
				}
			}
		}

		for (Map.Entry<Character, ArrayList<String>> entry : sRmap.entrySet()) {
			ArrayList<String> outputs = entry.getValue();
			char c = entry.getKey();

			for (int i = 0; i < outputs.size(); i++) {
				String rule = outputs.get(i);

				boolean allCharactersInFinals = true;
				for (int j = 0; j < rule.length(); j++) {
					char currentChar = rule.charAt(j);
					if (!finals.contains(currentChar)) {
						allCharactersInFinals = false;
						break;
					}
				}

				if (allCharactersInFinals && !outputs.contains("e") && !characters.contains(c)) {
					outputs.add("e");
					System.out.println("an e is addeddddd:  " + c);
					characters.add(c);
				}
			}
		}

	}

	public Map<Character, String> returnVRules() {

		boolean containsE;

		do {
			containsE = false;

			for (Map.Entry<Character, ArrayList<String>> entry : sRmap.entrySet()) {
				char character = entry.getKey();
				ArrayList<String> ruless = entry.getValue();
				workEpsRules(sRmap);

				for (int i = 0; i < ruless.size(); i++) {
					String rule = ruless.get(i);

					if (rule.equals("e")) {

						workOnRulesatedRules(sRmap, character);
						ruless.remove(i);
						i = -1;
						containsE = true;
					}
				}

			}
		} while (containsE);

		return vRmap;
	}

	public void workOnRulesatedRules(Map<Character, ArrayList<String>> sRmap, char charLookfor) {

		for (Map.Entry<Character, ArrayList<String>> entry : sRmap.entrySet()) {
			char character = entry.getKey();
			ArrayList<String> outputs = entry.getValue();

			if (character != charLookfor || true) {

				if (!gottenChars.contains(character) || true) {

					for (int i = 0; i < outputs.size(); i++) {
						String rule = outputs.get(i);

						if (rule.equals(String.valueOf(charLookfor))) {

							if (!gottenChars.contains(character)) {

								if (!outputs.contains("e")) {
									outputs.add("e");
									gottenChars.add(character);
								}
							}
						}

						int index = rule.indexOf(charLookfor);
						while (index != -1) {

							String updatedRule = rule.substring(0, index) + rule.substring(index + 1);

							if (!outputs.contains(updatedRule) && !updatedRule.equals("")) {
								outputs.add(updatedRule);
							}

							index = rule.indexOf(charLookfor, index + 1);
						}
					}
				}
			}
		}
	}

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
		do {

			StringBuilder stringBuilder = new StringBuilder();

			for (int i = 0; i < V.length(); i = i + 2) {
				char key = V.charAt(i);
				ArrayList<String> outputs = sRmap.getOrDefault(key, new ArrayList<>());

				outputs.removeIf(rule -> rule.length() == 1 && rule.charAt(0) == key);

				outputs.replaceAll(rule -> {
					if (rule.length() == 1 && Character.isUpperCase(rule.charAt(0))) {
						char charLookfor = rule.charAt(0);
						ArrayList<String> ruleLookfor = sRmap.get(charLookfor);

						return String.join(",", ruleLookfor);
					} else {
						return rule;
					}
				});

				Set<String> uniqueRules = new HashSet<>(outputs);

				List<String> sortedRules = new ArrayList<>(uniqueRules);
				sortedRules.sort(String::compareTo);

				if (!sortedRules.isEmpty()) {
					stringBuilder.append(key);
					stringBuilder.append("/");
					stringBuilder.append(String.join(",", sortedRules));
					stringBuilder.append(";");
				}
			}

			sRmap.clear();

			String[] entries = stringBuilder.toString().split(";");
			for (String entry : entries) {
				String[] parts = entry.split("/");
				char character = parts[0].charAt(0);
				String[] rulesArray = parts[1].split(",");
				ArrayList<String> ruless = new ArrayList<>(Arrays.asList(rulesArray));
				sRmap.put(character, ruless);
			}

		} while (workOnRules(sRmap));

		for (Map.Entry<Character, ArrayList<String>> entry : sRmap.entrySet()) {
			ArrayList<String> outputs = entry.getValue();
			removeDupes(outputs);
		}

		for (Map.Entry<Character, ArrayList<String>> entry : sRmap.entrySet()) {
			ArrayList<String> outputs = entry.getValue();

			outputs.removeIf(rule -> rule.length() == 1 && Character.isUpperCase(rule.charAt(0)));
		}

	}

	public static void removeDupes(ArrayList<String> outputs) {

		Set<String> differentRules = new HashSet<>();
		List<String> differentOutputs = new ArrayList<>();

		for (String rule : outputs) {

			String[] ruleParts = rule.split("[,\\s]+");

			for (String part : ruleParts) {
				String withoutSpace = part.trim();
				if (!withoutSpace.isEmpty()) {
					differentRules.add(withoutSpace);
				}
			}
		}

		differentOutputs.addAll(differentRules);
		outputs.clear();
		outputs.addAll(differentOutputs);
	}

	public static void main(String[] args) {
		CfgEpsUnitElim test = new CfgEpsUnitElim(
				"S;O;B;J;K;G;U#m;p;u;y;z#S/SJ,z,zJuU,zSUm;O/B,G,S,e,pOJz,yJuK;B/BSzO,S,U,yBz,zO;J/G,zOOKU,zUG;K/G,S,mBmOU;G/GpU,JyS,Km,O,e,z;U/J,uBJSB");

		test.eliminateEpsilonRules();
		System.out.println(test.toString());

	}

}