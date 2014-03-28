package ascentricGui;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public class ProductDetails extends Page {

	private Stage primaryStage;
	private ClientHolder client;
	private Scene previousScene;
	private Scene thisScene;
	private static ProductDetails instance;
	int gridVert = 1;
	protected ProductDetails nextPage;

	
	static {
		instance = new ProductDetails();
	}
	
	public static ProductDetails getInstance(){
		return instance;
	}

	@Override
	public void setUp(Stage primaryStage, Scene previousScene, ClientHolder client) {
		this.primaryStage = primaryStage;
		this.previousScene = previousScene;
		this.client = client;
		
		primaryStage.setTitle("Personal Information - Client");
//		primaryStage.setMaxWidth(1000);
		
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER_RIGHT);
//        grid.setGridLinesVisible(true);
        
        Map<String, TextField> theMap = setLabels(grid);
        
        generateFields(grid, theMap, "gia");
        generateFields(grid, theMap, "sas");
        gridVert++;
        createMovementButtons(grid);
        
        thisScene = new Scene(grid);
        primaryStage.setScene(thisScene);
	}

	private Map<String, TextField> setLabels(GridPane grid) {
		
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
		
		Label top = new Label("If you would like a name other than that of the account holder\n "
				+ "for your platform account, please enter here: ");
		top.setWrapText(true);
		grid.add(top, firstColumn, gridVert, 4, 1);
		TextField acctName = new TextField();
		textFields.put("accountName", acctName);
		acctName.setPrefWidth(100);
		grid.add(acctName, fourthColumn, gridVert++, 2, 2);
		
		Label cashLabel = new Label("Cash - Received with \nApplication");
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
		
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setPadding(new Insets(0, 20, 10, 20));
		hBox.getChildren().addAll(cashLabel,sofLabel,transferRegistration,approxTransCash,
				assetsRereg,reserveAcct,wrapperType,advisoryWrapper,discretionaryWrapper,
				generalInvestmentAccount,stocksAndSharesISA);
		
		gridVert++;

		return textFields;
		
	}
	
	private void generateFields(GridPane grid, Map<String, TextField> theMap, String wrap) {
		
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

	@Override
	public void goTo() {
		// TODO Auto-generated method stub

	}
	
	private void fillAndSaveClientInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createMovementButtons(GridPane grid) {
		 Button backBtn = new Button("Back");//Create button with the name sign in
	        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
	        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
	        backBtn.setPrefWidth(100);
	        hbBtn.getChildren().add(backBtn);
	        grid.add(hbBtn, 2, --gridVert);
	        
	        backBtn.setOnAction(new EventHandler<ActionEvent>(){
	            
	            @Override
	            public void handle(ActionEvent e){
	            	primaryStage.setTitle("PDF Filler 0.01");
	                primaryStage.setScene(previousScene);
	            }
	        });
	        
	        Button nextBtn = new Button("Next");//Create button with the name sign in
	        HBox hNextBtn = new HBox(21);//Layout pane with 21 pixel spacing
	        hNextBtn.setAlignment(Pos.BOTTOM_LEFT);
	        nextBtn.setPrefWidth(100);
	        hNextBtn.getChildren().add(nextBtn);
	        grid.add(hNextBtn, 10, gridVert);
	        
	        nextBtn.setOnAction(new EventHandler<ActionEvent>(){

				@Override
	            public void handle(ActionEvent e){
	            	fillAndSaveClientInfo();
	            	nextPage = ProductDetails.getInstance();
	            	nextPage.setUp(primaryStage, thisScene, client);
	            }
	        });
	}

}
