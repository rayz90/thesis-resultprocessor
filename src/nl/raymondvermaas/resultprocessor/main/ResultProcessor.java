package nl.raymondvermaas.resultprocessor.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.rits.cloning.Cloner;

import au.com.bytecode.opencsv.CSVReader;

import nl.raymondvermaas.resultprocessor.models.*;

public class ResultProcessor {

	private HashMap<Integer, Double> experts;
	private HashMap<String, BaseCriterion> bcs;
	private Scenario[] scenarios;

	public ResultProcessor(HashMap<Integer, Double> experts,
			HashMap<String, BaseCriterion> bcs, Scenario[] scenarios) {
		this.experts = experts;
		this.bcs = bcs;
		this.scenarios = scenarios;
	}

	public Scenario[] loadFromCsv(String filename)
			throws FileNotFoundException, IOException {
		HashMap<Integer, Criterion> critcol = new HashMap<Integer, Criterion>();

		CSVReader reader = new CSVReader(new FileReader(filename));
		String[] nextLine = reader.readNext();
		int firstCrit = firstCriterionId();
		int currentScenario = -1;
		for (int i = 0; i < nextLine.length; i++) {
			BaseCriterion bc = bcs.get(nextLine[i]);
			if (bc == null)
				continue;
			if (bc.getId() == firstCrit) {
				currentScenario++;
				scenarios[currentScenario].setCriteria(new Criterion[bcs.size()]);
			}	
			Criterion crit = new Criterion(bc, i);
			scenarios[currentScenario].addCriterion(bc.getId() - firstCrit,
					crit);
			critcol.put(new Integer(i), crit);
		}

		int curLine = 1;
		while ((nextLine = reader.readNext()) != null) {
			if (!experts.containsKey(new Integer(curLine))) {
				curLine++;
				continue;
			}
			double value = experts.get(new Integer(curLine)).doubleValue();

			for (int i = 0; i < nextLine.length; i++) {
				if (!critcol.containsKey(new Integer(i)))
					continue;

				String strIndex = nextLine[i];
				if (strIndex.compareTo("") == 0)
					continue;

				Criterion crit = critcol.get(new Integer(i));
				BaseCriterion bc = crit.getBase();
				int index = Integer.parseInt(strIndex) + bc.getIndexOffset();
				if (index > bc.getSize() - 1)
					index = bc.getSize() - 1;
				if (index < 0)
					index = 0;

				crit.add(index, value);
			}
			curLine++;
		}
		reader.close();

		return scenarios;
	}

	public Scenario[] getRiskScenarios(ArrayList<Impact> fraudscenarios, HashMap<AttackFraudCombination, Integer> attackfraudMatrix) {
		Cloner cloner = new Cloner();
		Scenario[] riskscenarios = new Scenario[attackfraudMatrix.size()];
		for (int i = 0; i < scenarios.length; i++) {
			for (int k = 0; k < fraudscenarios.size(); k++) {
				if(!attackfraudMatrix.containsKey(new AttackFraudCombination(i,k))) 
					continue;
				Scenario attack = cloner.deepClone(scenarios[i]);				
				int index = attackfraudMatrix.get(new AttackFraudCombination(i,k));
				for (int l = 0; l < bcs.size(); l++) {
					BaseCriterion bc = attack.getCriterion(l).getBase();
					for (int m = 0; m < bc.getMap().size(); m++) {
						bc.setValue(m,
								fraudscenarios.get(k).determineRiskValue(bc, m));
					}
				}
				attack.setName(attack.getName() + " - "
						+ fraudscenarios.get(k).getFraudscenario());
				riskscenarios[index] = attack;
			}
		}
		return riskscenarios;
	}

	private int firstCriterionId() {
		int first = Integer.MAX_VALUE;
		for (BaseCriterion bc : bcs.values()) {
			if (bc.getId() < first)
				first = bc.getId();
		}
		return first;
	}

	public String toString(Scenario[] scenarios) {
		String rtrn = "";
		for (Scenario scen : scenarios)
			rtrn += scen.toString() + "\n";
		return rtrn;
	}

	public void exportToXml(Scenario[] scenarios, String baseFile,
			String outputFile) {
		OutputXML xml = new OutputXML(baseFile, outputFile);
		xml.write(scenarios);
	}

}
