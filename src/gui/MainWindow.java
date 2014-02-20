package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;
 
public class MainWindow extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    /*
    The start method is the entry point for all javaFX applications, the main
    method is included only to meet requirements 
    */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Welcome");
        
        GridPane grid = createStartForm(primaryStage);
        
        createFormFields(grid);
        
        createButton(grid);
                
//        grid.setGridLinesVisible(true);

        primaryStage.show();
    }
    
    /*
    When attaching a css file to a JavaFx Gui it must be attached to each
    individual scene, thus allowing a different layout for each 
    */

    private GridPane createStartForm(Stage primaryStage) {
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        //sets the horizontal and vertical gaps around the border of the application
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Scene scene = new Scene(grid, 350, 350);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(
                MainWindow.class//This must be the class that the stylesheet is styling
                .getResource("Style.css").toExternalForm());
        
        return grid;
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
        Text sceneTitle = new Text("PDF Filler");
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 20));
        sceneTitle.setId("sceneTitle");
        grid.add(sceneTitle, 0, 0, 2, 1);
        
        Label pw = new Label("Select Client");
        grid.add(pw, 0, 2);
        
        TextField clientTextField = new TextField();
        grid.add(clientTextField, 1, 2);
        
        Label formType = new Label("Choose Form Type");
        grid.add(formType, 0, 3);
        
        //This list is to populate the combobox for debugging purposes
        ObservableList<String> formTypes =
            FXCollections.observableArrayList(
                    "Ascentric",
                    "Standard Life",
                    "Scot Prov"
            );
        
        ComboBox comboBox = new ComboBox(formTypes);
        
    }

    private void createButton(GridPane grid) {
        Button btn = new Button("Get Info");//Create button with the name sign in
        HBox hbBtn = new HBox(10);//Layout pane with 10 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);
        btn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Sign in button Pressed");
            }
        });
    }

    
    
}