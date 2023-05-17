package ElectionModel;
//package ElectionModel;
//
//import java.util.InputMismatchException;
//import java.util.Scanner;
//import java.util.Vector;
//
//import com.sun.prism.paint.Color;
//
//import ElectionListeners.ElectionModelEventListener;
//import ElectionModel.Party.politicalAlignement;
//import Exceptions.MenuInputInvalid;
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.layout.GridPane;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//public class oldMain {
//
//	public static void main(String[] args) throws Exception {
//		// hard code:
//
//		ElectionModelEventListener theElectionSquence = new ElectioneSquence();
//		theElectionSquence = (theElectionSquence).electionDataLoad();
//		mySet set = new mySet((theElectionSquence).getAllCitizens());
//
////		launch(args); 
//
//		// voting mechine
//		int closeLoop = 1;
//		while (closeLoop == 1) {
//			Scanner input = new Scanner(System.in);
//
//			System.out.println("\nWelcome");
//			System.out.println("Please enter number ");
//			System.out.println("1 - Add a voting location");
//			System.out.println("2 - Add a citizen");
//			System.out.println("3 - Add a party");
//			System.out.println("4 - Add a citizen as a canditate for party");
//			System.out.println("5 - Show all voting locations ");
//			System.out.println("6 - Show all citizens");
//			System.out.println("7 - Show all parties");
//			System.out.println("8 - Voting");
//			System.out.println("9 - Show voting results");
//			System.out.println("10 - Exit");
//			int menu = 0;
//			try {
//				menu = input.nextInt();
//				if (menu <= 0 || menu >= 11) {
//					throw new MenuInputInvalid();
//				}
//			} catch (MenuInputInvalid e) {
//				System.out.println(e.getMessage());
//			} catch (Exception InputMismatchException) {
//				System.out.println("input invalide\nplease enter a number btween 1-10");
//			}
//
//			switch (menu) {
//
//			case 1: {
////				do {
////					System.out.println(
////							"please enter the type  of voting place  you wish to create:\n1-citizen\n2-qurabtune\n3-army");
////					int answer = 0;
////					try {
////						answer = input.nextInt();
////						if (answer <= 0 || answer >= 4)
////							throw new Exception("input invalid\\nplease enter a number btween 1-3");
////
////						if (answer == 1) {
////							System.out.println("Please enter: \nlocation");
////							String location = input.next();
////							String Votingplacetype = "citizen";
////							Votingplaces V1 = new Votingplaces(location, (theElectionSquence).getCitizenList(),
////									(theElectionSquence).getPartyList(), Votingplacetype);
////							(theElectionSquence).addvotingplacetolist(V1);
////							break;
////						} else if (answer == 2) {
////							System.out.println("Please enter: \nlocation");
////							String location = input.next();
////							String Votingplacetype = "quarantine";
////							Votingplaces V1 = new Votingplaces(location,
////									(theElectionSquence).getQuarantineCitizenList(),
////									(theElectionSquence).getPartyList(), Votingplacetype);
////							(theElectionSquence).addvotingplacetolist(V1);
////							break;
////
////						} else if (answer == 3) {
////							System.out.println("Please enter: \nlocation");
////							String location = input.next();
////							String Votingplacetype = "army";
////							Votingplaces V1 = new Votingplaces(location, (theElectionSquence).getArmyCitizenList(),
////									(theElectionSquence).getPartyList(), Votingplacetype);
////							(theElectionSquence).addvotingplacetolist(V1);
////							break;
////
////						}
////					} catch (InputMismatchException e) {
////						System.out.println("Input invalid\nplease enter a number btween 1-3");
////					} catch (Exception e) {
////						System.out.println("Input invalid\nplease enter a number btween 1-3");
////					}
////				} while (true);
//			}
//				break;
//			// add to votingplace list
//
//			case 2: {
//				do {
//					try {
//						System.out.println("Please enter: \nname");
//						String name = input.next();
//						System.out.println("Id");
//						String id = input.next();
//						if (id.length() >= 10 || id.length() < 9) {
//							throw new Exception("Id too long/short\nplease enter id with 9 numbers");
//						}
//						if (id.startsWith("0")) {
//							throw new Exception("Id can not start with 0\nplease try againe");
//						}
//						System.out.println("Year of birth");
//						int yearOfBirth = input.nextInt();
//						int daysSick = 0;
//						boolean answer = true;
//						System.out.println("Are you in sick?\nY/N");
//						String inputAnswer = input.next();
//						if (!(inputAnswer.equalsIgnoreCase("N") || inputAnswer.equalsIgnoreCase("Y"))) {
//							throw new Exception("Input invalid");
//						} else if (inputAnswer.equalsIgnoreCase("N")) {
//							answer = false;
//						} else if (inputAnswer.equalsIgnoreCase("Y")) {
//							answer = true;
//							System.out.println("How many days were you sick?");
//							daysSick = input.nextInt();
//						}
//						System.out.println("Do you have a weapon?\nY/N");
//						Boolean weaponAnswer = false;
//						String weaponAnswerString = input.next();
//						if (!(weaponAnswerString.equalsIgnoreCase("N") || weaponAnswerString.equalsIgnoreCase("Y"))) {
//							throw new Exception("Input invalid");
//						} else if (weaponAnswerString.equalsIgnoreCase("N")) {
//							weaponAnswer = false;
//						} else if (weaponAnswerString.equalsIgnoreCase("Y")) {
//							weaponAnswer = true;
//						}
//						Citizen c1 = new Citizen(name, id, yearOfBirth, answer, daysSick, weaponAnswer);
//						if (set.addCitizenToList(c1)) {
//							throw new Exception("This Citizen is already in the system");
//						} else {
//							(theElectionSquence).addCitizen(c1);
//						}
//						break;
//						// adding list of all ppl
//					} catch (InputMismatchException e) {
//						System.out.println("Input invalid");
//					} catch (Exception e) {
//						System.out.println(e.getMessage());
//					}
//				} while (true);
//			}
//				break;
//
//			case 3: {
//				do {
//					try {
//						System.out.println("Please enter party name:\n");
//						String nameOfParty = input.next();
//						System.out.println("Year of birth");
//						String yearOfBirth = input.next();
//						String inputpoliticalAlignement;
//						System.out.println("What is the party politicalAlignement?\nLeft,Center,Right");
//						inputpoliticalAlignement = input.next().toLowerCase();
//						if (!(inputpoliticalAlignement.equalsIgnoreCase("left")
//								|| inputpoliticalAlignement.equalsIgnoreCase("right")
//								|| inputpoliticalAlignement.equalsIgnoreCase("center"))) {
//							throw new Exception("Input invalid");
//						} else if (inputpoliticalAlignement.equalsIgnoreCase("left")) {
//							break;
//						} else if (inputpoliticalAlignement.equalsIgnoreCase("right")) {
//							break;
//
//						} else if (inputpoliticalAlignement.equalsIgnoreCase("center")) {
//							break;
//						} else {
//							System.out.println("Input invalid\nplease try again");
//						}
//
//						Party.politicalAlignement TypeInput = politicalAlignement
//								.valueOf(inputpoliticalAlignement.toUpperCase());
//						Vector<Citizen> candidateList = new Vector<Citizen>();
//
//						System.out.println("Please enter 2 candidates to party:");
//						for (int i = 0; i < candidateList.size(); i++) {
//							System.out.println("Please enter: \nname");
//							String nameOfCandident = input.next();
//							System.out.println("Id");
//							String id = input.next();
//							if (id.length() >= 10 || id.length() < 9) {
//								throw new Exception("Id too long\n please enter id with 9 numbers");
//							}
//							if (id.startsWith("0")) {
//								throw new Exception("Id can not start with 0\nplease try againe");
//							}
//							System.out.println("Year of birth");
//							int yearBirthOfCandident = input.nextInt();
//							int daysSick = 0;
//							boolean answer2 = true;
//							System.out.println("Are you in sick?\nY/N");
//							String inputAnswer = input.next();
//							if (!(inputAnswer.equalsIgnoreCase("N") || inputAnswer.equalsIgnoreCase("Y"))) {
//								throw new Exception("Input invalid");
//							} else if (inputAnswer.equalsIgnoreCase("N")) {
//								answer2 = false;
//							} else if (inputAnswer.equalsIgnoreCase("Y")) {
//								answer2 = true;
//								System.out.println("How many days were you sick?");
//								daysSick = input.nextInt();
//							}
//							if (set.addCitizenToList(candidateList.elementAt(i))) {
//								throw new Exception("This Citizen is already in the system");
//							} else {
//								candidateList.add(new Candidate(nameOfCandident, id, yearBirthOfCandident, answer2,
//										daysSick, false, nameOfParty));
//								(theElectionSquence).addCitizen(candidateList.elementAt(i));
//								System.out.println("Candidate added sucssefully");
//							}
//
//						}
//						Party p1 = new Party(candidateList, nameOfParty, yearOfBirth, TypeInput);
//						(theElectionSquence).addpartylist(p1);
//						break;
//
//					}
//					catch (InputMismatchException e) {
//						System.out.println("Input invalid");
//						break;
//					} catch (Exception e) {
//						System.out.println(e.getMessage());
//						break;
//					}
//				} while (true);
//			}
//				break;
//
//			case 4: {
//				do {
//					try {
//						System.out.println("Please enter: \nname");
//						String name = input.next();
//						System.out.println("Id");
//						String id = input.next();
//						if (id.length() >= 10 || id.length() < 9) {
//							throw new Exception("Id too long\n please enter id with 9 numbers");
//						}
//						if (id.startsWith("0")) {
//							throw new Exception("Id can not start with 0\nplease try againe");
//						}
//						System.out.println("Year of birth");
//						int yearOfBirth = input.nextInt();
//						int daysSick = 0;
//						boolean answer = true;
//						System.out.println("Are you in sick?\nY/N");
//						String inputAnswer = input.next();
//						if (!(inputAnswer.equalsIgnoreCase("N") || inputAnswer.equalsIgnoreCase("Y"))) {
//							throw new Exception("Input invalid");
//						} else if (inputAnswer.equalsIgnoreCase("N")) {
//							answer = false;
//						} else if (inputAnswer.equalsIgnoreCase("Y")) {
//							answer = true;
//							System.out.println("How many days Were you sick?");
//							daysSick = input.nextInt();
//						}
//						System.out.println("Which party do you belong to?");
//						String inParty = input.next();
//						Candidate ca1 = new Candidate(name, id, yearOfBirth, answer, daysSick, false, inParty);
//
//						if (set.addCitizenToList(ca1)) {
//							throw new Exception("This Citizen is already in the system");
//						} else {
//							if ((theElectionSquence).addCandidantToParty(ca1)) {
//								(theElectionSquence).addCitizen(ca1);
//							}
//							else {
//								set.deleteLastCitizen();
//							}
//						}
//
//						break;
//
//					} catch (InputMismatchException e) {
//						System.out.println("Input invalid");
//					} catch (Exception e) {
//						System.out.println(e.getMessage());
//					}
//				} while (true);
//			}
//				break;
//
//			case 5: {
//				System.out.println((theElectionSquence).getVotingPlaceLIstToString().toString());
//
//			}
//				break;
//
//			case 6: {
//				System.out.println("Citizens: ");
//				System.out.println((theElectionSquence).getCitizenListToString(set));
//
//			}
//				break;
//			case 7: {
//				System.out.println("The Partys:\n-----------\n" + theElectionSquence.getPartyListToString().toString());
//
//			}
//				break;
//
//			case 8: {
//				theElectionSquence.votingTime(theElectionSquence.getVotingPlaceLIst(),
//						theElectionSquence.getPartyList());
//			}
//				break;
//			case 9: {
//				System.out.println(theElectionSquence.results());
//
//			}
//				break;
//			case 10:
//				System.out.println("Thank you for using the automated voting system\nhave a nice day!");
//				closeLoop = 2;
//				break;
//
//			}// end switch
//		} // end while loop
//		theElectionSquence.electionDataSaving(theElectionSquence);
//	}// end of main
//
//}
