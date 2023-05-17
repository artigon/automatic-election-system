package ElectionView;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.sun.source.tree.CatchTree;

import ElectionListeners.ElectionModelEventListener;
import ElectionListeners.EletionViewEvnetLIstener;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ElectionViewUI implements AbstractElectionView {
	Vector<EletionViewEvnetLIstener> allLIsteners = new Vector<EletionViewEvnetLIstener>();
	TextArea taShowVotingLocations = new TextArea();
	TextArea talShowCitizens = new TextArea();
	TextArea taShowParties = new TextArea();
	TextArea taShowResults = new TextArea();
	GridPane gpVotingTime = new GridPane();
	GridPane gpMenu = new GridPane();
	Label lblVotigTimeCitizenNameWelcom = new Label();
	TextArea taVotingTimePartyNames = new TextArea();
	int votingPlaceNum;
	String[] votingPlaceCitizensNames;
	String votingPlaceType = "N/A";
	String partyNames;
	int CitizenNum = 0;
	int[] partysVotes;
	int numOfCitizensThatVoted;
	boolean stopVotingTimeBoo = false;

	public ElectionViewUI(Stage theStage) {
		theStage.setTitle("Auto Election Mechine");
		GridPane gpRoot = new GridPane();
		gpRoot.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		gpRoot.setPadding(new Insets(10));
		gpRoot.setVgap(10);
		gpRoot.setHgap(10);

		// menu pane:
		GridPane gpMenu = new GridPane();
		gpMenu.setVisible(false);
		gpMenu.setPadding(new Insets(10));
		gpMenu.setVgap(10);
		gpMenu.setHgap(10);
		VBox vbMenuButtons = new VBox();
		vbMenuButtons.setSpacing(10);
		ToggleGroup menuButtonGroup = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Add Voting place");
		rb1.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		RadioButton rb2 = new RadioButton("Add Citizen");
		rb2.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		RadioButton rb3 = new RadioButton("Add Party");
		rb3.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		RadioButton rb4 = new RadioButton("Add Canidait");
		rb4.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		RadioButton rb5 = new RadioButton("Show all Voting places");
		rb5.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		RadioButton rb6 = new RadioButton("Show all Citizens");
		rb6.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		RadioButton rb7 = new RadioButton("Show all Partys");
		rb7.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		RadioButton rb8 = new RadioButton("Start Voting");
		rb8.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		RadioButton rb9 = new RadioButton("Show Voting Resolts");
		rb9.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		RadioButton rb10 = new RadioButton("Exit");
		rb10.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		rb1.setToggleGroup(menuButtonGroup);
		rb2.setToggleGroup(menuButtonGroup);
		rb3.setToggleGroup(menuButtonGroup);
		rb4.setToggleGroup(menuButtonGroup);
		rb5.setToggleGroup(menuButtonGroup);
		rb6.setToggleGroup(menuButtonGroup);
		rb7.setToggleGroup(menuButtonGroup);
		rb8.setToggleGroup(menuButtonGroup);
		rb9.setToggleGroup(menuButtonGroup);
		rb10.setToggleGroup(menuButtonGroup);
		vbMenuButtons.getChildren().addAll(rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10);
		gpMenu.add(vbMenuButtons, 0, 0, 10, 10);

		// data load?:
		GridPane gpDataLoad = new GridPane();
		gpDataLoad.setPadding(new Insets(10));
		gpDataLoad.setVgap(10);
		gpDataLoad.setHgap(10);
		HBox hbDataLoadButtpns = new HBox();
		hbDataLoadButtpns.setSpacing(20);
		Button btnDataLoadYes = new Button("Yes");
		btnDataLoadYes.setStyle("-fx-font-size: 1.5em;");
		Button btnDataLoadNo = new Button("No");
		btnDataLoadNo.setStyle("-fx-font-size: 1.5em;");
		hbDataLoadButtpns.getChildren().addAll(btnDataLoadYes, btnDataLoadNo);
		Label lblDataLoadQustion = new Label("Would you like to load saved data?");
		lblDataLoadQustion.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		gpDataLoad.add(lblDataLoadQustion, 2, 0);
		gpDataLoad.add(hbDataLoadButtpns, 2, 1);

		btnDataLoadNo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireDataLoadToModel(false);
				gpDataLoad.setVisible(false);
				gpMenu.setVisible(true);

			}
		});

		btnDataLoadYes.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireDataLoadToModel(true);
				gpDataLoad.setVisible(false);
				gpMenu.setVisible(true);

			}
		});

		// add voting place pane:
		GridPane gpAddVotingPlace = new GridPane();
		gpAddVotingPlace.setVisible(false);
		gpAddVotingPlace.setPadding(new Insets(10));
		gpAddVotingPlace.setVgap(10);
		gpAddVotingPlace.setHgap(10);
		Label lblWhatTypeOfVotingPlace = new Label("What type of voting place would you like to create?");
		lblWhatTypeOfVotingPlace.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		VBox vbVotingPlaceButtons = new VBox();
		vbVotingPlaceButtons.setSpacing(10);
		ToggleGroup votingPlaceButtonGroup = new ToggleGroup();
		RadioButton rbCitizenVotingPlace = new RadioButton("Citizen Voting PLace");
		rbCitizenVotingPlace.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		RadioButton rbQuarantineVotingPlace = new RadioButton("Quarantine Voting Place");
		rbQuarantineVotingPlace.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		RadioButton rbArmyVotingPlace = new RadioButton("Army Voting Place");
		rbArmyVotingPlace.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		RadioButton rbArmyQuarantineVotingPlace = new RadioButton("Army Quarantine Voting Place");
		rbArmyQuarantineVotingPlace.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		rbCitizenVotingPlace.setToggleGroup(votingPlaceButtonGroup);
		rbQuarantineVotingPlace.setToggleGroup(votingPlaceButtonGroup);
		rbArmyVotingPlace.setToggleGroup(votingPlaceButtonGroup);
		rbArmyQuarantineVotingPlace.setToggleGroup(votingPlaceButtonGroup);
		vbVotingPlaceButtons.getChildren().addAll(rbCitizenVotingPlace, rbQuarantineVotingPlace, rbArmyVotingPlace,
				rbArmyQuarantineVotingPlace);
		Label lblVotingPlaceLoction = new Label("Loction of Voting PLace:");
		lblVotingPlaceLoction.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		TextField tfVotingPlaceLoction = new TextField();

		HBox hbAddVotingPlaceButtons = new HBox();
		hbAddVotingPlaceButtons.setSpacing(20);
		Button btnAddVotingPlaceSave = new Button("Save");
		btnAddVotingPlaceSave.setStyle("-fx-font-size: 1.5em;");
		Button btnAddVotingPlaceBack = new Button("Back");
		btnAddVotingPlaceBack.setStyle("-fx-font-size: 1.5em;");
		hbAddVotingPlaceButtons.getChildren().addAll(btnAddVotingPlaceBack, btnAddVotingPlaceSave);

		btnAddVotingPlaceSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (!(rbCitizenVotingPlace.isSelected() || rbQuarantineVotingPlace.isSelected()
							|| rbArmyVotingPlace.isSelected() || rbArmyQuarantineVotingPlace.isSelected())) {
						throw new Exception("Cant leave voting place type uncheckd");
					} else if (tfVotingPlaceLoction.getText().isBlank()) {
						throw new Exception("Cant leave voting place loction empty");
					} else {
						if (rbCitizenVotingPlace.isSelected()) {
							fireNewVotingPlaceEventToModel(tfVotingPlaceLoction.getText(), "citizen");
						} else if (rbQuarantineVotingPlace.isSelected()) {
							fireNewVotingPlaceEventToModel(tfVotingPlaceLoction.getText(), "quarantine");
						} else if (rbArmyVotingPlace.isSelected()) {
							fireNewVotingPlaceEventToModel(tfVotingPlaceLoction.getText(), "army");
						} else if (rbArmyQuarantineVotingPlace.isSelected()) {
							fireNewVotingPlaceEventToModel(tfVotingPlaceLoction.getText(), "army quarantine");
						}

						gpMenu.setVisible(true);
						gpAddVotingPlace.setVisible(false);
						tfVotingPlaceLoction.clear();
						rbCitizenVotingPlace.setSelected(false);
						rbQuarantineVotingPlace.setSelected(false);
						rbArmyVotingPlace.setSelected(false);
						rbArmyQuarantineVotingPlace.setSelected(false);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		btnAddVotingPlaceBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpAddVotingPlace.setVisible(false);
				tfVotingPlaceLoction.clear();
				rbCitizenVotingPlace.setSelected(false);
				rbQuarantineVotingPlace.setSelected(false);
				rbArmyVotingPlace.setSelected(false);
				rbArmyQuarantineVotingPlace.setSelected(false);

			}
		});

		gpAddVotingPlace.add(lblWhatTypeOfVotingPlace, 0, 0);
		gpAddVotingPlace.add(vbVotingPlaceButtons, 0, 1, 10, 10);
		gpAddVotingPlace.add(lblVotingPlaceLoction, 0, 12);
		gpAddVotingPlace.add(tfVotingPlaceLoction, 0, 13);
		gpAddVotingPlace.add(hbAddVotingPlaceButtons, 0, 16);

		// Add Citizen pane:
		GridPane gpAddCitizen = new GridPane();
		gpAddCitizen.setVisible(false);
		gpAddCitizen.setPadding(new Insets(10));
		gpAddCitizen.setVgap(10);
		gpAddCitizen.setHgap(10);
		Label lblAddCitizenName = new Label("Name:");
		lblAddCitizenName.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		TextField tfAddCitizenName = new TextField();
		Label lblAddCitizenID = new Label("ID:");
		lblAddCitizenID.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		TextField tfAddCitizenID = new TextField();
		Label lblAddCitizenYear = new Label("Year of birth:");
		lblAddCitizenYear.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		TextField tfAddCitizenYear = new TextField();
		Label lblAddCitizenWeapon = new Label("Do you carry a weapon?");
		lblAddCitizenWeapon.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		CheckBox cbAddCitizenWeapon = new CheckBox();
		Label lblAddCitizenSick = new Label("ARe You Sick?");
		lblAddCitizenSick.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		CheckBox cbAddCitizenSick = new CheckBox();
		Label lblAddCitizenDaysOfSick = new Label("How many days have you been sick?");
		lblAddCitizenDaysOfSick.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		TextField tfAddCitizenDaysOfSick = new TextField();
		lblAddCitizenDaysOfSick.setVisible(false);
		tfAddCitizenDaysOfSick.setVisible(false);
		HBox hbAddCitizenButtons = new HBox();
		hbAddCitizenButtons.setSpacing(30);
		hbAddCitizenButtons.setAlignment(Pos.CENTER_LEFT);
		Button btnAddCitizenSave = new Button("Save");
		btnAddCitizenSave.setStyle("-fx-font-size: 1.5em;");
		Button btnAddCitizenBack = new Button("Back");
		btnAddCitizenBack.setStyle("-fx-font-size: 1.5em;");
		hbAddCitizenButtons.getChildren().addAll(btnAddCitizenBack, btnAddCitizenSave);

		gpAddCitizen.add(lblAddCitizenName, 0, 0);
		gpAddCitizen.add(tfAddCitizenName, 0, 1);
		gpAddCitizen.add(lblAddCitizenID, 0, 2);
		gpAddCitizen.add(tfAddCitizenID, 0, 3);
		gpAddCitizen.add(lblAddCitizenYear, 0, 4);
		gpAddCitizen.add(tfAddCitizenYear, 0, 5);
		gpAddCitizen.add(lblAddCitizenWeapon, 0, 6);
		gpAddCitizen.add(cbAddCitizenWeapon, 0, 7);
		gpAddCitizen.add(lblAddCitizenSick, 0, 8);
		gpAddCitizen.add(cbAddCitizenSick, 0, 9);
		gpAddCitizen.add(lblAddCitizenDaysOfSick, 0, 10);
		gpAddCitizen.add(tfAddCitizenDaysOfSick, 0, 11);
		gpAddCitizen.add(hbAddCitizenButtons, 0, 12);

		cbAddCitizenSick.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (cbAddCitizenSick.isSelected()) {
					lblAddCitizenDaysOfSick.setVisible(true);
					tfAddCitizenDaysOfSick.setVisible(true);
				} else {
					lblAddCitizenDaysOfSick.setVisible(false);
					tfAddCitizenDaysOfSick.setVisible(false);
				}

			}
		});

		btnAddCitizenSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (tfAddCitizenName.getText().isBlank() || tfAddCitizenID.getText().isBlank()
							|| tfAddCitizenYear.getText().isBlank()
							|| ((cbAddCitizenSick.isSelected()) ? tfAddCitizenDaysOfSick.getText().isBlank() : false)) {
						throw new Exception("Cant leave spaces empty");
					} else if (tfAddCitizenID.getText().length() != 9) {
						throw new Exception("ID invalid\nID must be a 9 digit number");
					} else if (Integer.parseInt(tfAddCitizenID.getText()) <= 0
							|| Integer.parseInt(tfAddCitizenYear.getText()) <= 0
							|| ((cbAddCitizenSick.isSelected())
									? Integer.parseInt(tfAddCitizenDaysOfSick.getText()) <= 0
									: false)) {
						throw new Exception("cant input negitive numbers");

					} else {
						int daysOfSickTemp = 0;
						if (cbAddCitizenSick.isSelected()) {
							daysOfSickTemp = Integer.parseInt(tfAddCitizenDaysOfSick.getText());
						}
						fireNewCitizenToModel(tfAddCitizenName.getText(), tfAddCitizenID.getText(),
								Integer.parseInt(tfAddCitizenYear.getText()), cbAddCitizenSick.isSelected(),
								daysOfSickTemp, cbAddCitizenWeapon.isSelected());
						gpMenu.setVisible(true);
						gpAddCitizen.setVisible(false);
						cbAddCitizenWeapon.setSelected(false);
						cbAddCitizenSick.setSelected(false);
						lblAddCitizenDaysOfSick.setVisible(false);
						tfAddCitizenDaysOfSick.setVisible(false);
						tfAddCitizenName.clear();
						tfAddCitizenID.clear();
						tfAddCitizenYear.clear();
						tfAddCitizenDaysOfSick.clear();

					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "invalid input\nID and Year must only be numbers");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		btnAddCitizenBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpAddCitizen.setVisible(false);
				cbAddCitizenWeapon.setSelected(false);
				cbAddCitizenSick.setSelected(false);
				lblAddCitizenDaysOfSick.setVisible(false);
				tfAddCitizenDaysOfSick.setVisible(false);
				tfAddCitizenName.clear();
				tfAddCitizenID.clear();
				tfAddCitizenYear.clear();
				tfAddCitizenDaysOfSick.clear();

			}
		});

		// add party pane:
		GridPane gpAddParty = new GridPane();
		gpAddParty.setVisible(false);
		gpAddParty.setPadding(new Insets(10));
		gpAddParty.setVgap(10);
		gpAddParty.setHgap(10);
		Label lblAddPartyName = new Label("Partys name:");
		lblAddPartyName.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddPartyName = new TextField();
		Label lblAddPartyYear = new Label("Year of patys birth:");
		lblAddPartyYear.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddPartyYear = new TextField();
		Label lblAddPartyPoliticalAlignement = new Label("Political alignement:");
		lblAddPartyPoliticalAlignement.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		ComboBox<String> cmbPoliticalAlignement = new ComboBox<String>();
		cmbPoliticalAlignement.getItems().addAll("Left", "Center", "Right");
		cmbPoliticalAlignement.setValue("Center");
		Label lblCandidates = new Label("Candidates:");
		lblCandidates.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		HBox hbAddPatyCandidates = new HBox();
		hbAddPatyCandidates.setSpacing(10);

		VBox vbAddPatyCandidate1 = new VBox();
		Label lblAddPartyCandidateName1 = new Label("Name:");
		lblAddPartyCandidateName1.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddPartyCandidateName1 = new TextField();
		Label lblAddPartyCandidateID1 = new Label("ID:");
		lblAddPartyCandidateID1.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddPartyCandidateID1 = new TextField();
		Label lblAddPartyCandidateYear1 = new Label("Year of birth:");
		lblAddPartyCandidateYear1.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddPartyCandidateYear1 = new TextField();
		Label lblAddPartyCandidateSick1 = new Label("ARe You Sick?");
		lblAddPartyCandidateSick1.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		CheckBox cbAddPartyCandidateSick1 = new CheckBox();
		Label lblAddPartyCandidateDaysOfSick1 = new Label("How many days have you been sick?");
		lblAddPartyCandidateDaysOfSick1.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddPartyCandidateDaysOfSick1 = new TextField();
		lblAddPartyCandidateDaysOfSick1.setVisible(false);
		tfAddPartyCandidateDaysOfSick1.setVisible(false);

		cbAddPartyCandidateSick1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (cbAddPartyCandidateSick1.isSelected()) {
					lblAddPartyCandidateDaysOfSick1.setVisible(true);
					tfAddPartyCandidateDaysOfSick1.setVisible(true);
				} else {
					lblAddPartyCandidateDaysOfSick1.setVisible(false);
					tfAddPartyCandidateDaysOfSick1.setVisible(false);
				}

			}
		});

		vbAddPatyCandidate1.getChildren().addAll(lblAddPartyCandidateName1, tfAddPartyCandidateName1,
				lblAddPartyCandidateID1, tfAddPartyCandidateID1, lblAddPartyCandidateYear1, tfAddPartyCandidateYear1,
				lblAddPartyCandidateSick1, cbAddPartyCandidateSick1, lblAddPartyCandidateDaysOfSick1,
				tfAddPartyCandidateDaysOfSick1);

		VBox vbAddPatyCandidate2 = new VBox();
		Label lblAddPartyCandidateName2 = new Label("Name:");
		lblAddPartyCandidateName2.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddPartyCandidateName2 = new TextField();
		Label lblAddPartyCandidateID2 = new Label("ID:");
		lblAddPartyCandidateID2.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddPartyCandidateID2 = new TextField();
		Label lblAddPartyCandidateYear2 = new Label("Year of birth:");
		lblAddPartyCandidateYear2.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddPartyCandidateYear2 = new TextField();
		Label lblAddPartyCandidateSick2 = new Label("Are You Sick?");
		lblAddPartyCandidateSick2.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		CheckBox cbAddPartyCandidateSick2 = new CheckBox();
		Label lblAddPartyCandidateDaysOfSick2 = new Label("How many days have you been sick?");
		lblAddPartyCandidateDaysOfSick2.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddPartyCandidateDaysOfSick2 = new TextField();
		lblAddPartyCandidateDaysOfSick2.setVisible(false);
		tfAddPartyCandidateDaysOfSick2.setVisible(false);

		cbAddPartyCandidateSick2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (cbAddPartyCandidateSick2.isSelected()) {
					lblAddPartyCandidateDaysOfSick2.setVisible(true);
					tfAddPartyCandidateDaysOfSick2.setVisible(true);
				} else {
					lblAddPartyCandidateDaysOfSick2.setVisible(false);
					tfAddPartyCandidateDaysOfSick2.setVisible(false);
				}

			}
		});

		vbAddPatyCandidate2.getChildren().addAll(lblAddPartyCandidateName2, tfAddPartyCandidateName2,
				lblAddPartyCandidateID2, tfAddPartyCandidateID2, lblAddPartyCandidateYear2, tfAddPartyCandidateYear2,
				lblAddPartyCandidateSick2, cbAddPartyCandidateSick2, lblAddPartyCandidateDaysOfSick2,
				tfAddPartyCandidateDaysOfSick2);

		hbAddPatyCandidates.getChildren().addAll(vbAddPatyCandidate1, vbAddPatyCandidate2);

		HBox hbAddPartyButtons = new HBox();
		hbAddPartyButtons.setSpacing(20);
		Button btnAddPartySave = new Button("Save");
		btnAddPartySave.setStyle("-fx-font-size: 1.5em;");
		Button btnAddPartyBack = new Button("Back");
		btnAddPartyBack.setStyle("-fx-font-size: 1.5em;");
		hbAddPartyButtons.getChildren().addAll(btnAddPartyBack, btnAddPartySave);

		gpAddParty.add(lblAddPartyName, 0, 0);
		gpAddParty.add(tfAddPartyName, 0, 1);
		gpAddParty.add(lblAddPartyYear, 0, 2);
		gpAddParty.add(tfAddPartyYear, 0, 3);
		gpAddParty.add(lblAddPartyPoliticalAlignement, 0, 4);
		gpAddParty.add(cmbPoliticalAlignement, 0, 5);
		gpAddParty.add(lblCandidates, 0, 6);
		gpAddParty.add(hbAddPatyCandidates, 0, 7);
		gpAddParty.add(hbAddPartyButtons, 0, 9);

		btnAddPartySave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (tfAddPartyName.getText().isBlank() || tfAddPartyYear.getText().isBlank()
							|| tfAddPartyCandidateName1.getText().isBlank()
							|| tfAddPartyCandidateYear1.getText().isBlank() || (cbAddPartyCandidateSick1.isSelected())
									? tfAddPartyCandidateDaysOfSick1.getText().isBlank()
									: false || tfAddPartyCandidateName2.getText().isBlank()
											|| tfAddPartyCandidateYear2.getText().isBlank()
											|| (cbAddPartyCandidateSick2.isSelected())
													? tfAddPartyCandidateDaysOfSick2.getText().isBlank()
													: false) {
						throw new Exception("Cant leave fields blank");
					} else if (tfAddPartyCandidateID1.getText().length() != 9
							|| tfAddPartyCandidateID2.getText().length() != 9) {
						throw new Exception("ID invalid\nID must be a 9 digit number");
					} else if (Integer.parseInt(tfAddPartyYear.getText()) <= 0
							|| Integer.parseInt(tfAddPartyCandidateID1.getText()) <= 0
							|| Integer.parseInt(tfAddPartyCandidateYear1.getText()) <= 0
							|| ((cbAddPartyCandidateSick1.isSelected())
									? Integer.parseInt(tfAddPartyCandidateDaysOfSick1.getText()) <= 0
									: false)
							|| Integer.parseInt(tfAddPartyCandidateID2.getText()) <= 0
							|| Integer.parseInt(tfAddPartyCandidateYear2.getText()) <= 0
							|| ((cbAddPartyCandidateSick2.isSelected())
									? Integer.parseInt(tfAddPartyCandidateDaysOfSick2.getText()) <= 0
									: false)) {
						throw new Exception("cant input negitive numbers");

					} else {
						fireNewPartyToModel(tfAddPartyName.getText(), tfAddPartyYear.getText(),
								cmbPoliticalAlignement.getValue().toString(), tfAddPartyCandidateName1.getText(),
								tfAddPartyCandidateID1.getText(), Integer.parseInt(tfAddPartyCandidateYear1.getText()),
								cbAddPartyCandidateSick1.isSelected(),
								(cbAddPartyCandidateSick1.isSelected())
										? Integer.parseInt(tfAddPartyCandidateDaysOfSick1.getText())
										: 0,
								tfAddPartyCandidateName2.getText(), tfAddPartyCandidateID2.getText(),
								Integer.parseInt(tfAddPartyCandidateYear2.getText()),
								cbAddPartyCandidateSick2.isSelected(),
								(cbAddPartyCandidateSick2.isSelected())
										? Integer.parseInt(tfAddPartyCandidateDaysOfSick2.getText())
										: 0);
						gpMenu.setVisible(true);
						gpAddParty.setVisible(false);
						tfAddPartyName.clear();
						tfAddPartyYear.clear();
						tfAddPartyCandidateName1.clear();
						tfAddPartyCandidateName2.clear();
						tfAddPartyCandidateYear1.clear();
						tfAddPartyCandidateYear2.clear();
						tfAddPartyCandidateID1.clear();
						tfAddPartyCandidateID2.clear();
						tfAddPartyCandidateDaysOfSick1.clear();
						tfAddPartyCandidateDaysOfSick2.clear();
						cbAddPartyCandidateSick1.setSelected(false);
						cbAddPartyCandidateSick2.setSelected(false);
						lblAddPartyCandidateDaysOfSick1.setVisible(false);
						tfAddPartyCandidateDaysOfSick1.setVisible(false);
						lblAddPartyCandidateDaysOfSick2.setVisible(false);
						tfAddPartyCandidateDaysOfSick2.setVisible(false);
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "invalid input\nID and Year must only be numbers");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		btnAddPartyBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpAddParty.setVisible(false);
				tfAddPartyName.clear();
				tfAddPartyYear.clear();
				tfAddPartyCandidateName1.clear();
				tfAddPartyCandidateName2.clear();
				tfAddPartyCandidateYear1.clear();
				tfAddPartyCandidateYear2.clear();
				tfAddPartyCandidateID1.clear();
				tfAddPartyCandidateID2.clear();
				tfAddPartyCandidateDaysOfSick1.clear();
				tfAddPartyCandidateDaysOfSick2.clear();
				cbAddPartyCandidateSick1.setSelected(false);
				cbAddPartyCandidateSick2.setSelected(false);
				lblAddPartyCandidateDaysOfSick1.setVisible(false);
				tfAddPartyCandidateDaysOfSick1.setVisible(false);
				lblAddPartyCandidateDaysOfSick2.setVisible(false);
				tfAddPartyCandidateDaysOfSick2.setVisible(false);

			}
		});

		// add candidate to a party gridpane
		GridPane gpAddCandidateToParty = new GridPane();
		gpAddCandidateToParty.setVisible(false);
		gpAddCandidateToParty.setPadding(new Insets(10));
		gpAddCandidateToParty.setVgap(10);
		gpAddCandidateToParty.setHgap(10);
		Label lblAddCandidateName = new Label("Name:");
		lblAddCandidateName.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddCandidateName = new TextField();
		Label lblAddCandidateID = new Label("ID:");
		lblAddCandidateID.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddCandidateID = new TextField();
		Label lblAddCandidateYear = new Label("Year of birth:");
		lblAddCandidateYear.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddCandidateYear = new TextField();
		Label lblAddCandidateSick = new Label("Are You Sick?");
		lblAddCandidateSick.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		CheckBox cbAddCandidateSick = new CheckBox();
		Label lblAddCandidateDaysOfSick = new Label("How many days have you been sick?");
		lblAddCandidateDaysOfSick.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddCandidateDaysOfSick = new TextField();
		lblAddCandidateDaysOfSick.setVisible(false);
		tfAddCandidateDaysOfSick.setVisible(false);
		Label lblAddCandidatePartyName = new Label("Which party Name:");
		lblAddCandidatePartyName.setStyle("-fx-text-fill: white;-fx-font-size: 1.5em;");
		TextField tfAddCandidatePartyName = new TextField();

		HBox hbAddCandidateButtons = new HBox();
		hbAddCandidateButtons.setSpacing(20);
		Button btnAddCandidateSave = new Button("Save");
		btnAddCandidateSave.setStyle("-fx-font-size: 1.5em;");
		Button btnAddCandiateBack = new Button("Back");
		btnAddCandiateBack.setStyle("-fx-font-size: 1.5em;");
		hbAddCandidateButtons.getChildren().addAll(btnAddCandiateBack, btnAddCandidateSave);

		gpAddCandidateToParty.add(lblAddCandidateName, 0, 0);
		gpAddCandidateToParty.add(tfAddCandidateName, 0, 1);
		gpAddCandidateToParty.add(lblAddCandidateID, 0, 2);
		gpAddCandidateToParty.add(tfAddCandidateID, 0, 3);
		gpAddCandidateToParty.add(lblAddCandidateYear, 0, 4);
		gpAddCandidateToParty.add(tfAddCandidateYear, 0, 5);
		gpAddCandidateToParty.add(lblAddCandidateSick, 0, 6);
		gpAddCandidateToParty.add(cbAddCandidateSick, 0, 7);
		gpAddCandidateToParty.add(lblAddCandidateDaysOfSick, 0, 8);
		gpAddCandidateToParty.add(tfAddCandidateDaysOfSick, 0, 9);
		gpAddCandidateToParty.add(lblAddCandidatePartyName, 0, 10);
		gpAddCandidateToParty.add(tfAddCandidatePartyName, 0, 11);
		gpAddCandidateToParty.add(hbAddCandidateButtons, 0, 12);

		cbAddCandidateSick.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (cbAddCandidateSick.isSelected()) {
					lblAddCandidateDaysOfSick.setVisible(true);
					tfAddCandidateDaysOfSick.setVisible(true);
				} else {
					lblAddCandidateDaysOfSick.setVisible(false);
					tfAddCandidateDaysOfSick.setVisible(false);
				}

			}
		});

		btnAddCandidateSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (tfAddCandidateName.getText().isBlank() || tfAddCandidateID.getText().isBlank()
							|| tfAddCandidateYear.getText().isBlank()
							|| ((cbAddCandidateSick.isSelected()) ? tfAddCandidateDaysOfSick.getText().isBlank()
									: false)) {
						throw new Exception("Cant leave spaces empty");
					} else if (tfAddCandidateID.getText().length() != 9) {
						throw new Exception("ID invalid\nID must be a 9 digit number");
					} else if (Integer.parseInt(tfAddCandidateID.getText()) <= 0
							|| Integer.parseInt(tfAddCandidateYear.getText()) <= 0
							|| ((cbAddCandidateSick.isSelected())
									? Integer.parseInt(tfAddCandidateDaysOfSick.getText()) <= 0
									: false)) {
						throw new Exception("cant input negitive numbers");

					} else {
						int daysOfSickTemp = 0;
						if (cbAddCitizenSick.isSelected()) {
							daysOfSickTemp = Integer.parseInt(tfAddCitizenDaysOfSick.getText());
						}

						fireNewCandidateToModel(tfAddCandidateName.getText(), tfAddCandidateID.getText(),
								Integer.parseInt(tfAddCandidateYear.getText()), cbAddCandidateSick.isSelected(),
								(cbAddCandidateSick.isSelected()) ? Integer.parseInt(tfAddCandidateDaysOfSick.getText())
										: 0,
								false, tfAddCandidatePartyName.getText());

						gpMenu.setVisible(true);
						gpAddCandidateToParty.setVisible(false);
						cbAddCandidateSick.setSelected(false);
						lblAddCandidateDaysOfSick.setVisible(false);
						tfAddCandidateDaysOfSick.setVisible(false);
						tfAddCandidateName.clear();
						tfAddCandidateID.clear();
						tfAddCandidateYear.clear();
						tfAddCandidateDaysOfSick.clear();
						tfAddCandidatePartyName.clear();
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "invalid input\nID and Year must only be numbers");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});

		btnAddCandiateBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpAddCandidateToParty.setVisible(false);
				cbAddCandidateSick.setSelected(false);
				lblAddCandidateDaysOfSick.setVisible(false);
				tfAddCandidateDaysOfSick.setVisible(false);
				tfAddCandidateName.clear();
				tfAddCandidateID.clear();
				tfAddCandidateYear.clear();
				tfAddCandidateDaysOfSick.clear();
				tfAddCandidatePartyName.clear();

			}
		});

		// show all voting locations
		GridPane gpShowVotingLocations = new GridPane();
		gpShowVotingLocations.setVisible(false);
		gpShowVotingLocations.setPadding(new Insets(10));
		gpShowVotingLocations.setVgap(10);
		gpShowVotingLocations.setHgap(10);
		Button btnShowVotingLocationsBack = new Button("Back");
		btnShowVotingLocationsBack.setStyle("-fx-font-size: 1.5em;");
		taShowVotingLocations.setEditable(false);
		taShowVotingLocations.setWrapText(true); // here\/
		taShowVotingLocations.setStyle("-fx-font-size: 1.5em;");// to change size change the value of "num"em
		gpShowVotingLocations.add(taShowVotingLocations, 0, 0);
		gpShowVotingLocations.add(btnShowVotingLocationsBack, 0, 5);

		btnShowVotingLocationsBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpShowVotingLocations.setVisible(false);
				taShowVotingLocations.clear();

			}
		});

		// show citizens gridpane
		GridPane gpShowCitizens = new GridPane();
		gpShowCitizens.setVisible(false);
		gpShowCitizens.setPadding(new Insets(10));
		gpShowCitizens.setVgap(10);
		gpShowCitizens.setHgap(10);
		Button btnShowCitizensBack = new Button("Back");
		btnShowCitizensBack.setStyle("-fx-font-size: 1.5em;");
		talShowCitizens.setEditable(false);
		talShowCitizens.setWrapText(true); // here\/
		talShowCitizens.setStyle("-fx-font-size: 1.5em;");// to change size change the value of "num"em

		gpShowCitizens.add(talShowCitizens, 0, 0);
		gpShowCitizens.add(btnShowCitizensBack, 0, 5);
		btnShowCitizensBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpShowCitizens.setVisible(false);
				talShowCitizens.clear();

			}
		});

		// show all partise gridpane:
		GridPane gpShowParties = new GridPane();
		gpShowParties.setVisible(false);
		gpShowParties.setPadding(new Insets(10));
		gpShowParties.setVgap(10);
		gpShowParties.setHgap(10);
		taShowParties.setEditable(false);
		taShowParties.setWrapText(true); // here\/
		taShowParties.setStyle("-fx-font-size: 1.5em;");// to change size change the value of "num"em

		Button btnShowPartiesBack = new Button("Back");
		btnShowPartiesBack.setStyle("-fx-font-size: 1.5em;");

		gpShowParties.add(taShowParties, 0, 0);
		gpShowParties.add(btnShowPartiesBack, 0, 5);
		btnShowPartiesBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpShowParties.setVisible(false);
				taShowParties.clear();

			}
		});

		// GridPane Voting time:
		GridPane gpVotingTime = new GridPane();
		gpVotingTime.setVisible(false);
		gpVotingTime.setPadding(new Insets(10));
		gpVotingTime.setVgap(10);
		gpVotingTime.setHgap(10);

		lblVotigTimeCitizenNameWelcom.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		taVotingTimePartyNames.setStyle("-fx-font-size: 1.5em;");
		Label lblVotingTimeMaskQustion = new Label("Do you have a mask?");
		lblVotingTimeMaskQustion.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		lblVotingTimeMaskQustion.setVisible(false);
		HBox votingTimeMaskButtons = new HBox();
		votingTimeMaskButtons.setSpacing(20);
		Button btnVotingTimeMaskQustionYes = new Button("Yes");
		btnVotingTimeMaskQustionYes.setStyle("-fx-font-size: 1.5em;");
		Button btnVotingTimeMaskQustionNo = new Button("No");
		btnVotingTimeMaskQustionNo.setStyle("-fx-font-size: 1.5em;");
		votingTimeMaskButtons.getChildren().addAll(btnVotingTimeMaskQustionYes, btnVotingTimeMaskQustionNo);
		votingTimeMaskButtons.setVisible(false);
		Label lblVotingTimeVotingQustion = new Label("Do you want to vote?");
		lblVotingTimeVotingQustion.setStyle("-fx-text-fill: white; -fx-font-size: 1.5em;");
		HBox votingTimeVoteQustionButtons = new HBox();
		votingTimeVoteQustionButtons.setSpacing(20);
		Button btnVotingTimeVotingQustionYes = new Button("Yes");
		btnVotingTimeVotingQustionYes.setStyle("-fx-font-size: 1.5em;");
		Button btnVotingTimeVotingQustionNo = new Button("No");
		btnVotingTimeVotingQustionNo.setStyle("-fx-font-size: 1.5em;");
		votingTimeVoteQustionButtons.getChildren().addAll(btnVotingTimeVotingQustionYes, btnVotingTimeVotingQustionNo);
		TextField tfVotingTimeVoteAnswer = new TextField();
		Button btnVotingTimeVoteButtone = new Button("Vote");
		btnVotingTimeVoteButtone.setStyle("-fx-font-size: 1.5em;");
		taVotingTimePartyNames.setVisible(false);
		taVotingTimePartyNames.setEditable(false);
		tfVotingTimeVoteAnswer.setVisible(false);
		btnVotingTimeVoteButtone.setVisible(false);
		gpVotingTime.add(lblVotigTimeCitizenNameWelcom, 0, 0);
		gpVotingTime.add(taVotingTimePartyNames, 0, 1);
		gpVotingTime.add(tfVotingTimeVoteAnswer, 0, 2);
		gpVotingTime.add(btnVotingTimeVoteButtone, 0, 3);
		gpVotingTime.add(lblVotingTimeMaskQustion, 0, 1);
		gpVotingTime.add(votingTimeMaskButtons, 0, 2);
		gpVotingTime.add(lblVotingTimeVotingQustion, 0, 1);
		gpVotingTime.add(votingTimeVoteQustionButtons, 0, 2);

		if (votingPlaceType.equalsIgnoreCase("quarantine") || votingPlaceType.equalsIgnoreCase("army quarantine")) {
			lblVotingTimeMaskQustion.setVisible(true);
			votingTimeMaskButtons.setVisible(true);
			lblVotingTimeVotingQustion.setVisible(false);
			votingTimeVoteQustionButtons.setVisible(false);
		}

		btnVotingTimeMaskQustionNo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				CitizenNum++;
				if (votingPlaceCitizensNames.length == CitizenNum) {
					fireVotingPlaceVotesToModel(votingPlaceNum, partysVotes, numOfCitizensThatVoted);
					if (stopVotingTimeBoo) {
						gpVotingTime.setVisible(false);
						gpMenu.setVisible(true);
					}
				} else {
					if (votingPlaceType.equalsIgnoreCase("quarantine")
							|| votingPlaceType.equalsIgnoreCase("army quarantine")) {
						lblVotingTimeMaskQustion.setVisible(true);
						votingTimeMaskButtons.setVisible(true);
						lblVotingTimeVotingQustion.setVisible(false);
						votingTimeVoteQustionButtons.setVisible(false);
						taVotingTimePartyNames.setVisible(false);
						btnVotingTimeVoteButtone.setVisible(false);
						tfVotingTimeVoteAnswer.setVisible(false);
					} else {
						taVotingTimePartyNames.setVisible(false);
						tfVotingTimeVoteAnswer.setVisible(false);
						btnVotingTimeVoteButtone.setVisible(false);
						lblVotingTimeVotingQustion.setVisible(true);
						votingTimeVoteQustionButtons.setVisible(true);

					}
				}
				if (!(stopVotingTimeBoo)) {
					lblVotigTimeCitizenNameWelcom.setText(!(votingPlaceCitizensNames[CitizenNum].equalsIgnoreCase(""))
							? (votingPlaceCitizensNames[CitizenNum] + " welcome to voting mechine\nits time to vote!")
							: "N/A");
				}

			}
		});

		btnVotingTimeVotingQustionNo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				CitizenNum++;
				if (votingPlaceCitizensNames.length == CitizenNum) {
					fireVotingPlaceVotesToModel(votingPlaceNum, partysVotes, numOfCitizensThatVoted);
					if (stopVotingTimeBoo) {
						gpVotingTime.setVisible(false);
						gpMenu.setVisible(true);
					}
				} else {
					if (votingPlaceType.equalsIgnoreCase("quarantine")
							|| votingPlaceType.equalsIgnoreCase("army quarantine")) {
						lblVotingTimeMaskQustion.setVisible(true);
						votingTimeMaskButtons.setVisible(true);
						lblVotingTimeVotingQustion.setVisible(false);
						votingTimeVoteQustionButtons.setVisible(false);
						taVotingTimePartyNames.setVisible(false);
						btnVotingTimeVoteButtone.setVisible(false);
						tfVotingTimeVoteAnswer.setVisible(false);
					} else {
						taVotingTimePartyNames.setVisible(false);
						tfVotingTimeVoteAnswer.setVisible(false);
						btnVotingTimeVoteButtone.setVisible(false);
						lblVotingTimeVotingQustion.setVisible(true);
						votingTimeVoteQustionButtons.setVisible(true);

					}
				}
				if (!(stopVotingTimeBoo)) {
					lblVotigTimeCitizenNameWelcom.setText(!(votingPlaceCitizensNames[CitizenNum].equalsIgnoreCase(""))
							? (votingPlaceCitizensNames[CitizenNum] + " welcome to voting mechine\nits time to vote!")
							: "N/A");
				}

			}
		});

		btnVotingTimeMaskQustionYes.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				lblVotingTimeVotingQustion.setVisible(true);
				votingTimeVoteQustionButtons.setVisible(true);
				lblVotingTimeMaskQustion.setVisible(false);
				votingTimeMaskButtons.setVisible(false);

			}
		});

		btnVotingTimeVotingQustionYes.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				lblVotingTimeVotingQustion.setVisible(false);
				votingTimeVoteQustionButtons.setVisible(false);
				taVotingTimePartyNames.setVisible(true);
				tfVotingTimeVoteAnswer.setVisible(true);
				btnVotingTimeVoteButtone.setVisible(true);
				numOfCitizensThatVoted++;

			}
		});

		btnVotingTimeVoteButtone.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if ((Integer.parseInt(tfVotingTimeVoteAnswer.getText()) - 1) > partysVotes.length) {
						tfVotingTimeVoteAnswer.clear();
						throw new Exception("Input invalid\ninput must be btween 1 - " + partysVotes.length);
					} else {
						if (votingPlaceType.equalsIgnoreCase("quarantine")
								|| votingPlaceType.equalsIgnoreCase("army quarantine")) {
							lblVotingTimeMaskQustion.setVisible(true);
							votingTimeMaskButtons.setVisible(true);
							lblVotingTimeVotingQustion.setVisible(false);
							votingTimeVoteQustionButtons.setVisible(false);
							taVotingTimePartyNames.setVisible(false);
							btnVotingTimeVoteButtone.setVisible(false);
							tfVotingTimeVoteAnswer.setVisible(false);
						} else {
							taVotingTimePartyNames.setVisible(false);
							tfVotingTimeVoteAnswer.setVisible(false);
							btnVotingTimeVoteButtone.setVisible(false);
							lblVotingTimeVotingQustion.setVisible(true);
							votingTimeVoteQustionButtons.setVisible(true);

						}

						partysVotes[Integer.parseInt(tfVotingTimeVoteAnswer.getText()) - 1]++;
						tfVotingTimeVoteAnswer.clear();
						CitizenNum++;
						if (votingPlaceCitizensNames.length == CitizenNum) {
							fireVotingPlaceVotesToModel(votingPlaceNum, partysVotes, numOfCitizensThatVoted);
							if (stopVotingTimeBoo) {
								gpVotingTime.setVisible(false);
								gpMenu.setVisible(true);
							}
						}
						if (!(stopVotingTimeBoo)) {
							lblVotigTimeCitizenNameWelcom
									.setText(!(votingPlaceCitizensNames[CitizenNum].equalsIgnoreCase(""))
											? (votingPlaceCitizensNames[CitizenNum]
													+ " welcome to voting mechine\nits time to vote!")
											: "N/A");
						}
					}

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Input must be a number");
					tfVotingTimeVoteAnswer.clear();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			}
		});

		// show voting resolts:
		GridPane gpShowResults = new GridPane();
		gpShowResults.setVisible(false);
		gpShowResults.setPadding(new Insets(10));
		gpShowResults.setVgap(10);
		gpShowResults.setHgap(10);
		taShowResults.setEditable(false);
		taShowResults.setWrapText(true); // here\/
		taShowResults.setStyle("-fx-font-size: 1.5em;");// to change size change the value of "num"em

		Button btnShowResultsBack = new Button("Back");
		gpShowResults.add(taShowResults, 0, 0);
		gpShowResults.add(btnShowResultsBack, 0, 1);

		btnShowResultsBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpShowResults.setVisible(false);
				taShowResults.clear();

			}
		});

		// menu button functions:
		rb1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				rb1.setSelected(false);
				gpMenu.setVisible(false);
				gpAddVotingPlace.setVisible(true);

			}
		});

		rb2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				rb2.setSelected(false);
				gpMenu.setVisible(false);
				gpAddCitizen.setVisible(true);

			}
		});

		rb3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				rb3.setSelected(false);
				gpMenu.setVisible(false);
				gpAddParty.setVisible(true);

			}
		});

		rb4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				rb4.setSelected(false);
				gpMenu.setVisible(false);
				gpAddCandidateToParty.setVisible(true);

			}
		});

		rb5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireShowVotingPlacesFromModel();
				rb5.setSelected(false);
				gpMenu.setVisible(false);
				gpShowVotingLocations.setVisible(true);

			}
		});
		rb6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireShowCitizensFromModel();
				rb6.setSelected(false);
				gpMenu.setVisible(false);
				gpShowCitizens.setVisible(true);

			}
		});
		rb7.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireShowPartysFromModel();
				rb7.setSelected(false);
				gpMenu.setVisible(false);
				gpShowParties.setVisible(true);

			}
		});
		rb8.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireStartVoting();
				CitizenNum = 0;
				stopVotingTimeBoo = false;
				rb8.setSelected(false);
				gpMenu.setVisible(false);
				gpVotingTime.setVisible(true);
			}
		});
		rb9.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireShowResultsFromModel();
				rb9.setSelected(false);
				gpMenu.setVisible(false);
				gpShowResults.setVisible(true);

			}
		});

		rb10.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				fireSaveModelData();
				Platform.exit();
			}
		});

		// adding GridPanes to main gpRoot:
		gpRoot.add(gpDataLoad, 5, 0, 20, 20);
		gpRoot.add(gpMenu, 5, 0, 20, 20);
		gpRoot.add(gpAddVotingPlace, 5, 0, 20, 20);
		gpRoot.add(gpAddCitizen, 5, 0, 20, 20);
		gpRoot.add(gpAddParty, 5, 0, 20, 20);
		gpRoot.add(gpAddCandidateToParty, 5, 0, 20, 20);
		gpRoot.add(gpShowCitizens, 5, 0, 20, 20);
		gpRoot.add(gpShowVotingLocations, 5, 0, 20, 20);
		gpRoot.add(gpShowParties, 5, 0, 20, 20);
		gpRoot.add(gpShowResults, 5, 0, 20, 20);
		gpRoot.add(gpVotingTime, 5, 0, 20, 20);

		theStage.setScene(new Scene(gpRoot, 700, 600));
		theStage.setResizable(false);
		theStage.show();

	}// end of contractor

	// for listeners:
	public void register(EletionViewEvnetLIstener newListener) {
		allLIsteners.add(newListener);

	}

	// for UI:
	public void setVotingPlacesList(String temp) {
		taShowVotingLocations.setText(temp);
	}

	public void setCitizensList(String temp) {
		talShowCitizens.setText(temp);
	}

	public void citizenIsAlredyInListFromModel() {
		JOptionPane.showMessageDialog(null, "Citizen is alredy registerd with that ID");
	}

	public void setPartyList(String temp) {
		taShowParties.setText(temp);
	}

	public void setResults(String temp) {
		taShowResults.setText(temp);
	}

	public void messageFromModel(String temp) {
		JOptionPane.showMessageDialog(null, temp);
	}

	public void votingPlaceCitizensForVoting(int votingPlaceNum, String[] votingPlaceCitizensNames,
			String votingPlaceType, String partyNames, int numOfPartys) {
		this.votingPlaceType = votingPlaceType;
		this.votingPlaceNum = votingPlaceNum;
		this.votingPlaceCitizensNames = new String[votingPlaceCitizensNames.length];
		for (int i = 0; i < this.votingPlaceCitizensNames.length; i++) {
			this.votingPlaceCitizensNames[i] = votingPlaceCitizensNames[i];
		}
		this.partyNames = partyNames;
		this.partysVotes = new int[numOfPartys];
		this.CitizenNum = 0;
		this.numOfCitizensThatVoted = 0;
		lblVotigTimeCitizenNameWelcom.setText(!(votingPlaceCitizensNames[CitizenNum].equalsIgnoreCase(""))
				? (votingPlaceCitizensNames[CitizenNum] + " welcome to voting mechine\nits time to vote!")
				: "N/A");
		taVotingTimePartyNames.setText(partyNames);
	}

	public void stopVotingTime() {
		stopVotingTimeBoo = true;
		JOptionPane.showMessageDialog(null, "Voting has finished");
	}

	// To model:
	public void fireNewVotingPlaceEventToModel(String location, String type) {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.newVotingPlaceEventToModel(location, type);
		}
	}

	public void fireNewCitizenToModel(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon) {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.newCitizenToModel(name, id, birthyear, inquarantine, DaysSick, isCaraingWeapon);
		}
	}

	public void fireShowVotingPlacesFromModel() {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.showVotingPlacesFromModel();
		}

	}

	public void fireShowCitizensFromModel() {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.showCitizensFromModel();
		}
	}

	public void fireNewPartyToModel(String partyName, String partyYear, String politicalAlignement,
			String partyCandidate1Name, String partyCandidate1ID, int partyCandidate1Year, boolean partyCandidate1Sick,
			int partyCandidate1DaysOfSick, String partyCandidate2Name, String partyCandidate2ID,
			int partyCandidate2Year, boolean partyCandidate2Sick, int partyCandidate2DaysOfSick) {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.newPartyToModel(partyName, partyYear, politicalAlignement, partyCandidate1Name, partyCandidate1ID,
					partyCandidate1Year, partyCandidate1Sick, partyCandidate1DaysOfSick, partyCandidate2Name,
					partyCandidate2ID, partyCandidate2Year, partyCandidate2Sick, partyCandidate2DaysOfSick);
		}
	}

	public void fireShowPartysFromModel() {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.ShowPartysFromModel();
		}
	}

	public void fireNewCandidateToModel(String name, String id, int birthyear, boolean inquarantine, int DaysSick,
			boolean isCaraingWeapon, String inParty) {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.newCandidateToMOdel(name, id, birthyear, inquarantine, DaysSick, isCaraingWeapon, inParty);
		}
	}

	public void fireShowResultsFromModel() {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.showResultsFromModel();
		}
	}

	public void fireSaveModelData() {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.saveModelData();
		}
	}

	public void fireDataLoadToModel(Boolean answer) {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.dataLoadToModel(answer);
		}
	}

	public void fireStartVoting() {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.startVoting();
		}
	}

	public void fireVotingPlaceVotesToModel(int votingPLaceNum, int[] votingPlacePartyVotes,
			int numOfCitizensThatVoted) {
		for (EletionViewEvnetLIstener l : allLIsteners) {
			l.votingPlaceVotesToModel(votingPLaceNum, votingPlacePartyVotes, numOfCitizensThatVoted);
		}
	}

}
