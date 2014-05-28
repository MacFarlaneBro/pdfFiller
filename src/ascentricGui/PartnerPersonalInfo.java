package ascentricGui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.DataFormatException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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

public class PartnerPersonalInfo extends Page{
	
	public static final PartnerPersonalInfo INSTANCE = new PartnerPersonalInfo();
    private TextField clientSurname;
    private Node clientFirstName;
    private int gridVert = 2;
    private double fieldWidth = 221;
    private Set<Label> theLabels;
    private Set<TextField> theFields;
    private String[] firstnames;
    private Map<String, String> clientData;
    private Map<String, TextField> fieldMap;
    private Button autoFillClientButton;
	private String sceneT = "Partner Personal Info";
	private CheckBox natInsTickClient;
	private CheckBox correspondenceAddress;
	
	private PartnerPersonalInfo(){}
	
	public void setUp(Stage primaryStage, Scene previousScene, ClientHolder client) {
		this.client = client;
        this.previousScene = previousScene;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Personal Information - Partner");
        
        grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);
        
        thisScene = new Scene(grid, PAGEWIDTH, PAGEHEIGHT);
        
        nextPage = AccessRightsFamilyGroups.INSTANCE;
        		
        createAutoFillFields(grid);
        
        createRemainingFields(grid);
        
        findClient(grid);
        
        autoFillClientInfo(grid);
        
        setColumnSizes(grid, 15, 200, 200, 200, 200);
        setRowSizes(grid);
        
        movementButtons2Columns(true);
        createMovementButtons(13, 5);

        primaryStage.setScene(thisScene);
        
