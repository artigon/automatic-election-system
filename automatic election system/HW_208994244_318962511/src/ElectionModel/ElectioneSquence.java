
package ElectionModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Vector;

import ElectionListeners.ElectionModelEventListener;

public class ElectioneSquence implements Serializable, ElectionModelEventListener {
	transient Vector<ElectionModelEventListener> allListeners = new Vector<ElectionModelEventListener>();
	static Date d = new Date();
	private static int year = d.getYear() + 1900;
	private static int month = d.getMonth() + 1;

	private Vector<Citizen> citizenList = new Vector<Citizen>();
	private Vector<Citizen> armyCitizenList = new Vector<Citizen>();
	private Vector<Citizen> quarantineCitizenList = new Vector<Citizen>();
	private Vector<Citizen> armyQuarantineCitizenList = new Vector<Citizen>();
	private Vector<Party> partyList = new Vector<Party>();
	private Vector<Votingplaces> votingPlaceList = new Vector<Votingplaces>();
	private mySet set;

	public ElectioneSquence() {

	}

	public void toWhatList(Vector<Citizen> theList) {
		for (int i = 0; i < theList.size(); i++) {
			addCitizen(theList.elementAt(i).getName(), theList.elementAt(i).getId(),
					theList.elementAt(i).getBirthyear(), theList.elementAt(i).isInquarantine(),
					theList.elementAt(i).getDaysSick(), theList.elementAt(i).isCarryWeapon());

		}
	}

	@Override
	public void addCitizen(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon) {
		Citizen newCitizen = new Citizen(name, id, birthyear, inquarantine, DaysSick, isCaraingWeapon);
		int age = year - newCitizen.getBirthyear();
		if (age > 17) {
			if (set.addCitizenToList(newCitizen)) {
				fireCitizenIsAlredyInListToUi();
			} else {
				if (age < 21) {
					for (int j = 0; j < votingPlaceList.size(); j++) {
						if (newCitizen.isInquarantine()) {
							if (votingPlaceList.elementAt(j).getVotingplacetype().equalsIgnoreCase("Army quarantine")) {
								newCitizen.setVotingPlaceSerialNumber(votingPlaceList.elementAt(j));
								votingPlaceList.elementAt(j).getCitizenListForVotingPlace().add(newCitizen);
								armyQuarantineCitizenList.add(newCitizen);
								fireMessegeToUI("Citizen  has been registered succsefully to army qurantine voting");
								System.out.println("Citizen  has been registered succsefully to army qurantine voting");
								break;

							} else {
								continue;
							}

						} else {
							if (votingPlaceList.elementAt(j).getVotingplacetype().equalsIgnoreCase("Army")) {
								newCitizen.setVotingPlaceSerialNumber(votingPlaceList.elementAt(j));
								votingPlaceList.elementAt(j).getCitizenListForVotingPlace().add(newCitizen);
								armyCitizenList.add(newCitizen);
								fireMessegeToUI("Citizen  has been registered succsefully to army voting");
								System.out.println("Citizen  has been registered succsefully to army voting");
								break;

							} else {
								continue;
							}

						}

					}

				} else if (newCitizen.isInquarantine() == true) {
					for (int j = 0; j < votingPlaceList.size(); j++) {
						if (votingPlaceList.elementAt(j).getVotingplacetype().equalsIgnoreCase("Quarantine")) {
							newCitizen.setVotingPlaceSerialNumber(votingPlaceList.elementAt(j));
							votingPlaceList.elementAt(j).getCitizenListForVotingPlace().add(newCitizen);
							quarantineCitizenList.add(newCitizen);
							fireMessegeToUI("Citizen has been registred succsefully to quarantine voting");
							System.out.println("Citizen has been registred succsefully to quarantine voting");
							break;

						} else {
							continue;
						}
					}
				} else {
					for (int j = 0; j < votingPlaceList.size(); j++) {
						if (votingPlaceList.elementAt(j).getVotingplacetype().equalsIgnoreCase("Citizen")) {
							newCitizen.setVotingPlaceSerialNumber(votingPlaceList.elementAt(j));
							votingPlaceList.elementAt(j).getCitizenListForVotingPlace().add(newCitizen);
							citizenList.add(newCitizen);
							fireMessegeToUI("Citizen has been succsefully registered to vote");
							System.out.println("Citizen has been succsefully registered to vote");
							break;
						} else {
							continue;
						}
					}

				}

			}

		} else {
			fireMessegeToUI("Citizen is not of age for voting!");
			System.out.println("Citizen is not of age for voting!");
		}
	}

