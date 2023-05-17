package ElectionModel;

import java.io.Serializable;
import java.util.Vector;

public class Party implements Serializable{
	public enum politicalAlignement{
		LEFT,CENTER,RIGHT,HIGH
	}
	private Vector<Citizen> candidatesList = new Vector<Citizen>();
	private String name;
	private String FoundationDate;
	private politicalAlignement politicalAlignement;
	private int vote = 0;

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public Party(Vector<Citizen> candidatesList, String name, String FoundationDate, politicalAlignement politicalAlignement) {
		this.candidatesList = candidatesList;
		this.name = name;
		this.FoundationDate = FoundationDate;
		this.politicalAlignement = politicalAlignement;
	}

	public Vector<Citizen> getCandidatesList() {
		return candidatesList;
	}

	public void setCandidatesList(Vector<Citizen> candidatesList) {
		this.candidatesList = candidatesList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFoundationDate() {
		return FoundationDate;
	}

	public void setFoundationDate(String FoundationDate) {
		this.FoundationDate = FoundationDate;
	}

	public politicalAlignement getPoliticalAlignement() {
		return politicalAlignement;
	}

	public void setPoliticalAlignement(politicalAlignement politicalAlignement) {
		this.politicalAlignement = politicalAlignement;
	}

	public String howManyVotesForParty() {
		StringBuffer temp = new StringBuffer();
		temp.append("name: " + getName() + "\nVotes: " + vote);
		return temp.toString();
	}

	public void addCandidat(Candidate theCandidate) {
		candidatesList.add(theCandidate);
//		for (int i = 0; i < candidatesList.capacity(); i++) {
//			if (candidatesList.elementAt(i) == null) {
//				candidatesList. = theCandidate;
//				break;
//
//			}
//		}
	}

	@Override
	public String toString() {
		StringBuffer temp = new StringBuffer();
		temp.append("name: " + name + "\nyear Of Birth: " + FoundationDate + "\npolitical alignement: "
				+ politicalAlignement + "\ncandidates list: ");
		for (int i = 0; i < candidatesList.size(); i++) {
			if (!(candidatesList.elementAt(i) == null)) {
				temp.append("\n" + (i + 1) + " - " + candidatesList.elementAt(i).getName());
			} else {
				continue;
			}
		}

		return temp.toString();
	}

}
