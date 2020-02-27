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
import travelgui.VacationPackageCollection;
import triptypes.Cruise;
import triptypes.Flight;
import triptypes.RoadTrip;
import triptypes.AllInclusiveResort;

/**
 * Test the Cruise class.
 * @author ntyler, bcline
 *
 */
public class VacationPackageCollectionTest 
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
	public void testVacationPackageCollection()
	{
		date1.set(2019, 4, 1, 10, 0);
		date2.set(2019, 4, 4, 14, 0);
		Cruise c = new Cruise("Floating Germ Palace", 4, "Knight", "Miami, FL", date1, date2, 199.5);
		VacationPackageCollection VP = new VacationPackageCollection();
		VP.addVacation(c);
		
		assertEquals(1, VP.getNumTrips());
		assertEquals(c,	VP.getAllVacations()[0]);
	}
	
	@Test
	public void testFilter()
	{
		String[] stops = new String[] {"Omaha", "Denver"};
		Cruise c = new Cruise("Floating Germ Palace", 4, "Knight", "Miami, FL", date1, date2, 199.5);
		RoadTrip r = new RoadTrip("Long", 14, stops, 1, 200, 4, 5);
		AllInclusiveResort a = new AllInclusiveResort("Spring Break on the Gulf", 14, 
				"Hello There", 4, 200, new String[] {"Surfing", "Skidiving"});
		
		VacationPackageCollection VP = new VacationPackageCollection();
		VacationPackageCollection fVP = new VacationPackageCollection();
		VP.addVacation(r);
		VP.addVacation(c);
		VP.addVacation(a);
		
		fVP = VP.filterVacationsFor(1);
		assertEquals(r, fVP.getAllVacations()[0]);
		
		fVP = VP.filterVacationsFor(2);
		assertEquals(c, fVP.getAllVacations()[0]);
		
		fVP = VP.filterVacationsFor(3);
		assertEquals(a, fVP.getAllVacations()[0]);
		
		fVP = VP.filterVacationsFor(0);
		assertEquals(null, fVP.getAllVacations()[0]);
	}
	
	@Test
	public void testGetFlightDetails()
	{
		String[] stops = new String[] {"Omaha", "Denver"};
		RoadTrip r = new RoadTrip("Long", 14, stops, 1, 200, 4, 5);
		Flight f = new Flight("AA", 8473, "OMA", "DAL", date1, date2, 100.00);
		Cruise c = new Cruise("Floating Germ Palace", 4, "Knight", "Miami, FL", date1, date2, 199.5);AllInclusiveResort a = new AllInclusiveResort("Spring Break on the Gulf", 14, 
				"Hello There", 4, 200, new String[] {"Surfing", "Skidiving"});
		c.addFlightLeg(f);
		
		VacationPackageCollection VP = new VacationPackageCollection();
		VP.addVacation(c);
		VP.addVacation(r);
		VP.addVacation(a);
		
		assertEquals("ERROR: Index is out of range!", VP.getFlightDetails(-1));
		assertEquals("ERROR: No flights are allowed for this type of trip!", VP.getFlightDetails(1));
		assertEquals("ERROR: The selected trip has no flight information.", VP.getFlightDetails(2));
		assertEquals("ERROR: Index is out of range!", VP.getFlightDetails(-1));
	}
}
