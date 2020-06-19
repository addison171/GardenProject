/**
 * @author Nick Sabatini
 */
package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
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
import javafx.scene.text.TextFlow;
import javafx.util.StringConverter;

public class SearchAllView {

	Controller ctrl;
	
	ComboBox<String> sunCbx;
	ComboBox<String> waterCbx;
	ComboBox<String> soilCbx;
	ComboBox<String> bloomCbx;
	ComboBox<String> nativeCbx;
	ComboBox<String> filterCbx;
	Slider errorSld;
	Button searchPlantsBtn;
	
		
	ArrayList<GridPane> plantItems;
	ScrollPane results;
	VBox resultsVb;
	
	ArrayList<GridPane> cartItems;
	ScrollPane cart;
	VBox cartVb;
	Label cartLbl;
	
	BorderPane borderpane;
	Scene scene;
	
	GridPane gp;
	
	Group searchRoot = new Group();
	
	
	public SearchAllView(Controller ctrl) {
		
		this.ctrl = ctrl;
		this.borderpane = new BorderPane();
		this.scene = new Scene(borderpane);

		
		//LEFT
		this.cart = new ScrollPane();
		cart.setPrefViewportHeight(500);
		this.cartVb = new VBox();
		
		this.cartItems = new ArrayList<GridPane>();
		cart.setContent(cartVb);
		cart.setPadding(new Insets(10,10,10,10));
		cartVb.setSpacing(10);
		
		this.cartLbl = new Label("Cart");
		this.cartVb.getChildren().add(cartLbl);
		this.cart.setPrefWidth(200);
		
		cart.setContent(cartVb);
		//LEFT
		
		
		//RIGHT
		GridPane filterPane = new GridPane();
		filterPane.setPadding(new Insets(10,10,10,10));
		filterPane.setVgap(10);
		filterPane.setHgap(10);
		filterPane.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		//features within the data input box
		Label cellsLbl = new Label("Search");
		GridPane.setConstraints(cellsLbl, 0, 0);
		
		//text field
		TextField cellFld = new TextField();
		cellFld.setPromptText("Keywords");
		cellFld.setPrefWidth(150);
		GridPane.setConstraints(cellFld, 1, 0);
		
		//sunlight label
		Label sunLbl = new Label("Sunlight Level:");
		sunLbl.setFont(new Font("Futura",14));
		sunLbl.setTextFill(Color.BLACK);
		GridPane.setConstraints(sunLbl, 0, 1);
		
		//sunlight combo box
		sunCbx = new ComboBox<String>();
		GridPane.setConstraints(sunCbx, 1, 1);
		sunCbx.getItems().addAll(
				"Sunny",
				"Medium",
				"Shady"
		);
		
		//water label
		Label waterLbl = new Label("Water Level:");
		waterLbl.setFont(new Font("Futura",14));
		waterLbl.setTextFill(Color.BLACK);
		GridPane.setConstraints(waterLbl, 0, 2);
		
		//water combo box
		waterCbx = new ComboBox<String>();
		GridPane.setConstraints(waterCbx, 1, 2);
		waterCbx.getItems().addAll(
				"Wet",
				"Medium",
				"Dry"
		);
		
		//soil label
		Label soilLbl = new Label("Soil Type:");
		soilLbl.setFont(new Font("Futura",14));
		soilLbl.setTextFill(Color.BLACK);
		GridPane.setConstraints(soilLbl, 0, 3);
		
		//soil combo box
		soilCbx = new ComboBox<String>();
		GridPane.setConstraints(soilCbx, 1, 3);
		soilCbx.getItems().addAll(
				"Clay",
				"Mix",
				"Sandy"
		);
		
		//bloom label
		Label bloomLbl = new Label("Bloom Season:");
		GridPane.setConstraints(bloomLbl, 0, 4);
		bloomLbl.setFont(new Font("Futura",14));
		bloomLbl.setTextFill(Color.BLACK);
		
		//bloom combo box
		bloomCbx = new ComboBox<String>();
		GridPane.setConstraints(bloomCbx, 1, 4);
		bloomCbx.getItems().addAll(
				"Spring",
				"Summer",
				"Fall",
				"Winter"
		);
		
		//Filter Strength Toggle
		Label errorLbl = new Label("Selectivity");
		errorLbl.setFont(new Font("Futura",14));
		errorLbl.setTextFill(Color.BLACK);
		
		GridPane.setConstraints(errorLbl, 0, 5);
		this.errorSld = new Slider();
		errorSld.setMin(0);
		errorSld.setMax(4);
		errorSld.setBlockIncrement(1);
		errorSld.setShowTickLabels(true);
		errorSld.setShowTickMarks(true);
		errorSld.setMinorTickCount(0);
		errorSld.setMajorTickUnit(1);
		errorSld.setSnapToTicks(true);
		errorSld.setLabelFormatter(new StringConverter<Double>() {

			@Override
			public Double fromString(String str) {
				// TODO Auto-generated method stub
				
				return null;
			}

			@Override
			public String toString(Double num) {
				// TODO Auto-generated method stub
				if (num == 0) { return "High"; }
				if (num == 1) { return "Very"; }
				if (num == 2) { return "Mid"; }
				if (num == 3) { return "Less"; }
				if (num == 4) {return "Not"; }

				return null;
			}
			
		});
		GridPane.setConstraints(errorSld, 1, 5);
		
		//Search button
		searchPlantsBtn = new Button("Search Plants");
		searchPlantsBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		GridPane.setConstraints(searchPlantsBtn, 1, 6);
		
		filterPane.getChildren().addAll(sunLbl, sunCbx, waterLbl, waterCbx, soilLbl, soilCbx, bloomLbl, bloomCbx,
				errorLbl, errorSld, searchPlantsBtn);
		//RIGHT

		
		
		//CENTER
		this.results = new ScrollPane();
		this.results.setPrefViewportHeight(500);
		this.resultsVb = new VBox();
		
		results.setContent(resultsVb);
		results.setPadding(new Insets(10,10,10,10));
		resultsVb.setSpacing(10);
		resultsVb.setPadding(new Insets(10,10,10,10));
		resultsVb.setPrefWidth(650);

		this.borderpane.setCenter(this.results);
		
		this.plantItems = new ArrayList<GridPane>();
		
		//CENTER
		
		
	
		//BORDERPANE
		this.borderpane = new BorderPane();
		
		borderpane.setRight(filterPane);
		borderpane.setCenter(results);
		borderpane.setLeft(this.cart);
		borderpane.setPadding(new Insets(10,10,10,10));
			
		
		scene = new Scene(borderpane);
		this.scene.setRoot(borderpane);	
		
		searchRoot.getChildren().add(borderpane);
		
	}
	/**
	 * Displays all the plants from the consumed arraylist of plants
	 * 
	 * @param plants - an arraylist of plants to display
	 */
	public void displayResults(ArrayList<Plant> plants) {
		
		this.plantItems.clear();
		this.resultsVb.getChildren().clear();
		
		for (Plant p : plants) {
			
			gp = new GridPane();
			Image img = new Image("images/" + p.getName() + ".png");
			ImageView iv = new ImageView(img);
			iv.setPreserveRatio(true);
			iv.setFitHeight(50);
			GridPane.setConstraints(iv, 0, 0);
			
			Button addToCartBtn = new Button("Add to Cart");
			addToCartBtn.setId(p.getName());
			GridPane.setConstraints(addToCartBtn, 2, 0);
			addToCartBtn.setOnMouseClicked(this.ctrl.getAddToCartBtnHandler());

			Text descFlw = new Text();
			descFlw.setText(p.getName() + ": " + "A plant that blooms in the " + p.getBloom() + " and thrives in " + p.getSunlight() + " and " +
					p.getSoil() + " soil. This plant can be described with the phrase, '" + p.getDescription() + ".'"  );
			descFlw.setWrappingWidth(400);
			GridPane.setConstraints(descFlw, 1, 0);
						
			gp.getChildren().addAll(iv, descFlw, addToCartBtn);
			gp.setPadding(new Insets(5,5,5,5));
			this.plantItems.add(gp);
		}
		
		resultsVb.getChildren().addAll(plantItems);
		//results.setContent(resultsVb);
		//borderpane.setCenter(results);
	}
	/**
	 * Edits left gridpane to contain plants user chooses to add to cart
	 * @param allItems - an arraylist of plants to add to cart
	 */
	public void displayCart(ArrayList<Plant> allItems) {
		
		this.cartItems.clear();
		this.cartVb.getChildren().clear();
		this.cartVb.getChildren().add(this.cartLbl);
		
		for (Plant p : allItems) {
				Image img = new Image("images/" + p.getName() + ".png");
				ImageView iv = new ImageView(img);
				iv.setPreserveRatio(true);
				iv.setFitHeight(50);
				GridPane.setConstraints(iv, 0, 0);
				
				GridPane cartgp = new GridPane();
				Label name = new Label(p.getName());
				GridPane.setConstraints(name, 1, 0);
				Label sun = new Label(p.getSunlight());
				GridPane.setConstraints(sun, 1, 1);
				Label water = new Label(p.getWater());
				GridPane.setConstraints(water, 1, 2);
				Label soil = new Label(p.getSoil());
				GridPane.setConstraints(soil, 1, 3);
				
				Button remove = new Button("Remove");
				remove.setOnMouseClicked(ctrl.getRemoveFromCartBtnHandler());
				remove.setId(p.getName());
				GridPane.setConstraints(remove, 1, 4);
				cartgp.getChildren().addAll(iv,name, sun, water, soil, remove);
				
				cartgp.setPadding(new Insets(5,5,5,5));
				this.cartItems.add(cartgp);
			}
		
		
		this.cartVb.getChildren().addAll(cartItems);
		this.cart.setContent(cartVb);

	}
	
}
