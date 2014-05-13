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
	public void setUp(Stage primaryStage, Scene previousScene, ClientHolder client) {

		this.client = client;
        this.previousScene = previousScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Income Payment Instructions");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        
        setColumnSizes(grid, 10, 150, 150, 150, 200);
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
		
		int gridVert = 2;
		
		Label nipi = new Label("Natural Income Payment Instructions");
		nipi.setFont(Font.font(null, FontWeight.BOLD, 12));
		GridPane.setHalignment(nipi, HPos.CENTER);
		grid.add(nipi, 1, 4, 2, 1);
		
		Label noIncWith = new Label("No income widthdrawal (transfer to deposit):");
		grid.add(noIncWith, 1, 5, 2, 1);
		CheckBox noInc = new CheckBox();
		checkBoxes.put("noIncomeWithdrawl", noInc);
		GridPane.setHalignment(noInc, HPos.CENTER);
		grid.add(noInc, 3, 5);
		
		Label leaveIncAcc = new Label("Leave in income account pending instructions:");
		grid.add(leaveIncAcc, 1, 6, 2, 1);
		CheckBox incAcc = new CheckBox();
		checkBoxes.put("leaveIncomeAccount", incAcc);
		GridPane.setHalignment(incAcc, HPos.CENTER);
		grid.add(incAcc, 3, 6);
		
		Label withdraw = new Label("Withdraw the Natural Income (This will be paid out upon receipt unless otherwise "
				+ "indicated below):");
		withdraw.setWrapText(true);
		grid.add(withdraw, 1, 7, 2, 2);
		CheckBox natInc = new CheckBox();
		checkBoxes.put("withdrawNaturalIncome", natInc);
		GridPane.setHalignment(natInc, HPos.CENTER);
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
		grid.add(timing, 3, 10);
		
		Label regWithDraw = new Label("Regular Withdrawal Instructions");
		regWithDraw.setFont(Font.font(null, FontWeight.BOLD, 12));
		grid.add(regWithDraw, 4, 4);
		
		gridVert = 5;
		Label pfd = new Label("You can choose whether a fixed payment is withdrawn from the deposit"
				+ " or the reserve account. Please insert the amount into the apropriate box:");
		pfd.setWrapText(true);
		GridPane.setValignment(pfd, VPos.CENTER);
		grid.add(pfd, 4, gridVert++, 2, 2);
		
		Label pfda = new Label("Payment From Deposit Account");
		grid.add(pfda, 4, gridVert);
		TextField pdaAmount = new TextField();
		textFields.put("pfda", pdaAmount);
		grid.add(pdaAmount, 5, gridVert++);
		
		grid.add(new Label("OR"), 4, gridVert++);
		
		Label pfra = new Label("Payment From Reserve Account");
		grid.add(pfra, 4, gridVert);
		TextField pfaAmount = new TextField();
		textFields.put("pfra", pfaAmount);
		grid.add(pfaAmount, 5, gridVert++);
		
		grid.add(new Label("Regularity:"), 4, gridVert);
		natTiming = new ComboBox<String>(FXCollections.observableArrayList(
                "Monthly",
                "Quarterly",
                "Half Yearly",
                "Annually"
        ));
		grid.add(natTiming, 5, gridVert++);
		grid.add(new Label("Wrapper(s)"), 4, gridVert);
		
		TextField regWrap = new TextField();
		textFields.put("regularWrapper", regWrap);
		grid.add(regWrap, 5, gridVert++);
		
		grid.add(new Label("Start Date (DDMMYYYY)"), 4, gridVert);
		TextField startDate = new TextField();
		textFields.put("startDate", startDate);
		grid.add(startDate, 5, gridVert);
	}

	protected void fillAndSaveClientInfo() {
		BankAccountDetails bad = client.getFirstClient().getBankAccountDetails();
		bad.setNoIncomeWithdrawl(checkBoxes.get("noIncomeWithdrawl").isSelected());
		bad.setLeaveInIncomeAccount(checkBoxes.get("leaveIncomeAccount").isSelected());
		bad.setWithdrawNaturalIncome(checkBoxes.get("withdrawNaturalIncome").isSelected());
		bad.setNatIncomeWrappers(textFields.get("natWrapper").getText());
		bad.setPaymentTiming(timing.getValue());
		bad.setPaymentFromDepositAccount(textFields.get("pfda").getText());
		bad.setPaymentFromReserveAccount(textFields.get("pfra").getText());
		bad.setRegWithdrawalPayTiming(natTiming.getValue());
		bad.setRegWithdrawlWrappers(textFields.get("regularWrapper").getText());
		
		//Sanitising the date input prior to storage
		if(textFields.get("startDate").getText().contains("/")){
			bad.setStartDate(textFields.get("startDate").getText().replace("/", ""));
		} else if(textFields.get("startDate").getText().contains("-")){
			bad.setStartDate(textFields.get("startDate").getText().replace("-", ""));
		} else if(textFields.get("startDate").getText().contains(" ")){
			bad.setStartDate(textFields.get("startDate").getText().replace(" ", ""));
		} else {
			bad.setStartDate(textFields.get("startDate").getText());
		}
	}
}