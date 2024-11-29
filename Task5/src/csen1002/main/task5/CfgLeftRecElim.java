package csen1002.main.task5;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Ahmed Essam
 * @id 49-2174
 * @labNumber 10
 */

public class CfgLeftRecElim {
	public String V = "";
	public String T = "";
	public String R = "";
	public String end = "";

	public String Varray[];
	public String Tarray[];
	public String Rarray[];
	public LinkedHashMap<String, ArrayList<String>> productionMap = new LinkedHashMap<String, ArrayList<String>>();

	public LinkedHashMap<String, ArrayList<String>> originalProductionMap = new LinkedHashMap<>();

	public CfgLeftRecElim(String cfg) {
		String[] parts = cfg.split("#");
		V = parts[0];
		T = parts[1];
		R = parts[2];
		Varray = splitStringBySemicolon(V);
		Tarray = splitStringBySemicolon(T);
		Rarray = splitStringBySemicolon(R);

		String[] productions = R.split(";");
		for (String production : productions) {
			String initialVariable = production.substring(0, 1);
			String[] initialVariableProductions = production.substring(2).split(",");
			ArrayList<String> productionList = new ArrayList<>(Arrays.asList(initialVariableProductions));
			productionMap.put(initialVariable, productionList);
		}

		for (String production : productions) {
			String initialVariable = production.substring(0, 1);
			String[] initialVariableProductions = production.substring(2).split(",");
			ArrayList<String> productionList = new ArrayList<>(Arrays.asList(initialVariableProductions));
			originalProductionMap.put(initialVariable, productionList);
		}

		System.out.println(productionMap);

	}

	public String[] splitStringBySemicolon(String str) {
		if (str == null || str.isEmpty()) {
			return new String[0];
		}
		return str.split(";");
	}

	public String toString() {
		StringBuilder concatenatedString = new StringBuilder();

		for (String cap : productionMap.keySet()) {
			concatenatedString.append(cap);
			concatenatedString.append(";");
		}

		concatenatedString.deleteCharAt(concatenatedString.length() - 1);
		concatenatedString.append("#");

		for (int i = 0; i < Tarray.length; i++) {
			concatenatedString.append(Tarray[i]);
			concatenatedString.append(";");
		}

		concatenatedString.deleteCharAt(concatenatedString.length() - 1);
		concatenatedString.append("#");

		for (String cap : productionMap.keySet()) {
			concatenatedString.append(cap).append("/");
			ArrayList<String> productions = productionMap.get(cap);
			for (int i = 0; i < productions.size(); i++) {
				concatenatedString.append(productions.get(i));
				if (i != productions.size() - 1) {
					concatenatedString.append(",");
				} else {
					concatenatedString.append(";");
				}
			}
		}

		concatenatedString.deleteCharAt(concatenatedString.length() - 1);

		return concatenatedString.toString();
	}

	public void doFirstInitialVariable() {
		boolean lR = false;
		ArrayList<String> switching = new ArrayList<>();
		ArrayList<String> switchingDashed = new ArrayList<>();

		for (String production : productionMap.get(Varray[0])) {
			if (production.charAt(0) == Varray[0].charAt(0)) {
				lR = true;
			} else {
				switching.add(production);
			}
		}

		if (lR == true) {

			int size = switchingDashed.size();
			for (int i = 0; i < size; i++) {
				switchingDashed.remove(0);
			}
			for (String production : productionMap.get(Varray[0])) {
				if (production.charAt(0) == Varray[0].charAt(0)) {
					String dashed = Varray[0] + "'";
					String dashedVars = production.substring(1) + dashed;
					switchingDashed.add(dashedVars);
				} else {
					String undashedVars = production + Varray[0] + "'";
					switching.add(undashedVars);
				}
			}
			switchingDashed.add("e");

			ArrayList<String> currentProductions = productionMap.get(Varray[0]);
			int sizes = currentProductions.size();
			for (int i = 0; i < sizes; i++) {
				currentProductions.remove(0);
			}

			for (String production : switching) {
				currentProductions.add(production);
			}
			for (String production : switchingDashed) {
				currentProductions.add(production);
			}
		}
	}

