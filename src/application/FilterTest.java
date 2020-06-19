/**
 * @author Sohan Gadiraju
 */
package application;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class FilterTest {

	@Test
	public void searchTest() {
		ArrayList<Plant> plants1 = new ArrayList<Plant>();
		ArrayList<Plant> plants2 = new ArrayList<Plant>();
		Plant blueOak = new Plant("Blue Oak", "Clay","Shade", "Wet", "Spring", "Cool");
		Plant redOak = new Plant("Red Oak", "Clay","Sunny", "Dry", "Fall", "Boring");
		plants1.add(blueOak);
		plants1.add(redOak);
		Filter f = new Filter("Fall", "Clay", "Sunny" , "Dry");
		plants2.add(redOak);
		assertEquals(plants2,f.search(plants1,0));
	}

	@Test
	public void suitablePlantsTest() {
		ArrayList<Plant> plants1 = new ArrayList<Plant>();
		ArrayList<Plant> plants2 = new ArrayList<Plant>();
		Plant blueOak = new Plant("Blue Oak", "Clay","Shade", "Wet", "Spring", "Cool");
		Plant redOak = new Plant("Red Oak", "Clay","Sunny", "Dry", "Fall", "Sick");
		plants1.add(blueOak);
		plants1.add(redOak);
		Filter f = new Filter( "Fall", "Clay", "Sunny" , "Dry");
		plants2.add(redOak);
		assertEquals(plants2,f.search(plants1,0));
	}


}
