/**
 * @author Sohan Gadiraju
 */

package application;

import java.io.Serializable;

import javafx.scene.image.ImageView;

public class Obstruction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String shape;
	private int xCell;
	private int yCell;
	private double xLoc;
	private double yLoc;
	
	public Obstruction(String n) {
		name = n;
	}
	/**
	 * setter for xLoc which holds the position of the obstruction on the screen
	 * @param x -location of obstruction
	 */
	public void setX(double x) {
		xLoc = x;
	}
	/**
	 * getter for xloc
	 * @return xLoc of the obstruction
	 */
	public Double getX() {
		return xLoc;
	}
	/**
	 * setter for yLoc which holds the position of the obstruction on the screen
	 * @param y -location of obstruction
	 */
	public void setY(double y) {
		yLoc = y;
	}
	/**
	 * getter for yloc
	 * @return yLoc of the obstruction
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
	 * Setter for the soil in the cell
	 * @param label - String for the name of the Obstruction
	 */
	public void setName(String label) {
		name = label; 
	}
	/**
	 * Getter for the name type
	 * @return returns the name type string
	 */
	public String getName() {
		return name;
	}
	
}
