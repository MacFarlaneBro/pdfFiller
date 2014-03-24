package ascentricGui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Page2{
	
	private Scene previousScene;
	private Scene thisScene;
	private GridPane grid;
	private String sceneT = "Partner Info";
	protected Stage primaryStage;
	private static Page2 instance;

	static { 
		setInstance(new Page2());
	}
	
	public void start(Stage primaryStage, Scene previousScene) {
        this.previousScene = previousScene;
        this.primaryStage = primaryStage;
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

	private void createMovementButtons(GridPane grid2) {
		// TODO Auto-generated method stub
		
	}

	private void autoFillClientInfo(GridPane grid2) {
		// TODO Auto-generated method stub
		
	}

	private void findClient(GridPane grid2) {
		// TODO Auto-generated method stub
		
	}

	private void createRemainingFields(GridPane grid2) {
		// TODO Auto-generated method stub
		
	}

	private void createAutoFillFields(GridPane grid2) {
		// TODO Auto-generated method stub
		
	}

	public static Page2 getInstance() {
		return instance;
	}

	public static void setInstance(Page2 instance) {
		Page2.instance = instance;
	}
}
