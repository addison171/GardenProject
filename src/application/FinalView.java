/**
 * @author Addison Kuykendall
 */
package application;



import javafx.geometry.Insets;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class FinalView {
	Text scores;
	Text pros;
	Text cons;
	Button saveButton;
	Button editButton;
	Button newgardenButton;
	Group finalRoot = new Group();
	GridPane gardenGrid;
	BorderPane finalviewBP = new BorderPane();
	ScrollPane conflictScroll;
	VBox conflictVB;
	


	
	/**
	 * draws the screen
	 */
	
	public FinalView(){
		
		
		GridPane gp1 = new GridPane();
		gp1.setPadding(new Insets(10,10,10,10));
		
		Text Pros = new Text("Pros: Plants well spaced			");
		Pros.setFont(Font.font ("Verdana", 20));
		GridPane.setConstraints(Pros, 0, 0);
		
		Text Cons = new Text("Cons: Little Variety");
		Cons.setFont(Font.font ("Verdana", 20));
		GridPane.setConstraints(Cons, 2, 0);
		
		Text space = new Text(" ");
		GridPane.setConstraints(space, 0, 1);

		Text grade = new Text("Overall Grade: C - Decent, but nothing special");
		GridPane.setConstraints(grade, 0, 3);
		grade.setFont(Font.font ("Verdana", 20));

		gp1.getChildren().addAll(Pros,Cons,grade, space);
		

		gardenGrid = new GridPane();
		gardenGrid.setPadding(new Insets(10,10,10,10));
		gardenGrid.setGridLinesVisible(true);
		gardenGrid.setPrefWidth(550);
		
		this.conflictVB = new VBox();
		this.conflictVB.setPadding(new Insets(10,10,10,10));
		this.conflictVB.setSpacing(10);
		this.conflictScroll = new ScrollPane();
		this.conflictScroll.setContent(this.conflictVB);
		this.conflictScroll.setPrefViewportHeight(500);
		this.conflictScroll.setPrefViewportWidth(400);

		
		finalviewBP.setBottom(gardenGrid);
		finalviewBP.setCenter(gp1);
		
		Scene finalScene = new Scene(finalviewBP);
		finalRoot.getChildren().add(finalviewBP);
		
	}
	/**
	 * updates any changes made to garden grid in edit cells view
	 * @param garden - a gridpane to be updated in the view
	 * @return returns an updated gridpane in case any changes are made
	 */
	public GridPane updateGrid(GridPane garden) {
		gardenGrid = garden;
		finalviewBP.setCenter(garden);
		return garden;
	}
	/**
	 * Displays a grading of the user's garden
	 * 
	 * @param userScore - an integer indicating the score of the user's garden
	 * @param possibleScore - an integer indicating the total possible score of the user's garden
	 * @param cells - a 2D cell array
	 * @param objs - an arraylist containing all the objects in the user's garden
	 */
	public void displayScore(int userScore, int possibleScore, Cell[][] cell, ArrayList<Object> objs, ArrayList<String> conflicts) {
		
		GridPane gp1 = new GridPane();
		gp1.setPadding(new Insets(10,10,10,10));
		gp1.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Text showScore = new Text("Overall Grade: "+ userScore + "/" + possibleScore);
		showScore.setFont(Font.font ("Futura", 24));
		GridPane.setConstraints(showScore, 0, 0);
		

		Text space = new Text(" ");
		GridPane.setConstraints(space, 0, 1);
		Text gardenGradeTxt = new Text();
		
		if(((double)userScore/(double)possibleScore)<.5) {
			gardenGradeTxt.setText("Please go back and try to improve your garden");
		}
		if(((double)userScore/(double)possibleScore)>.5 && (userScore/possibleScore)<.75) {
			gardenGradeTxt.setText("Your garden is okay but can be improved");
		}
		if(((double)userScore/(double)possibleScore)>.75 ) {
			gardenGradeTxt.setText("Your garden is very good. Save your garden or make an another");
		}
		
		gardenGradeTxt.setFont(Font.font ("Futura", 20));
		GridPane.setConstraints(gardenGradeTxt, 0, 2);
		gp1.getChildren().addAll(showScore, gardenGradeTxt, space);
		
		this.conflictVB.getChildren().clear();
		
		for (String conf : conflicts) {
			Text confTxt = new Text();
			confTxt.setWrappingWidth(400);
			confTxt.setText(conf);
			confTxt.setFont(Font.font("Futura", 12));
			this.conflictVB.getChildren().add(confTxt);
		}
		finalviewBP.setLeft(conflictScroll);
		finalviewBP.setTop(gp1);
	}
	

}
