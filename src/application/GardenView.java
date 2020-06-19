/**
 * @author Nick Sabitini
 */
package application;

import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
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

public class GardenView {
	Controller control;
	
	private final double WIDTH = 1350;
	private final double HEIGHT = 950;
	
	GridPane gardenGrid;
	ScrollPane cart;
	VBox cartVb;
	ArrayList<GridPane> cartItems;
	Label cartLbl;

	
	BorderPane borderpane;
	Scene scene;
	
	Group gardenRoot = new Group();
	 
	Controller ctrl;

	public GardenView(Controller ctrl) {
		
		this.ctrl = ctrl;
		this.borderpane = new BorderPane();
		this.scene = new Scene(borderpane);
		
		//CENTER
		gardenGrid = new GridPane();
		gardenGrid.setPadding(new Insets(10,10,10,10));
		gardenGrid.setGridLinesVisible(true);
		gardenGrid.setPrefWidth(550);
		

		//LEFT
		this.cart = new ScrollPane();
		this.cart.setPrefViewportHeight(500);
		this.cartVb = new VBox();
		
		this.cartItems = new ArrayList<GridPane>();
		cart.setContent(cartVb);
		cart.setPadding(new Insets(10,10,10,10));
		cartVb.setSpacing(10);
		
		this.cartLbl = new Label("Cart");
		this.cartLbl.setFont(new Font("Futura",18));
		this.cartLbl.setTextFill(Color.BLACK);
		this.cartVb.getChildren().add(cartLbl);
		this.cart.setPrefWidth(250);
		
		cart.setContent(cartVb);

		//LEFT
		
		
		
		//RIGHT
		
		
		
		//RIGHT
		

		//borderpane = new BorderPane();
		
		//borderpane.setCenter(gardenGrid);
		borderpane.setLeft(cart);
		borderpane.setPadding(new Insets(10,10,10,10));

		this.scene.setRoot(borderpane);
		gardenRoot.getChildren().add(borderpane);
			
	}
	
	/**
	 * @return - returns the updated garden
	 * @param garden - changes the gardenGrid in this view, this parameter should usually be filled with the main views garden
	 */
	public GridPane updateGrid(GridPane garden) {
		gardenGrid = garden;
		borderpane.setCenter(garden);
		return garden;
	}

