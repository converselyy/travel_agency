// COURSE: CSCI1620
// TERM: Spring 2020
//
// NAME: Brandon Cline
// RESOURCES: No external resources were referenced or used.

package travelgui;
import triptypes.AllInclusiveResort;
import triptypes.Cruise;
import triptypes.RoadTrip;
import triptypes.VacationPackage;

/**
 * This class serves as a collection to store, filter, and explore VacationPackages
 * within the trip search user interface. Up to 25 VacationPackages can be stored
 * in a single collection.
 * 
 * @author bcline
 */
public class VacationPackageCollection
{
	/**
	 * Maximum number of packages for a collection.
	 */
	private final int MAX_PKGS = 25;
	/**
	 * Number of trips in the package.
	 */
	private int numTrips;
	/**
	 * Array of VacationPackage objects for the collection.
	 */
	private VacationPackage[] collection = new VacationPackage[this.MAX_PKGS];
	
	/**
	 * Default constructor.
	 */
	public VacationPackageCollection()
	{
		this.numTrips = 0;
	}
	
	/**
	 * Returns the number of trips in the package collection.
	 * @return Number of trips.
	 */
	public int getNumTrips()
	{
		return this.numTrips;
	}
	
	/**
	 * Retrieves an array of all available packages from the
	 * collection. Valid packages are guaranteed to be stored
	 * contiguously in the left most array cells. Any empty cells
	 * will appear to the right of the last valid VacationPackage.
	 *  
	 * Unless a sort has occurred, packages will appear in theorder in which they were added.
	 * 
	 * @return The list of available packages.
	 */
	public VacationPackage[] getAllVacations()
	{
		return this.collection;
	}
	
	/**
	 * Adds a single trip package to the collection at the next
	 * available position. If adding this trip would result in more
	 * than 25 total packages, it will not be added to the collection.
	 * @param trip
	 */
	public void addVacation(VacationPackage trip)
	{
		if (this.numTrips < this.MAX_PKGS)
		{
			this.collection[this.numTrips] = trip;
			this.numTrips++;
		}
	}
	
	/**
	 * Retrieves a filtered subcollection of trips corresponding to a specific
	 * subtype. Should no trips of a specified type be present in this VacationPackageCollection
	 * an empty collection containing zero packages will be returned.
	 * 
	 * @param selection An integer signaling what type of packages to extract. 1 represents RoadTrips,
	 * 2 represents Cruises, and 3 represents All-Inclusive Resort packages.
	 * 
	 * @return The filtered subcollection of packages, or an empty collection if no matching
	 * packages exist in this collection.
	 */
	public VacationPackageCollection filterVacationsFor(int selection)
	{
		VacationPackageCollection temp = new VacationPackageCollection(); 
		switch (selection)
		{
		case 1:
			for (int i = 0; i < this.collection.length; i++)
			{
				if (this.collection[i] instanceof RoadTrip)
				{
					temp.addVacation(this.collection[i]);
				}
			}
			return temp;
		case 2:
			for (int i = 0; i < this.collection.length; i++)
			{
				if (this.collection[i] instanceof Cruise)
				{
					temp.addVacation(this.collection[i]);
				}
			}
			return temp;
		case 3:
			for (int i = 0; i < this.collection.length; i++)
			{
				if (this.collection[i] instanceof AllInclusiveResort)
				{
					temp.addVacation(this.collection[i]);
				}
			}
			return temp;
		default:
			return temp;
		}
	}
}
