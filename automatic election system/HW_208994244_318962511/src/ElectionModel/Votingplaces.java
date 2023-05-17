package ElectionModel;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Vector;

public class Votingplaces<T extends String> implements Serializable {
	private int serialnumber;
	private static int globalserialnumber = 1000;
	private T location;
	private double votingpercantage;
	private Vector<Citizen> citizenListForVotingPlace = new Vector<Citizen>();
	private Vector<Party> allThePartys = new Vector<Party>();
	private int[] votePartyArray;
	private T Votingplacetype;

	public void updatePartyVotingNumbersInVotingPlace(int partyPlace) {
		votePartyArray[partyPlace]++;
	}
	
	public void clearVotes() {
		for(int i = 0; i < votePartyArray.length; i++) {
			votePartyArray[i] = 0;
		}
	}

	public String partyVotes() {
		StringBuffer temp = new StringBuffer();
		temp.append("Voting place number: " + getSerialnumber() + "\n");
		for (int i = 0; i < getAllThePartys().size(); i++) {
			temp.append(getAllThePartys().elementAt(i).getName() + ": " + votePartyArray[i] + "\n");
		}
		return temp.toString();
	}

	public Vector<Citizen> getCitizenListForVotingPlace() {
		return citizenListForVotingPlace;
	}

	public int getSerialnumber() {
		return serialnumber;
	}

	public int getGlobalserialnumber() {
		return globalserialnumber;
	}

	public String getLocation() {
		return location;
	}

	public double getVotingpercantage() {
		return votingpercantage;
	}

	public void setSerialnumber(int serialnumber) {
		this.serialnumber = serialnumber;
	}

	public void setGlobalserialnumber(int globalserialnumber) {
		this.globalserialnumber = globalserialnumber;
	}

	public void setLocation(T location) {
		this.location = location;
	}

	public void setVotingpercantage(double votingpercantage) {
		this.votingpercantage = votingpercantage;
	}

	public Vector<Party> getAllThePartys() {
		return this.allThePartys;
	}

	public Votingplaces(T location, Vector<Citizen> citizens, Vector<Party> allThePartys, T Votingplacetype) {
		this.serialnumber = globalserialnumber++;
		this.location = location;
		this.votingpercantage = 0;
		this.citizenListForVotingPlace = citizens;
		this.allThePartys = allThePartys;
		this.Votingplacetype = Votingplacetype;
		this.votePartyArray = new int[allThePartys.size()];
	}

	public T getVotingplacetype() {
		return Votingplacetype;
	}

	public void setVotingplacetype(T votingplacetype) {
		Votingplacetype = votingplacetype;
	}

	public boolean equals(Votingplaces<?> theVotingPlace) {
		return (this.getGlobalserialnumber() == theVotingPlace.getGlobalserialnumber());

	}

	@Override
	public String toString() {
		StringBuffer temp = new StringBuffer();
		temp.append("\nVoting place serial number: " + serialnumber + "\nLocation: " + location
				+ "\nVoting place type : " + Votingplacetype + "\nVoting percantage: " + votingpercantage + "% "
				+ "\nCitizen list for this voting place: ");
		LinkedHashSet<Citizen> hashCitizen = new LinkedHashSet<Citizen>(citizenListForVotingPlace);
		citizenListForVotingPlace.clear();
		citizenListForVotingPlace.addAll(hashCitizen);
		for (int i = 0; i < citizenListForVotingPlace.size(); i++) {
			temp.append("\nName: " + citizenListForVotingPlace.elementAt(i).getName() + "\nID: "
					+ citizenListForVotingPlace.elementAt(i).getId() + "\n");
		}
		return temp.toString();
	}

}