        /*
         * As the details of the partner are often present in the clients file this try/catch block attempts to 
         * autofill the partner personal information fields as soon as the page is loaded
         */
		try {
				getClientInfo();
				fillClientInfo();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

    private void autoFillClientInfo(GridPane grid) {
    	autoFillClientButton = new Button("Fill Personal Information");//Create button with the name sign in
    	autoFillClientButton.setVisible(false);
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(autoFillClientButton);
        grid.add(hbBtn, 3, 1);
        
        final Text actionTarget = new Text();
        grid.add(actionTarget, 2, ++gridVert);
        autoFillClientButton.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                try {
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
		
		while(fields.hasNext()){
			TextField current = (TextField)fields.next();
			//Remove the time portion from the date of birth value
			if(current.getId()
					.equals("PartnerDOB") && current.getId()!= null){
				holder = clientData.get(current.getId());
				String formatted = holder.replace("-", "/");
				current.setText(formatted);
				continue;
			}
			
			//As the address data is stored in a possible 5 fields in the database, this conditional branch sorts it
			//into the two fields necessary for the form
			if(current.getId().equals("HomeAddress1")){
				current.setText(clientData.get("PartnerAddress1"));
			}
			if(current.getId().equals("HomeAddress2")){
				if(clientData.get("PartnerAddress2")!= null){
					current.setText(clientData.get("PartnerAddress2"));
				} else {
					current.setText(clientData.get("PartnerAddress3"));
				}
			}
			current.setText(clientData.get(current.getId()));
		}
	}

	private Map<String, String> getClientInfo() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		GetDatabase db = MSSQLDatabase.getDatabaseConnector();
		clientData = db.getPartnerPersonalData(client
				.getFirstClient()
				.getIndividualDetails()
				.getSurname() 
				+ '/' 
				+ client
				.getFirstClient()
				.getIndividualDetails()
				.getForename());
		return clientData;
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
        GridPane.setValignment(sceneTitle, VPos.CENTER);
        grid.add(sceneTitle, 1, 0, 2, 2);
        
        gridVert = 2;
        
        //Client Surname
        Label surname = new Label("Surname");
        grid.add(surname, 1, ++gridVert);
        clientSurname = new TextField();
        clientSurname.setId("Surname");
        clientSurname.setPrefWidth(fieldWidth);
        grid.add(clientSurname, 2, gridVert);
                
        checkBoxes = new HashMap<String, CheckBox>();
        
        CheckBox sameDetails = new CheckBox("If your contact details (including e-mail address)"
        		+ " are the same as the first applicant, please tick this box.");
        sameDetails.setWrapText(true);
        sameDetails.setTranslateX(50);
        checkBoxes.put("sameDetails", sameDetails);
        GridPane.setHalignment(sameDetails, HPos.CENTER);
        GridPane.setValignment(sameDetails, VPos.CENTER);
        grid.add(sameDetails, 2, 0, 2, 2);
        
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
                	if(clientSurname.getText().equals("")|| clientSurname.getText() == null){
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
	@SuppressWarnings("unchecked")
	private void setFirstNameDropdown() {
		ObservableList<String> firstNames =
                FXCollections.observableArrayList(firstnames);
		clientFirstName = new ComboBox<String>(firstNames);
		((ComboBox<String>)clientFirstName).setPrefWidth(fieldWidth);
		((ComboBox<String>)clientFirstName).setEditable(true);
		grid.add(clientFirstName, 2, 4);
	}

    private void getFirstNames() throws InstantiationException,
    IllegalAccessException, ClassNotFoundException, SQLException {
		GetDatabase db = MSSQLDatabase.getDatabaseConnector();
		firstnames = db.fetchInfoUsingName(clientSurname.getText());
	}

	protected void fillAndSaveClientInfo() throws DataFormatException {
		
		if(!natInsTickClient.isSelected()){
			
			String nas = null;
			
			//If client has not been declared as having no NI number and the field isn't null
			//then prepare the input for a validity check
			if(fieldMap.get("NationalInsuranceNumber").getText() != null 
					&& !fieldMap.get("NationalInsuranceNumber").getText().equals("")){
				nas = fieldMap.get("NationalInsuranceNumber").getText().replace("-", "");
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
		
		client.makeNewSecondClient();
		
		//Putting all form data in a new map, otherwise only auto-filled fields are included
		for(TextField field: theFields){
			fieldMap.put(field.getId(), field);
		}
		
		IndividualDetails id = client.getSecondClient().getIndividualDetails();
		id.setSurname(fieldMap.get("Surname").getText());
		id.setForename(fieldMap.get("ForeNames").getText());
		id.setTitle(fieldMap.get("Title").getText());
		id.setDob(fieldMap.get("DOB").getText());
		
		
		if(correspondenceAddress.isSelected()){
			id.setSameCorrDetails(true);
				Page nextNextPage = nextPage;
				nextPage = CorrespondenceAddress.INSTANCE;
				nextPage.setNextPage(nextNextPage);
		}
		
		//Rearranges date of birth from DB from YYYY-MM-DD to DDMMYYYY
		if(fieldMap.get("DOB").getText() != null
				&& fieldMap.get("DOB").getText().length() > 3
				&& fieldMap.get("DOB").getText().charAt(4) == '-'){
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
		} else if(fieldMap.get("DOB").getText() != null
				&& fieldMap.get("DOB").getText().length() > 3) {
			id.setDob(clientData.get("DOB").replace("/", ""));
		}
		
		if(fieldMap.get("NationalInsuranceNumber").getText() != null){
			id.setNationalInsuranceNumber(fieldMap.get("NationalInsuranceNumber").getText().replaceAll("-", ""));
		}
		
		if(checkBoxes.get("sameDetails").isSelected()){
			IndividualDetails firstID = client.getFirstClient().getIndividualDetails();
			id.setHomeNumber(firstID.getHomeNumber());
			id.setWorkNumber(firstID.getWorkNumber());
			id.setMobileNumber(firstID.getMobileNumber());
			id.setAddress(firstID.getAddress());
			id.setPostcode(firstID.getPostcode());
			id.setEmail(firstID.getEmail());
			id.setSameDetails(true);
		} else {
			id.setHomeNumber(clientData.get("HomeTel"));
			id.setWorkNumber(clientData.get("WorkTel"));
			id.setMobileNumber(clientData.get("Mobile"));
			id.setAddress(clientData.get("HomeAddress1") + ":"
			+ clientData.get("HomeAddress2") + ":" 
					+ clientData.get("HomeAddress3"));
			id.setPostcode(clientData.get("HomePostCode"));
			id.setEmail(clientData.get("Email"));
		}
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
        fieldMap = new HashMap<String, TextField>();
    	
        //Resetting gridVert to 2 here should stop backwards and forwards navigation from moving the fields up and down
        gridVert = 3;
        
        theFields.add(clientSurname);
 	
        //Client First Name
        Label firstName = new Label("First name");
        theLabels.add(firstName);
        firstName.setTextAlignment(TextAlignment.RIGHT);
        grid.add(firstName, 1, ++gridVert);
        clientFirstName = new TextField();
        clientFirstName.setId("ForeNames");
        clientFirstName.prefWidth(fieldWidth);
        grid.add(clientFirstName, 2, gridVert);
        theFields.add((TextField)clientFirstName);
        
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
        fieldMap.put("NationalInsuranceNumber", clientNatIns);
        clientNatIns.setPrefWidth(fieldWidth);
        grid.add(clientNatIns, 2, gridVert);
        
        //At his point the second column is creating, resetting the height of the fields
        gridVert = 2;
        
        //Client Home Number
        Label homeNumber = new Label("Home Telephone Number");
        theLabels.add(homeNumber);
        grid.add(homeNumber, 3, gridVert);
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
        grid.add(clientHomeAddress1, 4, gridVert++);
        //line 2
        TextField clientHomeAddress2 = new TextField();
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
        Label natInsTick = new Label("Tick if client has no National Insurance Number:");
        theLabels.add(email);
        grid.add(natInsTick, 1, 9, 2, 1);
        natInsTickClient = new CheckBox();
        GridPane.setHalignment(natInsTickClient, HPos.CENTER);
        natInsTickClient.setId("natInsTickClient");
        clientEmail.setPrefWidth(fieldWidth);
        grid.add(natInsTickClient, 2, 9);
	}
	
}
