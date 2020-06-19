/**
 * @author Sohan Gadiraju, Addison Kuykendall
 */
package application;

import javafx.geometry.Insets;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Preview {
	Controller control;
	ComboBox<String> timeOfDay;
	ComboBox<String> season;
	Button backToEdit;
	Group previewRoot = new Group();
	Scene scene;
	Button changeSeasonBtn;
	GridPane gardenGrid;
	BorderPane bp;


	public Preview() {
		
		bp = new BorderPane();
		bp.setPadding(new Insets(5,5,5,5));
		this.scene = new Scene(bp);
		
		gardenGrid = new GridPane();
		gardenGrid.setPadding(new Insets(10,10,10,10));
		gardenGrid.setGridLinesVisible(true);
		gardenGrid.setPrefWidth(550);
		
		GridPane previewPane = new GridPane();
		
		changeSeasonBtn = new Button("Change Season");
		GridPane.setConstraints(changeSeasonBtn, 1, 6);
		changeSeasonBtn.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		//Season
		Label seasonLbl = new Label(" Season Level: ");
		GridPane.setConstraints(seasonLbl, 0, 2);
		seasonLbl.setFont(new Font("Futura",14));
		seasonLbl.setTextFill(Color.BLACK);
		
		season = new ComboBox<String>();
		GridPane.setConstraints(season, 1, 2);
		season.getItems().addAll(
				"Spring",
				"Summer",
				"Fall",
				"Winter"
		);
		previewPane.getChildren().addAll(changeSeasonBtn, seasonLbl, season);
		previewPane.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		bp.setRight(previewPane);
		bp.setCenter(gardenGrid);
		
		//scene = new Scene(bp);
		//this.scene.setRoot(bp);
		
		previewRoot.getChildren().add(bp);
	}
	/**
	 * Updates the garden grid from the main view with changes made in Preview
	 * @param garden - changes the gardenGrid in this view, this parameter should usually be filled with the main views garden
	 * @return returns garden in case any changes are made
	 */
	public GridPane updateGrid(GridPane garden) {
		gardenGrid = garden;
		bp.setCenter(garden);
		return garden;
	}
	/**
	 * Changes the opacity of any plant which does not bloom in selected season to 0
	 * @param season - Season selected by user
	 * @param objs - an arraylist of objects that are currently on the grid
	 * @return returns garden in case any changes are made
	 */
	public void changeSeason(String season, ArrayList<Object> objs, ArrayList<ImageView> images) {
		for(int i = 0; i<objs.size(); i++) {
			if(objs.get(i) instanceof Plant) {
				Plant p = (Plant) objs.get(i);
				images.get(i).setOpacity(1);
				if (!(p.getBloom().equals(season))) {
					images.get(i).setOpacity(0);
				}
				else {
					images.get(i).setOpacity(100);
				}
			}
		}
	}
	public void revertOpacity(ArrayList<Object> objs, ArrayList<ImageView> images) {
		for(int i = 0; i<objs.size(); i++) {
			if(objs.get(i) instanceof Plant) {
				images.get(i).setOpacity(100);
			}
		}
	}
	
	public void inputGarden(GridPane garden) {
		gardenGrid = garden;   
	}
}

