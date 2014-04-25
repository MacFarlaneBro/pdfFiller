package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;
import ascentricGui.ClientPersonalInfo;

/**
 *
 * @author charlie brodie
 */
public class Main extends Application{
    
    private Stage primaryStage;
    private Scene firstScene;
    private ClientHolder client;
    
    public static void main(String[] args){
        launch(args);
    }
    
    private double fieldWidth = 100d;
	private ComboBox<String> registeredIndividual;
	private Text actionTarget;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("PDF Filler 0.9");
        primaryStage.setMinWidth(500);
        
        GridPane grid = new GridPane();
        Scene firstScene = new Scene(grid, 300, 300);
        
        grid.getColumnConstraints().add(new ColumnConstraints(150));
        
        grid.setAlignment(Pos.CENTER);
        //sets the horizontal and vertical gaps around the border of the application
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text sceneTitle = new Text("Welcome to the PDF Filler mark 0.9");
        sceneTitle.setId("sceneTitle");
        sceneTitle.setLayoutX(20);
        grid.add(sceneTitle, 0, 0, 3, 1);
        
        grid.add(new Label("Select Registered Individual:"), 0, 2);
		
		 ObservableList<String> ri =
	                FXCollections.observableArrayList(
	                        "Mrs Jacalyn Karen Baker",
	                        "Mr Douglas David McFarlane Brodie",
	                        "Mr Paul Michael Grant",
	                        "Mr Richard Neil Higham",
	                        "Miss Natalie Victoria Lucy Hull",
	                        "Mr Roy McLoughlin",
	                        "Mr David Gordon Tom",
	                        "Mr Jeremy Bryce Watson"
	                );
		registeredIndividual = new ComboBox<String>(ri);
		GridPane.setHalignment(registeredIndividual, HPos.CENTER);
    	grid.add(registeredIndividual, 1, 2, 2, 1);

        Label formType = new Label("Choose Form Type");
        grid.add(formType, 0, 3);
        
        //List of form types
        ObservableList<String> formTypes =
            FXCollections.observableArrayList(
                    "Ascentric"
            );
        
        ComboBox<String> comboBox = 
        		new ComboBox<String>(formTypes);
        comboBox.setPromptText("Ascentric");
        comboBox.setPrefWidth(fieldWidth);
        grid.add(comboBox, 1, 3);
        
        Button go = new Button("Go");
        go.setPrefWidth(fieldWidth);
        GridPane.setHalignment(go, HPos.RIGHT);
        grid.add(go, 2, 3);
        
        actionTarget = new Text("You must select a Registered Individual"
        		+ " before continuing.");
        actionTarget.setFill(Color.DARKRED);
        actionTarget.setVisible(false);
        grid.add(actionTarget, 0, 5, 3, 1);
        setUpFormFiller(go);
               
        primaryStage.show();
        this.firstScene = firstScene;
        
        primaryStage.setScene(firstScene);

    }   
    
	public void setUpFormFiller(Button btn){
       btn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
            	
            	if(registeredIndividual.getValue() == null){
            		actionTarget.setVisible(true);
            	} else {
            		actionTarget.setVisible(false);
	            	client = ClientHolder.getInstance();
	            	client.makeNewFirstClient();
	            	client.getFirstClient().getfinancialAdviserDetails()
	            	.setRegisteredIndividual(registeredIndividual.getValue());
	            	
	                ClientPersonalInfo.INSTANCE.setUp(primaryStage, firstScene, client);
            	}
            }
        });
    }
}