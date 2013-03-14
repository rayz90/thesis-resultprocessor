package nl.raymondvermaas.resultprocessor.models;

public class AttackFraudCombination {

	private int attackId;
	private int fraudId;
	
	public AttackFraudCombination() {
	}	
	
	public AttackFraudCombination(int attackId, int fraudId) {
		super();
		this.attackId = attackId;
		this.fraudId = fraudId;
	}

	public int getAttackId() {
		return attackId;
	}

	public void setAttackId(int attackId) {
		this.attackId = attackId;
	}

	public int getFraudId() {
		return fraudId;
	}

	public void setFraudId(int fraudId) {
		this.fraudId = fraudId;
	}

	@Override
	public int hashCode() {
		final int prime = 100;
		int result = 1;
		int aresult = prime + attackId;
		int fresult = prime + fraudId;
		String strres = "" + aresult;
		strres += fresult;
		result = Integer.parseInt(strres);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttackFraudCombination other = (AttackFraudCombination) obj;
		if (attackId != other.attackId)
			return false;
		if (fraudId != other.fraudId)
			return false;
		return true;
	}
	
	

	
}
