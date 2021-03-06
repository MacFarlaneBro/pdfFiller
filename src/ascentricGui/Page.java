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
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ascentricClientDetails.ClientHolder;

public abstract class Page {
	
	public static final int PAGEWIDTH = 900;
	public static final int PAGEHEIGHT = 500;
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
	private void warning(String warningString){
		
		System.out.println("Warning!");
		final Stage warningStage = new Stage();
		Button button = new Button("OK");
		warningStage.setTitle("Warning!");
		warningStage.getIcons().add(new Image("file:G:\\MasterAdviser\\Ascentric Application Filler\\Lib\\warning.png"));
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
		
//		grid.setGridLinesVisible(true);
					    
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
//            	setSize();
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
//            		setSize();
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
	
	protected void setSize() {
		primaryStage.setHeight(PAGEHEIGHT);
		primaryStage.setWidth(PAGEWIDTH);
	}

	public void setTitle(GridPane grid, String title, Stage primaryStage){
		
		Text sceneTitle = new Text(title);
		primaryStage.setTitle(title);
		sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        sceneTitle.setId("sceneTitle");
        grid.add(sceneTitle, 0, 0, 4, 2);
	}
	
	/**
	 * Allows specification of the column widths for the current window of the gui each argument specifies
	 * a column progressing from the left to the right, passing no arguments sets the content of the window
	 * to fill all available space
	 * @param grid - the gridpane relating to the current window
	 * @param sizes - the widths in pixels you want  the rows to be
	 */
    public void setColumnSizes(GridPane grid, int... sizes){
    	for(int size: sizes){
    		grid.getColumnConstraints().add(new ColumnConstraints(size));
    	}
    	ColumnConstraints c = new ColumnConstraints();
    	c.setFillWidth(true);
    	c.setHgrow(Priority.ALWAYS);
    	grid.getColumnConstraints().add(c);
    }
    
    /**
     * Allows specification of the row heights for the current window of the gui each argument specifies
	 * a row progressing from the top to the bottom, passing no arguments sets the content of the window
	 * to fill all available space
     * @param grid - the gridpane relating to the current window
	 * @param sizes - the widths in pixels you want  the rows to be
     */
    public void setRowSizes(GridPane grid, int... sizes){
    	for(int size: sizes){
    		grid.getRowConstraints().add(new RowConstraints(size));
    	}
    	RowConstraints r = new RowConstraints();
    	r.setFillHeight(true);
    	r.setVgrow(Priority.ALWAYS);
    	grid.getRowConstraints().add(r);
    }

	public void setNextPage(Page nextNextPage) {
		nextPage = nextNextPage;
	}

}
