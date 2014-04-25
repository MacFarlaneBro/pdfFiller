package ascentricGui;

import java.util.Map;
import java.util.zip.DataFormatException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public abstract class Page {
	
	public static final int pageWidth = 900;
	public static final int pageHeight = 500;
	protected Page nextPage;
	protected Scene thisScene;
	protected Scene previousScene;
	protected GridPane grid;
	protected Stage primaryStage;
	protected ClientHolder client;
	protected Map<String, CheckBox> checkBoxes;
	protected Map<String, TextField> textFields;
	private Boolean movementButtonWidth = false;
	
	public abstract void setUp(Stage primaryStage, Scene previousScene, ClientHolder client);
	
	protected abstract void fillAndSaveClientInfo() throws Exception;
	
	protected void movementButtons2Columns(Boolean tf){
		movementButtonWidth = tf;
	}
	
	/**
	 * Creates a little warning pop up box alerting the user to something
	 * @param warningString - The message that will appear in the warning box
	 */
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
	
	public void createMovementButtons(int depth, int nextWidth) {
					    
		Button backBtn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        backBtn.setPrefWidth(100);
        hbBtn.getChildren().add(backBtn);
        if(movementButtonWidth){
        	grid.add(hbBtn, 0, depth, 2, 1);
        } else { 
        	grid.add(hbBtn, 0, depth);
        }
        
        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
                primaryStage.setScene(previousScene);
            }
        });
        
        Button nextBtn = new Button("Next");//Create button with the name sign in
        HBox hNextBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hNextBtn.setAlignment(Pos.BOTTOM_RIGHT);
        nextBtn.setPrefWidth(100);
        hNextBtn.getChildren().add(nextBtn);
        if(movementButtonWidth){
        	 grid.add(hNextBtn, nextWidth-1, depth, 2, 1);
        } else { 
        	 grid.add(hNextBtn, nextWidth, depth);
        }
       
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
            public void handle(ActionEvent e){
            	try {
					fillAndSaveClientInfo();
					nextPage.setUp(primaryStage, thisScene, client);
				} catch(DataFormatException dfe){ 
					warning(dfe.getMessage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });	
	}

    public void setColumnSizes(GridPane grid, int... sizes){
    	for(int size: sizes){
    		grid.getColumnConstraints().add(new ColumnConstraints(size));
    	}
    	ColumnConstraints c = new ColumnConstraints();
    	c.setFillWidth(true);
    	c.setHgrow(Priority.ALWAYS);
    	grid.getColumnConstraints().add(c);
    }
    
    public void setRowSizes(GridPane grid, int... sizes){
    	for(int size: sizes){
    		grid.getRowConstraints().add(new RowConstraints(size));
    	}
    	RowConstraints r = new RowConstraints();
    	r.setFillHeight(true);
    	r.setVgrow(Priority.ALWAYS);
    	grid.getRowConstraints().add(r);
    }

}
