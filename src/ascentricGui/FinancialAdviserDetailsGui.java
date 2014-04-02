package ascentricGui;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ascentricClientDetails.Charge;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.FinancialAdviserDetails;
import ascentricClientDetails.InitialAdviserCharge;
import ascentricClientDetails.OngoingAdviserCharge;
import ascentricClientDetails.TradingCharge;

public class FinancialAdviserDetailsGui extends Page {
	
	private static Page instance;
	
	static{
		instance = new FinancialAdviserDetailsGui();
	}
	
	public static Page getInstance(){
		return instance;
	}

	private ClientHolder client;
	private Scene previousScene;
	private Stage primaryStage;
	private Scene thisScene;
	private HashMap<String, CheckBox> checkBoxes;
	private HashMap<String, TextField> textFields;
	private Page nextPage;
	
	private FinancialAdviserDetailsGui(){}

	@Override
	public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client) {
    	this.client = client;
        this.previousScene = firstScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Personal Information - Client");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setGridLinesVisible(true);
        
        thisScene = new Scene(grid, pageWidth, pageHeight);
        
        setColumnSizes(grid);
                
        createLeftFields(grid);
        
        createMovementButtons(grid);

       // grid.setGridLinesVisible(true);
        primaryStage.setScene(thisScene);
        
    	nextPage = FinancialAdviserDetailsGui.getInstance();

	}

	private void setColumnSizes(GridPane grid) {
		grid.getColumnConstraints().addAll(
				new ColumnConstraints(10),
				new ColumnConstraints(150),
				new ColumnConstraints(100),
				new ColumnConstraints(150),
				new ColumnConstraints(200),
				new ColumnConstraints(100)
				);
	}

	private void createLeftFields(GridPane grid) {
		
		int firstColumn = 0;
		int row = 3;
		
		checkBoxes = new HashMap<String, CheckBox>();
		textFields = new HashMap<String, TextField>();
		
		//First row of items
		CheckBox initAdvCharge = new CheckBox("1. Initial Adviser Charge");
		checkBoxes.put("initAdvCharge", initAdvCharge);
		grid.add(initAdvCharge, firstColumn, row, 2, 1);
		
		CheckBox initVAT = new CheckBox("VAT to be applied?");
		checkBoxes.put("initVat", initVAT);
		grid.add(initVAT, firstColumn + 3, row);
		
		grid.add(new Label("Applies to:"), firstColumn+4, row);
		row++;
		
		//Second row of items
		grid.add(new Label("of investment"), firstColumn+1, row);
		grid.add(new Label("fixed amount\n(applicable per payment in)"), firstColumn+3, row);
		
		CheckBox cashLumpSum = new CheckBox("A. Cash Lump Sum");
		checkBoxes.put("cashLumpSum", cashLumpSum);
		grid.add(cashLumpSum, firstColumn+4, row);
		row++;
		
		//Third row of items
		TextField initAdvPercent = new TextField();
		textFields.put("initAdvPercent", initAdvPercent);
		grid.add(initAdvPercent, firstColumn+1, row);
		
		grid.add(new Label("&/or"), firstColumn+2, row);
		
		TextField initAdvAmount = new TextField();
		textFields.put("initAdvAmount", initAdvAmount);
		grid.add(initAdvAmount, firstColumn+3, row);
		
		CheckBox cashTransfers = new CheckBox("B. Cash Transfers");
		checkBoxes.put("cashTransfers", cashTransfers);
		grid.add(cashTransfers, firstColumn+4, row);
		row++;
		
		//Fourth row of items
		CheckBox stockTransfers = new CheckBox("C. Stock Transfers");
		checkBoxes.put("stockTransfers", stockTransfers);
		grid.add(stockTransfers, firstColumn+4, row);
		row++;
		
		//Fifth row of items
		CheckBox regContCharge = new CheckBox("2. Regular Contributions Charge\n(by Direct Debit only)");
		checkBoxes.put("regContCharge", regContCharge);
		grid.add(regContCharge, firstColumn, row, 2, 1);
		
		CheckBox regVAT = new CheckBox("VAT to be applied?");
		checkBoxes.put("regVAT", regVAT);
		grid.add(regVAT, firstColumn+3, row);
		
		grid.add(new Label("Applies to:"), firstColumn+4, row);
		row++;
		
		//Sixth row of items;
		grid.add(new Label("of Investment"), firstColumn+1, row);
		grid.add(new Label("A. Regular Contributions"), firstColumn+4, row);
		row++;
		
		//Seventh row of items
		TextField regPercent = new TextField();
		textFields.put("regPercent", regPercent);
		grid.add(regPercent, firstColumn+1, row);
		
		//Eighth row of items
		CheckBox tradingCharge = new CheckBox("3. Trading Charge");
		checkBoxes.put("tradingCharge", tradingCharge);
		grid.add(tradingCharge, firstColumn, row, 2, 1);
		
		CheckBox tradVAT = new CheckBox("VAT to be applied?");
		checkBoxes.put("trad", tradVAT);
		grid.add(tradVAT, firstColumn + 3, row);
		
		grid.add(new Label("Applies to:"), firstColumn+4, row);
		row++;
		
		//Ninth row of items
		grid.add(new Label("of Investment"), firstColumn+1, row);
		CheckBox singleFund = new CheckBox("A. Single Fund Buy Trades");
		checkBoxes.put("singleFund", singleFund);
		grid.add(singleFund, firstColumn+4, row);
		row++;
		
		//Tenth row of items
		TextField tradChargePercent = new TextField();
		textFields.put("tradChargePercent", tradChargePercent);
		grid.add(tradChargePercent, firstColumn+1, row);
		
		CheckBox switchFund = new CheckBox("B. Switch Fund Buy Trades");
		checkBoxes.put("switchFund", switchFund);
		grid.add(switchFund, firstColumn+4, row);
		row++;
		
		//Eleventh row of items
		CheckBox ongAdvCharge = new CheckBox("4. Ongoing Adviser Charge");
		checkBoxes.put("ongAdvCharge", ongAdvCharge);
		grid.add(ongAdvCharge, firstColumn, row, 2, 1);
		
		CheckBox ongVAT = new CheckBox("VAT to be applied?");
		checkBoxes.put("ongVAT", ongVAT);
		grid.add(ongVAT, firstColumn + 3, row);
		
		grid.add(new Label("Applies to:"), firstColumn+4, row);
		row++;
		
		//Twelfth row of items
		grid.add(new Label("of investment"), firstColumn+1, row);
		grid.add(new Label("annual fixed amount"), firstColumn+3, row);
		
		CheckBox collectives = new CheckBox("A. Collectives");
		checkBoxes.put("collectives", collectives);
		grid.add(collectives, firstColumn+4, row);
		row++;
		
		//Thirteenth row of items
		TextField ongAdvPercent = new TextField();
		textFields.put("ongAdvvPercent", ongAdvPercent);
		grid.add(ongAdvPercent, firstColumn+1, row);
		
		grid.add(new Label("&/or"), firstColumn+2, row);
		
		TextField ongAdvAmount = new TextField();
		textFields.put("ongAdvAmount", ongAdvAmount);
		grid.add(ongAdvAmount, firstColumn+3, row);
		
		CheckBox cash = new CheckBox("B. Cash");
		checkBoxes.put("cash", cash);
		grid.add(cash, firstColumn+4, row);
		row++;
		
		//Fourteenth row of items
		CheckBox sas = new CheckBox("C. Stocks and Shares");
		checkBoxes.put("sas", sas);
		grid.add(sas, firstColumn+4, row);
		row++;
		
		//Fifteenth row of items
		CheckBox nonCustAss = new CheckBox("D. Non-Custody Assets");
		checkBoxes.put("nonCustAss", nonCustAss);
		grid.add(nonCustAss, firstColumn+4, row);
		row++;
		
	}
	

	private void fillAndSaveClientInfo() {
		FinancialAdviserDetails fad = client.getFirstClient().getfinancialAdviserDetails();
		
		if(checkBoxes.get("initAdvCharge").isSelected()){
			InitialAdviserCharge initAdv = new InitialAdviserCharge();
			initAdv.setVatApplied(checkBoxes.get("initVAT").isSelected());
			initAdv.setOfInvestment(textFields.get("initAdvPercent").getText());
			initAdv.setFixedAmount(textFields.get("initAdvAmount").getText());
			initAdv.setAppCashLump(checkBoxes.get("cashLumpSum").isSelected());
			initAdv.setAppCashTransfer(checkBoxes.get("cashTransfers").isSelected());
			initAdv.setAppStockTransfers(checkBoxes.get("stockTransfers").isSelected());
			fad.setInitAdviser(initAdv);
		}
		if(checkBoxes.get("regContCharge").isSelected()){
			Charge regCont = new Charge();
			regCont.setVatApplied(checkBoxes.get("regVAT").isSelected());
			regCont.setOfInvestment(textFields.get("regPercent").getText());
			fad.setRegContributions(regCont);
		}
		if(checkBoxes.get("tradCharge").isSelected()){
			TradingCharge tradCharge = new TradingCharge();
			tradCharge.setVatApplied(checkBoxes.get("tradVAT").isSelected());
			tradCharge.setOfInvestment(textFields.get("tradPercent").getText());
			tradCharge.setSingleFundBuyTrades(checkBoxes.get("singleFund").isSelected());
			tradCharge.setSwitchFundBuyTrades(checkBoxes.get("switchFund").isSelected());
			fad.setTradingCharge(tradCharge);
		}
		if(checkBoxes.get("ongCharge").isSelected()){
			OngoingAdviserCharge ongAdv = new OngoingAdviserCharge();
			ongAdv.setVatApplied(checkBoxes.get("initVAT").isSelected());
			ongAdv.setOfInvestment(textFields.get("initAdvPercent").getText());
			ongAdv.setCollectives(checkBoxes.get("collectives").isSelected());
			ongAdv.setCash(checkBoxes.get("cash").isSelected());
			ongAdv.setStocksAndShares(checkBoxes.get("sas").isSelected());
			ongAdv.setNonCustodyAssets(checkBoxes.get("nonCustAss").isSelected());
			fad.setOngoingAdviser(ongAdv);
		}
	}

	@Override
	public void createMovementButtons(GridPane grid) {
		
		Button backBtn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        backBtn.setPrefWidth(100);
        hbBtn.getChildren().add(backBtn);
        grid.add(hbBtn, 2, 11);
        
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
            	nextPage.setUp(primaryStage, thisScene, client);
            }
        });
	}
}