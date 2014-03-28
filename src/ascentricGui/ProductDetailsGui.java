package ascentricGui;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.ProductDetails;
import ascentricClientDetails.Wrapper;

public abstract class ProductDetailsGui extends Page {

	protected Stage primaryStage;
	protected ClientHolder client;
	protected Scene previousScene;
	protected Scene thisScene;
	protected static ProductDetailsGui instance;
	protected int gridVert = 3;
	protected ProductDetailsGui nextPage;
	protected Map<String, TextField> textFields;
	protected Map<String, CheckBox>	checkBoxes;

	@Override
	public void setUp(Stage primaryStage, Scene previousScene, ClientHolder client) {
		this.primaryStage = primaryStage;
		this.previousScene = previousScene;
		this.client = client;
		
;
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER_RIGHT);
//        grid.setGridLinesVisible(true);
        
		setTitleAndHeader(grid);
        setLabels(grid);
        
        generateFields(grid, "gia");
        generateFields(grid, "sas");
        gridVert++;
        createMovementButtons(grid);
        
        thisScene = new Scene(grid);
        primaryStage.setScene(thisScene);
	}


	protected Map<String, TextField> setLabels(GridPane grid) {
		
		int firstColumn = 2;
		int secondColumn = 3;
		int thirdColumn = 4;
		int fourthColumn = 5;
		int fifthColumn = 6;
		int sixthColumn = 7;
		int seventhColumn = 8;
		int eighthColumn = 9;
		int ninthColumn = 10;
		
		textFields = new HashMap<String, TextField>();
		checkBoxes = new HashMap<String, CheckBox>();
		
		Label top = new Label("If you would like a name other\nthan that of the account holder\n"
				+ "for your platform account, please enter here: ");
		top.setWrapText(true);
		grid.add(top, firstColumn, gridVert, 2, 2);
		TextField acctName = new TextField();
		textFields.put("accountName", acctName);
		acctName.setPrefWidth(100);
		grid.add(acctName, thirdColumn, ++gridVert, 2, 1);
		
		Label cashLabel = new Label("Cash - Received with \nApplication");
		cashLabel.setMaxWidth(Double.MAX_VALUE);
		cashLabel.setWrapText(true);
		GridPane.setHalignment(cashLabel, HPos.CENTER);
		grid.add(cashLabel, thirdColumn, ++gridVert, 1, 2);
		
		Label sofLabel = new Label("Source of funds \n(Cheque, BACS, Transfer)");
		sofLabel.setWrapText(true);
		grid.add(sofLabel, fourthColumn, gridVert, 1, 2);
		
		Label transferRegistration = new Label ("Transfer/Re-registration");
		transferRegistration.setWrapText(true);
		GridPane.setHalignment(transferRegistration, HPos.CENTER);
		grid.add(transferRegistration, fifthColumn, gridVert, 2, 1);
		
		Label approxTransCash = new Label ("Approximate cash\n to be transferred");
		approxTransCash.setWrapText(true);
		grid.add(approxTransCash, fifthColumn, gridVert+1, 1, 1);
		
		Label assetsRereg = new Label ("Assets to be\nre-registered");
		assetsRereg.setWrapText(true);
		grid.add(assetsRereg, sixthColumn, gridVert+1, 1, 1);
		
		Label reserveAcct = new Label("Reserve\nAccount");
		reserveAcct.setWrapText(true);
		grid.add(reserveAcct, seventhColumn, gridVert, 1, 2);
		
		Label wrapperType = new Label("Wrapper Type");
		wrapperType.setWrapText(true);
		GridPane.setHalignment(wrapperType, HPos.CENTER);
		grid.add(wrapperType, eighthColumn, gridVert, 2, 1);
		
		Label advisoryWrapper = new Label("Advisory Wrapper");
		advisoryWrapper.setWrapText(true);
		grid.add(advisoryWrapper, eighthColumn, gridVert+1, 1, 1);
		
		Label discretionaryWrapper = new Label ("Discretionary Wrapper");
		discretionaryWrapper.setWrapText(true);
		grid.add(discretionaryWrapper, ninthColumn, gridVert+1, 1, 1);
		
		Label generalInvestmentAccount = new Label("General Investment Account");
		generalInvestmentAccount.setWrapText(true);
		grid.add(generalInvestmentAccount, 2, ++gridVert+2);
		CheckBox giaCheckBox = new CheckBox();
		checkBoxes.put("giaCheckBox", giaCheckBox);
		GridPane.setHalignment(giaCheckBox, HPos.CENTER);
		grid.add(giaCheckBox, secondColumn, gridVert+2);
				
		Label stocksAndSharesISA = new Label("Stocks & Shares ISA");
		stocksAndSharesISA.setWrapText(true);
		grid.add(stocksAndSharesISA, 2, ++gridVert+2);
		CheckBox sasCheckBox = new CheckBox();
		checkBoxes.put("sasCheckBox", sasCheckBox);
		GridPane.setHalignment(sasCheckBox, HPos.CENTER);
		grid.add(sasCheckBox, secondColumn, gridVert+2);
		
		gridVert++;

		return textFields;
		
	}
	
	protected void generateFields(GridPane grid, String wrap) {
		
		int fieldWidth = 4;
		
		//generating all the textfields for the wrapper info
		for(int i = 0; i < 5; i++){
			TextField newField = new TextField();
			newField.setId(wrap + i);
			System.out.println(wrap + i);
			newField.setMaxWidth(70);
			textFields.put(wrap+i, newField);
			grid.add(newField, fieldWidth+i, gridVert);
		}
		
		fieldWidth +=5;
		
		CheckBox advWrap = new CheckBox();
		checkBoxes.put("adv" + wrap, advWrap);
		GridPane.setHalignment(advWrap, HPos.CENTER);
		grid.add(advWrap, fieldWidth++, gridVert);
		
		CheckBox discWrap = new CheckBox();
		checkBoxes.put("disc" + wrap, discWrap);
		GridPane.setHalignment(discWrap, HPos.CENTER);
		grid.add(discWrap, fieldWidth, gridVert);
		gridVert++;
		
	}
	
	public abstract void setTitleAndHeader(GridPane grid);
	
	public abstract void goTo();
	
	public void fillAndSaveClientInfo(String appType) {
		
		ProductDetails pd = null;
		
		if(appType.equals("First")){
			pd = client.getFirstClient().getProductDetails();
		} else if(appType.equals("Second")){
			pd = client.getSecondClient().getProductDetails();
		} else if(appType.equals("Joint")){
			pd = client.getJointAccount().getProductDetails();
		}
		
		//If the general investment account is selected then fill the details
		if(checkBoxes.get("giaCheckBox").isSelected()){
			Wrapper gia = pd.getGeneralInvestmentAccount();
			gia.setCash(textFields.get("gia0").getText());
			gia.setSourceOfFunds(textFields.get("gia1").getText());
			gia.setCashToBeTransferred(textFields.get("gia2").getText());
			gia.setAssetsToBeReregistered(textFields.get("gia3").getText());
			gia.setReserverAccount(textFields.get("gia4").getText());
			gia.setAdvisoryWrapper(checkBoxes.get("advgia").isSelected());
			gia.setDiscretionaryWrapper(checkBoxes.get("discgia").isSelected());
		}
		
		if(checkBoxes.get("sasCheckBox").isSelected()){
			Wrapper sas = pd.getStocksAndSharesISA();
			sas.setCash(textFields.get("sas0").getText());
			sas.setSourceOfFunds(textFields.get("sas1").getText());
			sas.setCashToBeTransferred(textFields.get("sas2").getText());
			sas.setAssetsToBeReregistered(textFields.get("sas3").getText());
			sas.setReserverAccount(textFields.get("sas4").getText());
			sas.setAdvisoryWrapper(checkBoxes.get("advsas").isSelected());
			sas.setDiscretionaryWrapper(checkBoxes.get("discsas").isSelected());
		}
	}

	public abstract void createMovementButtons(GridPane grid);



}
