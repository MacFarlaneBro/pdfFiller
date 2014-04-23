package ascentricGui;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FirstProductDetails extends ProductDetailsGui {
	
	public static final FirstProductDetails INSTANCE = new FirstProductDetails();
	public final String pageTitle = "Product Details - First Client";	
	
	private FirstProductDetails(){
		appType = "First";
	}
	
	public void setTitleAndHeader(GridPane grid) {
		primaryStage.setTitle(pageTitle);
		Text sceneTitle = new Text(pageTitle);
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        grid.add(sceneTitle, 1, 1, 2, 2);
		
	}
}
