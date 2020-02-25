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

import triptypes.CabinType;
import triptypes.Cruise;

/**
 * Test the Cruise class.
 * @author ntyler, bcline
 *
 */
public class CruiseTest 
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
	public void testConstrutor()
	{
		date1.set(2019, 4, 1, 10, 0);
		date2.set(2019, 4, 4, 14, 0);
		Cruise c = new Cruise("Floating Germ Palace", 4, "Knight", "Miami, FL", date1, date2, 199.5);
		
		assertEquals("Miami, FL", c.getHomePort());
		assertEquals(date1, c.getDepartureDate());
		assertEquals(date2, c.getReturnDate());
		assertEquals("Knight", c.getVesselName());
		assertEquals(4, c.getNumDays());
		assertEquals(199.5, c.getPrice(), DOUBLE_TOLERANCE);
	}
	
	@Test
	public void testAddExrusionValid()
	{
		date1.set(2019, 4, 1, 10, 0);
		date2.set(2019, 4, 4, 14, 0);
		Cruise c = new Cruise("Floating Germ Palace", 4, "Knight", "Miami, FL", date1, date2, 199.5);
		
		c.addExcursion("Water slide", 0.5);
		assertEquals(1, c.getNumExcursions());
		assertEquals(0.5, c.getExcursionCosts(), DOUBLE_TOLERANCE);
		assertEquals("Water slide", c.getExcursions()[1]);
		assertEquals(200, c.getPrice(), DOUBLE_TOLERANCE);
	}
	
	@Test
	public void testAddExrusionEmpty()
	{
		date1.set(2019, 4, 1, 10, 0);
		date2.set(2019, 4, 4, 14, 0);
		Cruise c = new Cruise("Floating Germ Palace", 4, "Knight", "Miami, FL", date1, date2, 199.5);
		
		c.addExcursion("", 0.5);
		assertEquals(0, c.getNumExcursions());
		assertEquals(199.5, c.getPrice(), DOUBLE_TOLERANCE);
	}
	
	@Test
	public void testAddExursionNegitive()
	{
		date1.set(2019, 4, 1, 10, 0);
		date2.set(2019, 4, 4, 14, 0);
		Cruise c = new Cruise("Floating Germ Palace", 4, "Knight", "Miami, FL", date1, date2, 199.5);
		
		c.addExcursion("Water slide", -0.5);
		assertEquals(1, c.getNumExcursions());
		assertEquals(0, c.getExcursionCosts(), DOUBLE_TOLERANCE);
		assertEquals("Water slide", c.getExcursions()[1]);
		assertEquals(199.5, c.getPrice(), DOUBLE_TOLERANCE);
	}
	
	@Test
	public void testLodging()
	{
		date1.set(2019, 4, 1, 10, 0);
		date2.set(2019, 4, 4, 14, 0);
		Cruise c = new Cruise("Floating Germ Palace", 4, "Knight", "Miami, FL", date1, date2, 200);
	
		c.setCabinType(CabinType.BALCONY);
		assertEquals(CabinType.BALCONY, c.getCabinType());
		assertEquals(200 * 3, c.getLodgingCost(), DOUBLE_TOLERANCE);
	}
	
	@Test
	public void testToString()
	{
		date1.set(2019, 4, 1, 10, 0);
		date2.set(2019, 4, 4, 14, 0);
		Cruise c = new Cruise("Hi", 4, "Knight", "Miami", date1, date2, 200);

		assertEquals("$200.00  Hi (Flight Not Included)\n           Cruising from Miami on the Knight", c.toString());
	}
}
