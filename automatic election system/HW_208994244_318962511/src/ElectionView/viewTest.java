package ElectionView;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class viewTest extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage theStage) throws Exception {
		theStage.setTitle("Auto Election Mechine");
		GridPane gpRoot = new GridPane();
		gpRoot.setPadding(new Insets(10));
		gpRoot.setVgap(10);
		gpRoot.setHgap(10);

		// menu pane:
		GridPane gpMenu = new GridPane();
		gpMenu.setPadding(new Insets(10));
		gpMenu.setVgap(10);
		gpMenu.setHgap(10);
		VBox vbMenuButtons = new VBox();
		vbMenuButtons.setSpacing(10);
		ToggleGroup menuButtonGroup = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Add Voting place");
		RadioButton rb2 = new RadioButton("Add Citizen");
		RadioButton rb3 = new RadioButton("Add Party");
		RadioButton rb4 = new RadioButton("Add Canidait");
		RadioButton rb5 = new RadioButton("Show all Voting places");
		RadioButton rb6 = new RadioButton("Show all Citizens");
		RadioButton rb7 = new RadioButton("Show all Partys");
		RadioButton rb8 = new RadioButton("Start Voting");
		RadioButton rb9 = new RadioButton("Show Voting Resolts");
		RadioButton rb10 = new RadioButton("Exit");
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

		// add voting place pane:
		GridPane gpAddVotingPlace = new GridPane();
		gpAddVotingPlace.setVisible(false);
		gpAddVotingPlace.setPadding(new Insets(10));
		gpAddVotingPlace.setVgap(10);
		gpAddVotingPlace.setHgap(10);
		Label lblWhatTypeOfVotingPlace = new Label("What trype of voting place would you like to create?");
		VBox vbVotingPlaceButtons = new VBox();
		vbVotingPlaceButtons.setSpacing(10);
		ToggleGroup votingPlaceButtonGroup = new ToggleGroup();
		RadioButton rbCitizenVotingPlace = new RadioButton("Citizen Voting PLace");
		RadioButton rbQuarantineVotingPlace = new RadioButton("Quarantine Voting Place");
		RadioButton rbArmyVotingPlace = new RadioButton("Army Voting Place");
		RadioButton rbArmyQuarantineVotingPlace = new RadioButton("Army Quarantine Voting Place");
		rbCitizenVotingPlace.setToggleGroup(votingPlaceButtonGroup);
		rbQuarantineVotingPlace.setToggleGroup(votingPlaceButtonGroup);
		rbArmyVotingPlace.setToggleGroup(votingPlaceButtonGroup);
		rbArmyQuarantineVotingPlace.setToggleGroup(votingPlaceButtonGroup);
		vbVotingPlaceButtons.getChildren().addAll(rbCitizenVotingPlace, rbQuarantineVotingPlace, rbArmyVotingPlace,
				rbArmyQuarantineVotingPlace);
		Label lblVotingPlaceLoction = new Label("Name of Voting PLace:");
		TextField tfVotingPlaceLoction = new TextField();

		Button btnAddVotingPlaceSave = new Button("Save");

		btnAddVotingPlaceSave.setOnAction(new EventHandler<ActionEvent>() {

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
		gpAddVotingPlace.add(btnAddVotingPlaceSave, 0, 16);

		// Add Citizen pane:
		GridPane gpAddCitizen = new GridPane();
		gpAddCitizen.setVisible(false);
		gpAddCitizen.setPadding(new Insets(10));
		gpAddCitizen.setVgap(10);
		gpAddCitizen.setHgap(10);
		Label lblAddCitizenName = new Label("Name:");
		TextField tfAddCitizenName = new TextField();
		Label lblAddCitizenID = new Label("ID:");
		TextField tfAddCitizenID = new TextField();
		Label lblAddCitizenYear = new Label("Year of birth:");
		TextField tfAddCitizenYear = new TextField();
		Label lblAddCitizenWeapon = new Label("Do you carry a weapon?");
		CheckBox cbAddCitizenWeapon = new CheckBox();
		Label lblAddCitizenSick = new Label("ARe You Sick?");
		CheckBox cbAddCitizenSick = new CheckBox();
		Label lblAddCitizenDaysOfSick = new Label("How many days have you been sick?");
		TextField tfAddCitizenDaysOfSick = new TextField();
		lblAddCitizenDaysOfSick.setVisible(false);
		tfAddCitizenDaysOfSick.setVisible(false);

		Button btnAddCitizenSave = new Button("Save");

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
		gpAddCitizen.add(btnAddCitizenSave, 0, 12);

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

				// add function!

			}
		});

		// add party pane:
		GridPane gpAddParty = new GridPane();
		gpAddParty.setVisible(false);
		gpAddParty.setPadding(new Insets(10));
		gpAddParty.setVgap(10);
		gpAddParty.setHgap(10);
		Label lblAddPartyName = new Label("Partys name:");
		TextField tfAddPartyName = new TextField();
		Label lblAddPartyYear = new Label("Year of patys birth:");
		TextField tfAddPartyYear = new TextField();
		Label lblAddPartyPoliticalAlignement = new Label("Political alignement:");
		ComboBox<String> cmbPoliticalAlignement = new ComboBox<String>();
		cmbPoliticalAlignement.getItems().addAll("Left", "Center", "Right");
		Label lblCandidates = new Label("Candidates:");
		HBox hbAddPatyCandidates = new HBox();
		hbAddPatyCandidates.setSpacing(10);

		VBox vbAddPatyCandidate1 = new VBox();
		Label lblAddPartyCandidateName1 = new Label("Name:");
		TextField tfAddPartyCandidateName1 = new TextField();
		Label lblAddPartyCandidateID1 = new Label("ID:");
		TextField tfAddPartyCandidateID1 = new TextField();
		Label lblAddPartyCandidateYear1 = new Label("Year of birth:");
		TextField tfAddPartyCandidateYear1 = new TextField();
		Label lblAddPartyCandidateSick1 = new Label("ARe You Sick?");
		CheckBox cbAddPartyCandidateSick1 = new CheckBox();
		Label lblAddPartyCandidateDaysOfSick1 = new Label("How many days have you been sick?");
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
		TextField tfAddPartyCandidateName2 = new TextField();
		Label lblAddPartyCandidateID2 = new Label("ID:");
		TextField tfAddPartyCandidateID2 = new TextField();
		Label lblAddPartyCandidateYear2 = new Label("Year of birth:");
		TextField tfAddPartyCandidateYear2 = new TextField();
		Label lblAddPartyCandidateSick2 = new Label("Are You Sick?");
		CheckBox cbAddPartyCandidateSick2 = new CheckBox();
		Label lblAddPartyCandidateDaysOfSick2 = new Label("How many days have you been sick?");
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

		Button btnAddPartySave = new Button("Save");

		gpAddParty.add(lblAddPartyName, 0, 0);
		gpAddParty.add(tfAddPartyName, 0, 1);
		gpAddParty.add(lblAddPartyYear, 0, 2);
		gpAddParty.add(tfAddPartyYear, 0, 3);
		gpAddParty.add(lblAddPartyPoliticalAlignement, 0, 4);
		gpAddParty.add(cmbPoliticalAlignement, 0, 5);
		gpAddParty.add(lblCandidates, 0, 6);
		gpAddParty.add(hbAddPatyCandidates, 0, 7);
		gpAddParty.add(btnAddPartySave, 0, 9);

		btnAddPartySave.setOnAction(new EventHandler<ActionEvent>() {

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
		TextField tfAddCandidateName = new TextField();
		Label lblAddCandidateID = new Label("ID:");
		TextField tfAddCandidateID = new TextField();
		Label lblAddCandidateYear = new Label("Year of birth:");
		TextField tfAddCandidateYear = new TextField();
		Label lblAddCandidateSick = new Label("Are You Sick?");
		CheckBox cbAddCandidateSick = new CheckBox();
		Label lblAddCandidateDaysOfSick = new Label("How many days have you been sick?");
		TextField tfAddCandidateDaysOfSick = new TextField();
		lblAddCandidateDaysOfSick.setVisible(false);
		tfAddCandidateDaysOfSick.setVisible(false);
		Label lblAddCandidatePartyName = new Label("Which party Name:");
		TextField tfAddCandidatePartyName = new TextField();

		Button btnAddCandidateSave = new Button("Save");

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
		gpAddCandidateToParty.add(btnAddCandidateSave, 0, 12);

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
		Label lblShowVotingLocations = new Label("placeholder-showcitizens");
		Button btnShowVotingLocations = new Button("Save");

		gpShowVotingLocations.add(lblShowVotingLocations, 0, 0);
		btnShowVotingLocations.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpShowVotingLocations.setVisible(false);
				lblShowVotingLocations.setVisible(false);

			}
		});

		// show citizens gridpane
		GridPane gpShowCitizens = new GridPane();
		gpShowCitizens.setVisible(false);
		gpShowCitizens.setPadding(new Insets(10));
		gpShowCitizens.setVgap(10);
		gpShowCitizens.setHgap(10);
		Label lblShowCitizens = new Label("placeholder-showcitizens");
		Button btnShowCitizensSave = new Button("Save");

		gpShowCitizens.add(lblShowCitizens, 0, 0);
		gpShowCitizens.add(btnShowCitizensSave, 0, 1);
		btnShowCitizensSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpShowCitizens.setVisible(false);
				lblShowCitizens.setVisible(false);

			}
		});

		// show all partise gridpane:
		GridPane gpShowParties = new GridPane();
		gpShowParties.setVisible(false);
		gpShowParties.setPadding(new Insets(10));
		gpShowParties.setVgap(10);
		gpShowParties.setHgap(10);
		Label lblShowParties = new Label("placeholder-ShowParties");
		Button btnShowParties = new Button("Save");

		gpShowParties.add(lblShowParties, 0, 0);
		btnShowParties.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				gpMenu.setVisible(true);
				gpShowParties.setVisible(false);
				lblShowParties.setVisible(false);

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
				rb5.setSelected(false);
				gpMenu.setVisible(false);
				gpShowVotingLocations.setVisible(true);

			}
		});
		rb6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				rb6.setSelected(false);
				gpMenu.setVisible(false);
				gpShowCitizens.setVisible(true);

			}
		});
		rb7.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				rb7.setSelected(false);
				gpMenu.setVisible(false);
				gpShowParties.setVisible(true);

			}
		});
		rb8.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				rb8.setSelected(false);
				gpMenu.setVisible(false);
				// gpAddCandidateToParty.setVisible(true);

			}
		});
		rb9.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				rb9.setSelected(false);
				gpMenu.setVisible(false);
				// gpAddCandidateToParty.setVisible(true);

			}
		});
		// adding GridPanes to main gpRoot:
		gpRoot.add(gpMenu, 5, 0, 20, 20);
		gpRoot.add(gpAddVotingPlace, 5, 0, 20, 20);
		gpRoot.add(gpAddCitizen, 5, 0, 20, 20);
		gpRoot.add(gpAddParty, 5, 0, 20, 20);
		gpRoot.add(gpAddCandidateToParty, 5, 0, 20, 20);
		gpRoot.add(gpShowCitizens, 5, 0, 20, 20);
		gpRoot.add(gpShowVotingLocations, 5, 0, 20, 20);
		gpRoot.add(gpShowParties, 5, 0, 20, 20);
		theStage.setScene(new Scene(gpRoot, 600, 500));
		theStage.show();

	}

}
