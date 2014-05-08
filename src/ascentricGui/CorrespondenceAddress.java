package ascentricGui;

import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public class CorrespondenceAddress extends Page {

	protected static final Page INSTANCE = new CorrespondenceAddress();
	private int gridVert = 1;
	
	private CorrespondenceAddress(){}

	@Override
	public void setUp(Stage primaryStage, Scene previousScene,
			ClientHolder client) {
		this.client = client;
        this.previousScene = previousScene;
        this.primaryStage = primaryStage;
        grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
                
        thisScene = new Scene(grid, pageWidth, pageHeight);
        grid.setGridLinesVisible(true);
        createFields(grid);
        
        setRowSizes(grid);
        setColumnSizes(grid);
                        
        movementButtons2Columns(true);
        
        createMovementButtons(6, 5);
                
        setTitle(grid, "Correspondence Address", primaryStage);
        
        primaryStage.setScene(thisScene);
	}

	private void createFields(GridPane grid) {
    	textFields = new HashMap<String, TextField>();
    	
    	//Correspondence Address Line 1
        Label address = new Label("Address");
        address.setTextAlignment(TextAlignment.RIGHT);
        grid.add(address, 1, ++gridVert);
        TextField addressLine1 = new TextField();
        addressLine1.setId("addressLine1");
        grid.add(addressLine1, 2, gridVert++);
        
        //Correspondence Address Line 2
        TextField addressLine2 = new TextField();
        addressLine2.setId("addressLine2");
        grid.add(addressLine2, 2, gridVert++);
        
        //Correspondence Address Line 3
        TextField addressLine3 = new TextField();
        addressLine3.setId("addressLine3");
        grid.add(addressLine3, 2, gridVert++);
        
        //Correspondence PostCode
        Label pCode = new Label("Post Code");
        pCode.setTextAlignment(TextAlignment.RIGHT);
        grid.add(pCode, 1, ++gridVert);
        TextField postCode = new TextField();
        postCode.setId("postCode");
        grid.add(postCode, 2, gridVert++);
	}


	@Override
	protected void fillAndSaveClientInfo() throws Exception {
		// TODO Auto-generated method stub

	}

}
