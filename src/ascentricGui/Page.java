package ascentricGui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public interface Page {
	
	public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client);
	
	public void goTo();
	
	public void createMovementButtons(GridPane grid);

}
