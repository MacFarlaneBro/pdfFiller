package ascentricGui;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.IndividualDetails;
import databaseAccess.GetDatabase;
import databaseAccess.MSSQLDatabase;
 
public class FirstApplicantIndividualDetails extends Page{
    
    private TextField clientSurname;
    private Node clientFirstName;
    private int gridVert = 2;
    private double fieldWidth = 221;
    private Set<Label> theLabels;
    private Set<TextField> theFields;
    private String[] firstnames;
    private Map<String, String> clientData;
    private Button autoFillClientButton;
	private String sceneT = "Client Personal Info";
	private ComboBox<String> applicationType;
	
	static {
		instance = new FirstApplicantIndividualDetails();
	}
	
	public static Page getInstance(){
		return instance;
	}

	/*
    The start method is the entry point for all javaFX applications, the main
    method is included only to meet requirements 
    */
    public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client) {
    	this.client = client;
        this.previousScene = firstScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Personal Information - Client");
        grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        
        thisScene = new Scene(grid, pageWidth, pageHeight);
        
        setColumnSizes(grid, 100, 250, 100, 150, 120);
        setRowSizes(grid);
        
        createAutoFillFields(grid);
   
        createRemainingFields(grid);
        
        findClient(grid);
        
        autoFillClientInfo(grid);
        
        createMovementButtons(12, 5);

        primaryStage.setScene(thisScene);
        
        try {
			getClientInfo();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
    }

    
    private void autoFillClientInfo(GridPane grid) {
    	autoFillClientButton = new Button("Fill Personal Information");//Create button with the name sign in
    	autoFillClientButton.setVisible(false);
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(autoFillClientButton);
        grid.add(hbBtn, 3, 2);
        
        final Text actionTarget = new Text();
        grid.add(actionTarget, 2, ++gridVert);
        autoFillClientButton.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                try {
                	System.out.println(clientSurname.getText());
                	if(clientSurname.getText().equals("")){
                		actionTarget.setFill(Color.FIREBRICK);
                		actionTarget.setText("More information Required, please\n enter a Surname and click 'Find Client\n'"
                				+ " first, then select the clients first\n name from the dropdown when it appears");
                	} else {
                		actionTarget.setFill(Color.BLUE);
                        actionTarget.setText("Finding Client Information");
                         
						getClientInfo();
						fillClientInfo();
						actionTarget.setFill(null);
                	}
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e2) {
					System.out.println("Error, Could Not Access Database");
					e2.printStackTrace();
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setText("Problem Accessing Database, Please check connection and retry");
				}
            }
        });
	}
    
	private void fillClientInfo() {
		Iterator<TextField> fields = theFields.iterator();
		String holder;
		int homeAddress = 1;
		
		while(fields.hasNext()){
			TextField current = fields.next();
			
			//Remove the time portion from the date of birth value
			if(current.getId()
					.equals("DOB")){
				holder = clientData.get(current.getId());
				String formatted = holder.replace("-", "/");
				current.setText(formatted);
				continue;
			}
			
			//As the address data is not stored in a coherent fashion in the database, this conditional branch sorts it
			//into the three fields necessary for the form
			if(current.getId().startsWith("HomeAddress")){
				for(int i = 0; i < 5; i++){
					if(clientData.get("HomeAddress" + homeAddress) != null){
						current.setText(clientData.get("HomeAddress" + homeAddress));
						homeAddress++;
						break;
					} else {
						homeAddress++;
					}
				}
			}
			current.setText(clientData.get(current.getId()));
		}
	}
	

	@SuppressWarnings("unchecked")
	private Map<String, String> getClientInfo() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		GetDatabase db = MSSQLDatabase.getDatabaseConnector();
		try{
			clientData = db.getClientPersonalData(clientSurname.getText() + '/' + ((ComboBox<String>)clientFirstName).getValue());
		} catch (ClassCastException ex){
			System.out.println("This is complaining because of the cast but really what I'm doing is fine. I think.");
		}
		return clientData;
	}

	/**
	 * Taking the basic grid of the display pane as an argument, this method initialises all the remaining
	 * fields of the first page of the form including the field labels and sets the ID values for each which
	 * allow them to be identified by the fillClientInfo method for autofilling purposes
	 * @param grid
	 */
	private void createRemainingFields(GridPane grid) {
    	
    	theFields = new HashSet<TextField>();
    	theLabels = new HashSet<Label>();
        
        //Client First Name
        Label firstName = new Label("First name");
        theLabels.add(firstName);
        firstName.setTextAlignment(TextAlignment.RIGHT);
        grid.add(firstName, 1, ++gridVert);
        clientFirstName = new TextField();
        clientFirstName.setId("FirstName");
        clientFirstName.prefWidth(fieldWidth);
        grid.add(clientFirstName, 2, gridVert);
        
        //Client title
        Label title = new Label("Title");
        theLabels.add(title);
        grid.add(title, 1, ++gridVert);
        TextField clientTitle = new TextField();
        clientTitle.setId("Title");
        theFields.add(clientTitle);
        clientTitle.setPrefWidth(fieldWidth);
        grid.add(clientTitle, 2, gridVert);
        
        //DOB
        Label dob = new Label("DOB (DD/MM/YYYY)");
        theLabels.add(dob);
        grid.add(dob, 1, ++gridVert);
        TextField clientDOB = new TextField();
        clientDOB.setId("DOB");
        theFields.add(clientDOB);
        clientDOB.setPrefWidth(fieldWidth);
        grid.add(clientDOB, 2, gridVert);
        
        //NatInsure
        Label natIns = new Label("National Insurance Number");
        theLabels.add(natIns);
        grid.add(natIns, 1, ++gridVert);
        TextField clientNatIns = new TextField();
        clientNatIns.setId("NationalInsuranceNumber");
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
        clientHomeTel.setId("HomeTel");
        theFields.add(clientHomeTel);
        clientHomeTel.setPrefWidth(fieldWidth);
        grid.add(clientHomeTel, 4, gridVert);
        
        //Client Work Number
        Label workNumber = new Label("Work Telephone Number");
        theLabels.add(workNumber);
        grid.add(workNumber, 3, ++gridVert);
        TextField clientWorkTel = new TextField();
        clientWorkTel.setId("WorkTel");
        theFields.add(clientWorkTel);
        clientWorkTel.setPrefWidth(fieldWidth);
        grid.add(clientWorkTel, 4, gridVert);
        
        //Client Mobile
        Label mobile = new Label("Mobile Number");
        theLabels.add(mobile);
        grid.add(mobile, 3, ++gridVert);
        TextField clientMobile = new TextField();
        clientMobile.setId("Mobile");
        theFields.add(clientMobile);
        clientMobile.setPrefWidth(fieldWidth);
        grid.add(clientMobile, 4, gridVert);
        
        //Client Address - possible 3 lines of input
        Label address = new Label("Address");
        theLabels.add(address);
        grid.add(address, 3, ++gridVert);
        
        //line 1
        TextField clientHomeAddress1 = new TextField();
        clientHomeAddress1.setId("HomeAddress1");
        theFields.add(clientHomeAddress1);
        clientHomeAddress1.setPrefWidth(fieldWidth);
        grid.add(clientHomeAddress1, 4, gridVert);
        //line 2
        TextField clientHomeAddress2 = new TextField();
        theFields.add(clientHomeAddress2);
        clientHomeAddress2.setId("HomeAddress2");
        clientHomeAddress2.setPrefWidth(fieldWidth);
        grid.add(clientHomeAddress2, 4, ++gridVert);
        //line3
        TextField clientHomeAddress3 = new TextField();
        theFields.add(clientHomeAddress3);
        clientHomeAddress3.setId("HomeAddress3");
        clientHomeAddress3.setPrefWidth(fieldWidth);
        grid.add(clientHomeAddress3, 4, ++gridVert);
        
        //Client PostCode
        Label postcode = new Label("Postcode");
        theLabels.add(postcode);
        grid.add(postcode, 3, ++gridVert);
        TextField clientHomePostCode = new TextField();
        clientHomePostCode.setId("HomePostCode");
        theFields.add(clientHomePostCode);
        clientHomePostCode.setPrefWidth(fieldWidth);
        grid.add(clientHomePostCode, 4, gridVert);
        
        //Client email
        Label email = new Label("E-mail");
        theLabels.add(email);
        grid.add(email, 3, ++gridVert);
        TextField clientEmail = new TextField();
        clientEmail.setId("Email");
        theFields.add(clientEmail);
        clientEmail.setPrefWidth(fieldWidth);
        grid.add(clientEmail, 4, gridVert);
        
        //Does the client have a national insurance number?
        Label natInsTick = new Label("Tick if client has no national Insurance number");
        theLabels.add(email);
        grid.add(natInsTick, 1, 9);
        CheckBox natInsTickClient = new CheckBox();
        natInsTickClient.setId("natInsTickClient");
        clientEmail.setPrefWidth(fieldWidth);
        grid.add(natInsTickClient, 2, 9);
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
        
        Text sceneTitle = new Text(sceneT);
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
            
            applicationType = new ComboBox<String>(formTypes);
            setNextPage(applicationType);
            applicationType.setPrefWidth(fieldWidth);
            applicationType.setPromptText("Single Client");
            applicationType.setValue("Single Client");
            grid.add(applicationType, 2, gridVert);
        
            //Client Surname
            Label surname = new Label("Surname");
            grid.add(surname, 1, ++gridVert);
            clientSurname = new TextField();
            clientSurname.setPrefWidth(fieldWidth);
            grid.add(clientSurname, 2, gridVert);
    }

	private void setNextPage(ComboBox<String> comboBox) {
		 comboBox.valueProperty().addListener(new ChangeListener<String>() {
	            @Override public void changed(ObservableValue ov, String t, String t1) {                
	               if(t1.equals("Single Client")){
	            	   nextPage = AccessRightsFamilyGroups.getInstance();
	               } else {
	            	   nextPage = SecondApplicantIndividualDetails.getInstance();
	               }
	            }    
	        });
		
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
                		actionTarget.setText("Please enter a surname to search for client");
                	} else {
                		actionTarget.setFill(Color.BLUE);
                        actionTarget.setText("Finding Client...");
                        autoFillClientButton.setVisible(true);
						getFirstNames();
						setFirstNameDropdown();
						actionTarget.setFill(null);
                	}
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e2) {
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

    private void getFirstNames() throws InstantiationException,
    IllegalAccessException, ClassNotFoundException, SQLException {
		GetDatabase db = MSSQLDatabase.getDatabaseConnector();
		firstnames = db.fetchInfoUsingName(clientSurname.getText());
	}


	@SuppressWarnings("unchecked")
	protected void fillAndSaveClientInfo() {
		client.makeNewFirstClient();
		
		client.getFirstClient().setApplicationType(applicationType.getValue());
		
		IndividualDetails id = client.getFirstClient().getIndividualDetails();
		
		id.setSurname(clientSurname.getText());
		if(!clientSurname.getText().equals("")){
			id.setForename(((ComboBox<String>)clientFirstName).getValue());
		id.setTitle(clientData.get("Title"));
		
		//Rearranges date of birth from DB from YYYY-MM-DD to DDMMYYYY
		if(clientData.get("DOB").charAt(4) == '-'){
			//Using stringbuilder to increase performance
			String original = clientData.get("DOB");
			StringBuilder dob = new StringBuilder();
			//Day
			dob.append(original.charAt(original.length()-2));
			dob.append(original.charAt(original.length()-1));
			//Month
			dob.append(original.charAt(original.length()-5));
			dob.append(original.charAt(original.length()-4));
			//Year
			dob.append(original.substring(0, 4));
			id.setDob(dob.toString());
		} else {
			id.setDob(clientData.get("DOB").replace("/", ""));
		}
		
		System.out.println(client.getFirstClient().getIndividualDetails().getDob());
		id.setNationalInsuranceNumber(clientData.get("NationalInsuranceNumber").replace("-", ""));
		id.setHomeNumber(clientData.get("HomeTel"));
		id.setWorkNumber(clientData.get("WorkTel"));
		id.setMobileNumber(clientData.get("MobTel"));
		//address lines separated by : to provide a string split character at stamp time
		id.setAddress(clientData.get("HomeAddress1") + ":"
		+ clientData.get("HomeAddress2") + ":" 
				+ clientData.get("HomeAddress3"));
		id.setPostcode(clientData.get("HomePostCode"));
		id.setEmail(clientData.get("Email"));
		}
	}



}