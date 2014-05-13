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
import ascentricClientDetails.CorrespondenceDetails;

public class CorrespondenceAddress extends Page {

	public static final Page INSTANCE = new CorrespondenceAddress();
	
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
                
        thisScene = new Scene(grid, PAGEWIDTH, PAGEHEIGHT);
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
        textFields.put("addressLine1", addressLine1);
        grid.add(addressLine1, 2, gridVert++, 2, 1);
        
        //Correspondence Address Line 2
        TextField addressLine2 = new TextField();
        textFields.put("addressLine2", addressLine2);
        grid.add(addressLine2, 2, gridVert++, 2, 1);
        
        //Correspondence PostCode
        Label pCode = new Label("Post Code");
        pCode.setTextAlignment(TextAlignment.RIGHT);
        GridPane.setHalignment(pCode, HPos.RIGHT);
        grid.add(pCode, 1, gridVert);
        TextField postCode = new TextField();
        textFields.put("postCode", postCode);
        grid.add(postCode, 2, gridVert++, 2, 1);
	}


	@Override
	protected void fillAndSaveClientInfo() throws Exception {
		CorrespondenceDetails cd = client.getFirstClient().getIndividualDetails().getCorrespondenceDetails();
		
		cd.setFirstAdd(textFields.get("addressLine1").getText());
		cd.setSecondAdd(textFields.get("addressLine2").getText());
		cd.setPostCode(textFields.get("postCode").getText());
		
	}

}
