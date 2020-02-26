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

import triptypes.Cruise;
import triptypes.Flight;  

/**
 * Tests the FlightOptionalPackage class.
 * @author ntyler, bcline
 *
 */
public class FlightOptionalPackageTest 
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
	public void testFlight()
	{
		Cruise c = new Cruise("Long", 10, "Knight", "Miami, FL", date1, date2, 199.5);
		c.addFlightLeg(new Flight("AA", 8473, "OMA", "DAL", date1, date2, 100.00) );
		c.addFlightLeg(new Flight("BB", 9473, "DAL", "MIM", date1, date2, 100.00) );
		
		assertEquals(200, c.getFlightCosts(), DOUBLE_TOLERANCE);
		assertEquals(true, c.hasFlights());
		
	}
	
	@Test
	public void testToString() 
	{
		Cruise c = new Cruise("Long", 10, "Knight", "Miami", date1, date2, 200);
		c.addFlightLeg(new Flight("AA", 8473, "OMA", "DAL", date1, date2, 100.00) );
		c.addFlightLeg(new Flight("BB", 9473, "DAL", "MIM", date1, date2, 100.00) );
		
		assertEquals("$400.00  Long (Flight Included)\n           Cruising from Miami on the Knight",
				c.toString());
	}
}
