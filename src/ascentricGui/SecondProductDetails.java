package ascentricGui;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SecondProductDetails extends ProductDetailsGui {

	public static final SecondProductDetails INSTANCE = new SecondProductDetails();
	private String pageTitle = "Product Details - Second Applicant";
	
	private SecondProductDetails(){
		appType = "Second";
	}
	
	@Override
	public void setTitleAndHeader(GridPane grid) {
		primaryStage.setTitle(pageTitle );
		Text sceneTitle = new Text(pageTitle);
        sceneTitle.setFont(Font.font("courier", FontWeight.NORMAL, 21));
        sceneTitle.setLayoutX(20);
        grid.add(sceneTitle, 1, 1, 11, 2);
		
	}

}
