package ElectionControlle;

import java.io.Serializable;
import java.util.Vector;

import ElectionListeners.ElectionModelEventListener;
import ElectionListeners.EletionViewEvnetLIstener;
import ElectionModel.Citizen;
import ElectionModel.ElectioneSquence;
import ElectionModel.Party;
import ElectionModel.Votingplaces;
import ElectionView.AbstractElectionView;

public class ElectionController implements Serializable, ElectionModelEventListener, EletionViewEvnetLIstener {
	private ElectioneSquence theModel;
	private AbstractElectionView theView;

	public ElectionController(ElectioneSquence theModel, AbstractElectionView theView) {
		this.theModel = theModel;
		this.theView = theView;

		this.theModel.register(this);
		this.theView.register(this);
	}
	
	public class ProjectAreaBean implements Serializable {
	    private static final long serialVersionUID = 1L;
	}
	
	@Override
	public void newVotingPlaceEventToModel(String location, String type) {
		theModel.addvotingplacetolist(location, type);

	}

	@Override
	public void showVotingPlacesFromModel() {
		theModel.getVotingPlaceLIstToString();
	}

	@Override
	public void addCitizen(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon) {
		// TODO Auto-generated method stub

	}

	@Override
	public void votingTime(Vector<Votingplaces> votingPlaceLIst, Vector<Party> partyList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void electionDataLoad(Boolean answer) {
		// TODO Auto-generated method stub
	}

	@Override
	public Vector<Citizen> getAllCitizens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void electionDataSaving(ElectionModelEventListener theElectionSquence) {
		// TODO Auto-generated method stub

	}

	@Override
	public void results() {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<Party> getPartyList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Votingplaces> getVotingPlaceLIst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getCitizenListToString() {
		// TODO Auto-generated method stub
	}

	@Override
	public void getVotingPlaceLIstToString() {
		// TODO Auto-generated method stub
	}

	@Override
	public void addpartyTolist(String partyName, String partyYear, String politicalAlignement,
			String partyCandidate1Name, String partyCandidate1ID, int partyCandidate1Year, boolean partyCandidate1Sick,
			int partyCandidate1DaysOfSick, String partyCandidate2Name, String partyCandidate2ID,
			int partyCandidate2Year, boolean partyCandidate2Sick, int partyCandidate2DaysOfSick) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector getQuarantineCitizenList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hardCodeInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector getCitizenList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector getArmyCitizenList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addvotingplacetolist(String theLocation, String theType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fireShowVotinPLacesToUI(String temp) {
		theView.setVotingPlacesList(temp);

	}

	@Override
	public void showCitizensFromModel() {
		theModel.getCitizenListToString();

	}

	@Override
	public void fireShowCitizensToUI(String temp) {
		theView.setCitizensList(temp);

	}

	@Override
	public void newCitizenToModel(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon) {
		theModel.addCitizen(name, id, birthyear, inquarantine, DaysSick, isCaraingWeapon);

	}

	@Override
	public void fireCitizenIsAlredyInListToUi() {
		theView.citizenIsAlredyInListFromModel();

	}

	@Override
	public void newPartyToModel(String partyName, String partyYear, String politicalAlignement,
			String partyCandidate1Name, String partyCandidate1ID, int partyCandidate1Year, boolean partyCandidate1Sick,
			int partyCandidate1DaysOfSick, String partyCandidate2Name, String partyCandidate2ID,
			int partyCandidate2Year, boolean partyCandidate2Sick, int partyCandidate2DaysOfSick) {
		theModel.addpartyTolist(partyName, partyYear, politicalAlignement, partyCandidate1Name, partyCandidate1ID,
				partyCandidate1Year, partyCandidate1Sick, partyCandidate1DaysOfSick, partyCandidate2Name,
				partyCandidate2ID, partyCandidate2Year, partyCandidate2Sick, partyCandidate2DaysOfSick);

	}

	@Override
	public void ShowPartysFromModel() {
		theModel.getPartyListToString();

	}

	@Override
	public void getPartyListToString() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fireShowPartysToUI(String temp) {
		theView.setPartyList(temp);

	}

	@Override
	public void newCandidateToMOdel(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon, String inParty) {
		theModel.addCandidantToParty(name, id, birthyear, inquarantine, DaysSick, isCaraingWeapon, inParty);

	}

	@Override
	public void addCandidantToParty(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon, String inParty) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showResultsFromModel() {
		theModel.results();

	}

	@Override
	public void fireShowResultsToUI(String temp) {
		theView.setResults(temp);

	}

	@Override
	public void fireMessegeToUI(String temp) {
		theView.messageFromModel(temp);

	}

	@Override
	public void saveModelData() {
		theModel.electionDataSaving(theModel);

	}

	@Override
	public void dataLoadToModel(Boolean answer) {
		theModel.electionDataLoad(answer);

	}

	@Override
	public void fireNewElectionSquenceForController(ElectioneSquence theElectioneSquence) {
		theModel = theElectioneSquence;
		this.theModel.register(this);

	}

	@Override
	public void startVoting() {
		theModel.restartVoting();
		theModel.citizensForVoting(0);
		
	}

	@Override
	public void fireCitizensForVotingToUI(int votingPlaceNum, String[] votingPlaceCitizensNames, String votingPlaceType,
			String partyNames,int numOfPartys) {
		theView.votingPlaceCitizensForVoting(votingPlaceNum, votingPlaceCitizensNames, votingPlaceType, partyNames,numOfPartys);
		
	}

	@Override
	public void votingPlaceVotesToModel(int votingPLaceNum, int[] votingPlacePartyVotes, int numOfCitizensThatVoted) {
		theModel.updateVotingPlaceVotes(votingPLaceNum, votingPlacePartyVotes, numOfCitizensThatVoted);
		
	}

	@Override
	public void fireStopVotingTimeToUI() {
		theView.stopVotingTime();
		
	}

	@Override
	public void restartVoting() {
		// TODO Auto-generated method stub
		
	}

}
