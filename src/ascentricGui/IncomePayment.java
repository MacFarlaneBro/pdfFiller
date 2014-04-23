package ascentricGui;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ascentricClientDetails.BankAccountDetails;
import ascentricClientDetails.ClientHolder;

public class IncomePayment extends Page {

	public static final IncomePayment INSTANCE = new IncomePayment();
	private ComboBox<String> timing;
	private ComboBox<String> natTiming;

	private IncomePayment(){}
	
	@Override
	public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client) {

		this.client = client;
        this.previousScene = firstScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Income Payment Instructions");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        
        setColumnSizes(grid);
        setRowSizes(grid, 40, 20, 20, 20, 20, 50, 20, 20, 20, 20, 20, 20);        
        
		Text sceneTitle = new Text("Income Payment Instructions");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        grid.add(sceneTitle, 1, 1, 2, 2);
        
        thisScene = new Scene(grid, pageWidth, pageHeight);       
        
        createFields(grid);
        
        nextPage = FinancialAdviserDetailsGui.INSTANCE;
        
        movementButtons2Columns(true);
        createMovementButtons(12, 6);

        primaryStage.setScene(thisScene);

	}

	private void createFields(GridPane grid) {
		
		checkBoxes = new HashMap<String, CheckBox>();
		textFields = new HashMap<String, TextField>();
		
		Label nipi = new Label("Natural Income Payment Instructions");
		nipi.setFont(Font.font(null, FontWeight.BOLD, 12));
		GridPane.setHalignment(nipi, HPos.CENTER);
		grid.add(nipi, 1, 4, 2, 1);
		
		Label noIncWith = new Label("No income widthdrawal (transfer to deposit)");
		grid.add(noIncWith, 1, 5, 2, 1);
		CheckBox noInc = new CheckBox();
		checkBoxes.put("noIncomeWithdrawl", noInc);
		grid.add(noInc, 3, 5);
		
		Label leaveIncAcc = new Label("Leave in income account pending instructions");
		grid.add(leaveIncAcc, 1, 6, 2, 1);
		CheckBox incAcc = new CheckBox();
		checkBoxes.put("leaveIncomeAccount", incAcc);
		grid.add(incAcc, 3, 6);
		
		Label withdraw = new Label("Withdraw the Natural Income\n (This will be paid out upon receipt unless\n otherwise "
				+ "indicated below)");
		grid.add(withdraw, 1, 7, 2, 2);
		CheckBox natInc = new CheckBox();
		checkBoxes.put("withdrawNaturalIncome", natInc);
		grid.add(natInc, 3, 7);
		
		Label wrapper = new Label("Wrapper(s)");
		grid.add(wrapper, 1, 9, 2, 1);
		TextField wrap = new TextField();
		textFields.put("natWrapper", wrap);
		grid.add(wrap, 3, 9);
		
		Label regularity = new Label("Regularity:");
		grid.add(regularity, 1, 10, 2, 1);
		GridPane.setHalignment(regularity, HPos.RIGHT);
		timing = new ComboBox<String>(FXCollections.observableArrayList(
                "Monthly",
                "Quarterly",
                "Half Yearly",
                "Annually"
        ));
		timing.setValue("Monthly");
		grid.add(timing, 3, 10);
		
		Label regWithDraw = new Label("Regular Withdrawal Instructions");
		regWithDraw.setFont(Font.font(null, FontWeight.BOLD, 12));
		grid.add(regWithDraw, 4, 4);
		
		Label pfd = new Label("Payment from deposit (or reserve\naccount"
				+ "where applicable) and\nwithdraw the"
				+ " following fixed\namount:");
		GridPane.setValignment(pfd, VPos.CENTER);
		
		grid.add(pfd , 4, 5, 2, 2);
		TextField amount = new TextField();
		textFields.put("amount", amount);
		grid.add(amount, 5, 6);
		
		grid.add(new Label("Regularity:"), 4, 7);
		natTiming = new ComboBox<String>(FXCollections.observableArrayList(
                "Monthly",
                "Quarterly",
                "Half Yearly",
                "Annually"
        ));
		grid.add(natTiming, 5, 7);
		natTiming.setValue("Monthly");
		grid.add(new Label("Wrapper(s)"), 4, 8);
		TextField regWrap = new TextField();
		textFields.put("regularWrapper", regWrap);
		grid.add(regWrap, 5, 8);
		
		grid.add(new Label("Start Date (DDMMYYYY)"), 4, 9);
		TextField startDate = new TextField();
		textFields.put("startDate", startDate);
		grid.add(startDate, 5, 9);
	}

	protected void fillAndSaveClientInfo() {
		BankAccountDetails bad = client.getFirstClient().getBankAccountDetails();
		bad.setNoIncomeWithdrawl(checkBoxes.get("noIncomeWithdrawl").isSelected());
		bad.setLeaveInIncomeAccount(checkBoxes.get("leaveIncomeAccount").isSelected());
		bad.setWithdrawNaturalIncome(checkBoxes.get("withdrawNaturalIncome").isSelected());
		bad.setNatIncomeWrappers(textFields.get("natWrapper").getText());
		bad.setPaymentTiming(timing.getValue());
		bad.setPaymentFromDeposit(textFields.get("amount").getText());
		bad.setRegWithdrawalPayTiming(natTiming.getValue());
		bad.setRegWithdrawlWrappers(textFields.get("regularWrapper").getText());
		
		//Sanitising the date input prior to storage
		if(textFields.get("startDate").getText().contains("/")){
			bad.setStartDate(textFields.get("startDate").getText().replace("/", ""));
		} else if(textFields.get("startDate").getText().contains("-")){
			bad.setStartDate(textFields.get("startDate").getText().replace("-", ""));
		} else {
			bad.setStartDate(textFields.get("startDate").getText());
		}
	}
}