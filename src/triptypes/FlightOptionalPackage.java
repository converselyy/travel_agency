// COURSE: CSCI1620
// TERM: Spring 2020
//
// NAME: Brandon Cline
// RESOURCES: No external resources were referenced or used.

package triptypes;

/**
 * This class encapsulates (ew) information about travel packages that can include
 * optional flight arrangements. Trips that have this format price flight
 * information as an additional charge to a base package price captured in
 * a concrete subclass of FlightOptionalPackage.
 * 
 * @author bcline
 */
public abstract class FlightOptionalPackage extends VacationPackage
{
	/**
	 * Maximum number of flights to be held in a flight package.
	 */
	private final int MAX_FLIGHTS = 12;
	/**
	 * Number of flights in the package.
	 */
	private int numFlights = 0;
	/**
	 * An array representing the number of flights included in the package.
	 */
	private Flight[] flights = new Flight[MAX_FLIGHTS];

	/**
	 * Initialises details for a newly created FlightOptionalPackage.
	 * Upon creation, this package will contain no flight bookings.
	 * However, flight legs can be added at a later point.
	 * 
	 * @param nameIn The promotional name of this package.
	 * @param daysIn The number of days this travel package covers.
	 */
	public FlightOptionalPackage(String nameIn, int daysIn)
	{
		super(nameIn, daysIn);
	}
	
	/**
	 * Adds a single one-way flight to this package. Round trips and
	 * layovers are handled by multiple calls to addFlightLeg with one
	 * call for each flight. Flights must be added in chronological order,
	 * with the soonest flight added first. A maximum of up to 12
	 * flights can be stored within a FlightOptionalPackage.
	 * 
	 * @param details A valid flight object to append to this itinerary.
	 * Invalid values (ie, null) or flights in excess of the 12 maximum
	 * will be ignored and will not impact this FlightOptionalPackage object.
	 */
	public void addFlightLeg(Flight details)
	{
		if (details != null && this.numFlights < this.MAX_FLIGHTS)
		{
			this.flights[this.numFlights] = details;
			this.numFlights++;
		}
	}
	
	/**
	 * A predicate method for identifying whether a concrete object has
	 * at least one flight attached to it.
	 * @return True when at least one flight has been added, false otherwise.
	 */
	public boolean hasFlights()
	{
		return this.numFlights > 0;
	}
	
	/**
	 * Retrieves the current itinerary for this travel package. Flights
	 * are positioned in the returned array in the order in which they were
	 * added to this FlightOptionalPackage.
	 * @return The current itinerary array of Flight objects. null when no flights
	 * have been added yet.
	 */
	public Flight[] getFlightItinerary()
	{
		if (this.numFlights > 0)
		{
			return this.flights;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * The total cost of all legs in this FlightOptionalPackage.
	 * @return The sum of all individual flight costs in this package.
	 */
	public double getFlightCosts()
	{
		double temp = 0.0;
		for (int i = 0; i < this.numFlights; i++)
		{
			temp += this.flights[i].getPrice();
		}
		return temp;
	}
	
	/**
	 * Retrieves a formatted string summary of this travel package. This string will
	 * be formatted exactly as prescribed by the TravelPackage class, with a postfix
	 * indicating whether flight information is included or not. For example, the
	 * strings below show two versions of a trip for each possible state.
	 * 
	 * $1234.56 Spring Break at the Beach (Flight Included)
	 * $1234.56 Spring Break at the Beach (Flight Not Included)
	 * 
	 * @return The formatted string summary.
	 */
	public String toString()
	{
		return String.format("$%.2f %s at the ");
	}
}
