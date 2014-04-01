package ascentricGui;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public class IncomePayment extends Page {

	private ClientHolder client;
	private Stage primaryStage;
	private Scene previousScene;
	private GridPane grid;
	private Scene thisScene;
	private Map<String, TextField> textFields;
	private Map<String, CheckBox> checkBoxes;
	private ComboBox<String> timing;
	protected Page nextPage;

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
        
        thisScene = new Scene(grid);       
        
        createFields(grid);
        
        createMovementButtons(grid);

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
		textFields.put("wrapper", wrap);
		grid.add(wrap, 3, 8);
		
		Label regularity = new Label("Regularity:");
		grid.add(regularity, 2, 9);
		timing = new ComboBox<String>(FXCollections.observableArrayList(
                "Monthly",
                "Quarterly",
                "Half Yearly",
                "Annually"
        ));
		grid.add(timing, 3, 9);
		
		grid.add(new Label("Regular Withdrawal Instructions"), 4, 4);
		
		grid.add(new Label("Payment from deposit (or reserve account\n"
				+ "where applicable) and withdraw the\n"
				+ "following fixed amount:"), 4, 5);
		TextField amount = new TextField();
		textFields.put("amount", amount);
		grid.add(amount, 5, 5);
		
		grid.add(new Label("Regularity:"), 4, 6);
		timing = new ComboBox<String>(FXCollections.observableArrayList(
                "Monthly",
                "Quarterly",
                "Half Yearly",
                "Annually"
        ));
		grid.add(timing, 5, 6);
		
		grid.add(new Label("Wrapper(s)"), 4, 7);
		TextField regWrap = new TextField();
		textFields.put("wrapper", regWrap);
		grid.add(regWrap, 5, 7);
		
		grid.add(new Label("Start Date (DDMMYYYY)"), 4, 8);
		TextField startDate = new TextField();
		textFields.put("startDate", startDate);
		grid.add(startDate, 5, 8);
	}
	

	protected void fillAndSaveClientInfo() {
		// TODO Auto-generated method stub
		
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
            	ProductDetailsFactory.makeSecond();
            	nextPage = FinancialAdviserDetails.getInstance();
            	nextPage.setUp(primaryStage, thisScene, client);
            }
        });

	}
}
