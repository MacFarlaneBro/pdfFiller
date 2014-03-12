package ascentricGui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Page2 extends Page1{
	
	private Scene firstScene;
	private GridPane grid;
	private Scene thisScene;
	
	public Page2(Stage primaryStage, Scene firstScene) {
		
	        this.firstScene = firstScene;
	        primaryStage.setTitle("Personal Information - Partner");
	        grid = new GridPane();
	        grid.setHgap(25);
	        grid.setVgap(25);
	        grid.setAlignment(Pos.CENTER);
	        
	        
	        System.out.println("New Scene created");
	        createAutoFillFields(grid);
	        createRemainingFields(grid);
	        findClient(grid);
	        autoFillClientInfo(grid);
	        createMovementButtons(grid);
	        System.out.println("New Scene Filled");
	        thisScene = new Scene(grid);

	       // grid.setGridLinesVisible(true);
	        primaryStage.setScene(thisScene);
	        System.out.println("New Scene set");

	}

}
