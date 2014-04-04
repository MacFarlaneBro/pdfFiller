package ascentricGui;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class JointProductDetails extends ProductDetailsGui {

	private String pageTitle = "Product Details - Joint Account";
	public final String appType = "Joint";
	
	@Override
	public void setTitleAndHeader(GridPane grid) {
		primaryStage.setTitle(pageTitle );
		Text sceneTitle = new Text(pageTitle);
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        grid.add(sceneTitle, 1, 1, 2, 2);
		
	}

}
