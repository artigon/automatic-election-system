package ElectionListeners;

public interface EletionViewEvnetLIstener {
	void newVotingPlaceEventToModel(String location, String type);

	void newCitizenToModel(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon);

	void newPartyToModel(String partyName, String partyYear, String politicalAlignement, String partyCandidate1Name,
			String partyCandidate1ID, int partyCandidate1Year, boolean partyCandidate1Sick,
			int partyCandidate1DaysOfSick, String partyCandidate2Name, String partyCandidate2ID,
			int partyCandidate2Year, boolean partyCandidate2Sick, int partyCandidate2DaysOfSick);

	void newCandidateToMOdel(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon, String inParty);

	void ShowPartysFromModel();

	void showVotingPlacesFromModel();

	void showCitizensFromModel();
	
	void showResultsFromModel();
	
	void saveModelData();
	
	void dataLoadToModel(Boolean answer);
	
	void startVoting();
	
	void votingPlaceVotesToModel(int votingPLaceNum, int[] votingPlacePartyVotes,
			int numOfCitizensThatVoted);
}