	public void detectAndEliminateLeftRecursion() {
		for (int i = 0; i < Varray.length; i++) {
			String initialV = Varray[i];
			elimLRinitalV(initialV, i);
		}
	}

	public void elimLRinitalV(String initialV, int index) {
		boolean lR = false;
		ArrayList<String> cfgafterProd = new ArrayList<>();
		ArrayList<String> varDash = new ArrayList<>();
		ArrayList<String> var = new ArrayList<>();

		if (index == 0) {
			processFirsts(initialV, lR, var, varDash);
		} else {
			processNotFirsts(initialV, index, cfgafterProd, lR, var, varDash);
		}
	}

	public void processFirsts(String initialV, boolean lR, ArrayList<String> var, ArrayList<String> varDash) {
		for (String production : productionMap.get(initialV)) {
			if (production.charAt(0) == initialV.charAt(0)) {
				lR = true;
			} else {
				var.add(production);
			}
		}

		if (lR) {
			int size = var.size();
			for (int i = 0; i < size; i++) {
				var.remove(0);
			}
			for (String production : productionMap.get(initialV)) {
				if (production.charAt(0) == initialV.charAt(0)) {
					String character = initialV + "'";
					String dashed = production.substring(1) + character;
					varDash.add(dashed);
				} else {
					String s = production + initialV + "'";
					var.add(s);
				}
			}
			varDash.add("e");
			updateProductionMap(initialV, var, varDash);
		}
	}

	public void updateProductionMap(String initialV, ArrayList<String> var, ArrayList<String> varDash) {

		int size = productionMap.get(initialV).size();
		for (int i = 0; i < size; i++) {
			productionMap.get(initialV).remove(0);
		}

		for (String production : var) {
			productionMap.get(initialV).add(production);
		}
		productionMap.put(initialV + "'", varDash);
	}

	public void processNotFirsts(String initialV, int index, ArrayList<String> cfgafterProd,
			boolean lR,
			ArrayList<String> var, ArrayList<String> varDash) {
		processProductions(initialV, index, cfgafterProd, var);
		checkLeftRecursion(initialV, lR, var, varDash);
	}

	public void processProductions(String initialV, int index, ArrayList<String> cfgafterProd, ArrayList<String> var) {
		for (int j = 0; j <= index - 1; j++) {
			for (String production : productionMap.get(initialV)) {
				if (production.charAt(0) == Varray[j].charAt(0)) {
					String firstPart = production.substring(1);
					for (String VarrayProduction : productionMap.get(Varray[j])) {
						String secondPart = VarrayProduction + firstPart;
						cfgafterProd.add(secondPart);
					}
				} else {
					if (!cfgafterProd.contains(production)) {
						cfgafterProd.add(production);
					}
				}
			}

			int size = productionMap.get(initialV).size();
			for (int i = 0; i < size; i++) {
				productionMap.get(initialV).remove(0);
			}

			for (String production : cfgafterProd) {
				productionMap.get(initialV).add(production);
			}
			int sizess = cfgafterProd.size();
			for (int i = 0; i < sizess; i++) {
				cfgafterProd.remove(0);
			}
		}
	}

	public void checkLeftRecursion(String initialV, boolean lR, ArrayList<String> var,
			ArrayList<String> varDash) {
		for (String production : productionMap.get(initialV)) {
			if (production.charAt(0) == initialV.charAt(0)) {
				lR = true;
			}
		}
		if (lR == true) {
			ArrayList<String> initialVProductions = productionMap.get(initialV);
			for (String production : initialVProductions) {
				if (production.charAt(0) == initialV.charAt(0)) {

					String dashed = production.substring(1) + initialV + "'";
					varDash.add(dashed);
				} else {
					String s = production + initialV + "'";
					var.add(s);
				}
			}
			if (!initialVProductions.contains("e")) {
				varDash.add("e");
			}

			for (int i = initialVProductions.size() - 1; i >= 0; i--) {
				initialVProductions.remove(i);
			}
			for (String production : var) {
				initialVProductions.add(production);
			}

			for (String production : varDash) {
				productionMap.put(initialV + "'", varDash);
			}
		}
	}

