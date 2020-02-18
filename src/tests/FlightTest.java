// COURSE: CSCI1620
// TERM: Spring 2020
// 
// NAME: Nate Tyler, Brandon Cline
// RESOURCES: We referred to no outside materials when
//            writing the code in this file.

package tests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import triptypes.Flight;  

/**
 * Tests the Flight class.
 * @author ntyler, bcline
 *
 */
public class FlightTest 
{

	/**
	 * Calendar instance used to check for testing.
	 */
	Calendar date1 = Calendar.getInstance();
	/**
	 * Calendar instance used to check for testing.
	 */
	Calendar date2 = Calendar.getInstance();
	
	/**
	 * The tolerance to use on all floating-point comparisons in the test
	 * cases.
	 */
	private static final double DOUBLE_TOLERANCE = 0.001;
	
	@Test
	public void testConstuctor()
	{
		date1.set(2019, 4, 22, 12, 0);
		date2.set(2019, 4, 22, 20, 0);
		
		Flight f = new Flight("AA", 8473, "OMA", "DAL", date1, date2, 100.00);
		
		assertEquals("AA8473 Departs OMA at 12:00 04-22-2019; Arrives DAL at 20:00 04-22-2019", f.toString());
		assertEquals(f.getPrice(), 100, DOUBLE_TOLERANCE);
	}

}
