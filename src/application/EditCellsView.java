/**
 * @author Sohan Gadiraju
 */
package application;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EditCellsView {
	Button SunlightLevel;
	Button WaterLevel;
	Button SoilType;
	Button Save;
	
	Group editCellsRoot = new Group();
	ComboBox<String> sunCbx;
	ComboBox<String> waterCbx;
	ComboBox<String> soilCbx;
	Text plotSize;
	Button addData;
	
	TextField cellFld;
	TextField cellFld2;
	TextField x;
	TextField y;
	

	
	 BorderPane borderpane;
	Scene scene;
	
	ScrollPane cart;
	
	GridPane gardenGrid;
	
	Button saveDataBtn;
	
	public EditCellsView() {
		
		
		//CENTER
		gardenGrid = new GridPane();
		gardenGrid.setPadding(new Insets(10,10,10,10));
		gardenGrid.setGridLinesVisible(true);
		gardenGrid.setPrefWidth(550);
		
		//CENTER
		
		
		//LEFT
		
		this.cart = new ScrollPane();
		VBox vb = new VBox();
		cart.setContent(vb);
		cart.setPadding(new Insets(10,10,10,10));
		
		//LEFT
		

		//RIGHT
		GridPane editCellsBox = new GridPane();
		editCellsBox.setPadding(new Insets(10,10,10,10));
		editCellsBox.setVgap(10);
		editCellsBox.setHgap(10);
		
		Text instruct = new Text("Enter the cells you would like to change in an x1, y1, x2, y2 format: \n \n");
		
		instruct.setWrappingWidth(350);
		GridPane.setConstraints(instruct, 0, 0, 2, 1 );
		
		
		//features within the data input box
		Label cellsLbl = new Label("Cells Selected: ");
		cellsLbl.setFont(new Font("Futura",14));
		cellsLbl.setTextFill(Color.BLACK);
		GridPane.setConstraints(cellsLbl, 0, 3);
		
		//text field
		cellFld = new TextField();
		cellFld.setPromptText("x1, y1, x2, y2");
		cellFld.setPrefWidth(200);
		GridPane.setConstraints(cellFld, 1, 3);
		
		//sunlight label
		Label sunLbl = new Label("Sunlight Level:");
		sunLbl.setFont(new Font("Futura",14));
		sunLbl.setTextFill(Color.BLACK);
		GridPane.setConstraints(sunLbl, 0, 4);
		
		//sunlight combo box
		sunCbx = new ComboBox<String>();
		GridPane.setConstraints(sunCbx, 1, 4);
		sunCbx.getItems().addAll(
				"Sunny",
				"Medium",
				"Shady"
		);
		
		//water label
		Label waterLbl = new Label("Water Level:");
		waterLbl.setFont(new Font("Futura",14));
		waterLbl.setTextFill(Color.BLACK);
		GridPane.setConstraints(waterLbl, 0, 5);
		
		//sunlight combo box
		waterCbx = new ComboBox<String>();
		GridPane.setConstraints(waterCbx, 1, 5);
		waterCbx.getItems().addAll(
				"Wet",
				"Medium",
				"Dry"
		);
		
		//water label
		Label soilLbl = new Label("Soil Type:");
		soilLbl.setFont(new Font("Futura",14));
		soilLbl.setTextFill(Color.BLACK);
		GridPane.setConstraints(soilLbl, 0, 6);
		
		//sunlight combo box
		soilCbx = new ComboBox<String>();
		GridPane.setConstraints(soilCbx, 1, 6);
		soilCbx.getItems().addAll(
				"Clay",
				"Mix",
				"Sandy"
		);
		
		// enter the data into selected boxes
		this.saveDataBtn = new Button("Enter Data");
		saveDataBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		GridPane.setConstraints(saveDataBtn, 1, 7);
		
		
		editCellsBox.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		editCellsBox.getChildren().addAll(instruct, cellsLbl, cellFld, sunLbl, sunCbx, waterLbl, waterCbx, soilLbl, soilCbx, saveDataBtn);
		//editCellsBox.getChildren().addAll(cellFld, cellFld2, sunLbl, sunCbx, waterLbl, waterCbx, soilLbl, soilCbx, saveDataBtn);
		//RIGHT


		//BORDERPANE
		borderpane = new BorderPane();
		
		borderpane.setCenter(gardenGrid);
		borderpane.setRight(editCellsBox);
		//borderpane.setLeft(this.cart);
		borderpane.setPadding(new Insets(10,10,10,10));
		
		scene =  new Scene(borderpane);
		
		this.scene.setRoot(borderpane);
		editCellsRoot.getChildren().add(borderpane);

	}
	
	/**
	 * highlightCells allows the user to highlight cells and change the sunlight, water, and soil
	 */
	public void highlightCells(GridPane gardenGrid, int x1, int x2, int y1, int y2) {
//		for(int i = x1; i<= x2; i++) {
//			for(int j= y1; j<=y2; j++) {
//				gardenGrid.setStyle( "-fx-background-color:black;" );
//			}
//		}
	}
	/**
	 * updates any changes made to garden grid in edit cells view
	 * @param garden - a gridpane to be updated in the view
	 * @return returns an updated gridpane in case any changes are made
	 */
	public GridPane updateGarden(GridPane garden) {
		borderpane.setCenter(garden);
		return garden;
	}
	
}
