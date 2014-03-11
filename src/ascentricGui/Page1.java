package ascentricGui;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import databaseAccess.GetDatabase;
import databaseAccess.MSSQLDatabase;
 
public class Page1{
    
    private Stage primaryStage;
    private Scene firstScene;
    private TextField clientSurname;
    private Node clientFirstName;
    private int gridVert = 2;
    private double fieldWidth = 221;
    private Set<Label> theLabels;
    private Set<TextField> theFields;
    private String[] firstnames;
    private Map<String, String> clientData;
    private GridPane grid;

    
    /*
    The start method is the entry point for all javaFX applications, the main
    method is included only to meet requirements 
    */
    public void start(Stage primaryStage, Scene firstScene) {
        this.firstScene = firstScene;
        primaryStage.setTitle("Personal Information");
        grid = new GridPane();
        grid.setHgap(25);
        grid.setVgap(25);
        grid.setAlignment(Pos.CENTER_RIGHT);
        
        Scene thisScene = new Scene(grid);
        
        createAutoFillFields(grid);
        
        createRemainingFields(grid);
        
        findClient(grid);
        
        autoFillClientInfo(grid);
        
        createBackButton(grid);

       // grid.setGridLinesVisible(true);
        primaryStage.setScene(thisScene);
    }

    
    private void autoFillClientInfo(GridPane grid2) {
    	Button btn = new Button("Fill Personal Information");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 3, 2);
        
