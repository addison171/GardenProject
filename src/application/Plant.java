/**
 * @author Sohan Gadiraju
 */
package application;

import java.io.Serializable;

import javafx.scene.image.ImageView;

class Plant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String idealSoil;
	private String idealWaterLevel;
	private String idealSunlightLevel;
	private String bloomTime;
	private String description;
	private int xCell;
	private int yCell;
	private Double xLoc;
	private Double yLoc;
	private double scale;
	/**
	 * 
	 * @param n String for the name of plant
	 * @param soil String for the plant's soil
	 * @param sun String for the plants sun level needed
	 * @param water string for the water level the plant needs
	 * @param bloom string of the bloom time of the plant
	 */
	public Plant(String n, String soil,String sun, String water, String bloom, String descrip){
		this.name = n;
		this.idealSoil = soil;
		this.idealSunlightLevel = sun;
		this.idealWaterLevel = water;
		this.bloomTime = bloom;
		this.description = descrip;
		
	}
	/**
	 * setter for xLoc which holds the position of the plant on the screen
	 * @param x -location of plant
	 */
	public void setX(double x) {
		xLoc = x;
	}
	/**
	 * getter for xloc
	 * @return xLoc of the plant
	 */
	public Double getX() {
		return xLoc;
	}
	/**
	 * setter for yLoc which holds the position of the plant on the screen
	 * @param y -location of plant
	 */
	public void setY(double y) {
		yLoc = y;
	}
	/**
	 * getter for yloc
	 * @return yLoc of the plant
	 */
	public Double getY() {
		return yLoc;
	}
	/**
	 * Setter for xCell which holds what column the cell is in
	 * @param x - the column number
	 */
	public void setXCell(int x) {
		xCell = x;
	}
	/**
	 * @return what column the cell is in
	 */
	public int getXCell() {
		return xCell;
	}
	/**
	 * Setter for the yCell which holds what row the cell is in
	 * @param y - the row number
	 */
	public void setYCell(int y) {
		yCell = y;
	}
	/**
	 * 
	 * @return what row the cell is in
	 */
	public int getYCell() {
		return yCell;
	}
	
	 /**
	  * Setter for name string
	  * @param newName the name of the plant to set
	  */
	public void setName(String newName) {
		    this.name = newName;
	}
	/**
	 * getter for name string
	 * @return returns the string for name
	 */
	public String getName() {
	    return this.name;
	}
	/**
	 * setter for the soil string
	 * @param soil - the soil of the plant
	 */
	public void setSoil(String soil) {
	    this.idealSoil = soil;
	}
	
	/**
	 * getter for the soil string
	 * @return returns the string for the soil type of the plant
	 */
	public String getSoil() {
	    return this.idealSoil;
	}
	/**
	 * Setter for the water string
	 * @param water - the water level string of the plant
	 */
	public void setWater(String water) {
	    this.idealWaterLevel = water;
	}

	/**
	 * getter for water string
	 * @return returns the string for the ideal water level the plant needs
	 */
	public String getWater() {
	    return this.idealWaterLevel;
	}
	/**
	 * Setter for the sunlight string
	 * @param sun - the sunlight level string of the plant
	 */
	public void setSunlight(String sun) {
	    this.idealSunlightLevel = sun;
	}

	/**
	 * Getter for sunlight level
	 * @return returns the sunlight level of a plant.
	 */
	public String getSunlight() {
	    return this.idealSunlightLevel;
	}
	/**
	 * Setter for the bloom time
	 * @param bloom - the bloom time string of the plant
	 */
	public void setBloom(String bloom) {
	    this.bloomTime = bloom;
	}

	/**
	 * Getter for bloom time
	 * @return returns the bloom time of a plant.
	 */
	public String getBloom() {
	    return this.bloomTime;
	}
	/**
	 * Setter for the description
	 * @param descrip - the description of the plant
	 */
	public void setDescription(String descrip) {
	    this.description = descrip;
	}

	/**
	 * Getter for description
	 * @return returns the description of a plant.
	 */
	public String getDescription() {
	    return this.description;
	}
	/**
	 * Setter for scale
	 */
	public void setScale(double size) {
		this.scale = size;
	}
	/**
	 * getter for scale
	 * @return scale of plant
	 */
	public double getScale() {
		return this.scale;
	}
}


