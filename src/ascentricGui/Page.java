package ascentricGui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public abstract class Page {
	
	private static Page instance;

	public abstract void setUp(Stage primaryStage, Scene firstScene, ClientHolder client);
	
	public abstract void goTo();
	
	public abstract void createMovementButtons(GridPane grid);
	
	public static Page getInstance(){
		return instance;
	}

}
