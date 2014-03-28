package ascentricGui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FirstProductDetails extends ProductDetails {
	
	public final String pageTitle = "Product Details - First Client";	
	
	@Override
	public void createMovementButtons(GridPane grid) {
		Button backBtn = new Button("Back");//Create button with the name sign in
        HBox hbBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        backBtn.setPrefWidth(100);
        hbBtn.getChildren().add(backBtn);
        grid.add(hbBtn, 2, --gridVert);
        
        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent e){
            	primaryStage.setTitle("PDF Filler 0.01");
                primaryStage.setScene(previousScene);
            }
        });
        
        Button nextBtn = new Button("Next");//Create button with the name sign in
        HBox hNextBtn = new HBox(21);//Layout pane with 21 pixel spacing
        hNextBtn.setAlignment(Pos.BOTTOM_LEFT);
        nextBtn.setPrefWidth(100);
        hNextBtn.getChildren().add(nextBtn);
        grid.add(hNextBtn, 10, gridVert);
        
        nextBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
            public void handle(ActionEvent e){
            	fillAndSaveClientInfo();
            	if(ProductDetailsFactory.getFirst()==null){
            		ProductDetailsFactory.makeFirst();
            	}
            	nextPage = ProductDetailsFactory.getFirst();
            	nextPage.setUp(primaryStage, thisScene, client);
            }
        });
	}

	@Override
	public void goTo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fillAndSaveClientInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitleAndHeader() {
		primaryStage.setTitle(pageTitle);
		
	}
}
