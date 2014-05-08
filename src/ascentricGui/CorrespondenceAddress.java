package ascentricGui;

import java.util.HashMap;

import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public class CorrespondenceAddress extends Page {

	protected static final Page INSTANCE = new CorrespondenceAddress();
	
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
        createFields(grid);
        
        setColumnSizes(grid, 150, 120, 150, 150);
        setRowSizes(grid, 35, 35, 35, 20, 20, 20, 70);
                        
        movementButtons2Columns(true);
        
        createMovementButtons(7, 5);
                
        setTitle(grid, "Correspondence Address", primaryStage);
        
        primaryStage.setScene(thisScene);
	}

	private void createFields(GridPane grid) {
    	textFields = new HashMap<String, TextField>();
    	
    	int gridVert = 2;
    	
    	//Correspondence Address Line 1
        Label address = new Label("Address");
        address.setTextAlignment(TextAlignment.RIGHT);
        GridPane.setHalignment(address, HPos.RIGHT);
        grid.add(address, 1, ++gridVert);
        TextField addressLine1 = new TextField();
        addressLine1.setId("addressLine1");
        grid.add(addressLine1, 2, gridVert++, 2, 1);
        
        //Correspondence Address Line 2
        TextField addressLine2 = new TextField();
        addressLine2.setId("addressLine2");
        grid.add(addressLine2, 2, gridVert++, 2, 1);
        
        //Correspondence Address Line 3
        TextField addressLine3 = new TextField();
        addressLine3.setId("addressLine3");
        grid.add(addressLine3, 2, gridVert++, 2, 1);
        
        //Correspondence PostCode
        Label pCode = new Label("Post Code");
        pCode.setTextAlignment(TextAlignment.RIGHT);
        GridPane.setHalignment(pCode, HPos.RIGHT);
        grid.add(pCode, 1, gridVert);
        TextField postCode = new TextField();
        postCode.setId("postCode");
        grid.add(postCode, 2, gridVert++, 2, 1);
	}


	@Override
	protected void fillAndSaveClientInfo() throws Exception {
		CorrespondenceDetails cd = client.getFirstClient().getIndividualDetails().getCorrespondenceDetails();
		
		cd.setFirstAdd(textFields.get("addressLine1").getText());
		cd.setSecondAdd(textFields.get("addressLine2").getText());
		cd.setThirdAdd(textFields.get("addressLine3").getText());
		cd.setPostCode(textFields.get("postCode").getText());
		
	}

}
