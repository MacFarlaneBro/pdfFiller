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

public abstract class ProductDetails extends Page {

	protected Stage primaryStage;
	protected ClientHolder client;
	protected Scene previousScene;
	protected Scene thisScene;
	protected static ProductDetails instance;
	protected int gridVert = 3;
	protected ProductDetails nextPage;

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
        Map<String, TextField> theMap = setLabels(grid);
        
        generateFields(grid, theMap, "gia");
        generateFields(grid, theMap, "sas");
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
		
		Map<String, TextField> textFields = new HashMap<String, TextField>();
		
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
		GridPane.setHalignment(giaCheckBox, HPos.CENTER);
		grid.add(giaCheckBox, secondColumn, gridVert+2);
				
		Label stocksAndSharesISA = new Label("Stocks & Shares ISA");
		stocksAndSharesISA.setWrapText(true);
		grid.add(stocksAndSharesISA, 2, ++gridVert+2);
		CheckBox sasCheckBox = new CheckBox();
		GridPane.setHalignment(sasCheckBox, HPos.CENTER);
		grid.add(sasCheckBox, secondColumn, gridVert+2);
		
		gridVert++;

		return textFields;
		
	}
	
	protected void generateFields(GridPane grid, Map<String, TextField> theMap, String wrap) {
		
		int fieldWidth = 4;
		
		//generating all the textfields for the wrapper info
		for(int i = 0; i < 5; i++){
			TextField newField = new TextField();
			newField.setId(wrap + i);
			newField.setMaxWidth(70);
			grid.add(newField, fieldWidth+i, gridVert);
		}
		
		fieldWidth +=5;
		
		CheckBox advWrap = new CheckBox();
		GridPane.setHalignment(advWrap, HPos.CENTER);
		grid.add(advWrap, fieldWidth++, gridVert);
		
		CheckBox discWrap = new CheckBox();
		GridPane.setHalignment(discWrap, HPos.CENTER);
		grid.add(discWrap, fieldWidth, gridVert);
		gridVert++;
		
	}
	
	public abstract void setTitleAndHeader(GridPane grid);
	
	public abstract void goTo();
	
	public abstract void fillAndSaveClientInfo();

	public abstract void createMovementButtons(GridPane grid);



}
