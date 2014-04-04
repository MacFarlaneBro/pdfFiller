package ascentricGui;

import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public abstract class Page {
	
	public static final int pageWidth = 900;
	public static final int pageHeight = 500;
	protected Page nextPage;
	protected Scene thisScene;
	protected Scene previousScene;
	protected GridPane grid;
	protected Stage primaryStage;
	protected ClientHolder client;
	protected Map<String, CheckBox> checkBoxes;
	protected Map<String, TextField> textFields;
	protected static Page instance;
	
	public abstract void setUp(Stage primaryStage, Scene firstScene, ClientHolder client);
	protected abstract void fillAndSaveClientInfo();
	
	public void createMovementButtons() {
	    
		Button backBtn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        backBtn.setPrefWidth(100);
        hbBtn.getChildren().add(backBtn);
        grid.add(hbBtn, 1, 11);
        
        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(previousScene);
            }
        });
        
        Button nextBtn = new Button("Next");//Create button with the name sign in
        HBox hNextBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hNextBtn.setAlignment(Pos.BOTTOM_LEFT);
        nextBtn.setPrefWidth(100);
        hNextBtn.getChildren().add(nextBtn);
        grid.add(hNextBtn, 5, 11);
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
            public void handle(ActionEvent e){
            	fillAndSaveClientInfo();
            	if(client.getJointAccount()!= null){
            		ProductDetailsFactory.makeJoint();
            		nextPage = ProductDetailsFactory.getThird();
            	} else {
	            	ProductDetailsFactory.makeFirst();
	            	nextPage = ProductDetailsFactory.getFirst();
	            	nextPage.setUp(primaryStage, thisScene, client);
            	}
            }
        });	
	}
	
	public static Page getInstance(){
		return instance;
	}
	
    public void setColumnSizes(GridPane grid, int... sizes){
    	for(int size: sizes){
    		grid.getColumnConstraints().add(new ColumnConstraints(size));
    	}
    }
    
    public void setRowSizes(GridPane grid, int... sizes){
    	for(int size: sizes){
    		grid.getRowConstraints().add(new RowConstraints(size));
    	}
    }

}
