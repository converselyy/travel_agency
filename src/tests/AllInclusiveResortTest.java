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

import triptypes.AllInclusiveResort;  

/**
 * Tests the AllInclusiveResort class.
 * @author ntyler, bcline
 *
 */

public class AllInclusiveResortTest 
{
	
	private static final double DOUBLE_TOLERANCE = 0.001;
	@Test
	public void testConstructor() 
	{
		AllInclusiveResort a = new AllInclusiveResort("Spring Break on the Gulf", 14, 
			"Hello There", 4, 200, new String[] {"Surfing", "Skidiving"});
		
		assertEquals("Spring Break on the Gulf", a.getName());
		assertEquals(14, a.getNumDays());
		assertEquals(4, a.getGuestsPerRoom());
		assertEquals(2800, a.getPrice(), DOUBLE_TOLERANCE);
		assertEquals("Surfing, Skidiving", a.getAmenities());
		assertEquals(a.getFlightCosts() + 0.5 * a.getLodgingCost(), a.getDepositAmount(), DOUBLE_TOLERANCE);
		assertEquals(2800, a.getLodgingCost(), DOUBLE_TOLERANCE);
	}
	
	@Test
	public void testToString()
	{
		AllInclusiveResort a = new AllInclusiveResort("Spring Break on the Gulf", 14, 
				"Hello There", 4, 200, new String[] {"Surfing", "Skidiving"});
		
	}
}
