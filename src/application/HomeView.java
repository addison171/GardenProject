/**
 * @author Sohan Gadiraju
 */


package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HomeView {
	
	FileChooser fileChooser = new FileChooser();
	GridPane grid = new GridPane();
	Scene scene = new Scene(grid);
	Group homeViewRoot = new Group();
	Button createNew;
	Button editExisting;

	public HomeView(Stage stage) {
		stage.setTitle("Garden Creator");
        
		grid.setPadding(new Insets(150,450,250,400));
		grid.setVgap(10);
		grid.setHgap(10);
				
		//create new label
		Label create = new Label("Create New");
		GridPane.setConstraints(create, 0, 0);
		create.setFont(new Font("Futura",20));
		create.setTextFill(Color.DARKOLIVEGREEN);
		
		//edit existing label
		Label edit = new Label("Edit Existing");
		GridPane.setConstraints(edit, 0, 1);
		edit.setFont(new Font("Futura",20));
		edit.setTextFill(Color.DARKOLIVEGREEN);
		//create new button
		createNew = new Button("Lets get started");
		GridPane.setConstraints(createNew, 1, 0);
		createNew.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		
		//edit existing button
		editExisting = new Button("Lets keep working");
		GridPane.setConstraints(editExisting, 1, 1);
		editExisting.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		grid.getChildren().addAll(create, edit, createNew, editExisting);
		Image background = new Image("images/home background.png");
//		ImageView bv = new ImageView(background);
//		bv.setFitHeight(stage.getHeight());
//		bv.setFitWidth(stage.getWidth());
		grid.setBackground(new Background(new BackgroundImage(background,BackgroundRepeat.REPEAT,
                  BackgroundRepeat.REPEAT,
                  BackgroundPosition.DEFAULT,
                  BackgroundSize.DEFAULT)));
		
		this.scene.setRoot(grid);
		
	}
}
