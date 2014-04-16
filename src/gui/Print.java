package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.filechooser.FileSystemView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import ascentricClientDetails.ClientHolder;
import ascentricForm.AscentricForm;

import com.itextpdf.text.DocumentException;

public class Print {
	
	private ClientHolder client;
	private GridPane grid;
	private Button save;
	private Scene thisScene;
	private Stage primaryStage;
	private Scene previousScene;
	private Text actionTarget;

	public Print(final ClientHolder client, final Stage primaryStage, Scene previousScene){
		
		this.client = client;
		this.primaryStage = primaryStage;
		this.previousScene = previousScene;
		save = new Button("Save Document");
		
		primaryStage.setTitle("Income Payment Instructions");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(save, 0, 1);
        
        Label tf = new Label("You have now completed filling in the document,"
        		+ "click Save to produce a finished document and return to the home screen.");
        
        grid.add(tf, 0, 0);
        
        save.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
            	
            	FileChooser fileChooser = new FileChooser();
            	
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
                fileChooser.setInitialFileName(client.getFirstClient().getIndividualDetails().getSurname()
                		+ " Ascentric Form");
                File file = fileChooser.showSaveDialog(primaryStage);
                
                if(!file.getName().contains(".")){
                	file = new File(file.getAbsolutePath() + ".pdf");
                }
                printDocument(file);
            }
        });
        
        actionTarget = new Text();
        actionTarget.setWrappingWidth(300);
        grid.add(actionTarget, 0, 3, 2, 4);
        actionTarget.setFont(Font.font(null, FontWeight.BOLD, 15));
        createMovementButtons(3,5);
        thisScene = new Scene(grid, 900, 500);  
        primaryStage.setScene(thisScene);
        
	}
	
	protected void printDocument(File file) {
		AscentricForm af = new AscentricForm();
		try {
			actionTarget.setFill(Color.BLUE);
            actionTarget.setText("Filling PDF");
			af.fillIt(client, file);
			Main main = new Main();
			main.start(primaryStage);
		} catch (IOException | DocumentException e) {
    		actionTarget.setFill(Color.FIREBRICK);
    		actionTarget.setText("The document has failed to fill, please check to see if a previous instance of the"
    				+ "document is open, if so, please close it and try again.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createMovementButtons(int depth,int nextWidth) {
					    
		Button backBtn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        backBtn.setPrefWidth(100);
        hbBtn.getChildren().add(backBtn);
       	grid.add(hbBtn, 0, depth);
        
        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(previousScene);
            }
        });
	}
	
} 

