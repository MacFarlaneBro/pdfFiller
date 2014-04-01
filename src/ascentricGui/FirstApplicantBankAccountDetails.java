package ascentricGui;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public class FirstApplicantBankAccountDetails extends Page{

	private ClientHolder client;
	private Scene previousScene;
	private Stage primaryStage;
	private GridPane grid;
	private Scene thisScene;
	private HashMap<String, TextField> textFields;
	private String type;
	protected Page nextPage;
	
	public FirstApplicantBankAccountDetails(String type){
		this.type = type;
	}

	@Override
	public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client) {
		
		this.client = client;
        this.previousScene = firstScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Client Bank Account Details");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        
		if(type == "first"){
			primaryStage.setTitle("First Applicant Bank Account Details");
			Text sceneTitle = new Text("First Applicant Bank Account Details");
	        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
	        grid.add(sceneTitle, 1, 1, 2, 2);
		}
        
        thisScene = new Scene(grid);       
        
        createFields(grid);
        
        createMovementButtons(grid);

//        grid.setGridLinesVisible(true);
        primaryStage.setScene(thisScene);
		
	}

	private void createFields(GridPane grid) {
		
		textFields = new HashMap<String, TextField>();
		
		Label warning = new Label("Failure to provide bank details at the time of application\n "
				+ "may result in delays when making future payments");
		warning.setFont(Font.font(null, FontWeight.BOLD, 12));
		grid.add(warning, 2, 3, 2, 2);
		
		Label accountHolderName = new Label("Names of account holder(s)");
		grid.add(accountHolderName, 2, 5);
		TextField names1 = new TextField();
		names1.setId("names1");
		textFields.put("names1", names1);
		grid.add(names1, 2, 6, 2, 1);
		TextField names2 = new TextField();
		names2.setId("names2");
		textFields.put("names2", names2);
		grid.add(names2, 2, 7, 2, 1);
		
		Label accountNumber = new Label("Bank/Building Society account number");
		grid.add(accountNumber, 2, 8);
		TextField acctNumber = new TextField();
		acctNumber.setId("accountNumber");
		textFields.put("accountNumber", acctNumber);
		grid.add(acctNumber, 2, 9);
		
		Label srtcde = new Label("Branch sort code");
		grid.add(srtcde, 2, 10);
		TextField sortCode = new TextField();
		sortCode.setId("sortCode");
		textFields.put("sortCode", sortCode);
		grid.add(sortCode, 2, 11);
		
		Label addLabel = new Label("Full Name and Postal address of your\nBank or Building Society");
		addLabel.setFont(Font.font(null, FontWeight.BOLD, 12));
		grid.add(addLabel, 4, 4, 2, 2);
		
		Label bankName = new Label("Name");
		grid.add(bankName, 4, 6);
		TextField bankNameField = new TextField();
		bankNameField.setId("bankName");
		textFields.put("bankName", bankNameField);
		grid.add(bankNameField, 5, 6);
		
		Label bankAddress = new Label("Address");
		grid.add(bankAddress, 4, 7);
		TextField bankAddressField1 = new TextField();
		bankAddressField1.setId("bankAddress1");
		textFields.put("bankAddress1", bankAddressField1);
		grid.add(bankAddressField1, 5, 7);
		TextField bankAddressField2 = new TextField();
		bankAddressField2.setId("bankAddress2");
		textFields.put("bankAddress2", bankAddressField2);
		grid.add(bankAddressField2, 5, 8);
		TextField bankAddressField3 = new TextField();
		bankAddressField3.setId("bankAddress3");
		textFields.put("bankAddress3", bankAddressField3);
		grid.add(bankAddressField3, 5, 9);
		
		Label bankPostCode = new Label("Postcode");
		grid.add(bankPostCode, 4, 10);
		TextField bankPostCodeField = new TextField();
		bankPostCodeField.setId("postcode");
		textFields.put("postcode", bankPostCodeField);
		grid.add(bankPostCodeField, 5, 10);
		
	}

	@Override
	public void goTo() {
		
	}

	@Override
	public void createMovementButtons(GridPane grid) {
		Button backBtn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        backBtn.setPrefWidth(100);
        hbBtn.getChildren().add(backBtn);
        grid.add(hbBtn, 2, 12);
        
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
        hNextBtn.setAlignment(Pos.CENTER_RIGHT);
        grid.add(hNextBtn, 5, 12);
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>(){


			@Override
            public void handle(ActionEvent e){
            	fillAndSaveClientInfo();
	            if(client.getSecondClient()!= null){
	            	ProductDetailsFactory.makeSecond();
	            	nextPage = ProductDetailsFactory.getSecond();
	            	nextPage.setUp(primaryStage, thisScene, client);
            	} else {
            		nextPage = new FirstApplicantBankAccountDetails("first");
            		nextPage.setUp(primaryStage, thisScene, client);
            	}
            }
        });
	}

	protected void fillAndSaveClientInfo() {
		// TODO Auto-generated method stub
		
	}

}
