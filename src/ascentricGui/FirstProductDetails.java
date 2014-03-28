package ascentricGui;

import ascentricClientDetails.ProductDetails;
import ascentricClientDetails.Wrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FirstProductDetails extends ProductDetailsGui {
	
	public final String pageTitle = "Product Details - First Client";	
	public final String appType = "First";
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
            	fillAndSaveClientInfo(appType);
            	
            	System.out.println(client.getFirstClient().getProductDetails().getGeneralInvestmentAccount().getCash());
            	if(ProductDetailsFactory.getSecond()==null){
            		ProductDetailsFactory.makeSecond();
            	}
            	nextPage = ProductDetailsFactory.getSecond();
            	nextPage.setUp(primaryStage, thisScene, client);
            }
        });
	}

	@Override
	public void goTo() {
		// TODO Auto-generated method stub
		
	}
	
	public void setTitleAndHeader(GridPane grid) {
		primaryStage.setTitle(pageTitle);
		Text sceneTitle = new Text(pageTitle);
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        grid.add(sceneTitle, 1, 1, 2, 2);
		
	}

}
