package ElectionModel;

import java.io.Serializable;

public class Citizen implements Serializable {

	private String name;
	private String id;
	private int birthyear;
	private Votingplaces votingplaceSerialnumber;
	private boolean inquarantine;
	private int DaysSick;
	private boolean carryWeapon;

	public int getDaysSick() {
		return DaysSick;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}

	public void setVotingplaceSerialnumber(Votingplaces votingplaceSerialnumber) {
		this.votingplaceSerialnumber = votingplaceSerialnumber;
	}

	public void setInquarantine(boolean inquarantine) {
		this.inquarantine = inquarantine;
	}

	public void setDaysSick(int daysSick) {
		DaysSick = daysSick;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public int getBirthyear() {
		return birthyear;
	}

	public Votingplaces getVotingplaceSerialNumber() {
		return votingplaceSerialnumber;
	}

	public void setVotingPlaceSerialNumber(Votingplaces serialNumber) {
		this.votingplaceSerialnumber = serialNumber;
	}

	public boolean isInquarantine() {
		return inquarantine;
	}

	public boolean isCarryWeapon() {
		return carryWeapon;
	}

	public void setCarryWeapon(boolean carryWeapon) {
		this.carryWeapon = carryWeapon;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Name: " + name + "\n");
		stringBuffer.append("Id number: " + id + "\n");
		stringBuffer.append("Year of birth: " + birthyear + "\n");
		stringBuffer.append("Voting place: " + votingplaceSerialnumber.getSerialnumber() + "\n");
		stringBuffer.append("Quarantine status?: " + ((inquarantine) ? "Positive" : " Negetive") + "\n");
		stringBuffer.append("Days sick : " + DaysSick + "\n");
		return stringBuffer.toString();
	}

	public Citizen(String name, String id, int birthyear, boolean inquarantine, int DaysSick, boolean isCaraingWeapon) {
		super();
		this.name = name;
		this.id = id;
		this.birthyear = birthyear;
		this.inquarantine = inquarantine;
		this.DaysSick = DaysSick;
		this.carryWeapon = isCaraingWeapon;

	}
}
