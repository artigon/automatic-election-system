package ElectionMain;

import ElectionControlle.ElectionController;
import ElectionModel.ElectioneSquence;
import ElectionView.AbstractElectionView;
import ElectionView.ElectionViewUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class ElectioneMain extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage theStage) throws Exception {
		ElectioneSquence theModel = new ElectioneSquence();
		AbstractElectionView theView = new ElectionViewUI(theStage);
		ElectionController theController = new ElectionController(theModel, theView);
		
		
	}

}
