/**
 * @author Addison Kuykendall, Nick Sabatini
 */
package application;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InputDataView{
	
	ComboBox<String> sunCbx;
	ComboBox<String> waterCbx;
	ComboBox<String> soilCbx;
	Text plotSize;
	Button addData;
	 Group inputDataRoot = new Group();
	TextField cellFld;
	TextField cellFld2;
	TextField x;
	TextField y;
	
	Button createPlot;

	
	BorderPane borderpane;
	Scene scene;
	
	ScrollPane cart;
	
	GridPane gardenGrid;
	
	Button saveDataBtn;
	
	public InputDataView() {
		
		//CENTER
		gardenGrid = new GridPane();
		gardenGrid.setPadding(new Insets(10,10,10,10));
		gardenGrid.setGridLinesVisible(true);
		gardenGrid.setPrefWidth(550);
		
		/*for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				ImageView iv = new ImageView();
				iv.setFitHeight(40);
				iv.setFitWidth(40);
				gardenGrid.add(iv, i, j);
			}
		}*/
		//CENTER
		
		
		//LEFT
		
		this.cart = new ScrollPane();
		VBox vb = new VBox();
		cart.setContent(vb);
		cart.setPadding(new Insets(10,10,10,10));
		
		GridPane gp1 = new GridPane();
		
		Label sun1 = new Label("Sunlight Preference:");
		sun1.setFont(new Font("Futura",16));
		sun1.setTextFill(Color.BLACK);
		GridPane.setConstraints(sun1, 1, 0);
		
		Label water1 = new Label("Water Preference:");
		water1.setFont(new Font("Futura",16));
		water1.setTextFill(Color.BLACK);
		GridPane.setConstraints(water1, 1, 1);
		
		
		Label soil1 = new Label("Soil Preference:");
		soil1.setFont(new Font("Futura",16));
		soil1.setTextFill(Color.BLACK);
		GridPane.setConstraints(soil1, 1, 2);
		
		gp1.getChildren().addAll(sun1,water1,soil1);
		vb.getChildren().add(gp1);
		
		GridPane gp2 = new GridPane();
		Label sun2 = new Label("Sunlight preference");
		GridPane.setConstraints(sun2, 1, 0);
		Label water2 = new Label("Water preference");
		GridPane.setConstraints(water2, 1, 1);
		Label soil2 = new Label("Soil Preference");
		GridPane.setConstraints(soil2, 1, 2);
		
		gp2.getChildren().addAll(sun2,water2,soil2);
		vb.getChildren().add(gp2);
		
		
		//LEFT
		

		//RIGHT
		GridPane inputDataBox = new GridPane();
		inputDataBox.setPadding(new Insets(10,10,10,10));
		inputDataBox.setVgap(10);
		inputDataBox.setHgap(10);
		
		Text instruct = new Text("Input the size of your garden (in feet) in the corresponding boxes, once you hit 'Create' the garden will appear"
				+ "as a grid.\n\nWarning: if you ever change the size of your garden after you first create it, the cells and any plants you've"
				+ "inserted will be wiped clean.  So please, make sure you go outside and get the right dimensions and garden information first.\n\n"
				+ "Once you create your plot, you will be able to select individual cells to change their information.\n\n"
				+ "NOTE: Each box indicates a 2'by 2' area of your garden or 4 square feet\n\n"
				+ "Happy virtual gardening!");
		
		instruct.setWrappingWidth(350);
		GridPane.setConstraints(instruct, 0, 0, 2, 1 );
		
		x = new TextField();
		x.setPromptText("Height (feet)");
		GridPane.setConstraints(x, 0, 1);
		x.setMaxWidth(100);
		
		y = new TextField();
		y.setPromptText("Width (feet)");
		GridPane.setConstraints(y, 1, 1);
		y.setMaxWidth(100);
		
		createPlot = new Button("Create Plot");
		createPlot.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		GridPane.setConstraints(createPlot, 0, 2);
		
		
		//sunlight label
		Label sunLbl = new Label("Sunlight Level:");
		sunLbl.setFont(new Font("Futura",14));
		sunLbl.setTextFill(Color.BLACK);
		GridPane.setConstraints(sunLbl, 0, 3);
		
		//sunlight combo box
		sunCbx = new ComboBox<String>();
		GridPane.setConstraints(sunCbx, 1, 3);
		sunCbx.getItems().addAll(
				"Sunny",
				"Medium",
				"Shady"
		);
		
		//water label
		Label waterLbl = new Label("Water Level:");
		waterLbl.setFont(new Font("Futura",14));
		waterLbl.setTextFill(Color.BLACK);
		GridPane.setConstraints(waterLbl, 0, 4);
		
		//sunlight combo box
		waterCbx = new ComboBox<String>();
		GridPane.setConstraints(waterCbx, 1, 4);
		waterCbx.getItems().addAll(
				"Wet",
				"Medium",
				"Dry"
		);
		
		//water label
		Label soilLbl = new Label("Soil Type:");
		soilLbl.setFont(new Font("Futura",14));
		soilLbl.setTextFill(Color.BLACK);
		GridPane.setConstraints(soilLbl, 0, 5);
		
		//sunlight combo box
		soilCbx = new ComboBox<String>();
		GridPane.setConstraints(soilCbx, 1, 5);
		soilCbx.getItems().addAll(
				"Clay",
				"Mix",
				"Sandy"
		);
		
		// enter the data into selected boxes
		this.saveDataBtn = new Button("Enter Data");
		saveDataBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		GridPane.setConstraints(saveDataBtn, 1, 6);
		
		
		inputDataBox.getChildren().addAll(instruct, x, y, createPlot, sunLbl, sunCbx, waterLbl, waterCbx, soilLbl, soilCbx, saveDataBtn);
		//inputDataBox.getChildren().addAll(cellFld, cellFld2, sunLbl, sunCbx, waterLbl, waterCbx, soilLbl, soilCbx, saveDataBtn);
		//RIGHT
		inputDataBox.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		//BORDERPANE
		borderpane = new BorderPane();
		borderpane.setCenter(gardenGrid);
		borderpane.setRight(inputDataBox);
		//borderpane.setLeft(this.cart);
		borderpane.setPadding(new Insets(10,10,10,10));
		
		scene =  new Scene(borderpane);
		
		this.scene.setRoot(borderpane);
		inputDataRoot.getChildren().add(borderpane);

	}
	/**
	 * updates any changes made to garden grid in edit cells view
	 * 
	 * @param garden - a gridpane to be updated in the view
	 * @return returns an updated gridpane in case any changes are made
	 */
	public GridPane displayPlot(GridPane garden) {
		borderpane.setCenter(garden);
		return garden;
	}
	
	public void update() {
	}
	
	/**
	 * goes back to the garden view with the settings updated according to the selections.
	 */
	public void view() {
		
	}
}
