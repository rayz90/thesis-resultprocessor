package nl.raymondvermaas.resultprocessor.main;

import java.util.HashMap;
import java.util.List;

import nl.raymondvermaas.resultprocessor.models.AttackFraudCombination;
import nl.raymondvermaas.resultprocessor.models.BaseCriterion;
import nl.raymondvermaas.resultprocessor.models.Impact;
import nl.raymondvermaas.resultprocessor.models.Scenario;

public final  class YamlConfig {

	private String basexml;
	private String resultxml;
	private String inputcsv;
	private boolean calculateImpact;
	private HashMap<Integer, Double> experts;
	private HashMap<String, BaseCriterion> basecriteria;
	private List<Scenario> scenarios;
	private HashMap<AttackFraudCombination, Integer> attackfraudmatrix;
	private List<Impact> fraudscenarios;
	
	public String getBasexml() {
		return basexml;
	}
	public void setBasexml(String basexml) {
		this.basexml = basexml;
	}
	public String getResultxml() {
		return resultxml;
	}
	public void setResultxml(String resultxml) {
		this.resultxml = resultxml;
	}
	public String getInputcsv() {
		return inputcsv;
	}
	public void setInputcsv(String inputcsv) {
		this.inputcsv = inputcsv;
	}
	public boolean isCalculateImpact() {
		return calculateImpact;
	}
	public void setCalculateImpact(boolean calculateImpact) {
		this.calculateImpact = calculateImpact;
	}
	public HashMap<Integer, Double> getExperts() {
		return experts;
	}
	public void setExperts(HashMap<Integer, Double> experts) {
		this.experts = experts;
	}
	public HashMap<String, BaseCriterion> getBasecriteria() {
		return basecriteria;
	}
	public void setBasecriteria(HashMap<String, BaseCriterion> basecriteria) {
		this.basecriteria = basecriteria;
	}
	public List<Scenario> getScenarios() {
		return scenarios;
	}
	public void setScenarios(List<Scenario> scenarios) {
		this.scenarios = scenarios;
	}
	
	public List<Impact> getFraudscenarios() {
		return fraudscenarios;
	}
	public void setFraudscenarios(List<Impact> fraudscenarios) {
		this.fraudscenarios = fraudscenarios;
	}
	public HashMap<AttackFraudCombination, Integer> getAttackfraudmatrix() {
		return attackfraudmatrix;
	}
	public void setAttackfraudmatrix(
			HashMap<AttackFraudCombination, Integer> attackfraudmatrix) {
		this.attackfraudmatrix = attackfraudmatrix;
	}	
	
	
}
