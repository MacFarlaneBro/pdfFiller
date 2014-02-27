/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author charliebrodie
 */
public class Main extends Application{
    
    private Stage primaryStage;
    private Scene firstScene;
    
    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("PDF Filler 0.01");
        primaryStage.setMinWidth(500);
        primaryStage.initStyle(StageStyle.DECORATED);
        
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
        //sets the horizontal and vertical gaps around the border of the application
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text sceneTitle = new Text("Welcome to the PDF Filler mark 0.01");
        sceneTitle.setId("sceneTitle");
        sceneTitle.setLayoutX(20);
        grid.add(sceneTitle, 0, 0, 3, 1);
        
        Button newClient = new Button("New Client");
        newClient.setMinWidth(100);
        setUpNewClientButton(newClient);
        Button existingClient = new Button("Existing Client");
        setUpExistingClientButton(existingClient);
        Scene firstScene = new Scene(grid, 420, 400);
        
        primaryStage.setScene(firstScene);
        firstScene.getStylesheets().add(
                CurrentClient.class//This must be the class that the stylesheet is styling
                .getResource("Style.css").toExternalForm());

//        grid.setGridLinesVisible(true);
        
        grid.add(newClient, 1, 4);
        grid.add(existingClient, 2, 4);
                
        primaryStage.show();
        this.firstScene = firstScene;

    }   
    
    public void setUpNewClientButton(Button btn){
        final Text actionTarget = new Text();
        btn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                NewClient client = new NewClient();
            }
        });
    }
    
    public void setUpExistingClientButton(Button btn){
        CurrentClient client = new CurrentClient();
        final Text actionTarget = new Text();
        btn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                CurrentClient client = new CurrentClient();
                client.start(primaryStage, firstScene);
            }
        });
    }
}