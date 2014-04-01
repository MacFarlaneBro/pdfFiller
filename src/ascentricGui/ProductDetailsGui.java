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
		
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER_RIGHT);
        grid.setGridLinesVisible(true);
        
		setTitleAndHeader(grid);
        set1stLayerLabels(grid);
        
        generate1stLayerFields(grid, "gia");
        generate1stLayerFields(grid, "sas");
        
        generate2ndLayerLabels(grid);
        generate2ndLayerFields(grid);
        
        //Centering all the textfields within their columns
        TextField[] theFields = textFields.values().toArray(new TextField[0]);
        for(TextField tf: theFields){
        	GridPane.setHalignment(tf, HPos.CENTER);
        	tf.setDisable(true);
        }
        
        gridVert++;
        createMovementButtons(grid);
        
        thisScene = new Scene(grid);
        primaryStage.setScene(thisScene);
	}


	protected Map<String, TextField> set1stLayerLabels(GridPane grid) {
		
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
		
		Label top = new Label("If you would like a name other than that of the account holder"
				+ " for your platform account, please enter here: ");
		top.setWrapText(true);
		grid.add(top, firstColumn, gridVert, 4, 2);
		TextField acctName = new TextField();
		textFields.put("accountName", acctName);
		acctName.setPrefWidth(100);
		grid.add(acctName, fifthColumn, ++gridVert, 2, 1);
		gridVert++;
		Label cashLabel = new Label("Cash - Received with \nApplication");
		GridPane.setHalignment(cashLabel, HPos.CENTER);
		grid.add(cashLabel, thirdColumn, ++gridVert, 1, 1);
		
		Label sofLabel = new Label("Source of funds \n(Cheque, BACS, Transfer)");
		sofLabel.setWrapText(true);
		grid.add(sofLabel, fourthColumn, gridVert, 1, 1);
		
		Label transferRegistration = new Label ("Transfer/Re-registration");
		transferRegistration.setWrapText(true);
		GridPane.setHalignment(transferRegistration, HPos.CENTER);
		grid.add(transferRegistration, fifthColumn, gridVert-1, 2, 1);
		
		Label approxTransCash = new Label ("Approximate cash\n to be transferred");
		approxTransCash.setWrapText(true);
		grid.add(approxTransCash, fifthColumn, gridVert, 1, 1);
		
		Label assetsRereg = new Label ("Assets to be\nre-registered");
		assetsRereg.setWrapText(true);
		grid.add(assetsRereg, sixthColumn, gridVert, 1, 1);
		
		Label reserveAcct = new Label("Reserve\nAccount");
		reserveAcct.setWrapText(true);
		grid.add(reserveAcct, seventhColumn, gridVert, 1, 1);
		
		Label wrapperType = new Label("Wrapper Type");
		wrapperType.setWrapText(true);
		GridPane.setHalignment(wrapperType, HPos.CENTER);
		grid.add(wrapperType, eighthColumn, gridVert-1, 2, 1);
		
		Label advisoryWrapper = new Label("Advisory\nWrapper");
		advisoryWrapper.setWrapText(true);
		grid.add(advisoryWrapper, eighthColumn, gridVert, 1, 1);
		
		Label discretionaryWrapper = new Label ("Discretionary\nWrapper");
		discretionaryWrapper.setWrapText(true);
		grid.add(discretionaryWrapper, ninthColumn, gridVert, 1, 1);
		
		Label generalInvestmentAccount = new Label("General Investment Account");
		GridPane.setHalignment(generalInvestmentAccount, HPos.CENTER);
		grid.add(generalInvestmentAccount, 2, ++gridVert+2);
		CheckBox giaCheckBox = new CheckBox();
		checkBoxes.put("giaCheckBox", giaCheckBox);
		GridPane.setHalignment(giaCheckBox, HPos.CENTER);
		grid.add(giaCheckBox, secondColumn, gridVert+2);
				
		Label stocksAndSharesISA = new Label("Stocks & Shares ISA");
		GridPane.setHalignment(stocksAndSharesISA, HPos.CENTER);
		stocksAndSharesISA.setWrapText(true);
		grid.add(stocksAndSharesISA, 2, ++gridVert+2);
		CheckBox sasCheckBox = new CheckBox();
		checkBoxes.put("sasCheckBox", sasCheckBox);
		GridPane.setHalignment(sasCheckBox, HPos.CENTER);
		grid.add(sasCheckBox, secondColumn, gridVert+2);
		
		gridVert++;

		return textFields;
		
	}
	
	protected void generate1stLayerFields(GridPane grid, String wrap) {
		
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
			System.out.println("First");
		} else if(appType.equals("Second")){
			System.out.println("Second");
			pd = client.getSecondClient().getProductDetails();
		} else if(appType.equals("Joint")){
			System.out.println("Joint");
			pd = client.getJointAccount().getProductDetails();
		}
		
		//If the general investment account is selected then fill the details
		if(checkBoxes.get("giaCheckBox").isSelected()){
			Wrapper gia = pd.getGeneralInvestmentAccount();
			pd.getGeneralInvestmentAccount().setCash(textFields.get("gia0").getText());
			System.out.println("CashTime:" + gia.getCash());
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
		System.out.println("More CashTime: "+client.getFirstClient().getProductDetails().getGeneralInvestmentAccount().getCash());
	}
	
	private void generate2ndLayerLabels(GridPane grid) {
		Label thirdPartyProdAcc = new Label("Third Party Product Accounts \n (insert name of third party)");
		thirdPartyProdAcc.setWrapText(true);
		GridPane.setHalignment(thirdPartyProdAcc, HPos.CENTER);
		grid.add(thirdPartyProdAcc, 4, ++gridVert, 1, 1);
		
		Label amountToBeReceived = new Label("Amount to be received");
		GridPane.setHalignment(amountToBeReceived, HPos.CENTER);
		amountToBeReceived.setWrapText(true);
		grid.add(amountToBeReceived, 5, gridVert, 1, 1);
		
		Label sourceOfFunds = new Label ("Source of Funds \n (Cheque, BACS, Transfer)");
		sourceOfFunds.setWrapText(true);
		GridPane.setHalignment(sourceOfFunds, HPos.CENTER);
		grid.add(sourceOfFunds, 6, gridVert, 1, 1);
		
		Label approxTransCash = new Label ("Wrapper Type");
		GridPane.setHalignment(approxTransCash, HPos.CENTER);
		approxTransCash.setWrapText(true);
		grid.add(approxTransCash, 7, gridVert, 2, 1);

		Label thirdAdvWrap = new Label ("Advisory\nWrapper");
		GridPane.setHalignment(thirdAdvWrap, HPos.CENTER);
		grid.add(thirdAdvWrap, 7, gridVert+1);
		
		Label thirdDiscWrap = new Label ("Discretionary\nWrapper");
		GridPane.setHalignment(thirdDiscWrap, HPos.CENTER);
		grid.add(thirdDiscWrap, 8, gridVert+1);
		
		gridVert+=2;

	}
	
	private void generate2ndLayerFields(GridPane grid) {
		int fieldWidth = 4;
		
		//generating all the textfields for the wrapper info
		for(int i = 0; i < 3; i++){
			TextField newField = new TextField();
			newField.setId("Third" + i);
			System.out.println("Third" + i);
			newField.setMaxWidth(70);
			textFields.put("Third"+i, newField);
			grid.add(newField, fieldWidth+i, gridVert);
		}
		
		CheckBox advWrap = new CheckBox();
		checkBoxes.put("ThirdAdv", advWrap);
		GridPane.setHalignment(advWrap, HPos.CENTER);
		grid.add(advWrap, fieldWidth+3, gridVert);
		
		CheckBox discWrap = new CheckBox();
		checkBoxes.put("ThirdDisc", discWrap);
		GridPane.setHalignment(discWrap, HPos.CENTER);
		grid.add(discWrap, fieldWidth+4, gridVert);
		gridVert+=3;
	}

	public abstract void createMovementButtons(GridPane grid);

}
