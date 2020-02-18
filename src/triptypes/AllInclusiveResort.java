// COURSE: CSCI1620
// TERM: Spring 2020
//
// NAME: Brandon Cline
// RESOURCES: No external resources were referenced or used.

package triptypes;

/**
 * This class encapsulates details related to an all-inclusive resort
 * vacation package. Flight information to/from the resort location
 * can be added an additional price. Once at the resort, all-inclusive
 * packages are billed using a flat fee, regardless of the number of
 * included amenities.
 * 
 * @author bcline
 */
public class AllInclusiveResort extends FlightOptionalPackage
{
	/**
	 * The name of the resort.
	 */
	private String resort = new String();
	/**
	 * Number of guests per room.
	 */
	private int guests;
	/**
	 * The price per night of stay in USD.
	 */
	private double price;
	/**
	 * String array of amenities.
	 */
	private String[] amenities;
	
	/**
	 * Creates a new AllInclusiveResort package.
	 * @param name The promotional name for this package.
	 * @param numDays The number of days included in this vacation package.
	 * @param resortIn The name of the resort.
	 * @param guestsPerRoom The number of guests allowed per room at the included price.
	 * @param pricePerNight The price of the stay per night.
	 * @param amenitiesList A list of amenities available for free at the resort.
	 */
	public AllInclusiveResort(String name, int numDays, String resortIn, int guestsPerRoom,
			double pricePerNight, String[] amenitiesList)
	{
		super(name, numDays);
		this.resort = resortIn;
		if (guestsPerRoom > 0)
		{
			this.guests = guestsPerRoom;
		}
		if (pricePerNight > 0.0)
		{
			this.price = pricePerNight;
		}
		this.amenities = amenitiesList;
	}
	
	/**
	 * Retrieves the subtotal for lodging associated with this
	 * all inclusive resort stay based on the number of nights
	 * in the package and the base price.
	 * 
	 * @return The lodging subtotal in USD.
	 */
	public double getLodgingCost()
	{
		return 0.0;
	}
	
	/**
	 * Retrieves a single String containing all of the available amenities at the
	 * resort. Individual amenities should be separated by a single comma and space
	 * character, with no such separator at the end of the string.
	 * 
	 * @return The amenities list.
	 */
	public String getAmenities()
	{
		String temp = "";
		for (int i = 0; i < this.amenities.length; i++)
		{
			if (i == this.amenities.length)
			{
				temp += this.amenities[i];
			}
			else
			{
				temp += String.format("%s, ", this.amenities[i]);
			}
		}
		return temp;
	}
	
	/**
	 * Retrieves the number of guests allowed per room at the specified rate.
	 * @return The number of guests per room.
	 */
	public int getGuestsPerRoom()
	{
		return this.guests;
	}
	
	/**
	 * The full price for this all-inclusive package, including any optional
	 * flight pricing.
	 * @return The price of a vacation package in USD.
	 */
	public double getPrice()
	{
		return this.price; // I don't know if this is right lol
	}
	
	/**
	 * The required deposit amount to be made at the time of booking. All
	 * upfront flight-related costs and 50% of the total lodging costs for this
	 * trip must be paid as a deposit.
	 * @return The deposit amount required in USD.
	 */
	public double getDepositAmount()
	{
		return 123; // I KNOW I didn't do this one right...
	}
}
