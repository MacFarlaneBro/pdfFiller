package ascentricGui;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
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

	private ComboBox<String> timing;
	private ComboBox<String> natTiming;

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
        
		Text sceneTitle = new Text("Income Payment Instructions");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        grid.add(sceneTitle, 1, 1, 2, 2);
        
        thisScene = new Scene(grid, pageWidth, pageHeight);       
        
        createFields(grid);
        
        nextPage = FinancialAdviserDetailsGui.getInstance();
        
        createMovementButtons(12, 7);

//        grid.setGridLinesVisible(true);
        primaryStage.setScene(thisScene);

	}

	private void createFields(GridPane grid) {
		
		checkBoxes = new HashMap<String, CheckBox>();
		textFields = new HashMap<String, TextField>();
		
		Label nipi = new Label("Natural Income Payment Instructions");
		grid.add(nipi, 2, 4);
		
		Label noIncWith = new Label("No income widthdrawal (transfer to deposit)");
		grid.add(noIncWith, 2, 5);
		CheckBox noInc = new CheckBox();
		checkBoxes.put("noIncomeWithdrawl", noInc);
		grid.add(noInc, 3, 5);
		
		Label leaveIncAcc = new Label("Leave in income account pending instructions");
		grid.add(leaveIncAcc, 2, 6);
		CheckBox incAcc = new CheckBox();
		checkBoxes.put("leaveIncomeAccount", incAcc);
		grid.add(incAcc, 3, 6);
		
		Label withdraw = new Label("Withdraw the Natural Income \n(This will be paid out upon receipt unless\notherwise "
				+ "indicated below)");
		grid.add(withdraw, 2, 7);
		CheckBox natInc = new CheckBox();
		checkBoxes.put("withdrawNaturalIncome", natInc);
		grid.add(natInc, 3, 7);
		
		Label wrapper = new Label("Wrapper(s)");
		grid.add(wrapper, 2, 8);
		TextField wrap = new TextField();
		textFields.put("natWrapper", wrap);
		grid.add(wrap, 3, 8);
		
		Label regularity = new Label("Regularity:");
		grid.add(regularity, 2, 9);
		timing = new ComboBox<String>(FXCollections.observableArrayList(
                "Monthly",
                "Quarterly",
                "Half Yearly",
                "Annually"
        ));
		timing.setValue("Monthly");
		grid.add(timing, 3, 9);
		
		grid.add(new Label("Regular Withdrawal Instructions"), 4, 4);
		
		grid.add(new Label("Payment from deposit (or reserve account\n"
				+ "where applicable) and withdraw the\n"
				+ "following fixed amount:"), 4, 5);
		TextField amount = new TextField();
		textFields.put("amount", amount);
		grid.add(amount, 5, 5);
		
		grid.add(new Label("Regularity:"), 4, 6);
		natTiming = new ComboBox<String>(FXCollections.observableArrayList(
                "Monthly",
                "Quarterly",
                "Half Yearly",
                "Annually"
        ));
		grid.add(natTiming, 5, 6);
		natTiming.setValue("Monthly");
		grid.add(new Label("Wrapper(s)"), 4, 7);
		TextField regWrap = new TextField();
		textFields.put("regularWrapper", regWrap);
		grid.add(regWrap, 5, 7);
		
		grid.add(new Label("Start Date (DDMMYYYY)"), 4, 8);
		TextField startDate = new TextField();
		textFields.put("startDate", startDate);
		grid.add(startDate, 5, 8);
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
		bad.setStartDate(textFields.get("startDate").getText());
	}
}
