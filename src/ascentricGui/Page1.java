package ascentricGui;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import databaseAccess.GetDatabase;
import databaseAccess.MSSQLDatabase;
 
public class Page1{
    
    private Stage primaryStage;
    private Scene firstScene;
    private TextField clientSurname;
    private TextField clientFirstName;
    
    /*
    The start method is the entry point for all javaFX applications, the main
    method is included only to meet requirements 
    */
    public void start(Stage primaryStage, Scene firstScene) {
        this.firstScene = firstScene;
        primaryStage.setTitle("PDF Filler 0.01");
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        
        Scene thisScene = new Scene(grid);
                
        createFormFields(grid);
        
        autoFillClientInfo(grid);
        
        createBackButton(grid);

//        grid.setGridLinesVisible(true);
        primaryStage.setScene(thisScene);
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
    
    private void createFormFields(GridPane grid) {
        double fieldWidth = 120;
        Text sceneTitle = new Text("Client Info");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 20));
        sceneTitle.setId("sceneTitle");
        grid.add(sceneTitle, 0, 0, 2, 1);
        
        //Client Surname
        Label surname = new Label("Surname");
        grid.add(surname, 0, 2);
        clientSurname = new TextField();
        clientSurname.setPrefWidth(fieldWidth);
        grid.add(clientSurname, 1, 2);
        
        //Client First Name
        Label firstName = new Label("First name");
        grid.add(firstName, 0, 3);
        clientFirstName = new TextField();
        clientFirstName.setPrefWidth(fieldWidth);
        grid.add(clientFirstName, 1, 3);
        
        //Client PostCode
        Label postcode = new Label("Postcode");
        grid.add(postcode, 0, 4);
        TextField clientPostcode = new TextField();
        clientPostcode.setPrefWidth(fieldWidth);
        grid.add(clientPostcode, 1, 4);
        
        //Client Telephone Number
        Label telNumber = new Label("Telephone Number");
        grid.add(telNumber, 0, 5);
        TextField clientTelNumber = new TextField();
        clientTelNumber.setPrefWidth(fieldWidth);
        grid.add(clientTelNumber, 1, 5);
    }

    private void autoFillClientInfo(GridPane grid) {
        Button btn = new Button("Fill Personal Info");//Create button with the name sign in
        HBox hbBtn = new HBox(10);//Layout pane with 10 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 2);
        
        final Text actionTarget = new Text();
        grid.add(actionTarget, 2, 7);
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
						accessDatabase();
						actionTarget.setFill(null);
                	}
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error, Could Not Access Database");
					e1.printStackTrace();
					actionTarget.setFill(Color.FIREBRICK);
					actionTarget.setText("Problem Accessing Database, Please check connection and retry");
				}
            }
        });
    }

    protected void accessDatabase() throws InstantiationException,
    IllegalAccessException, ClassNotFoundException, SQLException {
    	
		GetDatabase db = new MSSQLDatabase();
		
		db.fetchInfoUsingName(clientSurname.getText() + "," + clientFirstName.getText());
	}

	private void createBackButton(GridPane grid) {
        Button btn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(10);//Layout pane with 10 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 6);
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(firstScene);
            }
        });
    }
}