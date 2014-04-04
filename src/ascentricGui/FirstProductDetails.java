package ascentricGui;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FirstProductDetails extends ProductDetailsGui {
	
	public final String pageTitle = "Product Details - First Client";	
	
	public FirstProductDetails(){
		appType = "First";
	}
	
	public void setTitleAndHeader(GridPane grid) {
		primaryStage.setTitle(pageTitle);
		Text sceneTitle = new Text(pageTitle);
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        grid.add(sceneTitle, 1, 1, 2, 2);
		
	}
}
