package ElectionView;

import ElectionListeners.EletionViewEvnetLIstener;

public interface AbstractElectionView {
	void register(EletionViewEvnetLIstener newListener);

	void fireNewVotingPlaceEventToModel(String location, String type);

	void fireNewCitizenToModel(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon);

	void fireNewPartyToModel(String partyName, String partyYear, String politicalAlignement, String partyCandidate1Name,
			String partyCandidate1ID, int partyCandidate1Year, boolean partyCandidate1Sick,
			int partyCandidate1DaysOfSick, String partyCandidate2Name, String partyCandidate2ID,
			int partyCandidate2Year, boolean partyCandidate2Sick, int partyCandidate2DaysOfSick);

	void fireNewCandidateToModel(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon, String inParty);

	void fireShowVotingPlacesFromModel();

	void fireSaveModelData();

	void setVotingPlacesList(String temp);

	void fireShowCitizensFromModel();

	void fireShowPartysFromModel();

	void fireShowResultsFromModel();

	void setCitizensList(String temp);

	void citizenIsAlredyInListFromModel();

	void setPartyList(String temp);

	void setResults(String temp);

	void messageFromModel(String temp);

	void fireDataLoadToModel(Boolean answer);

	void votingPlaceCitizensForVoting(int votingPlaceNum, String[] votingPlaceCitizensNames, String votingPlaceType,
			String partyNames, int numOfPartys);

	void stopVotingTime();

}
