/**
 * @author Sohan Gadiraju
 */

package application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Filter implements Serializable{

	private static final long serialVersionUID = 1L;
	private String season;
	private String soil;
	private String lightLevel;
	private String water;
	Model m;
	int numCriteria;
	
	private ArrayList<Plant> filteredPlants;
	/**
	 * @param seas 			String to specify the season a plant grows in when filtering through plants
	 * @param soilType 		String specifying the soil type of a plant when using filter
	 * @param light 		String to specify the light level of plants when using filter
	 * @param waterLevel 	String to specify the water level a plant needs when using the filter
	 */
	public Filter(String seas, String soilType, String light, String waterLevel) {
		this.season = seas;
		this.soil = soilType;
		this.lightLevel = light;
		this.water = waterLevel;
		m = new Model();
		this.filteredPlants = new ArrayList<Plant>();
		numCriteria = 4;
	}
	
	/**
	 * Will compare all plants in ArrayList to chosen data and return a new ArrayList of plants
		only containing the plants that suit the data
	 * @param plants the arraylist that contains all the plant data
	 * @return the a new arraylist of plants that suit chosen data.
	 */
	public ArrayList<Plant> search(ArrayList<Plant> plants, int error){
			
			this.filteredPlants.clear();
			
			for (Plant p : plants) {
				
				int i = numCriteria;
				
				if (!p.getSoil().equals(soil)) {
					i--;
				}
				if (!p.getSunlight().equals(lightLevel)) {
					i--;
				}
				if (!p.getWater().equals(water)) {
					i--;
				}
				if (!p.getBloom().equals(season)) {
					i--;
				}
				
				if (i >= numCriteria - error) {
					this.filteredPlants.add(p);
				}
			}
	
			return filteredPlants;
		}


	/**
	 * Will compare all plants in ArrayList to garden data and return a new ArrayList of plants
		only containing plants that suit at least one attribute of the garden specifications
	 * @param plants - the arraylist that contains all the plant data
	 * @return the plant object the filter sifts for.
	 */
	public ArrayList<Plant> suitablePlants(ArrayList<Plant> plants){
		ArrayList<Plant> suitablePlants = new ArrayList<Plant>();
		
		for(Plant p: plants) {
			if (p.getSoil().equals(m.soilType)) {
				suitablePlants.add(p);
			}
			else if (p.getSunlight().equals(m.sunLight)) {
				suitablePlants.add(p);
			}
			else if (p.getWater().equals(m.waterLevel)) {
				suitablePlants.add(p);
			}
		}
		return suitablePlants;
	}
	
}
