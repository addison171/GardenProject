/**
 *  @author Addison Kuykendall
 * #
 */
package application;
	
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public class Controller extends Application {
	private Model model;
	View view;
	private GardenView gv;
	private HomeView hv;
	private InputDataView idv;
	private InsertObstructionView iov;
	private Preview pv;
	private EditCellsView ecv;
	private SearchAllView searchv;
	private FinalView fv;
	private SaveAsView sav;
	String name;
	Stage s;
	FileChooser fileChooser = new FileChooser();

    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Starts the program. Instantiates each view so the program can switch screens.
     * Sets all the buttons to the associate button handlers.
     * @param primaryStage - stage from the view
     */
    @Override
    public void start(Stage primaryStage) {
    	view = new View(primaryStage);
    	model = new Model(view.getWidth(), view.getHeight());
    	model.allPlants = model.readPlantsFromCSV("PlantData.csv");
    	model.createObstructions();
    	//The home view
    	this.hv = new HomeView(primaryStage);
    	hv.createNew.setOnAction(saveAsClick());  
    	hv.editExisting.setOnAction(openExistingClick());  
    	
    	//primaryStage.setScene(hv.scene);
    	view.changeScene(hv.grid);
    	
    	//Insert obstruction view
    	iov = new InsertObstructionView(this);
    	iov.addToCartBtn.setOnAction(getAddToCartBtnHandlerObs());
    	
    	view.inputDataBtn.setOnAction(inputDataClick());
    	view.editCellsBtn.setOnAction(editCellsClick());
    	view.finalViewBtn.setOnAction(finalViewClick());
    	view.previewBtn.setOnAction(previewClick());
    	view.searchBtn.setOnAction(searchClick());
    	view.saveBtn.setOnAction(saveAllClick());
    	view.gardenViewBtn.setOnAction(gardenViewClick());
		view.insertObstruction.setOnAction(insertObstructionClick());
		view.saveBtn.setOnAction(saveGardenClick());

    	
    	//The SaveAsView
    	sav = new SaveAsView();
    	sav.saveAsBtn.setOnAction(openNewClick());
    	
    	//The garden View
        this.gv = new GardenView(this);

		
		//The preview View
    	this.pv = new Preview();
    	pv.changeSeasonBtn.setOnAction(changeSeasonClick());
		pv.inputGarden(gv.gardenGrid);


        //The InputDataView
        this.idv = new InputDataView();        
		idv.saveDataBtn.setOnAction(saveAllClick());
		idv.createPlot.setOnAction(createPlotClick());

		//The search view
        this.searchv = new SearchAllView(this);
        searchv.searchPlantsBtn.setOnAction(searchPlantsClick());

        //The Edit cells view
        this.ecv = new EditCellsView();
        ecv.saveDataBtn.setOnAction(SelectCellsClick());
        
  
		//The final view
		this.fv = new FinalView();

		
		//Importing the plant images
       // view.importImages(model.allPlants);
		s = primaryStage;
		primaryStage.show();
    }
    
    /**
     * Event handler for opening a new garden
     * @return the method called
     */
    public EventHandler<ActionEvent> openNewClick(){
    	name = sav.newCellFld.getText();
    	return event ->openNewClicked();
    }

    public void openNewClicked() {
    	view.changeScene(idv.inputDataRoot);
    }
    
    /**
     * Saves the garden on the computer
     * @return
     */
    public EventHandler<ActionEvent> saveGardenClick(){
 	   return event -> saveGardenClicked();
    }
    
    public void saveGardenClicked() {
 	   model.saveAll(name, view.theStage, fileChooser, model);
    }
    
    /**
     * Event handler for opening an existing garden
     * @return The method called
     */
    public EventHandler<ActionEvent> openExistingClick(){
    	return event ->openExistingClicked();
    }
    
    public void openExistingClicked() {
                        File file = fileChooser.showOpenDialog(view.theStage);
                        if (file != null) {
                            model = model.open(file, view, gv);
                            for(ImageView iv : view.images) {
                            	iv.setOnMouseDragged(plantIconDrag(iv, model.gardenGridList.get(view.images.indexOf(iv))));
                            }
                        }
    	gardenViewClicked();
    }
    
    /**
     * Event handler for going from the home screen to opening a project
     * @return the method called
     */
    public EventHandler<ActionEvent> saveAsClick(){
    	return event ->saveAsClicked();
    }
    
    public void saveAsClicked() {
    	view.changeScene(sav.saveAsRoot);
    }
    
    /**
     * Handler for when the user inputs cells that they would like to change the information
     * @return the Method called
     */
    public EventHandler<ActionEvent> SelectCellsClick(){
    	return event -> SelectCellsClicked();
    }
    
    public void SelectCellsClicked() {
    	String coordinates = ecv.cellFld.getText();
    	String[] splitC = coordinates.split(",");
    	int x1 = Integer.parseInt(splitC[0]);
    	int y1 = Integer.parseInt(splitC[1]);
    	int x2 = Integer.parseInt(splitC[2]);
    	int y2 = Integer.parseInt(splitC[3]);
    	model.editCells(model.cells, x1, y1, x2, y2, ecv.soilCbx.getValue(), ecv.waterCbx.getValue(), ecv.sunCbx.getValue());
    }
    
    
    /**
     * Handler to move to the garden view
     * @return The method called
     */
    public EventHandler<ActionEvent> gardenViewClick(){
    	return event ->gardenViewClicked();
    }
    /**
     * Updates Garden View's scene to contain updates form other screens
     * Also makes the plants in the cart draggable
     */
    public void gardenViewClicked() {
    	view.changeScene(gv.gardenRoot);
    	if(!this.model.gardenGridList.isEmpty()) {
    		pv.revertOpacity(this.model.gardenGridList, this.view.images);
    	}
    	this.model.gardenCart.clear();
    	for(Plant p: this.model.cartPlants) {
    		this.model.gardenCart.add(p);
    	}
    	for(Obstruction o: this.model.cartObs) {
    		this.model.gardenCart.add(o);
    	}

    	gv.updateCart(this.model.gardenCart);
    	
    	int j = 0;
    	int i = 0;
    	for(GridPane p1 : gv.cartItems) {
    		if((((Label) p1.getChildren().get(3)).getText()).equals("Obs")){
    			p1.setOnMousePressed(onPlantDrag(this.model.cartObs.get(j)));
    			j++;
    		}
    		else {
    			p1.setOnMousePressed(onPlantDrag(this.model.cartPlants.get(i)));
    			i++;
    		}
    	}
		//cartgp.setOnMousePressed(event -> control.onPlantPress(event, p));
    	
    	view.gardenGrid = gv.updateGrid(view.gardenGrid);
    	
    }
  
    
    /**
     * Handler for when the user inputs the size of the plot
     * @return The method called
     */
    public EventHandler<ActionEvent> createPlotClick(){
    	return event ->createPlotClicked();
    }
    /**
     * Creates the plot of the size inputed, determines the size of each cell, and displays the plot in the input data screen
     */
    public void createPlotClicked() {
    	String tx = idv.x.getText();
    	String ty = idv.y.getText();
    	model.plotX = Integer.parseInt(tx);
    	model.plotY = Integer.parseInt(ty);
    	model.cells = new Cell[model.plotX][model.plotY];
    	view.plotEditSize(model.plotX, model.plotY);
    	int scale = Math.max(model.plotX, model.plotY);
    	model.cellWidth = 650/scale;
    	model.cellHeight = 650/scale;
    	view.gardenGrid = idv.displayPlot(view.gardenGrid);
    }
    
    /**
     * Clicking to search all 
     * @return returns event that the search goes to when clicked
     */
    
    public EventHandler<ActionEvent> searchClick() {
    	return event -> searchClicked();
	}
    
    
    /**
     * Transition to search UI
     * 
     */
    public void searchClicked() {
    	view.changeScene(searchv.searchRoot);
    }
    
    /**
     * Clicking to insert obstructions
     * @return returns event that leads to obstructions
     */
    public EventHandler<ActionEvent> insertObstructionClick() {
    	return event -> insertObstructionClicked();
	}
    
    
    /**
     * Transition to insert obstruction ui after button clicked
     * @param event - event object from the insert obstruction button being clicked
     */
    public void insertObstructionClicked() {
    	view.gardenGrid = iov.updateGrid(view.gardenGrid);
    	view.changeScene(iov.iovRoot);
    }
	
    /**
     * Eventhandler for viewing the garden button being clicked
     * @return returns an event that the view button was clicked
     */
    public EventHandler<ActionEvent> viewClick() {
    	return event -> viewClicked();
	}
    
    /**
     * Transition to the view garden ui
     * @param event - event object from view button being clicked
     */
    public void viewClicked() {
    	view.changeScene(fv.finalRoot);
    	
    }
    
    /**
     * Eventhandler for input data button being clicked
     * @return returns event object from clicking input data button
     */
    public EventHandler<ActionEvent> inputDataClick() {
    	return event -> inputDataClicked();
	}		

    
    /**
     * Transition to input data screen from button click
     * @param event - event object from input data button being clicked
     */
    public void inputDataClicked() {
    	view.changeScene(idv.inputDataRoot);
    }
    
    /**
     * The preview button being clicked
     * @return returns event object that preview button was selected
     */
    public EventHandler<ActionEvent> previewClick() {
    	return event -> previewClicked();
	}
    
    /**
     * Preview button transition
     * @param event - event object for the preview button being clicked
     */
    public void previewClicked() {
    	view.changeScene(pv.previewRoot);
    	view.gardenGrid = pv.updateGrid(view.gardenGrid);
    }
    
    /**
     * The edit cell button being clicked
     * @return return event object for the edit cells button being clicked
     */
    public EventHandler<ActionEvent> editCellsClick() {
    	return event -> editCellsClicked();
	}
    
    /**
     * Transition to edit cells ui from the button being clicked
     * @param event - event object confirming the editcells button was clicked
     */
    public void editCellsClicked() {
    	view.changeScene(ecv.editCellsRoot);
    	if(!this.model.gardenGridList.isEmpty()) {
        	pv.revertOpacity(this.model.gardenGridList, this.view.images);
    	}
    	view.gardenGrid = ecv.updateGarden(view.gardenGrid);
    }
    
    /**
     * The final view button being clicked
     * @return returns object that finalview button was clicked
     */
    public EventHandler<ActionEvent> finalViewClick() {
    	return event -> finalViewClicked();
	}
    
    /**
     * View transition to final view screen
     * @param event - event object confirming the finalview button was clicked
     */
    public void finalViewClicked() {
    	view.gardenGrid = fv.updateGrid(view.gardenGrid);
    	view.changeScene(fv.finalRoot);
    	if(!this.model.gardenGridList.isEmpty()) {
        	pv.revertOpacity(this.model.gardenGridList, this.view.images);
    		int score = this.model.plantGrader(this.model.gardenGridList, this.model.cells);
    		int totalScore = this.model.totalScore(this.model.gardenGridList);
    		fv.displayScore(score, totalScore, this.model.cells, this.model.gardenGridList, model.ConflictLog);
    	}
    }
    
    /**
     * The save all button being clicked
     * @return returns events object that saveall button was clicked
     */
    public EventHandler<ActionEvent> saveAllClick() {
    	return event -> saveAllClicked();
	}
    
    /**
     * Saved all progress after button click
     * @param event - event object that confirms the saveall button was clicked
     */
    public void saveAllClicked() {
    	model.soilType = idv.soilCbx.getValue();
    	model.sunLight = idv.sunCbx.getValue();
    	model.waterLevel = idv.waterCbx.getValue();
    	model.cells = model.inputData();   
    }

    /**
     * Calls the search plant clicked function when the button is clicked
     * @return The function that should happen
     */
    public EventHandler<ActionEvent> searchPlantsClick() {
    	return event -> searchPlantsClicked();
    }
    /**
     * event handler for what should happen when searchPlants is clicked
     */
    public void searchPlantsClicked() {
    	model.f = new Filter(searchv.bloomCbx.getValue().toString(), searchv.soilCbx.getValue().toString(), 
    						 searchv.sunCbx.getValue().toString(), searchv.waterCbx.getValue().toString());
    	ArrayList<Plant> filteredPlants = model.f.search(model.allPlants, (int)searchv.errorSld.getValue());

    	this.searchv.displayResults(filteredPlants);
    }
    /**
     * Event handler for when plants are added to the cart
     * @return the method called
     */
    public EventHandler<MouseEvent> getAddToCartBtnHandler(){
    	return event -> addToCartBtnClicked(event);
    }
    /**
     * Event handler for when obstructions are added to the cart
     * @return the method called
     */
    public EventHandler<ActionEvent> getAddToCartBtnHandlerObs(){
    	return event -> addToCartBtnClickedObs(event);
    }
    public void addToCartBtnClicked(MouseEvent e) {
    	Button thisBtn = (Button)e.getSource();
    	
    	this.model.cartPlants.add(this.model.plantMap.get(thisBtn.getId()));
    	this.searchv.displayCart(model.cartPlants);
    }
    /**
     * Adds obstructions to the obstructions carts
     * @param e - the click
     */
    public void addToCartBtnClickedObs(ActionEvent e) {
    	this.model.cartObs.add(model.ObsMap.get(iov.obstruction.getValue()));
    	this.iov.displayCart(model.cartObs);
    }
    
    /**
     * Removes plants from the cart
     * @return method to be called to remove the plant
     */
    public EventHandler<MouseEvent> getRemoveFromCartBtnHandler(){
    	return event -> removeFromCartBtnClicked(event);
    }
    public void removeFromCartBtnClicked(MouseEvent e) {
    	Button thisBtn = (Button)e.getSource();
    	
    	this.model.cartPlants.remove(this.model.plantMap.get(thisBtn.getId()));
    	this.model.gardenCart.remove(this.model.plantMap.get(thisBtn.getId()));
    	this.searchv.displayCart(model.cartPlants);
    	this.gv.updateCart(model.gardenCart);
    	if(view.getScene()==gv.gardenRoot){
    		gardenViewClicked();
    	}
    }
    /**
     * Event handler to remove obstructions from the cart
     * @return the method to be called to remove the obstruction
     */
    
    public EventHandler<MouseEvent> getRemoveFromCartBtnHandlerObs(){
    	return event -> removeFromCartBtnClickedObs(event);
    }
    public void removeFromCartBtnClickedObs(MouseEvent e) {
    	Button thisBtn = (Button)e.getSource();
    	
    	this.model.cartObs.remove(this.model.ObsMap.get(thisBtn.getId()));
    	this.model.gardenCart.remove(this.model.ObsMap.get(thisBtn.getId()));
    	this.iov.displayCart(model.cartObs);
    	this.gv.updateCart(model.gardenCart);
    	if (view.getScene()==gv.gardenRoot) {
    		gardenViewClicked();
    	}
    }
    
    
    
    /**
     * Event Handler when seasonChange button is clicked
     * @return the method to be called when the button is clicked
     */
    public EventHandler<ActionEvent> changeSeasonClick(){
    	return event -> onChangeSeasonClick();
    }
    public void onChangeSeasonClick() {
    	pv.revertOpacity(this.model.gardenGridList, this.view.images);
    	pv.changeSeason(pv.season.getValue(), this.model.gardenGridList, this.view.images);
    }
    
    /**
     * event handler for dragging
     * @param p - the plant or obstruction being dragged
     * @return the method to be called by the handler
     */
    public EventHandler<MouseEvent> onPlantDrag(Object p){
    	return event -> onPlantPress(event, p);
    }
    
    /**
     * Creates a copy of the plant or obstruction image and allows it to be dragged
     * @param event - the mouse
     * @param o - the plant or obstruction being dragged
     */
    public void onPlantPress(MouseEvent event, Object o) {
    	//TODO spawn plant icon
    	if(o instanceof Plant) {
    		Plant a = (Plant) o;
    		Plant p = new Plant(a.getName(), a.getSoil(), a.getSunlight(), a.getWater(), a.getBloom(), a.getDescription());
    		p.setScale(a.getScale());
    		this.model.gardenGridList.add((Object)p);
    		o = (Object)p;
    	}
    	if(o instanceof Obstruction) {
    		Obstruction a = (Obstruction)o;
    		Obstruction obs = new Obstruction(a.getName());
    		this.model.gardenGridList.add((Object)obs);
    		o = (Object)obs;
    	}
    	model.setX(event.getX());
    	model.setY(event.getY());
    	ImageView im = gv.makeIcon((Object)o, event.getX(), event.getY(), model.cellHeight, model.cellWidth);
    	im.setOnMouseDragged(plantIconDrag(im, (Object)o));
    	view.images.add(im);
    }
    /**
     * handler for picture being dragged in the grid pane
     * @param im - the image of the object
     * @param o - the object
     * @return - the drag method
     */
    public EventHandler<MouseEvent> plantIconDrag(ImageView im, Object o){
    	return event -> dragPlantIcon(event, im, o);
    }
    /**
     * Moves the image across the grid. Also sets the plant or obstructions column and row location
     * @param event - the mouse drag
     * @param im - the image being moved
     * @param o - the object associated with the image
     */
    public void dragPlantIcon(MouseEvent event, ImageView im, Object o) {
    	model.setX(model.getX() + event.getX()); //event.getX() is the amount of horiz drag
    	model.setY(model.getY() + event.getY());
    	gv.setPlantIconLocation(im, model.getX(), model.getY());
    	int x = (int)((im.getTranslateX()/650)*model.plotX);
    	int y = (int)((im.getTranslateY()/650)*model.plotY);
		if(o instanceof Plant) {
			((Plant) o).setXCell(x);
			((Plant) o).setYCell(y);
			((Plant) o).setX(im.getLayoutX() - (1350/2) + model.getX());
			((Plant) o).setY(im.getLayoutY() - (950/2) + model.getY());
			
		}
		else {
			((Obstruction) o).setXCell(x);
			((Obstruction) o).setYCell(y);
			((Obstruction) o).setX(im.getLayoutX() - (1350/2) + model.getX());
			((Obstruction) o).setY(im.getLayoutY() - (950/2) + model.getY());
		}
		if(event.getButton() == MouseButton.SECONDARY) {
    		gv.deleteIcon(im, event.getX(), event.getY(), view.images, model.gardenGridList);
    	}	
		
    }
}