	public void votingTime(Vector<Votingplaces> votingPlaceLIst, Vector<Party> partyList) {
		Scanner input = new Scanner(System.in);
		for (int i = 0; i < votingPlaceLIst.size(); i++) {
			int peopleThatHaveVoted = 0;
			for (int j = 0; j < votingPlaceLIst.elementAt(i).getCitizenListForVotingPlace().size(); j++) {
				boolean canVote = true;
				if (votingPlaceLIst.elementAt(i).getVotingplacetype().equalsIgnoreCase("Quarantine")
						|| votingPlaceLIst.elementAt(i).getVotingplacetype().equalsIgnoreCase("Army quarantine")) {
					do {
						try {
							System.out.println(
									((Citizen) votingPlaceLIst.elementAt(i).getCitizenListForVotingPlace().elementAt(j))
											.getName() + " Do you have a mask?\nY/N");
							String answerMask = input.next();
							if (!(answerMask.equalsIgnoreCase("y") || answerMask.equalsIgnoreCase("n"))) {
								throw new Exception("Input invalid");
							} else if (answerMask.equalsIgnoreCase("y")) {
								break;
							} else if (answerMask.equalsIgnoreCase("n")) {
								canVote = false;
								break;
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
							break;
						}
					} while (true);
				}
				if (canVote == true) {

					do {
						try {
							System.out.println(
									((Citizen) votingPlaceLIst.elementAt(i).getCitizenListForVotingPlace().elementAt(j))
											.getName() + " Do you wish to vote?\n Y/N");
							String voteAnswer = input.next();
							if (!(voteAnswer.equalsIgnoreCase("y") || voteAnswer.equalsIgnoreCase("n"))) {
								throw new Exception("Input invalid");
							} else if (voteAnswer.equalsIgnoreCase("y")) {
								peopleThatHaveVoted++;
								System.out.println("Please choose the party you wish to vote for: ");
								for (int s = 0; s < partyList.size(); s++) {
									System.out.println("\n" + (s + 1) + "-" + partyList.elementAt(s).getName());

								}
								do {
									try {
										int votingMenu = input.nextInt() - 1;
										partyList.elementAt(votingMenu)
												.setVote(partyList.elementAt(votingMenu).getVote() + 1);
										votingPlaceLIst.elementAt(i).updatePartyVotingNumbersInVotingPlace(votingMenu);
										break;
									} catch (InputMismatchException e) {
										System.out.println("Input invalid\n please enter an integer");
									}
								} while (true);
								break;
							} else if (voteAnswer.equalsIgnoreCase("n")) {
								System.out.println("Have a nice day");
								break;
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
							break;
						}
					} while (true);

					votingPlaceLIst.elementAt(i).setVotingpercantage(((double) peopleThatHaveVoted
							/ votingPlaceLIst.elementAt(i).getCitizenListForVotingPlace().size()) * 100);
				}

				else {
					System.out.println("Have a nice day");
				}
			}
		}

	}
	
	public void restartVoting() {
		for (int i = 0; i < votingPlaceList.size(); i++) {
			votingPlaceList.elementAt(i).clearVotes();
		}
		for (int i = 0; i < partyList.size(); i++) {
			partyList.elementAt(i).setVote(0);
		}
	}

	public void citizensForVoting(int votingPlaceNum) {
		
		if (votingPlaceList.size() == votingPlaceNum) {
			fireStopVotingTimeToUI();
		} else {
			System.out.println("starting with voting place num " + votingPlaceNum);
			String[] votingPlaceCitizens = new String[votingPlaceList.elementAt(votingPlaceNum)
					.getCitizenListForVotingPlace().size()];
			StringBuffer partyListNames = new StringBuffer();
			String votingPlaceType = votingPlaceList.elementAt(votingPlaceNum).getVotingplacetype();

			for (int i = 0; i < votingPlaceList.elementAt(votingPlaceNum).getCitizenListForVotingPlace().size(); i++) {
				votingPlaceCitizens[i] = ((Citizen) votingPlaceList.elementAt(votingPlaceNum)
						.getCitizenListForVotingPlace().elementAt(i)).getName();
			}
			for (int i = 0; i < partyList.size(); i++) {
				partyListNames.append((i + 1) + " - " + partyList.elementAt(i).getName() + "\n");
			}

			fireCitizensForVotingToUI(votingPlaceNum, votingPlaceCitizens, votingPlaceType, partyListNames.toString(),
					partyList.size());
		}
	}

	public void updateVotingPlaceVotes(int votingPLaceNum, int[] votingPlacePartyVotes, int numOfCitizensThatVoted) {
		System.out.println("finishd with voting place num " + votingPLaceNum);
		votingPlaceList.elementAt(votingPLaceNum).setVotingpercantage(((double) numOfCitizensThatVoted
				/ votingPlaceList.elementAt(votingPLaceNum).getCitizenListForVotingPlace().size()) * 100);
		for (int i = 0; i < votingPlacePartyVotes.length; i++) {
			for (int j = 0; j < votingPlacePartyVotes[i]; j++) {
				partyList.elementAt(i).setVote(partyList.elementAt(i).getVote() + 1);
				votingPlaceList.elementAt(votingPLaceNum).updatePartyVotingNumbersInVotingPlace(i);
			}
		}
		if (votingPlaceList.size() != votingPLaceNum) {
			citizensForVoting(votingPLaceNum + 1);
		} else {
			fireMessegeToUI("Voting has finishd");
			System.out.println("finishd voting");
		}

	}

	public void results() {
		StringBuffer temp = new StringBuffer();
		temp.append("The results are:\nFrom each voting place: \n");
		for (int i = 0; i < votingPlaceList.size(); i++) {
			temp.append(votingPlaceList.elementAt(i).partyVotes() + "\n");
		}
		System.out.println("The Partys: ");
		for (int j = 0; j < partyList.size(); j++) {
			temp.append(partyList.elementAt(j).howManyVotesForParty() + "\n");
		}
		fireShowResultsToUI(temp.toString());
	}

	public void setCitizenList(Vector<Citizen> citizenList) {
		this.citizenList = citizenList;
	}

	public void setArmyCitizenList(Vector<Citizen> armyCitizenList) {
		this.armyCitizenList = armyCitizenList;
	}

	public void setQuarantineCitizenList(Vector<Citizen> quarantineCitizenList) {
		this.quarantineCitizenList = quarantineCitizenList;
	}

	public void setPartyList(Vector<Party> allParties) {
		this.partyList = allParties;
	}

	public void setVotingPlaceList(Vector<Votingplaces> votingPlaceList) {
		this.votingPlaceList = votingPlaceList;
	}

	public void getCitizenListToString() {
		fireShowCitizensToUI(set.toString());

	}

	public Vector<Citizen> getArmyQuarantineCitizenList() {
		return armyQuarantineCitizenList;
	}

	public void getPartyListToString() {
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < partyList.size(); i++) {
			temp.append("\n" + partyList.elementAt(i).toString() + "\n");

		}
		fireShowPartysToUI(temp.toString());
	}

	public Vector<Citizen> getCitizenList() {
		return citizenList;
	}

	public Vector<Citizen> getArmyCitizenList() {
		return armyCitizenList;
	}

	public Vector<Citizen> getQuarantineCitizenList() {
		return quarantineCitizenList;
	}

	public Vector<Party> getPartyList() {
		return partyList;
	}

	public Vector<Votingplaces> getVotingPlaceLIst() {
		return votingPlaceList;
	}

	public void getVotingPlaceLIstToString() {
		StringBuffer temp = new StringBuffer();
		for (int i = 0; i < votingPlaceList.size(); i++) {
			temp.append(votingPlaceList.elementAt(i).toString());
		}
		System.out.println(temp.toString());
		fireShowVotinPLacesToUI(temp.toString());

	}

	public void addCandidantToParty(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon, String inParty) {
		Candidate theCandidate = new Candidate(name, id, birthyear, inquarantine, DaysSick, isCaraingWeapon, inParty);
		addCitizen(name, id, birthyear, inquarantine, DaysSick, isCaraingWeapon);
		boolean skiper = true;
		for (int i = 0; i < partyList.size(); i++) {
			if (partyList.elementAt(i).getName().equalsIgnoreCase(theCandidate.getInParty())) {
				partyList.elementAt(i).addCandidat(theCandidate);
				fireMessegeToUI("Candidate has been added succsefully to " + partyList.elementAt(i).getName());
				System.out.println("Candidate has been added succsefully to " + partyList.elementAt(i).getName());
				skiper = false;
				break;
			}
			if (!(skiper)) {
				fireMessegeToUI("There is no Party with the name: " + theCandidate.getInParty());
				System.out.println("There is no Party with the name: " + theCandidate.getInParty());
			}
		}

	}

	@Override
	public void addvotingplacetolist(String theLocation, String theType) {
		if (theType.equalsIgnoreCase("citizen")) {
			String location = theLocation;
			String Votingplacetype = theType;
			Vector<Citizen> VC = (Vector<Citizen>) citizenList.clone();
			VC.clear();
			Votingplaces<String> V1 = new Votingplaces<String>(location, VC, getPartyList(), Votingplacetype);
			votingPlaceList.add(V1);
			fireMessegeToUI("add voting place succeses");
			return;
		} else if (theType.equalsIgnoreCase("quarantine")) {
			String location = theLocation;
			String Votingplacetype = theType;
			Vector<Citizen> VQC = (Vector<Citizen>) quarantineCitizenList.clone();
			VQC.clear();

			Votingplaces<String> V1 = new Votingplaces<String>(location, VQC, getPartyList(), Votingplacetype);
			votingPlaceList.add(V1);
			fireMessegeToUI("add voting place succeseS");
			return;
		} else if (theType.equalsIgnoreCase("army")) {
			String location = theLocation;
			String Votingplacetype = theType;
			Vector<Citizen> VAC = (Vector<Citizen>) armyCitizenList.clone();
			VAC.clear();
			Votingplaces<String> V1 = new Votingplaces<String>(location, VAC, getPartyList(), Votingplacetype);
			votingPlaceList.add(V1);
			fireMessegeToUI("add voting place succeseS");
			return;

		} else if (theType.equalsIgnoreCase("army quarantine")) {
			String location = theLocation;
			String Votingplacetype = theType;
			Vector<Citizen> VCAQ = (Vector<Citizen>) armyQuarantineCitizenList.clone();
			VCAQ.clear();
			Votingplaces<String> V1 = new Votingplaces<String>(location, VCAQ, getPartyList(), Votingplacetype);
			votingPlaceList.add(V1);
			fireMessegeToUI("add voting place succeseS");
			return;

		}
		fireMessegeToUI("not add voting place succeseS");
		System.out.println("add voting place succeseS");
	}

	public void addpartyTolist(String partyName, String partyYear, String politicalAlignement,
			String partyCandidate1Name, String partyCandidate1ID, int partyCandidate1Year, boolean partyCandidate1Sick,
			int partyCandidate1DaysOfSick, String partyCandidate2Name, String partyCandidate2ID,
			int partyCandidate2Year, boolean partyCandidate2Sick, int partyCandidate2DaysOfSick) {
		boolean skiper = false;
		Vector<Citizen> candidateList = new Vector<Citizen>();
		candidateList.add(new Candidate(partyCandidate1Name, partyCandidate1ID, partyCandidate1Year,
				partyCandidate1Sick, partyCandidate1DaysOfSick, false, partyName));
		candidateList.add(new Candidate(partyCandidate2Name, partyCandidate2ID, partyCandidate2Year,
				partyCandidate2Sick, partyCandidate2DaysOfSick, false, partyName));
		toWhatList(candidateList);
		for (int i = 0; i < set.getSetList().size(); i++) {
			if ((candidateList.elementAt(0).getName().equalsIgnoreCase(set.getSetList().elementAt(i).getName())
					&& candidateList.elementAt(0).getId().equalsIgnoreCase(set.getSetList().elementAt(i).getId()))
					&& (candidateList.elementAt(1).getName().equalsIgnoreCase(set.getSetList().elementAt(i).getName())
							&& candidateList.elementAt(1).getId()
									.equalsIgnoreCase(set.getSetList().elementAt(i).getId()))) {
				skiper = true;
			}
		}
		if (skiper = true) {
			Party.politicalAlignement thePoliticalAlignement = null;
			if (politicalAlignement.equalsIgnoreCase("Left")) {
				thePoliticalAlignement = ElectionModel.Party.politicalAlignement.LEFT;
			} else if (politicalAlignement.equalsIgnoreCase("Right")) {
				thePoliticalAlignement = ElectionModel.Party.politicalAlignement.RIGHT;
			} else if (politicalAlignement.equalsIgnoreCase("Center")) {
				thePoliticalAlignement = ElectionModel.Party.politicalAlignement.CENTER;
			}
			Party theParty = new Party(candidateList, partyName, partyYear, thePoliticalAlignement);
			partyList.add(theParty);
			fireMessegeToUI("Party add sucssesfuly");
		} else {
			fireMessegeToUI("party not add");
		}
	}

	public void hardCodeInput() {

		// partys
		Vector<Party> allParties = new Vector<Party>();

		Vector<Citizen> candidatelist1 = new Vector<Citizen>();
		candidatelist1.add(new Candidate("Captain Black-Beard", "666666666", 1666, false, 0, false, "Pirates"));
		candidatelist1.add(new Candidate("First mate Jack the boy", "777777777", 2001, true, 2, false, "Pirates"));
		allParties.add(new Party(candidatelist1, "Pirates", "2000", Party.politicalAlignement.HIGH));

		Vector<Citizen> candidatelist2 = new Vector<Citizen>();
		candidatelist2.add(new Candidate("Matitiyahoo", "10000001", 0005, false, 0, false, "Yehooda"));
		candidatelist2.add(new Candidate("Rivka", "124532111", 1200, true, 8, false, "Yehooda"));
		allParties.add(new Party(candidatelist2, "Yehooda", "1059", Party.politicalAlignement.RIGHT));

		Vector<Citizen> candidatelist3 = new Vector<Citizen>();
		candidatelist3.add(new Candidate("Yosi", "207652341", 1970, false, 0, false, "People"));
		candidatelist3.add(new Candidate("Keren", "304522761", 2002, false, 0, false, "People"));
		allParties.add(new Party(candidatelist3, "People", "1082", Party.politicalAlignement.LEFT));

		setPartyList(allParties);

		// voting places

		Vector<Votingplaces> allVotingPLaces = new Vector<Votingplaces>();

		allVotingPLaces.add(new Votingplaces("Tel-aviv", getCitizenList(), allParties, "Citizen"));
		allVotingPLaces.add(new Votingplaces("Zeheleem", getArmyCitizenList(), allParties, "Army"));
		allVotingPLaces.add(new Votingplaces("Hospital", getQuarantineCitizenList(), allParties, "Quarantine"));
		allVotingPLaces.add(new Votingplaces("harap", getArmyQuarantineCitizenList(), allParties, "Army quarantine"));

		setVotingPlaceList(allVotingPLaces);

		// citizens
		Vector<Citizen> allCitizens = new Vector<Citizen>();
		allCitizens.add(new Citizen("Beny", "111111111", 1965, false, 0, false));
		allCitizens.add(new Citizen("Boby", "222222222", 1985, true, 7, false));
		allCitizens.add(new Citizen("Nono", "333333333", 2010, false, 0, false));
		allCitizens.add(new Citizen("Lili", "444444444", 1982, true, 5, false));
		allCitizens.add(new Citizen("Rooven", "555555555", 2000, false, 0, false));
		allCitizens.add(new Citizen("Officer", "123432133", 2001, true, 0, true));

		toWhatList(allCitizens);
		toWhatList(candidatelist1);
		toWhatList(candidatelist2);
		toWhatList(candidatelist3);

		LinkedHashSet<Citizen> hashCitizen = new LinkedHashSet<Citizen>(citizenList);
		citizenList.clear();
		citizenList.addAll(hashCitizen);

		LinkedHashSet<Citizen> hashQuarantineCitizenList = new LinkedHashSet<Citizen>(quarantineCitizenList);
		quarantineCitizenList.clear();
		quarantineCitizenList.addAll(hashQuarantineCitizenList);

		LinkedHashSet<Citizen> hashArmyCitizenList = new LinkedHashSet<Citizen>(armyCitizenList);
		armyCitizenList.clear();
		armyCitizenList.addAll(hashArmyCitizenList);

		LinkedHashSet<Citizen> hashrAmyQuarantineCitizenList = new LinkedHashSet<Citizen>(armyQuarantineCitizenList);
		armyQuarantineCitizenList.clear();
		armyQuarantineCitizenList.addAll(hashrAmyQuarantineCitizenList);

	}

	public void electionDataSaving(ElectionModelEventListener SavedElection) {

		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("Election data.txt"));
			objectOut.writeObject(SavedElection);
			objectOut.close();
			fireMessegeToUI("The Election  was succesfully written to a file");
			System.out.println("The Election  was succesfully written to a file");

		} catch (Exception e) {
//			e.printStackTrace();
		}

	}

	public void electionDataLoad(Boolean answer) {
		try {
			ElectioneSquence theElectionSquenc = new ElectioneSquence();
			if (!(answer)) {
				theElectionSquenc.set = new mySet(getAllCitizens());
				theElectionSquenc.hardCodeInput();
				fireNewElectionSquenceForController(theElectionSquenc);
			} else {
				ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("Election data.txt"));
				theElectionSquenc = (ElectioneSquence) inFile.readObject();
				theElectionSquenc.allListeners = new Vector<ElectionModelEventListener>();
				fireNewElectionSquenceForController(theElectionSquenc);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public Vector<Citizen> getAllCitizens() {
		Vector<Citizen> allCitizens = new Vector<Citizen>();
		for (int i = 0; i < citizenList.size(); i++) {
			allCitizens.add(citizenList.elementAt(i));
		}
		for (int j = 0; j < quarantineCitizenList.size(); j++) {
			allCitizens.add(quarantineCitizenList.elementAt(j));
		}
		for (int k = 0; k < armyCitizenList.size(); k++) {
			allCitizens.add(armyCitizenList.elementAt(k));
		}
		for (int n = 0; n < armyQuarantineCitizenList.size(); n++) {
			allCitizens.add(armyQuarantineCitizenList.elementAt(n));
		}

		return allCitizens;
	}

	// for listenres:
	public void register(ElectionModelEventListener newListener) {
		allListeners.add(newListener);
	}

	// To UI:
	@Override
	public void fireShowVotinPLacesToUI(String temp) {
		for (ElectionModelEventListener l : allListeners) {
			l.fireShowVotinPLacesToUI(temp);
		}
	}

	public void fireShowCitizensToUI(String temp) {
		for (ElectionModelEventListener l : allListeners) {
			l.fireShowCitizensToUI(temp);
		}
	}

	public void fireCitizenIsAlredyInListToUi() {
		for (ElectionModelEventListener l : allListeners) {
			l.fireCitizenIsAlredyInListToUi();
		}
	}

	public void fireShowPartysToUI(String temp) {
		for (ElectionModelEventListener l : allListeners) {
			l.fireShowPartysToUI(temp);
		}
	}

	public void fireShowResultsToUI(String temp) {
		for (ElectionModelEventListener l : allListeners) {
			l.fireShowResultsToUI(temp);
		}
	}

	public void fireMessegeToUI(String temp) {
		for (ElectionModelEventListener l : allListeners) {
			l.fireMessegeToUI(temp);
		}
	}

	public void fireNewElectionSquenceForController(ElectioneSquence theElectioneSquence) {
		for (ElectionModelEventListener l : allListeners) {
			l.fireNewElectionSquenceForController(theElectioneSquence);
		}
	}

	public void fireCitizensForVotingToUI(int votingPlaceNum, String[] votingPlaceCitizensNames, String votingPlaceType,
			String partyNames, int numOfPartys) {
		for (ElectionModelEventListener l : allListeners) {
			l.fireCitizensForVotingToUI(votingPlaceNum, votingPlaceCitizensNames, votingPlaceType, partyNames,
					numOfPartys);
		}

	}

	public void fireStopVotingTimeToUI() {
		for (ElectionModelEventListener l : allListeners) {
			l.fireStopVotingTimeToUI();
		}
	}

}