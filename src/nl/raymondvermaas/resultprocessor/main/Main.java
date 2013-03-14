package nl.raymondvermaas.resultprocessor.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.collect.Lists;

import nl.raymondvermaas.resultprocessor.models.*;

public class Main {

	private static final String CSV_FILENAME = "/home/raymondv/Dropbox/Master/Thesis/data/20130214_results.csv";
	private static final String BASE_XML = "/home/raymondv/Dropbox/Master/Thesis/resultprocessor_impact/resultprocessor/base.xml";
	private static final String OUTPUT_XML = "/home/raymondv/thesis-model.jsmaa";
	public static final boolean IMPACTCALCULATION = true;
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		HashMap<Integer, Double> experts = new HashMap<Integer, Double>();
		experts.put(new Integer(28), 0.00462000); // 1
		experts.put(new Integer(27), 0.00000901); // 2
		experts.put(new Integer(26), 0.00000000); // 3
		experts.put(new Integer(25), 0.28850000); // 4
		experts.put(new Integer(24), 0.00000000); // 5
		experts.put(new Integer(23), 0.00567300); // 6
		experts.put(new Integer(22), 0.00086970); // 7
		experts.put(new Integer(21), 0.11710000); // 8
		experts.put(new Integer(20), 0.01200000); // 9
		experts.put(new Integer(19), 0.00000000); // 10
		experts.put(new Integer(18), 0.00000000); // 11
		experts.put(new Integer(17), 0.03243000); // 12
		experts.put(new Integer(16), 0.00000000); // 13
		experts.put(new Integer(15), 0.00010560); // 14
		experts.put(new Integer(14), 0.00000000); // 15
		experts.put(new Integer(13), 0.12200000); // 16
		experts.put(new Integer(12), 0.00008797); // 17
		experts.put(new Integer(11), 0.03494000); // 18
		experts.put(new Integer(10), 0.03571000); // 19
		experts.put(new Integer(9), 0.00000000); // 20
		experts.put(new Integer(8), 0.03014000); // 21
		experts.put(new Integer(7), 0.00647100); // 22
		experts.put(new Integer(6), 0.00009114); // 23
		experts.put(new Integer(5), 0.00019090); // 24
		experts.put(new Integer(4), 0.00000000); // 25
		experts.put(new Integer(3), 0.15610000); // 26
		experts.put(new Integer(2), 0.15300000); // 27

		HashMap<String, BaseCriterion> bcs = new HashMap<String, BaseCriterion>();
		ArrayList<Double> weeks = new ArrayList<Double>();
		for (int i = 0; i < weeks.size(); i++) {
			weeks.add(new Double(i));
		}
		bcs.put("Elapsed time in weeks", new BaseCriterion(12, "Elapsed Time",
				weeks, Lists.newArrayList(new Double[]{2.0,4.0}),0));
		bcs.put("Specialist expertise", new BaseCriterion(13,
				"Specialist Expertise", Lists.newArrayList(new Double[] { 0.0, 2.0, 5.0, 5.0 }), Lists.newArrayList(new Double[]{2.0,4.0}),-1));
		bcs.put("Knowledge of the system", new BaseCriterion(14,
				"Knowledge of the system", Lists.newArrayList(new Double[] { 0.0, 1.0, 4.0, 10.0 }), Lists.newArrayList(new Double[]{2.0,4.0}),-1));
		bcs.put("Window of opportunity", new BaseCriterion(15,
				"Window of opportunity", Lists.newArrayList(new Double[] { 0.0, 1.0, 4.0, 12.0, 26.0 }), Lists.newArrayList(new Double[]{2.0,4.0}),-1));
		bcs.put("Equipment availability", new BaseCriterion(16,
				"Equipment availability", Lists.newArrayList(new Double[] { 0.0, 3.0, 7.0, 7.0 }), Lists.newArrayList(new Double[]{2.0,4.0}),-1));

		Scenario[] scenarios = new Scenario[] {
				new Scenario("Relay Attack", 
						new Criterion[bcs.size()]),
				new Scenario("Relay Attack using malicious app",
						new Criterion[bcs.size()]),
				new Scenario("Eavesdropping using malicious app",
						new Criterion[bcs.size()]),
				new Scenario("Eavesdropping", 
						new Criterion[bcs.size()]),
				new Scenario("Eavesdropping by exploiting the terminal",
						new Criterion[bcs.size()]),
				new Scenario("Modify transactions by exploiting the terminal",
						new Criterion[bcs.size()]),
				new Scenario("Malicious terminal", 
						new Criterion[bcs.size()]),
				new Scenario("DoS using 0day", 
						new Criterion[bcs.size()]),
				new Scenario("DoS of terminal", 
						new Criterion[bcs.size()]),
				new Scenario("Theft", 
						new Criterion[bcs.size()]) };

		ArrayList<Impact> fraudscenarios = Lists.newArrayList(new Impact[] {
				new Impact("Privacy Infringement", 2.0),
				new Impact("Single malicious transaction", 3.0),
				new Impact("Multiple malicious transactions", 2.0),
				new Impact("Disable service", 3.0) });
		
		HashMap<AttackFraudCombination, Integer> attackfraudMatrix = new HashMap<AttackFraudCombination, Integer>();
		 attackfraudMatrix.put(new AttackFraudCombination(0, 0),0);
	     attackfraudMatrix.put(new AttackFraudCombination(0, 1),1);
	     attackfraudMatrix.put(new AttackFraudCombination(1, 2),2);
	     attackfraudMatrix.put(new AttackFraudCombination(2, 0),3);
	     attackfraudMatrix.put(new AttackFraudCombination(3, 0),4);
	     attackfraudMatrix.put(new AttackFraudCombination(4, 0),5);
	     attackfraudMatrix.put(new AttackFraudCombination(5, 1),6);
	     attackfraudMatrix.put(new AttackFraudCombination(6, 2),7);
	     attackfraudMatrix.put(new AttackFraudCombination(7, 3),8);
	     attackfraudMatrix.put(new AttackFraudCombination(8, 3),9);
	     attackfraudMatrix.put(new AttackFraudCombination(9, 0),10);
	     attackfraudMatrix.put(new AttackFraudCombination(9, 2),11);
			
	     /*{ 0, 1, -1, -1 },
				{ -1, -1, 2, -1 }, 
				{ 3, -1, -1, -1 },
				{ 4, -1, -1, -1 }, 
				{ 5, -1, -1, -1 },
				{ -1, 6, -1, -1 }, 
				{ -1, -1, 7, -1 },
				{ -1, -1, -1, 8 }, 
				{ -1, -1, -1, 9 },
				{ 10, -1, 11, -1 } */

		ResultProcessor rp = new ResultProcessor(experts, bcs, scenarios);

		Scenario[] risks = rp.loadFromCsv(CSV_FILENAME);
		
		if(Main.IMPACTCALCULATION)
			risks = rp.getRiskScenarios(fraudscenarios, attackfraudMatrix);

		System.out.println(rp.toString(risks));

		rp.exportToXml(risks, BASE_XML, OUTPUT_XML);

	}

}