        final Text actionTarget = new Text();
        grid.add(actionTarget, 2, ++gridVert);
        btn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                try {
                	System.out.println(clientSurname.getText());
                	if(clientSurname.getText().equals("")){
                		actionTarget.setFill(Color.FIREBRICK);
                		actionTarget.setText("More information Required, please enter a Surname and click 'Find Client'"
                				+ " first, then select the clients first name from the dropdown when it appears");
                	} else {
                		actionTarget.setFill(Color.BLUE);
                        actionTarget.setText("Finding Client Information");
						getClientInfo();
						fillClientInfo();
						actionTarget.setFill(null);
                	}
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					System.out.println("Error, Could Not Access Database");
					e2.printStackTrace();
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setText("Problem Accessing Database, Please check connection and retry");
				}
            }
        });
		
	}
    
	protected void fillClientInfo() {
		Iterator<TextField> fields = theFields.iterator();
		while(fields.hasNext()){//This confusing little string of methods simply finds the map key corresponding to the current field
			TextField current = ((TextField)fields.next());
			//name and fills it with the clients data
			current.setText(clientData.get("client" + current.toString()));
		}
	}


	@SuppressWarnings("unchecked")
	private Map<String, String> getClientInfo() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		GetDatabase db = MSSQLDatabase.getDatabaseConnector();
		clientData = db.getClientPersonalData(clientSurname.getText() + '/' + ((ComboBox<String>)clientFirstName).getValue());
		return clientData;
	}


	private void createRemainingFields(GridPane grid) {
    	
    	theFields = new HashSet<TextField>();
    	theLabels = new HashSet<Label>();
        
        //Client First Name
        Label firstName = new Label("First name");
        theLabels.add(firstName);
        firstName.setTextAlignment(TextAlignment.RIGHT);
        grid.add(firstName, 1, ++gridVert);
        clientFirstName = new TextField();
        clientFirstName.prefWidth(fieldWidth);
        grid.add(clientFirstName, 2, gridVert);
        
        //Client title
        Label title = new Label("Title");
        theLabels.add(title);
        grid.add(title, 1, ++gridVert);
        TextField clientTitle = new TextField();
        theFields.add(clientTitle);
        clientTitle.setPrefWidth(fieldWidth);
        grid.add(clientTitle, 2, gridVert);
        
        //DOB
        Label dob = new Label("dob");
        theLabels.add(dob);
        grid.add(dob, 1, ++gridVert);
        TextField clientDOB = new TextField();
        theFields.add(clientDOB);
        clientDOB.setPrefWidth(fieldWidth);
        grid.add(clientDOB, 2, gridVert);
        
        //NatInsure
        Label natIns = new Label("National Insurance Number");
        theLabels.add(natIns);
        grid.add(natIns, 1, ++gridVert);
        TextField clientNatIns = new TextField();
        theFields.add(clientNatIns);
        clientNatIns.setPrefWidth(fieldWidth);
        grid.add(clientNatIns, 2, gridVert);
        
        //At his point the second column is creating, resetting the height of the fields
        gridVert = 2;
        
        //Client Home Number
        Label homeNumber = new Label("Home Telephone Number");
        theLabels.add(homeNumber);
        grid.add(homeNumber, 3, ++gridVert);
        TextField clientHomeTel = new TextField();
        theFields.add(clientHomeTel);
        clientHomeTel.setPrefWidth(fieldWidth);
        grid.add(clientHomeTel, 4, gridVert);
        
        //Client Work Number
        Label workNumber = new Label("Work Telephone Number");
        theLabels.add(workNumber);
        grid.add(workNumber, 3, ++gridVert);
        TextField clientWorkTel = new TextField();
        theFields.add(clientWorkTel);
        clientWorkTel.setPrefWidth(fieldWidth);
        grid.add(clientWorkTel, 4, gridVert);
        
        //Client Mobile
        Label mobile = new Label("Mobile Number");
        theLabels.add(mobile);
        grid.add(mobile, 3, ++gridVert);
        TextField clientMobile = new TextField();
        theFields.add(clientMobile);
        clientMobile.setPrefWidth(fieldWidth);
        grid.add(clientMobile, 4, gridVert);
        
        //Client Address - possible 3 lines of input
        Label address = new Label("Address");
        theLabels.add(address);
        grid.add(address, 3, ++gridVert);
        
        //resetting a small horizontal gap between the three lines of address
        grid.setHgap(4);
        
        //line 1
        TextField clientHomeAddress1 = new TextField();
        theFields.add(clientHomeAddress1);
        clientHomeAddress1.setPrefWidth(fieldWidth);
        grid.add(clientHomeAddress1, 4, gridVert);
        //line 2
        TextField clientHomeAddress2 = new TextField();
        theFields.add(clientHomeAddress2);
        clientHomeAddress2.setPrefWidth(fieldWidth);
        grid.add(clientHomeAddress2, 4, ++gridVert);
        //line3
        TextField clientHomeAddress3 = new TextField();
        theFields.add(clientHomeAddress3);
        clientHomeAddress3.setPrefWidth(fieldWidth);
        grid.add(clientHomeAddress3, 4, ++gridVert);
        
        //resetting the Hgap to the normal level for the remaining fields
        grid.setHgap(25);
        
        //Client PostCode
        Label postcode = new Label("Postcode");
        theLabels.add(postcode);
        grid.add(postcode, 3, ++gridVert);
        TextField clientHomePostCode = new TextField();
        theFields.add(clientHomePostCode);
        clientHomePostCode.setPrefWidth(fieldWidth);
        grid.add(clientHomePostCode, 4, gridVert);
        
        //Client email
        Label email = new Label("E-mail");
        theLabels.add(email);
        grid.add(email, 3, ++gridVert);
        TextField clientEmail = new TextField();
        theFields.add(clientEmail);
        clientEmail.setPrefWidth(fieldWidth);
        grid.add(clientEmail, 4, gridVert);
		
	}


	/*
    the numbers in the grid.add() method specify the number of rows and columns
    in the the gridpane. These rows and columns are dynamically added with the
    vgap and hgap spacing that separates them.
        The area surrounding the total area covered by these rows and columns is
    specified by the padding. If no padding is specified then a default padding
    is used.
    
    When working with the gridpane you can specify that gridlines should be
    visible which is useful for debugging purposes
    */
    
    private void createAutoFillFields(GridPane grid) {
        
        Text sceneTitle = new Text("Client Info");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        sceneTitle.setId("sceneTitle");
        grid.add(sceneTitle, 1, 1, 2, 2);
        
      //Type of Application
        Label appType = new Label("Application Type");
        grid.add(appType, 1, ++gridVert);
        ObservableList<String> formTypes =
                FXCollections.observableArrayList(
                        "Single Client",
                        "Two Clients",
                        "Joint Account"
                );
            
            ComboBox<String> comboBox = 
            		new ComboBox<String>(formTypes);
            comboBox.setPrefWidth(fieldWidth);
            grid.add(comboBox, 2, gridVert);
        
        //Client Surname
        Label surname = new Label("Surname");
        grid.add(surname, 1, ++gridVert);
        clientSurname = new TextField();
        clientSurname.setPrefWidth(fieldWidth);
        grid.add(clientSurname, 2, gridVert);
    }
    
    /**
     * Takes an argument in the form of the surname of a client and searches the database for said client
     * then proceeds to autofill all the fields with the necessary client information
     * @param grid
     */
    private void findClient(GridPane grid) {
        Button btn = new Button("Find Client");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 2);
        
        final Text actionTarget = new Text();
        grid.add(actionTarget, 2, ++gridVert);
        btn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                try {
                	System.out.println(clientSurname.getText());
                	if(clientSurname.getText().equals("")){
                		actionTarget.setFill(Color.FIREBRICK);
                		actionTarget.setText("Please enter a surname to search for the client");
                	} else {
                		actionTarget.setFill(Color.BLUE);
                        actionTarget.setText("Finding Client...");
						getFirstNames();
						setFirstNameDropdown();
						actionTarget.setFill(null);
                	}
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					System.out.println("Error, Could Not Access Database");
					e2.printStackTrace();
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setText("Problem Accessing Database, Please check connection and retry");
				}
            }


        });
    }
    
    /*
     * This method reinitializes the first name field of the form to a drop down of all the first names correlating to the given
     * surname used for the detail finder
     */
	private void setFirstNameDropdown() {
		ObservableList<String> firstNames =
                FXCollections.observableArrayList(firstnames);
		clientFirstName = new ComboBox<String>(firstNames);
		((ComboBox<?>)clientFirstName).setPrefWidth(fieldWidth);
		grid.add(clientFirstName, 2, 5);
	}

    protected void getFirstNames() throws InstantiationException,
    IllegalAccessException, ClassNotFoundException, SQLException {
		GetDatabase db = MSSQLDatabase.getDatabaseConnector();
		firstnames = db.fetchInfoUsingName(clientSurname.getText());
	}

	private void createBackButton(GridPane grid) {
        Button btn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, gridVert);
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(firstScene);
            }
        });
    }
}