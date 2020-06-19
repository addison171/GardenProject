/**
 * @author Addison Kuykendall
 */
package application;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class ModelTest {
	@Test
	public void testInputData() {
		Model m = new Model(2, 2);
		m.soilType = "sandy";
		m.sunLight = "high";
		m.waterLevel = "high";
		m.cells = m.inputData();
		for(Cell[] innerArray : m.cells) {
			for(Cell val: innerArray) {
				assertEquals(val.getSoil(), ("sandy"));
				assertEquals(val.getSun(), ("high"));
				assertEquals(val.getWater(), ("high"));
			}
		}
	}
	
	@Test
	public void testInputDataTwo() {
		Model m = new Model(5, 5);
		m.soilType = "mix";
		m.sunLight = "low";
		m.waterLevel = "low";
		m.cells = m.inputData();
		for(Cell[] innerArray : m.cells) {
			for(Cell val: innerArray) {
				assertEquals(val.getSoil(), ("mix"));
				assertEquals(val.getSun(), ("low"));
				assertEquals(val.getWater(), ("low"));
			}
		}
	}
	
	@Test
	public void testAddObject() {
		Model m = new Model(3,3);
		Plant plant = new Plant("a", "b", "c", "w", "b","d");
		m.addObject(m.cells, 0, 0, 1, 1, plant);
		assertEquals(m.cells[0][0].getPlant(), plant);
		
	}

	@Test
	public void testAddObject2() {
		Model m = new Model(3,3);
		Plant plant = new Plant("a", "b", "c", "w", "b","d");
		Plant plant2 = new Plant("a", "b", "c", "w", "b","d");
		m.addObject(m.cells, 0, 0, 1, 1, plant);
		m.addObject(m.cells, 1, 1, 2, 2, plant2);
		assertEquals(m.cells[0][0].getPlant(), plant);
		assertEquals(m.cells[1][1].getPlant(), plant2);
		
	}
	
	@Test
	public void testEditCells() {
		Model m = new Model(3,3);
		m.waterLevel = "low";
		m.soilType = "sandy";
		m.sunLight = "low";
		m.inputData();
		assertEquals(m.cells[0][0].getSoil(), "sandy");
		m.editCells(m.cells, 0, 0, 2, 2, "clay", "high", "high");
		assertEquals(m.cells[0][0].getSoil(), "clay");
		}	
		
	@Test
	public void testEditCells2() {
		Model m = new Model(3,3);
		m.waterLevel = "low";
		m.soilType = "sandy";
		m.sunLight = "low";
		m.inputData();
		assertEquals(m.cells[0][0].getSoil(), "sandy");
		m.editCells(m.cells, 0, 0, 0, 0, "clay", "high", "high");
		assertEquals(m.cells[0][0].getSoil(), "clay");
		}
	
	@Test
	public void testPlantGrader() {
		Model m = new Model(3, 3);
		ArrayList<Object> p = new ArrayList<Object>();
		p.add(new Plant("Blue Oak", "Clay","Shade", "Wet", "Spring", "desc"));
		m.soilType = "Sandy";
		m.waterLevel = "Wet";
		m.sunLight = "Shade";
		m.cells = m.inputData();
		int score = 1;
		assertEquals(score, m.plantGrader(p, m.cells));	
	}	
	
	@Test
	public void testPlantGrader2() {
		Model m = new Model(3, 3);
		ArrayList<Object> p = new ArrayList<Object>();
		p.add(new Plant("Blue Oak", "Clay","Shade", "Wet", "Spring", "desc"));
		p.add(new Plant("Blue Oak", "Sandy","Shade", "Wet", "Spring", "desc"));
		p.add(new Plant("Blue Oak", "Sandy","Shade", "Wet", "Spring", "desc"));
		m.soilType = "Sandy";
		m.waterLevel = "Wet";
		m.sunLight = "Shade";
		m.cells = m.inputData();
		int score = 7;
		assertEquals(score, m.plantGrader(p, m.cells));	
	}
	
	@Test
	public void testPlantGrader3() {
		Model m = new Model(3, 3);
		ArrayList<Object> p = new ArrayList<Object>();
		m.soilType = "Sandy";
		m.waterLevel = "Wet";
		m.sunLight = "Shade";
		m.cells = m.inputData();
		int score = 0;
		assertEquals(score, m.plantGrader(p, m.cells));	
	}
	
	@Test
	public void testPlantGrader4() {
		Model m = new Model(3, 3);
		ArrayList<Object> p = new ArrayList<Object>();
		p.add(new Plant("Blue Oak", "Mix","Sunny", "Dry", "Spring", "desc"));
		m.soilType = "Sandy";
		m.waterLevel = "Wet";
		m.sunLight = "Shade";
		m.cells = m.inputData();
		int score = -1;
		assertEquals(score, m.plantGrader(p, m.cells));	
	}
	
	@Test
	public void testTotalScore() {
		Model m = new Model(3,3);
		Plant plant = new Plant("a", "b", "c", "w", "b","d");
		ArrayList<Object> p = new ArrayList<Object>();
		p.add(plant);
		assertEquals(m.totalScore(p), 3);
	}
	
}

