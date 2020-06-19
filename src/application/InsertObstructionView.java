/**
 * @author Sohan Gadiraju
 */
package application;

import java.util.ArrayList;


import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import javafx.stage.Stage;

public class InsertObstructionView {
	Controller ctrl;
	Image circle;
	Image rectangle;
	
	BorderPane borderpane;
	Scene scene;
	
	ArrayList<GridPane> cartItems;
	ScrollPane cart;
	VBox cartVb;
	Label cartLbl;
	
	Button addToCartBtn;
	GridPane gardenGrid;
	ComboBox<String> obstruction;
	TextArea label;
	Group iovRoot = new Group();

	
	//updates the view according to what inputs have been changed
	public InsertObstructionView(Controller ctrl) {
		
		this.ctrl = ctrl;
		
		//CENTER
		gardenGrid = new GridPane();
		gardenGrid.setPadding(new Insets(10,10,10,10));
		gardenGrid.setGridLinesVisible(true);
		gardenGrid.setPrefWidth(550);
		
		
		this.borderpane = new BorderPane();
		this.scene = new Scene(borderpane);

		
		//LEFT
		this.cart = new ScrollPane();
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
		
		this.cart = new ScrollPane();
		VBox vb = new VBox();
		cart.setContent(vb);
		cart.setPadding(new Insets(10,10,10,10));
		
		GridPane gp1 = new GridPane();
		Label cart = new Label("Obstructions Cart:");
		GridPane.setConstraints(cart, 1, 1);
		gp1.setPrefSize(40, 40);
		gp1.getChildren().addAll(cart);
		vb.getChildren().add(gp1);
		

		//RIGHT
		GridPane inputDataBox = new GridPane();
		inputDataBox.setPadding(new Insets(10,10,10,10));
		inputDataBox.setVgap(10);
		inputDataBox.setHgap(10);
		
		Text instruct = new Text("Choose the type of obstruction you wish to add");
		
		instruct.setWrappingWidth(350);
		GridPane.setConstraints(instruct, 0, 0, 2, 1 );

		
		//sunlight label
		Label obstructionType = new Label("Obstruction");
		obstructionType.setFont(new Font("Futura",14));
		obstructionType.setTextFill(Color.BLACK);
		GridPane.setConstraints(obstructionType, 0, 4);
		
		//sunlight combo box
		obstruction = new ComboBox<String>();
		GridPane.setConstraints(obstruction, 1, 4);
		obstruction.getItems().addAll(
				"House",
				"Pond",
				"Building",
				"Black Box"
		);
		addToCartBtn = new Button("Add to Cart");
		addToCartBtn.setOnAction(this.ctrl.getAddToCartBtnHandlerObs());
		addToCartBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		GridPane.setConstraints(addToCartBtn, 0, 6);
		
		inputDataBox.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		inputDataBox.getChildren().addAll(addToCartBtn, instruct, obstructionType, obstruction);
		//inputDataBox.getChildren().addAll(cellFld, cellFld2, sunLbl, sunCbx, waterLbl, waterCbx, soilLbl, soilCbx, saveDataBtn);
		//RIGHT
		

		//BORDERPANE
		borderpane = new BorderPane();
		
		borderpane.setCenter(gardenGrid);
		borderpane.setRight(inputDataBox);
		borderpane.setLeft(cart);
		borderpane.setPadding(new Insets(10,10,10,10));
		
		scene =  new Scene(borderpane);
		
		this.scene.setRoot(borderpane);
		iovRoot.getChildren().add(borderpane);

	}
	/**
	 * Edits left gridpane to contain any obstructions user selects to add
	 * @param obstructions - an arraylist containing obstructions
	 */
	public void displayCart(ArrayList<Obstruction> obstructions) {
		
		this.cartItems.clear();
		this.cartVb.getChildren().clear();
		this.cartVb.getChildren().add(this.cartLbl);
		
		for (Obstruction o : obstructions) {
			Image img = new Image("images/" + o.getName() + ".png");
			ImageView iv = new ImageView(img);
			iv.setPreserveRatio(true);
			iv.setFitHeight(50);
			GridPane.setConstraints(iv, 0, 0);
			
			GridPane cartgp = new GridPane();
			Label name = new Label(o.getName());
			GridPane.setConstraints(name, 1, 0);
	
			Button remove = new Button("Remove");
			remove.setOnMouseClicked(ctrl.getRemoveFromCartBtnHandlerObs());
			remove.setId(o.getName());
			GridPane.setConstraints(remove, 1, 3);
			
			cartgp.getChildren().addAll(iv,name, remove);
			cartgp.setPadding(new Insets(5,5,5,5));
			this.cartItems.add(cartgp);
		}
		
		this.cartVb.getChildren().addAll(cartItems);
		this.cart.setContent(cartVb);
		borderpane.setLeft(cart);

	}
	/**
	 * updates any changes made to garden grid in edit cells view
	 * @param garden - a gridpane to be updated in the view
	 * @return returns an updated gridpane in case any changes are made
	 */
	public GridPane updateGrid(GridPane garden) {
		gardenGrid = garden;
		borderpane.setCenter(garden);
		return garden;
	}
	
}

