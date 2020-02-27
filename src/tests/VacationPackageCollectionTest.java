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
	}
}
