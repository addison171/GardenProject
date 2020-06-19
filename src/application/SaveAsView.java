package application;

/**
* @author addison Kuykendall
 */
import javafx.scene.Node;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SaveAsView {
	Group saveAsRoot = new Group();
	BorderPane borderpane;
	Scene scene;
	GridPane gp1;
	
	TextField newCellFld;
	
	Button openBtn;
	Button saveAsBtn;
	
	/**
	 * Save as view. Has a button for opening new vs existing file
	 */
	public SaveAsView() {
		borderpane = new BorderPane();
		this.scene = new Scene(borderpane);
		
		
		//openBtn = new Button("Open Existing Project");
		saveAsBtn = new Button("Save New Project As");
		saveAsBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		gp1 = new GridPane();
		
		gp1.setPadding(new Insets(150,450,250,400));
		gp1.setVgap(10);
		gp1.setHgap(10);
		
		newCellFld = new TextField();
		newCellFld.setPromptText("Name of New File");
		GridPane.setConstraints(newCellFld, 0, 3);
		
		Text space = new Text(" ");
		GridPane.setConstraints(space, 0, 0);
					
		GridPane.setConstraints(saveAsBtn, 1, 3);
			
		gp1.getChildren().addAll(newCellFld, space, saveAsBtn);
		Image background = new Image("images/home background.png");
		
		gp1.setBackground(new Background(new BackgroundImage(background,BackgroundRepeat.REPEAT,
                  BackgroundRepeat.REPEAT,
                  BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT)));
		
		
		BorderPane borderpane = new BorderPane();
		
		borderpane.setCenter(gp1);
					
		this.scene.setRoot(borderpane);
		
		saveAsRoot.getChildren().add(borderpane);
		
		
	}

}