	public void eliminateLeftRecursion() {
		processProductions("", 0);
		detectAndEliminateLeftRecursion();

	}

	public String processProductions(String x, int iter) {
		StringBuilder finalProductions = new StringBuilder();

		LinkedHashMap<String, ArrayList<String>> originalProductionMapCopy = new LinkedHashMap<>(productionMap);

		ArrayList<String> productions = new ArrayList<>(Arrays.asList(R.split(";")));

		for (String production : productions) {
			System.out.println(productionMap);
			String variable = production.substring(0, 1);
			ArrayList<String> productionsList = productionMap.get(variable);
			StringBuilder updatedProduction = new StringBuilder();
			updatedProduction.append(variable).append("/");

			for (String prod : productionsList) {
				char firstChar = prod.charAt(0);
				int varIndex = V.indexOf(variable);
				int prodIndex = V.indexOf(firstChar);
				if (prodIndex < varIndex) {
					ArrayList<String> variableProductions = productionMap.get(firstChar + "");
					if (variableProductions != null) {
						for (String varProd : variableProductions) {
							updatedProduction.append(varProd).append(prod.substring(1)).append(",");
						}
					} else {

						updatedProduction.append(prod).append(",");
					}
				} else {
					updatedProduction.append(prod).append(",");
				}
			}
			updatedProduction.deleteCharAt(updatedProduction.length() - 1);
			finalProductions.append(updatedProduction).append(";");

			// processProductions();
			x += processRules(updatedProduction, productionMap) + ";";

			// System.out.println("yalla" + x);

		}
		x = x.substring(0, x.length() - 1);
		// full += x;
		// System.out.println("yayyyyy" + full);
		System.out.println(x);

		iter++;

		productionMap.clear();
		productionMap.putAll(originalProductionMapCopy);

		return finalProductions.toString();
	}

	public static String processRules(StringBuilder input, Map<String, ArrayList<String>> productionMap) {
		String[] parts = input.toString().split("/");

		String variable = parts[0];
		String[] rules = parts[1].split(",");

		StringBuilder newVariable = new StringBuilder();
		StringBuilder output = new StringBuilder();

		ArrayList<String> leftRec = new ArrayList<String>();

		for (String rule : rules) {
			if (rule.startsWith(variable) || rule.startsWith(variable + "'")) {
				leftRec.add(variable);
			} else {
				continue;
			}
		}

		for (String rule : rules) {
			if (leftRec.contains(variable)) {
				if (rule.startsWith(variable)) {

					newVariable.append(rule).append(variable).append("',");
					newVariable.deleteCharAt(0);
				} else {

					output.append(rule).append(variable + "',");
				}
			} else if (!leftRec.contains(variable)) {
				output.append(rule).append(",");
			}
		}

		if (newVariable.length() > 0) {
			newVariable.deleteCharAt(newVariable.length() - 1);
		}
		if (output.length() > 0) {
			output.deleteCharAt(output.length() - 1);
		}

		String newVariableString = variable + "'";
		String result = variable + "/" + output.toString();
		if (newVariable.length() > 0) {
			result += ";" + newVariableString + "/" + newVariable.toString();
		}
		// System.out.println("yoyoy" + variable);

		// System.out.println("here" + newVariableString);
		// System.out.println("hhahah" + output);

		productionMap.put(variable, new ArrayList<>(Arrays.asList(output.toString().split(","))));

		productionMap.put(newVariableString, new ArrayList<>(Arrays.asList(newVariable.toString().split(","))));

		return result;
	}

	public static void main(String[] args) {
		CfgLeftRecElim garrab = new CfgLeftRecElim(
				"S;T;L#a;b;c;d;i#S/ScTi,La,Ti,b;T/aSb,LabS,i;L/SdL,Si");
		garrab.eliminateLeftRecursion();
		System.out.println(garrab.toString());

		// garrab

	}

}