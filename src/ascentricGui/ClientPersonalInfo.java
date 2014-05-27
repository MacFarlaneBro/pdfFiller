package ascentricGui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.DataFormatException;

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
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utilities.AutoCompleteComboBoxListener;
import ascentricClientDetails.ClientHolder;
import ascentricClientDetails.IndividualDetails;
import databaseAccess.GetDatabase;
import databaseAccess.MSSQLDatabase;
 
public class ClientPersonalInfo extends Page{
    
	public static final ClientPersonalInfo INSTANCE = new ClientPersonalInfo();
    private TextField clientSurname;
    private Node clientFirstName;
    private Node postcode;
    private int gridVert = 2;
    private double fieldWidth = 221;
    private Set<Label> theLabels;
    private Set<TextField> theFields;
    private String[] firstnames;
    private Map<String, String> clientData;
    private Map<String, TextField> fieldMap;
    private Button autoFillClientButton;
	private ComboBox<String> applicationType;
	private CheckBox natInsTickClient;
	private CheckBox facetoface;
	private Text actionTarget;
    private boolean duplicateFirstNames = false;
	private CheckBox correspondenceAddress;
	
	private ClientPersonalInfo(){}

	/*
    The start method is the entry point for all javaFX applications, the main
    method is included only to meet requirements 
    */
    public void setUp(Stage primaryStage, Scene firstScene, ClientHolder client) {
    	this.client = client;
        this.previousScene = firstScene;
        this.primaryStage = primaryStage;
        grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
                
        thisScene = new Scene(grid, PAGEWIDTH, PAGEHEIGHT);
        
        setColumnSizes(grid, 20, 250, 175, 190, 170, 20);
        setRowSizes(grid, 35, 35);
                
    	fieldMap = new HashMap<String, TextField>();
    	        
        createAutoFillFields(grid);
   
        createRemainingFields(grid);
        
        findClient(grid);
        
        autoFillClientInfo(grid);
        
        movementButtons2Columns(true);
        createMovementButtons(12, 5);
        
        createClearButton();
        
        setTitle(grid, "Client Personal Info", primaryStage);
        
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
        
        gridVert = 2;
        
        autoFillClientButton.setOnAction(new EventHandler<ActionEvent>(){
            
			@SuppressWarnings("unchecked")
			@Override
            public void handle(ActionEvent e){
                try {
                	if(duplicateFirstNames){
                		getClientInfoWithPostCode();
						fillClientInfo();
						actionTarget.setVisible(false);
                	} else {
	                	System.out.println(clientSurname.getText());
	                	if(clientSurname.getText().equals("")){
	                		actionTarget.setFill(Color.FIREBRICK);
	                		actionTarget.setText("More information Required, please\n enter a Surname and click 'Search Surname\n'"
	                				+ " first, then select the clients first\n name from the dropdown when it appears");
	                	} else if(((ComboBox<String>)clientFirstName).getValue()==null) {
	        				actionTarget.setText("A firstname and surname are required to search for a client, please select"
	        						+ "a first\nname from the dropdown or click 'clear fields' to enter the details manually.");
	                	} else {
	                		actionTarget.setFill(Color.BLUE);
	                        actionTarget.setText("Finding Client Information");
	                        Map<String,String> info = getClientInfo();
	                        if(info.get("client1") == null){
								fillClientInfo();
								actionTarget.setVisible(false);
	                        } else {
	                        	duplicateFirstNames = true;
	                        	actionTarget.setText("Multiple Clients with this name have been found, please select"
	                        			+ " the\nrequired clients postcode from the dropdown then press find\nclient again.");
	                        	actionTarget.setVisible(true);
	                        	setPostCode(info);
	                        }
	                	}
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
    
	private void setPostCode(Map<String, String> info) {
		ObservableList<String> postCodes =
                FXCollections.observableArrayList(info.values());
		postcode = new ComboBox<String>(postCodes);
		((ComboBox<?>)postcode).setPrefWidth(fieldWidth);
		grid.add(postcode, 4, 9);
		
	}

	private void fillClientInfo() {
		String holder;
		int homeAddress = 1;
		
		//Use of a for loop prevents premature declaration
		for(Iterator<TextField> fields = theFields.iterator(); fields.hasNext(); ){
			TextField current = fields.next();
			
			//Remove the time portion from the date of birth value
			if(current.getId()
					.equals("DOB")){
				holder = clientData.get(current.getId());
				current.setText(holder);
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
			System.out.println(current.getId());
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
	
	@SuppressWarnings("unchecked")
	private Map<String, String> getClientInfoWithPostCode() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		GetDatabase db = MSSQLDatabase.getDatabaseConnector();
		try{
			clientData = db.getClientPersonalData(clientSurname.getText() 
					+ '/' 
					+ ((ComboBox<String>)clientFirstName).getValue(),
					((ComboBox<String>)postcode).getValue());
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
    	
    	facetoface = new CheckBox("Tick here if you have confirmed the clients identity\nin a face"
    			+ " to face meeting");
    	grid.add(facetoface, 1, 2, 2, 1);
        
        //Client First Name
        Label firstName = new Label("First name");
        theLabels.add(firstName);
        firstName.setTextAlignment(TextAlignment.RIGHT);
        grid.add(firstName, 1, ++gridVert);
        clientFirstName = new TextField();
        fieldMap.put("FirstName",(TextField) clientFirstName);
        clientFirstName.setId("FirstName");
        clientFirstName.prefWidth(fieldWidth);
        grid.add(clientFirstName, 2, gridVert);
        
        //Client title
        Label title = new Label("Title");
        theLabels.add(title);
        grid.add(title, 1, ++gridVert);
        TextField clientTitle = new TextField();
        fieldMap.put("Title", clientTitle);
        clientTitle.setId("Title");
        theFields.add(clientTitle);
        clientTitle.setPrefWidth(fieldWidth);
        grid.add(clientTitle, 2, gridVert);
        
        //DOB
        Label dob = new Label("DOB (DD/MM/YYYY)");
        theLabels.add(dob);
        grid.add(dob, 1, ++gridVert);
        TextField clientDOB = new TextField();
        fieldMap.put("DOB", clientDOB);
        clientDOB.setId("DOB");
        theFields.add(clientDOB);
        clientDOB.setPrefWidth(fieldWidth);
        grid.add(clientDOB, 2, gridVert);
        
        //NatInsure
        Label natIns = new Label("National Insurance Number");
        theLabels.add(natIns);
        grid.add(natIns, 1, ++gridVert);
        TextField clientNatIns = new TextField();
        fieldMap.put("nas", clientNatIns);
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
        fieldMap.put("HomeTel", clientHomeTel);
        clientHomeTel.setId("HomeTel");
        theFields.add(clientHomeTel);
        clientHomeTel.setPrefWidth(fieldWidth);
        grid.add(clientHomeTel, 4, gridVert);
        
        //Client Work Number
        Label workNumber = new Label("Work Telephone Number");
        theLabels.add(workNumber);
        grid.add(workNumber, 3, ++gridVert);
        TextField clientWorkTel = new TextField();
        fieldMap.put("WorkTel", clientWorkTel);
        clientWorkTel.setId("WorkTel");
        theFields.add(clientWorkTel);
        clientWorkTel.setPrefWidth(fieldWidth);
        grid.add(clientWorkTel, 4, gridVert);
        
        //Client Mobile
        Label mobile = new Label("Mobile Number");
        theLabels.add(mobile);
        grid.add(mobile, 3, ++gridVert);
        TextField clientMobile = new TextField();
        fieldMap.put("Mobile", clientMobile);
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
        fieldMap.put("HomeAddress1", clientHomeAddress1);
        clientHomeAddress1.setId("HomeAddress1");
        theFields.add(clientHomeAddress1);
        clientHomeAddress1.setPrefWidth(fieldWidth);
        grid.add(clientHomeAddress1, 4, gridVert++);
        //line 2
        TextField clientHomeAddress2 = new TextField();
        fieldMap.put("HomeAddress2", clientHomeAddress2);
        theFields.add(clientHomeAddress2);
        clientHomeAddress2.setId("HomeAddress2");
        clientHomeAddress2.setPrefWidth(fieldWidth);
        grid.add(clientHomeAddress2, 4, gridVert++);
        
        //Correspondence Address Check box
    	correspondenceAddress = new CheckBox("Tick here if the clients the clients correspondence address differs"
    			+ " from that given above");
    	correspondenceAddress.setDisable(false);
    	correspondenceAddress.setWrapText(true);
    	grid.add(correspondenceAddress, 3, gridVert++, 2, 1);
        
        //Client PostCode
        Label postcode = new Label("Postcode");
        theLabels.add(postcode);
        grid.add(postcode, 3, gridVert);
        TextField clientHomePostCode = new TextField();
        fieldMap.put("HomePostCode", clientHomePostCode);
        clientHomePostCode.setId("HomePostCode");
        theFields.add(clientHomePostCode);
        clientHomePostCode.setPrefWidth(fieldWidth);
        grid.add(clientHomePostCode, 4, gridVert);
        
        //Client email
        Label email = new Label("E-mail");
        theLabels.add(email);
        grid.add(email, 3, ++gridVert);
        TextField clientEmail = new TextField();
        fieldMap.put("Email", clientEmail);
        clientEmail.setId("Email");
        theFields.add(clientEmail);
        clientEmail.setPrefWidth(fieldWidth);
        grid.add(clientEmail, 4, gridVert);
        
        //Does the client have a national insurance number?
        Label natInsTick = new Label("Tick if client has no National Insurance number");
        theLabels.add(email);
        grid.add(natInsTick, 1, 9);
        natInsTickClient = new CheckBox();
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
        fieldMap.put("Surname", clientSurname);
        grid.add(clientSurname, 2, gridVert);
    }

	private void setNextPage(ComboBox<String> comboBox) {
		comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> ov, String t, String t1) { 
                if(t1.equals("Single Client")){
            	    nextPage = AccessRightsFamilyGroups.INSTANCE;
                } else {
            	    nextPage = PartnerPersonalInfo.INSTANCE;
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
        Button btn = new Button("Search Surname");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 2);
        
        gridVert++;
        
        actionTarget = new Text();
        grid.add(actionTarget, 1, 10);
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
						actionTarget.setVisible(false);
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
	@SuppressWarnings("unchecked")
	private void setFirstNameDropdown() {
		ObservableList<String> firstNames =
                FXCollections.observableArrayList(firstnames);
		FXCollections.sort(firstNames);
		clientFirstName = new ComboBox<String>(firstNames);
		((ComboBoxBase<String>)clientFirstName).setEditable(true);
		((ComboBox<String>)clientFirstName).setPrefWidth(fieldWidth);
        new AutoCompleteComboBoxListener<>(((ComboBox<String>)clientFirstName));

		grid.add(clientFirstName, 2, 5);
	}

    private void getFirstNames() throws InstantiationException,
    IllegalAccessException, ClassNotFoundException, SQLException {
		GetDatabase db = MSSQLDatabase.getDatabaseConnector();
		firstnames = db.fetchInfoUsingName(clientSurname.getText());
	}
    
	@SuppressWarnings("unchecked")
	protected void fillAndSaveClientInfo() throws DataFormatException{
		IndividualDetails id = client.getFirstClient().getIndividualDetails();
		if(correspondenceAddress.isSelected()){
			id.setSameCorrDetails(true);
			if(applicationType.getValue().equals("Single Client")){
				Page nextNextPage = nextPage;
				nextPage = CorrespondenceAddress.INSTANCE;
				nextPage.setNextPage(nextNextPage);
			}
		}
				
		if(!natInsTickClient.isSelected()){
						
			//This hideous lump is where I make sure that the NI number is accounted for
			String nas = null;
			//If client has not been declared as having no NI number and the field isn't null then check the input for validity
			if(fieldMap.get("nas").getText() != null && !fieldMap.get("nas").getText().equals("")){
				nas = fieldMap.get("nas").getText().replace("-", "");
			}
			
			//If the field is null
    		if(nas == null || nas.equals("")){
    			throw new DataFormatException("Warning! No national insurance number has been entered!\n"
    							+ "If the client has no NI number please tick the appropriate\nbox before"
    							+ " continuing.\n");
    		} else if(//if the field is invalid
    				nas.length()!= 9//if the number isn't 9 characters
				|| (!Character.isAlphabetic(nas.charAt(0))//if the first
				&& !Character.isAlphabetic(nas.charAt(1)))//second
				|| (!Character.isAlphabetic(nas.charAt(8))))//or ninth characters aren't letters
				{
    			throw new DataFormatException("The NI number entered: " 
				+ nas 
				+ " is incorrectly formatted, please re-enter it and try again.\n");	
    		}
		}
		
		client.getFirstClient().getfinancialAdviserDetails().setFaceToFaceContact(facetoface.isSelected());
		client.getFirstClient().setApplicationType(applicationType.getValue());
		
		if(applicationType.getValue().equals("Joint Account")){
			client.makeNewJointClient();
		};
		System.out.print("I made a new " + applicationType.getValue());
		
		id.setSurname(clientSurname.getText());
				
		//Checking whether clientfirstname is in combobox or textfield name before adding it to the db
		if(clientFirstName.getClass().equals(ComboBox.class)){
			id.setForename(((ComboBox<String>)clientFirstName).getValue());
		} else {
			id.setForename(((TextField)clientFirstName).getText());
		}
		
		id.setTitle(fieldMap.get("Title").getText());
		
		if(fieldMap.get("DOB").getText()!= null && fieldMap.get("DOB").getText().length()>4){
			//Rearranges date of birth from DB from YYYY-MM-DD to DDMMYYYY
			if(fieldMap.get("DOB").getText().charAt(4) == '-'){
				//Using string builder to increase performance
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
				id.setDob(fieldMap.get("DOB").getText().replace("/", ""));
			}
		}
		
		if(fieldMap.get("nas").getText()!= null){
			id.setNationalInsuranceNumber(fieldMap.get("nas").getText().replace("-", ""));
		}
		
		id.setHomeNumber(fieldMap.get("HomeTel").getText());
		id.setWorkNumber(fieldMap.get("WorkTel").getText());
		id.setMobileNumber(fieldMap.get("Mobile").getText());
		
		//address lines separated by ':' to provide a string split character at stamp time
		id.setAddress(fieldMap.get("HomeAddress1").getText() + ":"
		+ fieldMap.get("HomeAddress2").getText());
		id.setPostcode(fieldMap.get("HomePostCode").getText());
		id.setEmail(fieldMap.get("Email").getText());
	}
	
	public void createClearButton(){
		Button clear = new Button("Clear Details");
		grid.add(clear, 4, 1);
		clear.setOnAction(new EventHandler<ActionEvent>(){
				
				public void handle(ActionEvent e){
					duplicateFirstNames = false;
					setUp(primaryStage, previousScene, client);
				}
		});
	}
}