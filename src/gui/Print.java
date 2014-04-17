package gui;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;
import ascentricForm.AscentricForm;
import ascentricGui.Page;

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
        grid.add(save, 3, 2, 2, 1);

    	Page page = new GenericPage();
    	
    	page.setRowSizes(grid, 100, 100, 100, 100, 100);
    	page.setColumnSizes(grid, 100, 100, 100, 100, 100, 100, 100);
        
        Label tf = new Label("You have now completed filling in the document,"
        		+ "click Save to produce a finished document and return to the home screen.");
        
        grid.add(tf, 1, 1, 4, 1);
        tf.setWrapText(true);
        
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
        actionTarget.setWrappingWidth(600);
        grid.add(actionTarget, 1, 3, 6, 1);
        actionTarget.setFont(Font.font(null, FontWeight.BOLD, 15));
        createMovementButtons(2);
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
    				+  " document, or of the pdf filler application is open, if so, please close it and try again.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createMovementButtons(int depth) {
					    
		Button backBtn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.CENTER);
        backBtn.setPrefWidth(100);
        hbBtn.getChildren().add(backBtn);
        GridPane.setValignment(backBtn, VPos.CENTER);
       	grid.add(hbBtn, 1, depth, 1, 1);
        
        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(previousScene);
            }
        });
	}
	
} 

