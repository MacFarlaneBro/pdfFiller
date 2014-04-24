package gui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
	private Button nextBtn;
	private boolean printed;

	public Print(final ClientHolder client, final Stage primaryStage, Scene previousScene){
		
		this.client = client;
		this.primaryStage = primaryStage;
		this.previousScene = previousScene;
		
		primaryStage.setTitle("Save File");
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        
//        grid.setGridLinesVisible(true);
        
		save = new Button("Save Document");
        grid.add(save, 4, 2, 2, 1);

    	Page page = new GenericPage();
    	page.setRowSizes(grid, 100, 100, 100, 100, 100);
    	page.setColumnSizes(grid, 100, 100, 100, 100, 100, 100, 100);
        
        Label tf = new Label("You have now completed filling in the document,"
        		+ " click 'Save Document' to produce a finished document."
        		+ " The document will then be opened in adobe acrobat"
        		+ " so that you may check it to make sure everything is correct"
        		+ " before returning to the home screen.");
        
        grid.add(tf, 1, 1, 4, 1);
        tf.setWrapText(true);
        
        save.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
            	
            	FileChooser fileChooser = new FileChooser();
            	
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
                fileChooser.setInitialFileName(client.getFirstClient().getIndividualDetails().getForename()
                		+ client.getFirstClient().getIndividualDetails().getSurname()
                		+ " Ascentric Form "
                		+ (new SimpleDateFormat("ddMMyy")).format(new Date()));
                File file = fileChooser.showSaveDialog(primaryStage);
                
                if(!file.getName().contains(".")){
                	file = new File(file.getAbsolutePath() + ".pdf");
                }
                printDocument(file);
            }
        });
        
        actionTarget = new Text();
        actionTarget.setWrappingWidth(600);
        grid.add(actionTarget, 1, 4, 6, 1);
        actionTarget.setFont(Font.font(null, FontWeight.BOLD, 15));
        createMovementButtons(3);
        thisScene = new Scene(grid, 900, 500);  
        primaryStage.setScene(thisScene);
        
	}
	
	public void warning(String warningString){

		System.out.println("Warning!");
		final Stage warningStage = new Stage();
		Button button = new Button("OK");
		warningStage.setTitle("Warning!");
		warningStage.initModality(Modality.WINDOW_MODAL);
		warningStage.setScene(new Scene(VBoxBuilder.create().children(new Text(warningString), button).alignment(Pos.CENTER).padding(new Insets(5)).build()));
				button.setOnAction(
						new EventHandler<ActionEvent>(){
							@Override
							public void handle(ActionEvent arg0) {
								warningStage.close();
							}
						}
				);		
		warningStage.show();
	}
	
	protected void printDocument(File file) {
		AscentricForm af = new AscentricForm();
		try {
			printed = true;
			actionTarget.setFill(Color.BLUE);
            actionTarget.setText("Filling PDF");
			af.fillIt(client, file);
			Desktop dt = Desktop.getDesktop();
			dt.open(file);
			actionTarget.setFill(Color.BLACK);
			actionTarget.setText("Document Filled!");
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
        backBtn.setPrefWidth(100);
       	grid.add(backBtn, 1, depth, 1, 1);
        
        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(previousScene);
            }
        });
        
        nextBtn = new Button("Home");//Create button with the name sign in
        nextBtn.setPrefWidth(100);
        grid.add(nextBtn, 4, depth);
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
            public void handle(ActionEvent e){
				Main main = new Main();
				if(printed){
					try {
						main.start(primaryStage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					warning("You have yet to save the document,\nif you return to the home page all"
							+ "details will be lost.");
				}
            }
        });	
	}
	
} 

