package nl.raymondvermaas.resultprocessor.models;

public class Impact {
	private double impact;
	private String fraudscenario;

	public Impact(String name, double value) {
		this.fraudscenario = name;
		this.impact = value;
	}

	public Impact() {}
	
	public double getImpact() {
		return impact;
	}

	public void setImpact(double impact) {
		this.impact = impact;
	}

	public String getFraudscenario() {
		return fraudscenario;
	}

	public void setFraudscenario(String fraudscenario) {
		this.fraudscenario = fraudscenario;
	}
	
	public double determineRiskValue(BaseCriterion bc, int bcIndex) {
		double value =bc.getValue(bcIndex);
		int index= -1;
		
		Double[] borders =  (Double[]) bc.getBorders().toArray(new Double[1]);
		for(int i=0;i<borders.length;i++) {
			if(value < borders[i].doubleValue()) {
				index = i;
				break;
			}
			
			if(index == -1 && i == borders.length-1){
				index = i+1;
			}
		}

		int multiplier = 0;
		if ((index+1)-impact >= 0)
			multiplier =-1;
		else
			multiplier=1;
		
		double border = 0.0;
		if ((int) (((index+1)-impact)) == 0.0 || impact == 2.0)
			border = 0;
		else
			border = borders[(int) (Math.abs((index+1)-impact)-1)].doubleValue(); 
		
		double newvalue = value + multiplier * border; 
		return newvalue;
	}
}