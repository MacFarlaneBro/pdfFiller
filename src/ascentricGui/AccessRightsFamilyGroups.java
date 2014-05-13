package ascentricGui;

import java.util.TreeMap;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.IndividualDetails;

public class AccessRightsFamilyGroups extends Page {

	public static final AccessRightsFamilyGroups INSTANCE = new AccessRightsFamilyGroups();
	private TextField cliRefNum;

	private AccessRightsFamilyGroups(){}

	@Override
	public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client) {
    	this.client = client;
        this.previousScene = firstScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Access Rights");
        GridPane grid = new GridPane();
        this.grid = grid;
        grid.setHgap(10);
        grid.setVgap(10);
        
        setColumnSizes(grid, 100, 140, 140, 140, 140);
        setRowSizes(grid, 20,20,20,20,20,20,20,20,20);
        
        thisScene = new Scene(grid, PAGEWIDTH, PAGEHEIGHT);
        
        if(client.getJointAccount() == null){
        	nextPage = FirstProductDetails.INSTANCE;
        } else {
        	nextPage = JointProductDetails.INSTANCE;
        }
        
        createFields(grid);
        
		createMovementButtons(15, 5);

        primaryStage.setScene(thisScene);       
	}

	private void createFields(GridPane grid) {
		
		checkBoxes = new TreeMap<String, CheckBox>();
		Text sceneTitle = new Text("Access Rights");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        sceneTitle.setId("sceneTitle");
        grid.add(sceneTitle, 1, 1, 2, 2);
    	
    	int gridVert = 3;
    	
    	Label singleApp  = new Label("Single Applicant");
    	grid.add(singleApp, 2, gridVert);
    	GridPane.setHalignment(singleApp, HPos.CENTER);
    	
    	if(client.getSecondClient()!= null){
	    	Label secondApp  = new Label("Second Applicant");
	    	grid.add(secondApp, 3, gridVert);
	    	GridPane.setHalignment(secondApp, HPos.CENTER);
    	}
    	
    	if(client.getJointAccount()!= null){
	    	Label jointApp  = new Label("Joint Account");
	    	grid.add(jointApp, 4, gridVert);
	    	GridPane.setHalignment(jointApp, HPos.CENTER);
    	}
    	
        //Label for first row of checkboxes
        Label noOnlineAccess = new Label("No Online Access");
        grid.add(noOnlineAccess, 1, ++gridVert);
        
        createCheckboxes(gridVert, "NoOnAcc");
        
        //Label for second row of checkboxes
        Label enquiryOnly = new Label("Enquiry Only");
        grid.add(enquiryOnly, 1, ++gridVert);
        
        createCheckboxes(gridVert, "EnqOnly");
        
        //Label for third row of checkboxes
        Label tradingAccess = new Label("Trading Only");
        grid.add(tradingAccess, 1, ++gridVert);
        
        createCheckboxes(gridVert, "TradingAcc");
        
        sceneTitle = new Text("Family Groups");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        sceneTitle.setId("sceneTitle");
        grid.add(sceneTitle, 1, gridVert++, 2, gridVert);
        
        gridVert+=4;
        
        CheckBox famGroup1Check = new CheckBox("More than 1 applicant Linked to a Family Group");
        famGroup1Check.setAlignment(Pos.CENTER);
		grid.add(famGroup1Check, 1, gridVert, 3, 1);
		famGroup1Check.setId("famGroup1Check");
		checkBoxes.put("famGroup1Check", famGroup1Check);

		CheckBox famGroup2Check = new CheckBox("Link to an existing Ascentric account");
		famGroup2Check.setAlignment(Pos.CENTER);
		grid.add(famGroup2Check, 1, ++gridVert, 3, 1);
		famGroup2Check.setId("famGroup2Check");
		checkBoxes.put("famGroup2Check", famGroup2Check);
		
		
		Label ifPart2 = new Label("If part 2 of the above applies, please provide\nappropriate client reference number:");
		grid.add(ifPart2, 1, ++gridVert, 3, 1);
		
		cliRefNum = new TextField();
		grid.add(cliRefNum, 3, gridVert, 2, 1);
					
	}
	
	protected void fillAndSaveClientInfo() {
		
		IndividualDetails singleApplicant = client.getFirstClient().getIndividualDetails();
		singleApplicant.setOnlineAccess(checkBoxes.get("SingleAppNoOnAcc").isSelected());
		singleApplicant.setEnquiryOnly(checkBoxes.get("SingleAppEnqOnly").isSelected());
		singleApplicant.setTradingAccess(checkBoxes.get("SingleAppTradingAcc").isSelected());
		
		if(client.getSecondClient()!= null){
			IndividualDetails secondApplicant = client.getSecondClient().getIndividualDetails();
			secondApplicant.setOnlineAccess(checkBoxes.get("SecondAppNoOnAcc").isSelected());
			secondApplicant.setEnquiryOnly(checkBoxes.get("SecondAppEnqOnly").isSelected());
			secondApplicant.setTradingAccess(checkBoxes.get("SecondAppTradingAcc").isSelected());
		}
		
		if(client.getJointAccount()!= null){
			IndividualDetails jointAccount = client.getJointAccount().getIndividualDetails();
			jointAccount.setOnlineAccess(checkBoxes.get("JointAccNoOnAcc").isSelected());
			jointAccount.setEnquiryOnly(checkBoxes.get("JointAccEnqOnly").isSelected());
			jointAccount.setTradingAccess(checkBoxes.get("JointAccTradingAcc").isSelected());
		}
		
		singleApplicant.setFamilyGroup(checkBoxes.get("famGroup1Check").isSelected());
		singleApplicant.setExistingAccount(checkBoxes.get("famGroup2Check").isSelected());
		if(checkBoxes.get("famGroup2Check").isSelected()){
			singleApplicant.setClientRef((cliRefNum.getText()));
		}

	}

	private void createCheckboxes(int gridVert, String row) {
		//Setting leftmost checkbox
		CheckBox singleApp = new CheckBox();
		GridPane.setHalignment(singleApp, HPos.CENTER);
		grid.add(singleApp, 2, gridVert);
		singleApp.setId("SingleApp" + row);
		singleApp.setSelected(false);
		checkBoxes.put("SingleApp" + row, singleApp);
		
		if(client.getSecondClient()!= null){
			CheckBox secondApp = new CheckBox();
			GridPane.setHalignment(secondApp, HPos.CENTER);
			grid.add(secondApp, 3, gridVert);
			secondApp.setId("SecondApp" + row);
			singleApp.setSelected(false);
			checkBoxes.put("SecondApp" + row, secondApp);
		}
		if(client.getJointAccount()!= null){
			CheckBox jointAcc = new CheckBox();
			GridPane.setHalignment(jointAcc, HPos.CENTER);
			grid.add(jointAcc, 4, gridVert);
			jointAcc.setId("JointAcc" + row);
			singleApp.setSelected(false);
			checkBoxes.put("JointAcc" + row, jointAcc);
		}
	}

}
