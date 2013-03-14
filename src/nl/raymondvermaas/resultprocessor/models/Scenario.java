package nl.raymondvermaas.resultprocessor.models;

public class Scenario {

	public String name;
	public Criterion[] criteria;
	
	public Scenario(){};
	
	public Scenario(String name, Criterion[] criteria) {
		this.name = name;
		this.criteria = criteria;
	}
	
	public Scenario(String name, int length) {
		this.name = name;
		this.criteria = new Criterion[length];
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addCriterion(int index, Criterion criterion) {
		criteria[index] = criterion;
	}
	
	public Criterion getCriterion(int index) {
		return criteria[index];
	}

	
	public Criterion[] getCriteria() {
		return criteria;
	}

	public void setCriteria(Criterion[] criteria) {
		this.criteria = criteria;
	}

	@Override
	public String toString() {
		String str =  "Scenario [" + name.toUpperCase() + "]:\n";
		for(Criterion crit : criteria) {
			try {
			str+=crit.toString();
			} catch (Exception e) {
				System.err.println(name.toUpperCase());
			}
		}
		return str;
	}
	
	public int countCriteria() {
		return criteria.length;
	}
}
