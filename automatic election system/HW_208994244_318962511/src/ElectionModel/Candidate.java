package ElectionModel;

import java.io.Serializable;

public class Candidate extends Citizen implements Serializable {
	private String inParty;

	public Candidate(String name, String id, int birthyear, boolean inquarantine, int DaysSick, boolean isCaraingWeapon,
			String inParty) {
		super(name, id, birthyear, inquarantine, DaysSick, isCaraingWeapon);
		this.inParty = inParty;
	}

	public String getInParty() {
		return inParty;
	}

	public void setInParty(String inParty) {
		this.inParty = inParty;
	}

	@Override
	public String toString() {
		return "Candidate " + super.toString() + "What party: " + inParty + "\n";
	}

}
