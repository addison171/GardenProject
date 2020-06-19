/**
 * @author Addison Kuykendall
 */
package application;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class View {
	Controller control;
	Canvas canvas;
	Stage theStage;
	Scene theScene;
	Plant[][] plants;
	int canvasWidth = 1000;
	int canvasHeight = 500;
    GraphicsContext gc;
    public Button test;
    Image[] plantImages;
    BorderPane borderpane = new BorderPane(); 
    Button gardenViewBtn;
	Button inputDataBtn;
	Button previewBtn;
	Button editCellsBtn;
	Button searchBtn;
	Button finalViewBtn;
	Button saveBtn;
	Button searchPlants;
	Button insertObstruction;
	GridPane gardenGrid = new GridPane();
	ArrayList<ImageView> images = new ArrayList<>();
	
	/**
	 * 
	 * @param theStage primary stage being created for the view, an object of stage
	 */
	public View(Stage aStage) {
		theStage = aStage;
		theStage.setTitle("Garden");
		
		Group root = new Group();
		theScene = new Scene(root);
		theStage.setScene(theScene);
		
		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		
		HBox menu = new HBox();
		menu.setSpacing(1);
		
		
		//InsertObstruction button
		insertObstruction = new Button("Insert Obstruction");
		insertObstruction.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		insertObstruction.setStyle("-fx-font-size:14");
		insertObstruction.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		
		
		//input data
		inputDataBtn = new Button("Input Data");
		inputDataBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		inputDataBtn.setStyle("-fx-font-size:14");
		inputDataBtn.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

		//gardenView button
		gardenViewBtn = new Button("Garden View");
		gardenViewBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		gardenViewBtn.setStyle("-fx-font-size:14");
		gardenViewBtn.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		
		
		//preview
		previewBtn = new Button("Preview");
		previewBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		previewBtn.setStyle("-fx-font-size:14");
		previewBtn.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

		//edit cells
		editCellsBtn = new Button("Edit Cells");
		editCellsBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		editCellsBtn.setStyle("-fx-font-size:14");
		editCellsBtn.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		
		//search all
		searchBtn = new Button("Plants Search");
		searchBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		searchBtn.setStyle("-fx-font-size:14");
		searchBtn.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		
		//final view 
		finalViewBtn = new Button("Final View");
		finalViewBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		finalViewBtn.setStyle("-fx-font-size:14");
		finalViewBtn.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		
		//save button
		saveBtn = new Button("Save");
		saveBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		saveBtn.setStyle("-fx-font-size:14");
		saveBtn.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
		
		
		menu.getChildren().addAll(inputDataBtn, editCellsBtn, searchBtn, insertObstruction, gardenViewBtn, previewBtn, finalViewBtn, saveBtn);
		//menu.getChildren().addAll(inputDataBtn, gardenViewBtn ,previewBtn,editCellsBtn, searchBtn, finalViewBtn,saveBtn);

		
		this.borderpane.setTop(menu);
		root.getChildren().add(borderpane);

	}
	/**
	 * Creates array of Images filled with images of plants
	 * @param plants - an arraylist of plants
	 */
	public void importImages(ArrayList<Plant> plants) {
		plantImages = new Image[plants.size()];
        // Eclipse will look for <path/to/project>/bin/<relative path specified>
        String img_name = "images/";
        String ext = ".png";
        for(int i= 0; i<plants.size();i++) {
			plantImages[i]=createImage(img_name + plants.get(i).getName() + ext);
		}
       
	}
	/**
	 * Switches screens
	 * @param n - the new root. Usually a group
	 */
	public void changeScene(Parent n) {
		this.borderpane.setCenter(n);
		//theStage.getScene().setRoot(n);
		//theStage.setScene(n);
	}
	/**
	 * gets the current scene
	 * @return the current scene which is placed in the center of the borderpane
	 */
	public Node getScene() {
		return this.borderpane.getCenter();
	}
	/**
	 * Displays the image (usually to display plants)
	 * @param img - image to be displayed
	 */
	public Image createImage(String img) {
		Image image = new Image(img);
		return image;
	}
	/**
	 * Displays the image (usually to display plants)
	 * @param img - Plant name that correlates with image to be displayed
	 */
	public Image accessImage(String img) {
		for(int i = 0; i<plantImages.length; i++) {
			if(new Image("images/" + img + ".png") == plantImages[i])
				return plantImages[i];
		}
		return null;
	}
	
	/**
	 * Creates a gridpane with the size parameters that are inputed
	 * @param x - width of plot
	 * @param y - height of plot
	 */
	public void plotEditSize(int x, int y) {
		int scale = Math.max(x, y);
		for (int i=0; i<x; i++) {
			for (int j=0; j<y; j++) {
				ImageView iv = new ImageView();
				iv.setFitHeight(650 / scale);
				iv.setFitWidth(650 / scale);
				iv.setPreserveRatio(true);
				GridPane.setConstraints(iv, i, j);
				this.gardenGrid.getChildren().add(iv);
				this.gardenGrid.setGridLinesVisible(true);
			}
		}
		this.gardenGrid.getColumnConstraints().add(new ColumnConstraints(650/scale));
		this.gardenGrid.getRowConstraints().add(new RowConstraints(650/scale));
	}
	
	/**
	 * Adds a button 
	 * @param b - button being added
	 */
	public void addButton(Button b) {
		
	}
	public int getWidth() {
		return canvasWidth;
	}
	
	public int getHeight() {
		return canvasHeight;
	}
}
