/**
 * @author Sohan Gadiraju
 */
package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Plant> plants;
	ArrayList<Plant> allPlants;
	ArrayList<Plant> cartPlants;
	ArrayList<Obstruction> allObstructions;
	ArrayList<Obstruction> cartObs;
	ArrayList<Plant> resultPlants;
	ArrayList<Object> gardenCart;
	ArrayList<Object> gardenGridList;
	HashMap<String,Plant> plantMap;
	HashMap<String,Obstruction> ObsMap;
	ArrayList<String> ConflictLog;
	Filter f;
	File saveFile;
	int score;
	Cell[][] cells;
	String soilType;
	String waterLevel;
	String sunLight;
	double canvasHeight;
	double canvasWidth;
	int plotX;
	int plotY;
	double x;
	double y;
	double cellWidth;
	double cellHeight;

	//private Desktop desktop = Desktop.getDesktop();
	
	public Model() {
		this.gardenGridList = new ArrayList<Object>();
		this.plants = new ArrayList<Plant>();
		this.allPlants = new ArrayList<Plant>();
		this.cartPlants = new ArrayList<Plant>();
		this.resultPlants = new ArrayList<Plant>();
		this.plantMap = new HashMap<String,Plant>();
		this.cartObs = new ArrayList<Obstruction>();
		this.gardenCart = new ArrayList<Object>();
		this.ConflictLog = new ArrayList<String>();
		this.allObstructions = new 	ArrayList<Obstruction>();
		this.ObsMap = new 	HashMap<String,Obstruction>();


		
	}
	public Model(double width, double height) {
		canvasHeight = height;
		canvasWidth = width;
		this.gardenGridList = new ArrayList<Object>();
		this.plants = new ArrayList<Plant>();
		this.allPlants = new ArrayList<Plant>();
		this.cartPlants = new ArrayList<Plant>();
		this.resultPlants = new ArrayList<Plant>();
		this.plantMap = new HashMap<String,Plant>();
		this.cartObs = new ArrayList<Obstruction>();
		this.gardenCart = new ArrayList<Object>();
		this.ConflictLog = new ArrayList<String>();
		this.allObstructions = new 	ArrayList<Obstruction>();
		this.ObsMap = new 	HashMap<String,Obstruction>();
	}
	public Model(String soil,String water,String sun ) {
		soilType = soil;
		waterLevel = water;
		sunLight = sun;
		this.gardenGridList = new ArrayList<Object>();
		this.plants = new ArrayList<Plant>();
		this.allPlants = new ArrayList<Plant>();
		this.cartPlants = new ArrayList<Plant>();
		this.resultPlants = new ArrayList<Plant>();
		this.plantMap = new HashMap<String,Plant>();
		this.cartObs = new ArrayList<Obstruction>();
		this.ConflictLog = new ArrayList<String>();
		this.allObstructions = new 	ArrayList<Obstruction>();
		this.ObsMap = new 	HashMap<String,Obstruction>();




		this.gardenCart = new ArrayList<Object>();
	}
	public Model(int plotX, int plotY) {
		cells = new Cell[plotX][plotY];
		for(int i = 0; i<cells.length; i++) {
			for(int q = 0; q<cells[0].length; q++) {
	        	cells[i][q] = new Cell();
			}
		}
		this.gardenGridList = new ArrayList<Object>();
		this.plants = new ArrayList<Plant>();
		this.allPlants = new ArrayList<Plant>();
		this.cartPlants = new ArrayList<Plant>();
		this.resultPlants = new ArrayList<Plant>();
		this.plantMap = new HashMap<String,Plant>();
		this.cartObs = new ArrayList<Obstruction>();
		this.gardenCart = new ArrayList<Object>();
		this.ConflictLog = new ArrayList<String>();
		this.allObstructions = new 	ArrayList<Obstruction>();
		this.ObsMap = new 	HashMap<String,Obstruction>();


	}
	
	public void createObstructions(){
		
		this.ObsMap.put("House", new Obstruction("House"));
		this.allObstructions.add(this.ObsMap.get("House"));
		
		this.ObsMap.put("Pond", new Obstruction("Pond"));
		this.allObstructions.add(this.ObsMap.get("Pond"));
		
		this.ObsMap.put("Building", new Obstruction("Building"));
		this.allObstructions.add(this.ObsMap.get("Pond"));

		this.ObsMap.put("Black Box", new Obstruction("Black Box"));
		this.allObstructions.add(this.ObsMap.get("Black Box"));
	}
	

	/**
	 * method to load data into allPlants
	 * @param filename - a csv file containing data
	 * @return returns a new arraylist of plants with all data from file
	 */
	public ArrayList<Plant> readPlantsFromCSV(String fileName) { 
		ArrayList<Plant> plants = new ArrayList<>(); 
		Path pathToFile = Paths.get(fileName); 
		// create an instance of BufferedReader 
		// using try with resource, Java 7 feature to close resources 
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) 
		{ 
			// read the first line from the text file
			String line = br.readLine(); 
			// loop until all lines are read
			while (line != null) { 
				
				// use string.split to load a string array with the values from 
				// each line of 
				// the file, using a comma as the delimiter 
				String[] attributes = line.split(","); 
				Plant p = createPlant(attributes);
				p.setScale(Double.parseDouble(attributes[6]));
				
				// adding Plant into ArrayList 
				plants.add(p);
				this.plantMap.put(p.getName(), p);
				
				// read next line before looping 
				// if end of file reached, line would be null
				line = br.readLine(); 
			} 
		} 
		
		catch (IOException ioe) { 
			ioe.printStackTrace(); 
		}
		return plants;
	}
	/**	
	 * Creates plant according to consumed metadata
	 * @param metadata - metadata consumed from a line of the csv file
	 * @return returns a new plant according to consumed Metadata
	 */
	private Plant createPlant(String[] metadata) { 
		String name = metadata[0]; 
		String idealSoil = metadata[1];
		String idealWaterLevel = metadata[3];
		String idealSunlightLevel = metadata[2];
		String bloomTime = metadata[4];
		String description = metadata[5];
		
		
		// create and return Plant of this metadata 
		return new Plant(name, idealSoil,idealSunlightLevel, idealWaterLevel, bloomTime, description);
	}
	/**
	 * saves the file
	 * @param fileName - the named of the saved file
	 * @param ext - the extension to save it as
	 * @param theStage - the stage the program is using
	 * @param fileChooser - pop up file chooser
	 */
	public void saveAll(String fileName, Stage theStage, FileChooser fileChooser, Model model) {
		try {
		   saveFile = fileChooser.showSaveDialog(theStage);
		   fileChooser.initialFileNameProperty().set(fileName);
		   if(saveFile!=null) {
			   File dir = saveFile.getParentFile();
			   fileChooser.setInitialDirectory(dir);
		   }
		   FileOutputStream fos = new FileOutputStream(saveFile);
		   ObjectOutputStream outputStream = new ObjectOutputStream(fos);
		   outputStream.writeObject(model);
		   outputStream.close();
		} 
		catch (IOException ex) {
			   System.err.println(ex);
			}
	}
	/**
	 * opens file browser to open a new garden
	 * @return - A previously saved Model
	 */
	public Model open(File file, View view, GardenView gv) {
		Model savedModel = null;	 
	    try {
	        FileInputStream fis = new FileInputStream(file);
	        ObjectInputStream inputStream = new ObjectInputStream(fis);
	        savedModel = (Model) inputStream.readObject();
	        inputStream.close();
	    } 
	    catch (IOException | ClassNotFoundException ex) {
	        System.err.println(ex);
	    }
	    addImagesToView(savedModel, view, gv);
	    return savedModel;
	}
	
	/**
	 * When a file is loaded in, this method adds all the images to the grid
	 * @param model - the model being loaded in
	 * @param view - the view so images can be accessed
	 * @param gv - gardenview so the grid can be changed
	 */
	public void addImagesToView(Model model, View view, GardenView gv) {
        view.plotEditSize(model.plotX, model.plotY);
        view.importImages(model.allPlants);
		gv.gardenGrid = view.gardenGrid;
        for(Object obj : model.gardenGridList) {
        	if(obj instanceof Plant) {
        		Plant p = (Plant)obj;
        		ImageView iv = gv.makeIcon(p, 0, 0, model.cellHeight, model.cellWidth);
        		view.images.add(iv);
        		iv.setTranslateX(p.getX());
        		iv.setTranslateY(p.getY());
        	}
        	if(obj instanceof Obstruction) {
        		Obstruction o = (Obstruction)obj;
        		ImageView iv = gv.makeIcon(o, 0, 0, model.cellHeight, model.cellWidth);
        		view.images.add(iv);
        		iv.setTranslateX(o.getX());
        		iv.setTranslateY(o.getY());
        	}
        }
	}

	/**
	 * allows user to change the data for all cells.
	 * @return returns the new updated cell array
	 */
	public Cell[][] inputData() {
		for(int i = 0; i<cells.length; i++) {
			for(int q = 0; q<cells[0].length; q++) {
				if(cells[i][q]==null) {
					cells[i][q] = new Cell();
				}
	        	cells[i][q].setSoil(soilType);
	        	cells[i][q].setSunlight(sunLight);
	        	cells[i][q].setWater(waterLevel);
			}
		}
		return cells;
	}
	/**
	 * Allows user to add an object of either a plant or obstruction by changing the cells in the parameter
	 * @param cells - all cells available
	 * @param x1 - x coordinate of top left cell of placed object
	 * @param y1 - y coordinate of top left cell of placed object
	 * @param x2 - x coordinate of bottom right cell of placed object
	 * @param y2 - y coordinate of bottom right cell of placed object
	 * @param obj - the object being added to the cells
	 * @return - the new updated cell array
	 */
	public Cell[][] addObject(Cell[][] cells, int x1, int y1, int x2, int y2, Object obj) {
		for(int i = x1; i<= x2; i++) {
			for(int j= y1; j<=y2; j++) {
				if (obj instanceof Plant) {
					cells[i][j].setPlant((Plant)obj);
				}
				else if (obj instanceof Obstruction) {
					cells[i][j].setObstruction((Obstruction)obj);
				}
			}
		}
		
		return cells;
	}
	/**
	 * Allows user to change the data in selected cells
	 * @param cells - all cells available
	 * @param x1 - x coordinate of top left cell selected
	 * @param y1 - y coordinate of top left cell selected
	 * @param x2 - x coordinate of bottom right cell selected
	 * @param y2 - y coordinate of bottom right cell selected
	 * @param newSoil - the new soil needed to be assigned to highlighted cells
	 * @param newWater - the new water level needed to be assigned to highlighted cells
	 * @param newSun - the new sunlight level needed to be assigned to highlighted cells
	 * @return - the updated list of cells
	 */
	public Cell[][] editCells(Cell[][] cells, int x1, int y1, int x2, int y2, String newSoil, String newWater, String newSun) {
		for(int i = x1; i<= x2; i++) {
			for(int j= y1; j<=y2; j++) {
				cells[i][j].setSoil(newSoil);
				cells[i][j].setWater(newWater);
				cells[i][j].setSunlight(newSun);
			}
		}
		return cells;
	}
	/**
	 * Grades plants depending on attributes of garden and the plants own attributes (1 point added for every common attribute)
	 * @param objects - ArrayList of all objects in the garden
	 * @param cells - The cell the plant is located in
	 * @return - the score of the garden as an integer
	 */
	public int plantGrader(ArrayList<Object> objs, Cell[][] cells) {
		int score = 0;
		this.ConflictLog.clear();
		for(Object o: objs) {
			if (o instanceof Plant) {
				Plant p = (Plant) o;
				p.getSoil();
				if (p.getSoil().equals(cells[p.getYCell()][p.getXCell()].getSoil())) {
					score++;
				}
				else if (p.getSoil().equals("Sandy") & cells[p.getYCell()][p.getXCell()].getSoil().equals("Clay") ||
						p.getSoil().equals("Clay") & cells[p.getYCell()][p.getXCell()].getSoil().equals("Sandy")) {
					score--;
					this.ConflictLog.add("Your " + p.getName() + " at cell (" + p.getXCell() + "," + p.getYCell() +
							") has a conflict because the soil is " + cells[p.getYCell()][p.getXCell()].getSoil() + 
							" but this plant prefers " + p.getSoil() + " soil. THIS LOST YOU 1 POINT");
				}
				else {
					this.ConflictLog.add("Your " + p.getName() + " at cell (" + p.getXCell() + "," + p.getYCell() +
							") has a conflict because the soil is " + cells[p.getYCell()][p.getXCell()].getSoil() + 
							" but this plant prefers " + p.getSoil() + " soil. THIS DID NOT GIVE YOU ANY POINTS");
				}
				
				if (p.getWater().equals(cells[p.getYCell()][p.getXCell()].getWater())) {
					score++;
				}
				else if (p.getWater().equals("Wet") & cells[p.getYCell()][p.getXCell()].getWater().equals("Dry") || 
						p.getWater().equals("Dry") & cells[p.getYCell()][p.getXCell()].getWater().equals("Wet")) {
					score--;
					this.ConflictLog.add("Your " + p.getName() + " at cell (" + p.getXCell() + "," + p.getYCell() +
							") has a conflict because the moisture level is " + cells[p.getYCell()][p.getXCell()].getWater() + 
							" but this plant prefers " + p.getWater() + " water level. THIS LOST YOU 1 POINT");
				}
				else {
					this.ConflictLog.add("Your " + p.getName() + " at cell (" + p.getXCell() + "," + p.getYCell() +
							") has a conflict because the moisture level is " + cells[p.getYCell()][p.getXCell()].getWater() + 
							" but this plant prefers " + p.getWater() + " water level. THIS DID NOT GIVE YOU ANY POINTS");
				}
				
				if (p.getSunlight().equals(cells[p.getYCell()][p.getXCell()].getSun())) {
					score++;
				}
				else if (p.getSunlight().equals("Sunny") & cells[p.getYCell()][p.getXCell()].getSun().equals("Shady") || 
						p.getSunlight().equals("Shady") & cells[p.getYCell()][p.getXCell()].getSun().equals("Sunny")) {
					score--;
					this.ConflictLog.add("Your " + p.getName() + " at cell (" + p.getXCell() + "," + p.getYCell() +
							") has a conflict because the sunlight level is " + cells[p.getYCell()][p.getXCell()].getSun() + 
							" but this plant prefers " + p.getSunlight() + " sunlight level. THIS LOST YOU 1 POINT");
				}
				else {
					this.ConflictLog.add("Your " + p.getName() + " at cell (" + p.getXCell() + "," + p.getYCell() +
							") has a conflict because the sunlight level is " + cells[p.getYCell()][p.getXCell()].getSun() + 
							" but this plant prefers " + p.getSunlight() + " sunlight level. THIS DID NOT GIVE YOU ANY POINTS");
				}
			}
		}
		return score;
	}
	/**
	 * Calculates the total possible points available for garden based on amount of plants placed in Garden
	 * @param objs - ArrayList of all objects in the garden
	 * @return - the total possible score of the garden as an integer
	 */
	public int totalScore (ArrayList<Object> objs) {
		int plantCounter = 0;
		for(Object o: objs) {
			if (o instanceof Plant) {
				plantCounter++;
				
			}
		}
		return plantCounter * 3;
			
	}
	/**
	* gets the x location
	* 
	* @param None
	* @return x - a double indicating x location
	*/
	public double getX() {
		return x;
	}
	/**
	* gets the y location
	* 
	* @param None
	* @return y - a double indicating y location
	*/
	public double getY() {
		return y;
	}
	/**
	* sets the x location value
	* 
	* @param xVal
	*/
	public void setX(double xVal) {
		x = xVal;
	}
	/**
	* sets the y location value
	* 
	* @param yVal
	*/
	public void setY(double yVal) {
		y = yVal;
	}

}
	