	/**
	* Function to make a duplicate plant node
	* 
	* @param p  -The Plant whose attributes will be used to make the BorderPane
	* @return BorderPane - an updated borderpane
	*/
	public BorderPane makePlantNode(Plant p) {
		BorderPane bp = new BorderPane();
		bp.getStyleClass().add("plant");
		ImageView photo = new ImageView();
		photo.setImage(new Image("images/" + p.getName() + ".png"));
		photo.setFitHeight(85);
		photo.setFitWidth(85);
		Text description = new Text(p.getDescription());
		description.getStyleClass().add("description");
		TextFlow descrip = new TextFlow(description);
		descrip.setMaxWidth(90);
		Text name = new Text(p.getName());
		name.getStyleClass().add("title");
		bp.setLeft(photo);
		bp.setCenter(gardenGrid);
		bp.setOnMousePressed(event -> control.onPlantPress(event, p));
		return bp;
	}
	/**
	* Makes an icon for the object for drag drop
	*  
	* @param p the Plant whose icon we're displaying
	* @param x the x location of where the icon will first be placed on the screen
	* @param y the y location of where the icon will first be placed on the screen
	*/
	public ImageView makeIcon(Object p, double x, double y, double height, double width) {
		String name = "";
		double scale = 1;
		if(p instanceof Plant) {
			p = (Plant) p;
			name = ((Plant) p).getName();
			scale = ((Plant) p).getScale();
		}
		if(p instanceof Obstruction) {
			p = (Obstruction) p;
			name = ((Obstruction) p).getName();
		}
		Image pi = new Image("images/" + name + ".png");
		ImageView im = new ImageView();
		im.setImage(pi);
		im.setFitHeight(height*scale);
		im.setFitWidth(width*scale);
		im.setPreserveRatio(true);
		gardenGrid.getChildren().add(im);
		return im;
	}
	/**
	* Deletes an icon from the grid
	*  
	* @param im - the image View of the plant we are trying to delete
	* @param x the x location of where the icon was last placed on the screen
	* @param y the y location of where the icon was last be placed on the screen
	*/
	public void deleteIcon(ImageView im, double x, double y, ArrayList<ImageView> images, ArrayList<Object> plants) {
		gardenGrid.getChildren().remove(im);
		plants.remove(images.indexOf(im));
		images.remove(im);
	}
	/**
	* Changes the x and y location of a specific  imageview
	* 
	* @param im the ImageView whose location has been changed
	* @param x the new x location of the PlantIcon
	* @param y the new y location of the PlantIcon
	*/
	public void setPlantIconLocation(ImageView im, double x, double y) {
		double xChange = im.getLayoutX() + x;
		double yChange = im.getLayoutY() + y;
		im.setTranslateX(xChange);
		im.setTranslateY(yChange);
	}
	/**
	 * updates the cart to show any items added to cart in other views. Called every time GardenView is clicked
	 * @param allItems - an arraylist that contains all objects needed to be displayed in cart
	 */
	public void updateCart(ArrayList<Object> allItems) {
		this.cartItems.clear();
		this.cartVb.getChildren().clear();
		this.cartVb.getChildren().add(this.cartLbl);
		
		for (Object o : allItems) {
			if(o instanceof Plant) {
				Plant p = (Plant) o;
				Image img = new Image("images/" + p.getName() + ".png");
				ImageView iv = new ImageView(img);
				iv.setPreserveRatio(true);
				iv.setFitHeight(50);
				GridPane.setConstraints(iv, 0, 0);
				
				GridPane cartgp = new GridPane();
				
				Label name = new Label(p.getName());
				name.setFont(new Font("Futura",14));
				name.setTextFill(Color.BLACK);
				GridPane.setConstraints(name, 1, 0);
				
				Label sun = new Label(p.getSunlight());
				sun.setFont(new Font("Futura",14));
				sun.setTextFill(Color.BLACK);
				GridPane.setConstraints(sun, 1, 1);
				
				Label water = new Label(p.getWater());
				water.setFont(new Font("Futura",14));
				water.setTextFill(Color.BLACK);
				GridPane.setConstraints(water, 1, 2);
				
				Label soil = new Label(p.getSoil());
				soil.setFont(new Font("Futura",14));
				soil.setTextFill(Color.BLACK);
				GridPane.setConstraints(soil, 1, 3);
				
				Button remove = new Button("Remove");
				remove.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
				remove.setOnMouseClicked(ctrl.getRemoveFromCartBtnHandler());
				remove.setId(p.getName());
				GridPane.setConstraints(remove, 1, 4);
				cartgp.getChildren().addAll(iv,name, sun, water, soil, remove);
				
				cartgp.setPadding(new Insets(5,5,5,5));
				
				this.cartItems.add(cartgp);
			}
			if(o instanceof Obstruction) {
				Obstruction obs = (Obstruction) o;
				Image img = new Image("images/" + obs.getName() + ".png");
				ImageView iv = new ImageView(img);
				iv.setPreserveRatio(true);
				iv.setFitHeight(50);
				GridPane.setConstraints(iv, 0, 0);
				GridPane cartgp = new GridPane();
				Label name = new Label(obs.getName());
				name.setFont(new Font("Futura",14));
				name.setTextFill(Color.BLACK);
				GridPane.setConstraints(name, 1, 0);
		
				Label type = new Label("Obs");
				
				Button remove = new Button("Remove");
				remove.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
				remove.setOnMouseClicked(ctrl.getRemoveFromCartBtnHandlerObs());
				remove.setId(obs.getName());
				GridPane.setConstraints(remove, 1, 3);
				
				cartgp.getChildren().addAll(iv,name, remove, type);
				
				cartgp.setPadding(new Insets(5,5,5,5));
				this.cartItems.add(cartgp);
			}
		}

		this.cartVb.getChildren().addAll(cartItems);
		this.cart.setContent(cartVb);
		borderpane.setLeft(cart);
	}
}
