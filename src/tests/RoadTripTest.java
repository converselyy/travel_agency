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

import triptypes.RoadTrip;

public class RoadTripTest 
{

	private static final double DOUBLE_TOLERANCE = 0.001;
	
	@Test
	public void testConstructor()
	{
		String[] stops = new String[] {"Omaha", "Denver"};
		RoadTrip r = new RoadTrip("Long", 14, stops, 100, 200, 4, 5);
		
		assertEquals("Long", r.getName());
		assertEquals(14, r.getNumDays());
		assertEquals("Omaha, Denver", r.getStops());
		assertEquals(2, r.getNumStops());
		assertEquals(100, r.getFuelPrice(), DOUBLE_TOLERANCE);
		assertEquals(5, r.getHotelStars());

	}
	
	@Test
	public void testSetPersons()
	{
		String[] stops = new String[] {"Omaha", "Denver"};
		RoadTrip r = new RoadTrip("Long", 14, stops, 1, 200, 4, 5);
		
		r.setPersons(6);
		assertEquals(6, r.getNumPersons());
		assertEquals(7392, r.getLodgingCost(), DOUBLE_TOLERANCE);
		assertEquals(60.25, r.getCarCost(), DOUBLE_TOLERANCE);
		assertEquals(7392 + 60.25, r.getDepositAmount(), DOUBLE_TOLERANCE);;
		assertEquals(0, r.getAmountDue(), DOUBLE_TOLERANCE);
		assertEquals(200 / 28.0, r.getEstimatedFuelCost(), DOUBLE_TOLERANCE);
		assertEquals(7392 + 60.25 + 200.0 / 28, r.getPrice(), DOUBLE_TOLERANCE);
	}
	
	@Test
	public void testSetFuelPriceVaild()
	{
		String[] stops = new String[] {"Omaha", "Denver"};
		RoadTrip r = new RoadTrip("Long", 14, stops, 100, 200, 4, 5);
		
		r.setFuelPrice(3.5);
		assertEquals(3.5, r.getFuelPrice(), DOUBLE_TOLERANCE);
	}
	
	@Test
	public void testSetFuelPriceInvalid()
	{
		String[] stops = new String[] {"Omaha", "Denver"};
		RoadTrip r = new RoadTrip("Long", 14, stops, 100, 200, 4, 5);
		
		r.setFuelPrice(-3.5);
		assertEquals(2.5, r.getFuelPrice(), DOUBLE_TOLERANCE);
	}
	
	@Test
	public void testToString()
	{
		String[] stops = new String[] {"Omaha", "Denver"};
		RoadTrip r = new RoadTrip("Long", 1, stops, 1, 45, 1, 1);
		
		assertEquals("$" + r.getPrice() + "  Long\n           A road trip with stops at Omaha, Denver",
				r.toString());
	}
}
