package ascentricGui;

import java.util.HashSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public class Page3 extends Page {

	private static Page instance;
	private ClientHolder client;
	private Scene previousScene;
	private Stage primaryStage;
	private Scene thisScene;
	private Object clientData;
	private Page nextPage;
	private GridPane grid;
	private int gridVert;


	static { 
		instance = new Page3();
	}
	
	public static Page getInstance(){
		return instance;
	}

	@Override
	public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client) {
    	this.client = client;
        this.previousScene = firstScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Access Rights");
        GridPane grid = new GridPane();
        this.grid = grid;
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);
        
        thisScene = new Scene(grid);
        
        createFields(grid);

       // grid.setGridLinesVisible(true);
        primaryStage.setScene(thisScene);
        System.out.println("New Scene Set");
       
	}

	private void createFields(GridPane grid) {
		
		Text sceneTitle = new Text("Access Rights");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        sceneTitle.setId("sceneTitle");
        grid.add(sceneTitle, 1, 1, 2, 2);
    	
    	int gridVert = 3;
    	
    	Label singleApp  = new Label("Single Applicant");
    	grid.add(singleApp, 2, gridVert);
    	Label secondApp  = new Label("Second Applicant");
    	grid.add(secondApp, 3, gridVert);
    	Label jointApp  = new Label("Joint Account");
    	grid.add(jointApp, 4, gridVert);
    	
        //Label for first row of checkboxes
        Label noOnlineAccess = new Label("No Online Access");
        noOnlineAccess.setTextAlignment(TextAlignment.CENTER);
        grid.add(noOnlineAccess, 1, ++gridVert);
        
        createCheckboxes(gridVert, "NoOnAcc");
        
        //Label for second row of checkboxes
        Label enquiryOnly = new Label("Enquiry Only");
        enquiryOnly.setTextAlignment(TextAlignment.RIGHT);
        grid.add(enquiryOnly, 1, ++gridVert);
        
        createCheckboxes(gridVert, "EnqOnly");
        
        //Label for third row of checkboxes
        Label tradingAccess = new Label("Trading Only");
        tradingAccess.setTextAlignment(TextAlignment.RIGHT);
        grid.add(tradingAccess, 1, ++gridVert);
        
        createCheckboxes(gridVert, "TradingAcc");
        
        sceneTitle = new Text("Family Groups");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        sceneTitle.setId("sceneTitle");
        grid.add(sceneTitle, 1, gridVert++, 2, gridVert);
        
        gridVert+=4;
        
        CheckBox famGroup1Check = new CheckBox("More than 1 applicant Linked to a Family Group");
        famGroup1Check.setAlignment(Pos.CENTER);
		grid.add(famGroup1Check, 2, gridVert, 3, 1);
		singleApp.setId("famGroup1Check");
        
		CheckBox famGroup2Check = new CheckBox("Link to an existing Ascentric account");
		famGroup2Check.setAlignment(Pos.CENTER);
		grid.add(famGroup2Check, 2, ++gridVert, 3 ,1);
		famGroup2Check.setId("famGroup2Check");
		
		this.gridVert = gridVert+=2;
		
		createMovementButtons(grid);
		
	}

	private void createCheckboxes(int gridVert, String row) {
		//Setting leftmost checkbox
		CheckBox singleApp = new CheckBox();
		singleApp.setAlignment(Pos.CENTER);
		grid.add(singleApp, 2, gridVert);
		singleApp.setId("SingleApp" + row);
		
		CheckBox enqOnly = new CheckBox();
		enqOnly.setAlignment(Pos.CENTER);
		grid.add(enqOnly, 3, gridVert);
		enqOnly.setId("EnqOnly" + row);
		
		CheckBox tradAcc = new CheckBox();
		tradAcc.setAlignment(Pos.CENTER);
		grid.add(tradAcc, 4, gridVert);
		tradAcc.setId("TradingAcc" + row);
		
	}

	@Override
	public void goTo() {
		// TODO Auto-generated method stub
		
	}
	
	private void fillAndSaveClientInfo(Object clientData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createMovementButtons(GridPane grid) {
	    Button backBtn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        backBtn.setPrefWidth(200);
        hbBtn.getChildren().add(backBtn);
        grid.add(hbBtn, 1, --gridVert);
        
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
        nextBtn.setPrefWidth(200);
        hNextBtn.getChildren().add(nextBtn);
        grid.add(hNextBtn, 4, gridVert);
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
            public void handle(ActionEvent e){
            	fillAndSaveClientInfo(clientData);
            }
        });
		
	}

}
