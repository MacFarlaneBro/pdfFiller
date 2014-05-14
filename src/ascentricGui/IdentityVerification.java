package ascentricGui;

import java.util.HashMap;

import gui.Print;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.ConfirmationDetails;

public class IdentityVerification extends Page{

	public static final Page INSTANCE = new IdentityVerification();
	
	@SuppressWarnings("unused")
	private Print nextPage;
	private int gridVert;
	private int firstColumn;
	private int secondColumn;
	private int thirdColumn = 3;

	private CheckBox moneyLaunder;

	private CheckBox identityVerification;
	
	private IdentityVerification(){}

	@Override
	public void setUp(Stage primaryStage, Scene previousScene,
			ClientHolder client) {

		this.client = client;
        this.previousScene = previousScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Confirmation of Verification of Identity Certificate");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);
        
        setColumnSizes(grid, 3, 200, 200, 200, 200);
        setRowSizes(grid, 0, 20, 20, 30, 30, 30, 30, 30, 30, 30, 40, 100);        
        
        thisScene = new Scene(grid, PAGEWIDTH, PAGEHEIGHT);
		Text sceneTitle = new Text("Confirmation of Identity");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        grid.add(sceneTitle, 1, 1, 2, 2);
                
        createPrivateIndividualFields(grid);
        createConfirmationFields(grid);
                
        movementButtons2Columns(true);
        createMovementButtons(12, 5);

        primaryStage.setScene(thisScene);
	}

	private void createPrivateIndividualFields(GridPane grid) {
		
		firstColumn = 1;
		secondColumn = 2;
		gridVert = 3;
		textFields = new HashMap<String, TextField>();

		Label privInd = new Label("Private Individual");
		privInd.setFont(Font.font(null, FontWeight.BOLD, 12));
		grid.add(privInd, firstColumn, gridVert++);
		
		//Name of Individual
		grid.add(new Label("Name of Individual"), firstColumn, gridVert);
		TextField name = new TextField();
		grid.add(name, secondColumn, gridVert++);
		textFields.put("name", name);
		
		//Individuals Current Address
		grid.add(new Label("Individual's current address"), firstColumn, gridVert);
		TextField currAdd1 = new TextField();
		grid.add(currAdd1, secondColumn, gridVert++);
		textFields.put("currAdd1", currAdd1);
		TextField currAdd2 = new TextField();
		grid.add(currAdd2, secondColumn, gridVert++);
		textFields.put("currAdd2", currAdd2);
		TextField currAdd3 = new TextField();
		grid.add(currAdd3, secondColumn, gridVert++);
		textFields.put("currAdd3", currAdd3);
		
		//Current Postcode
		grid.add(new Label("Current Postcode"), firstColumn, gridVert);
		TextField postCode = new TextField();
		grid.add(postCode, secondColumn, gridVert++);
		textFields.put("currPostCode", postCode);
		
		//Date of Birth
		grid.add(new Label("Date of Birth (DDMMYYYY)"), firstColumn, gridVert);
		TextField dob = new TextField();
		grid.add(dob, secondColumn, gridVert);
		textFields.put("dob", dob);
		
		gridVert = 3; //resetting gridvert to its original value prior to printing the second column;
		int thirdColumn = 3;
		int fourthColumn = 4;
		
		//Individual's previous address
		grid.add(new Label("Individual's previous address"), thirdColumn, ++gridVert);
		TextField prevAdd1 = new TextField();
		grid.add(prevAdd1, fourthColumn, gridVert++);
		textFields.put("prevAdd1", prevAdd1);
		TextField prevAdd2 = new TextField();
		grid.add(prevAdd2, fourthColumn, gridVert++);
		textFields.put("prevAdd2", prevAdd2);
		TextField prevAdd3 = new TextField();
		grid.add(prevAdd3, fourthColumn, gridVert++);
		textFields.put("prevAdd3", prevAdd3);
		
		//Previous Postcode
		grid.add(new Label("Previous Postcode"), thirdColumn, gridVert);
		TextField previousPostCode = new TextField();
		grid.add(previousPostCode, fourthColumn, gridVert++);
		textFields.put("previousPostCode", previousPostCode);
	}

	private void createConfirmationFields(GridPane grid) {
		gridVert+=2;
		
		moneyLaunder = new CheckBox("The evidence I/we have obtained meets the standard"
				+ " requirements which are defined within the guidance for the UK Financial Sector"
				+ "issued by the Joint Money Laundering Steering Group (JMLSG)");
		moneyLaunder.setWrapText(true);
		grid.add(moneyLaunder, firstColumn, gridVert, 2, 2);
		
		identityVerification = new CheckBox("The evidence I/we have obtained exceeds the"
				+ " standard requirements and I/we have attached the further evidence I/we used to"
				+ " verify the identity of my/our client to this form.");
		identityVerification.setWrapText(true);
		grid.add(identityVerification, thirdColumn, gridVert, 2, 2);
		
	}
	
	@Override
	protected void fillAndSaveClientInfo(){
		ConfirmationDetails cd = client.getFirstClient().getConfirmationDetails();
		cd.setName(textFields.get("name").getText());
		cd.setCurrentAddress(textFields.get("currAdd1").getText() + ":"
				+ textFields.get("currAdd2").getText() + ":"
				+ textFields.get("currAdd3").getText());
		cd.setCurrentPostCode(textFields.get("currPostCode").getText());
		
		//Sanitising the dob input prior to storage
		if(textFields.get("dob").getText().contains("/")){
			cd.setDob(textFields.get("dob").getText().replace("/", ""));
		} else if(textFields.get("dob").getText().contains("-")){
			cd.setDob(textFields.get("dob").getText().replace("-", ""));
		} else if(textFields.get("dob").getText().contains(" ")){
			cd.setDob(textFields.get("dob").getText().replace(" ", ""));
		} else {
			cd.setDob(textFields.get("dob").getText());
		}
		
		cd.setPreviousAddress(textFields.get("prevAdd1").getText() + ":"
				+ textFields.get("prevAdd2").getText() + ":"
				+ textFields.get("prevAdd3").getText());
		cd.setPreviousPostCode(textFields.get("previousPostCode").getText());
		cd.setClientIdentityCheck(identityVerification.isSelected());
		cd.setMoneyLaunderingCheck(moneyLaunder.isSelected());
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
        grid.add(hNextBtn, nextWidth-1, depth, 2, 1);
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
            public void handle(ActionEvent e){
				fillAndSaveClientInfo();
            	nextPage = new Print(client, primaryStage, thisScene);
            }
        });	
	}
}
