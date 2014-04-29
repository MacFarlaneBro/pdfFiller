package ascentricGui;

import gui.Print;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ascentricClientDetails.Charge;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.FinancialAdviserDetails;
import ascentricClientDetails.InitialAdviserCharge;
import ascentricClientDetails.OngoingAdviserCharge;
import ascentricClientDetails.TradingCharge;

public class FinancialAdviserDetailsGui extends Page {
	
	public static final FinancialAdviserDetailsGui INSTANCE = new FinancialAdviserDetailsGui();
	
	@SuppressWarnings("unused")
	private Print nextPage;
	
	private FinancialAdviserDetailsGui(){}

	@Override
	public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client) {
    	this.client = client;
        this.previousScene = firstScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Financial Adviser Details");
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(8);
        grid.setAlignment(Pos.CENTER);
//        grid.setGridLinesVisible(true);

        setColumnSizes(grid, 3);
        setRowSizes(grid, 5, 12, 12, 30, 32, 18, 18, 30);
        thisScene = new Scene(grid, pageWidth, pageHeight);
         
        createBoxes();

        createLeftFields(grid);
                
        movementButtons2Columns(true);
        createMovementButtons(20, 6);
        
        primaryStage.setScene(thisScene);

	}

	private void createBoxes() {
		float leftBoundary = 5;
		float boxWidth = 800;
		
		Rectangle r1 = RectangleBuilder.create()
			.fill(Color.WHITE)
			.strokeWidth(2)
			.stroke(Color.BLACK)
			.opacity(0.1)
			.width(boxWidth)
			.height(130)
			.translateX(leftBoundary)
			.translateY(111)
			.build();
		
		Rectangle r2 = RectangleBuilder.create()
			.fill(Color.WHITE)
			.strokeWidth(2)
			.stroke(Color.BLACK)
			.opacity(0.1)
			.width(boxWidth)
			.height(81)
			.translateX(leftBoundary)
			.translateY(220)
			.build();
		
		Rectangle r3 = RectangleBuilder.create()
			.fill(Color.WHITE)
			.strokeWidth(2)
			.stroke(Color.BLACK)
			.opacity(0.1)
			.width(boxWidth)
			.height(70)
			.translateX(leftBoundary)
			.translateY(299)
			.build();
		
		Rectangle r4 = RectangleBuilder.create()
				.fill(Color.WHITE)
				.strokeWidth(2)
				.stroke(Color.BLACK)
				.opacity(0.1)
				.width(boxWidth)
				.height(120)
				.translateX(leftBoundary)
				.translateY(397)
				.build();
		
		grid.getChildren().add(r1);
		grid.getChildren().add(r2);
		grid.getChildren().add(r3);
		grid.getChildren().add(r4);
		
	}

	private void createLeftFields(GridPane grid) {
		
		Text sceneTitle = new Text("Financial Adviser Details");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        sceneTitle.setId("sceneTitle");
        grid.add(sceneTitle, 1, 1, 2, 2);
		
		int firstColumn = 1;
		int row = 3;
		
		checkBoxes = new HashMap<String, CheckBox>();
		textFields = new HashMap<String, TextField>();
		
		//First row of items
		CheckBox initAdvCharge = new CheckBox("1. Initial Adviser Charge");
		checkBoxes.put("initAdvCharge", initAdvCharge);
		grid.add(initAdvCharge, firstColumn, row, 2, 1);
		
		CheckBox initVAT = new CheckBox("VAT to be applied?");
		checkBoxes.put("initVAT", initVAT);
		grid.add(initVAT, firstColumn + 3, row);
		
		grid.add(new Label("Applies to:"), firstColumn+4, row);
		row++;
		
		//Second row of items
		grid.add(new Label("% of investment"), firstColumn+1, row);
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
		grid.add(new Label("% of investment"), firstColumn+1, row);
		grid.add(new Label("A. Regular Contributions"), firstColumn+4, row);
		row++;
		
		//Seventh row of items
		TextField regPercent = new TextField();
		textFields.put("regPercent", regPercent);
		grid.add(regPercent, firstColumn+1, row);
		row++;
		
		//Eighth row of items
		CheckBox tradCharge = new CheckBox("3. Trading Charge");
		checkBoxes.put("tradCharge", tradCharge);
		grid.add(tradCharge, firstColumn, row, 2, 1);
		
		CheckBox tradVAT = new CheckBox("VAT to be applied?");
		checkBoxes.put("tradVAT", tradVAT);
		grid.add(tradVAT, firstColumn + 3, row);
		
		grid.add(new Label("Applies to:"), firstColumn+4, row);
		row++;
		
		//Ninth row of items
		grid.add(new Label("% of investment"), firstColumn+1, row);
		CheckBox singleFund = new CheckBox("A. Single Fund Buy Trades");
		checkBoxes.put("singleFund", singleFund);
		grid.add(singleFund, firstColumn+4, row);
		row++;
		
		//Tenth row of items
		TextField tradChargePercent = new TextField();
		textFields.put("tradPercent", tradChargePercent);
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
		grid.add(new Label("% of investment"), firstColumn+1, row);
		grid.add(new Label("annual fixed amount"), firstColumn+3, row);
		
		CheckBox collectives = new CheckBox("A. Collectives");
		checkBoxes.put("collectives", collectives);
		grid.add(collectives, firstColumn+4, row);
		row++;
		
		//Thirteenth row of items
		TextField ongAdvPercent = new TextField();
		textFields.put("ongAdvPercent", ongAdvPercent);
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
	

	protected void fillAndSaveClientInfo() {
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
			tradCharge.setOfInvestment(
					textFields.get("tradPercent").getText());
			tradCharge.setSingleFundBuyTrades(checkBoxes.get("singleFund").isSelected());
			tradCharge.setSwitchFundBuyTrades(checkBoxes.get("switchFund").isSelected());
			fad.setTradingCharge(tradCharge);
		}
		if(checkBoxes.get("ongAdvCharge").isSelected()){
			OngoingAdviserCharge ongAdv = new OngoingAdviserCharge();
			ongAdv.setVatApplied(checkBoxes.get("ongVAT").isSelected());
			ongAdv.setOfInvestment(textFields.get("ongAdvPercent").getText());
			ongAdv.setCollectives(checkBoxes.get("collectives").isSelected());
			ongAdv.setCash(checkBoxes.get("cash").isSelected());
			ongAdv.setAnnualFixedAmount(textFields.get("ongAdvAmount").getText());
			ongAdv.setStocksAndShares(checkBoxes.get("sas").isSelected());
			ongAdv.setNonCustodyAssets(checkBoxes.get("nonCustAss").isSelected());
			fad.setOngoingAdviser(ongAdv);
		}
	}
	
	
	public void createMovementButtons(int depth,int nextWidth) {
	    
		Button backBtn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        backBtn.setPrefWidth(100);
        hbBtn.getChildren().add(backBtn);
        grid.add(hbBtn, 0, depth, 2, 1);
        
        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(previousScene);
            }
        });
        
        Button nextBtn = new Button("Next");//Create button with the name sign in
        HBox hNextBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hNextBtn.setAlignment(Pos.BOTTOM_RIGHT);
        nextBtn.setPrefWidth(100);
        hNextBtn.getChildren().add(nextBtn);
        grid.add(hNextBtn, nextWidth, depth);
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
            public void handle(ActionEvent e){
            	fillAndSaveClientInfo();
            	nextPage = new Print(client, primaryStage, thisScene);
            }
        });	
	}
}