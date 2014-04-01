package ascentricGui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public class FinancialAdviserDetails extends Page {
	
	private static Page instance;
	
	static{
		instance = new FinancialAdviserDetails();
	}
	
	private FinancialAdviserDetails(){}

	@Override
	public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client) {
		System.out.println("Welcome to financial Adviser Details");

	}

	@Override
	public void createMovementButtons(GridPane grid) {
		// TODO Auto-generated method stub

	}

}
