package ascentricGui;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import ascentricClientDetails.ClientHolder;
import ascentricForm.AscentricForm;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Print {
	
	private ClientHolder client;
	private GridPane grid;
	private Button print;
	private Stage primaryStage;
	private Scene thisScene;

	public Print(ClientHolder client, Stage primaryStage){
		
		this.client = client;
		this.primaryStage = primaryStage;
		print = new Button("Print Document");
		
		primaryStage.setTitle("Income Payment Instructions");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(print, 0, 0);
        
        print.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                printDocument();
            }
        });
        
        thisScene = new Scene(grid, 900, 500);  
		
        primaryStage.setScene(thisScene);
	}
	
	protected void printDocument() {
		AscentricForm af = new AscentricForm();
		try {
			af.fillIt(client);
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
