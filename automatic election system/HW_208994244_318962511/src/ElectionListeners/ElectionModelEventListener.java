package ElectionListeners;

import java.util.Vector;

import ElectionModel.Candidate;
import ElectionModel.Citizen;
import ElectionModel.ElectioneSquence;
import ElectionModel.Party;
import ElectionModel.Votingplaces;
import ElectionModel.mySet;

public interface ElectionModelEventListener {
	void addCitizen(String name, String id, int birthyear, boolean inquarantine, int DaysSick, boolean isCaraingWeapon);

	void votingTime(Vector<Votingplaces> votingPlaceLIst, Vector<Party> partyList);

	void electionDataLoad(Boolean answer);

	Vector<Citizen> getAllCitizens();

	void electionDataSaving(ElectionModelEventListener theElectionSquence);

	void results();

	Vector<Party> getPartyList();

	Vector<Votingplaces> getVotingPlaceLIst();

	void getPartyListToString();

	void getCitizenListToString();

	void getVotingPlaceLIstToString();

	void addCandidantToParty(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon, String inParty);

	void addpartyTolist(String partyName, String partyYear, String politicalAlignement, String partyCandidate1Name,
			String partyCandidate1ID, int partyCandidate1Year, boolean partyCandidate1Sick,
			int partyCandidate1DaysOfSick, String partyCandidate2Name, String partyCandidate2ID,
			int partyCandidate2Year, boolean partyCandidate2Sick, int partyCandidate2DaysOfSick);

	void addvotingplacetolist(String theLocation, String theType);

	Vector getQuarantineCitizenList();

	void hardCodeInput();

	Vector getCitizenList();

	Vector getArmyCitizenList();
	
	void restartVoting();

	// to UI functions:
	void fireShowVotinPLacesToUI(String temp);

	void fireShowCitizensToUI(String temp);

	void fireCitizenIsAlredyInListToUi();

	void fireShowPartysToUI(String temp);

	void fireShowResultsToUI(String temp);

	void fireMessegeToUI(String temp);

	void fireNewElectionSquenceForController(ElectioneSquence theElectioneSquence);

	void fireCitizensForVotingToUI(int votingPlaceNum, String[] votingPlaceCitizensNames, String votingPlaceType,
			String partyNames, int numOfPartys);

	void fireStopVotingTimeToUI();

}
