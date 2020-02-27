// COURSE: CSCI1620
// TERM: Spring 2020
//
// NAME: Brandon Cline, Nate Tyler
// RESOURCES: No external resources were referenced or used.

package travelgui;
import triptypes.AllInclusiveResort;
import triptypes.Cruise;
import triptypes.FlightOptionalPackage;
import triptypes.RoadTrip;
import triptypes.VacationPackage;
import triptypes.Flight;

/**
 * This class serves as a collection to store, filter, and explore VacationPackages
 * within the trip search user interface. Up to 25 VacationPackages can be stored
 * in a single collection.
 * 
 * @author bcline, ntyler
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
	
	/**
	 * Produces a summary of flight information inside a VacationPackage
	 * for detail display elsewhere. In addition to properly extracting flight
	 * details when the given index corresponds to a FlightOptionalPackage with one or
	 * more flight legs, this method will also dectect various error conditions. In
	 * erroneous situations, one of the following strings will be produced:
	 * 
	 * ERROR: Index is out of range!
	 * ERROR: No flights are allowed for this type of trip!
	 * ERROR: The selected trip has no flight information.
	 * 
	 * Each of these errors corresponds to invalid range input, packages for which flightscannot be added in the first place, and packages which potentially could (but do not yet)have flight data.
	 * @param i The index position in the collection from which to extract flightinformation.
	 * @return The formatted flight details, with one Flight per line and each
	 * Flight displayed as detailed in Flight.toString.
	 */
	public String getFlightDetails(int index)
	{
		String temp = new String();
		
		if (index > this.MAX_PKGS || index < 0)
		{
			temp = "ERROR: Index is out of range!";
		}
		else if (!(this.collection[index] instanceof FlightOptionalPackage))
		{
			temp = "ERROR: No flights are allowed for this type of trip!";
		}
		else
		{
			if (((FlightOptionalPackage)this.collection[index]).hasFlights())
			{
				Flight[] f = ((FlightOptionalPackage) this.collection[index]).getFlightItinerary();
				for (int i = 0; i < f.length; i++)
				{	
					if (f[i] != null)
					{
						temp += f[i].toString() + "\n";
					}
				}
			}
			else
			{
				temp = "ERROR: The selected trip has no flight information.";
			}
		}
		
		return temp;
	}
	
	/**
	 * Provides 0-based indexed access to the VacationPackageCollection.
	 * @param index The index position whose VacationPackage should be returned.
	 * @return The selected VacationPackage when index is valid.
	 * The method will return null otherwise.
	 */
	public VacationPackage getItemAt(int index)
	{
		if (index < this.numTrips && index > 0)
		{
			return this.collection[index];
		}
		return null;
	}
	
	// full disclosure, this is the sketchiest method I've ever written
	/**
	 * Produces a stable sort of the contents of this VacationPackageCollection,
	 * with the sort order determined by an externally specified criteria. When
	 * byPrice is true, the method will sort all available packages in ascending
	 * order by total package price. When false, the method sorts packages in
	 * standard lexicographically ascending order by package name
	 * (see, https://en.wikipedia.org/wiki/Lexicographical_order).
	 * 
	 * @param byPrice A flag which sets the sort criteria as described above.
	 */
	public void sortCollection(boolean byPrice)
	{
		if (byPrice)
		{
			this.priceSort();
		}
		else
		{
			this.lexiSort();
		}
		
	}
	
	/**
	 * Helper method for sorting by price.
	 */
	private void priceSort()
	{
		int smallIndex = 0;
		double smallVal = 0;
		
		for (int i = 0; i < this.numTrips; i++)
		{
			smallIndex = i;
			smallVal = this.collection[i].getPrice();
			for (int j = i + 1; j < this.numTrips; j++)
			{
				if (this.collection[j].getPrice() < smallVal)
				{
					smallIndex = j;
					smallVal = this.collection[j].getPrice();
				}
			}
			this.swapArray(i, smallIndex);
		}
	}
	
	/**
	 * Helper method for sorting lexicographically.
	 */
	private void lexiSort()
	{
		int smallIndex = 0;
		char smallVal = 0;
		
		for (int i = 0; i < this.numTrips; i++)
		{
			smallIndex = i;
			smallVal = this.collection[i].getName().charAt(0);
			for (int j = i + 1; j < this.numTrips; j++)
			{
				if (this.collection[j].getName().charAt(0) < smallVal)
				{
					smallIndex = j;
					smallVal = this.collection[j].getName().charAt(0);
				}
			}
			this.swapArray(i, smallIndex);
		}
	}
	
	/**
	 * Swaps two array locations in the collection array.
	 * @param arr1 - First index
	 * @param arr2 - Second index
	 */
	private void swapArray(int arr1, int arr2)
	{
		VacationPackage temp = this.collection[arr1];
		this.collection[arr1] = this.collection[arr2];
		this.collection[arr2] = temp;
	}
}
