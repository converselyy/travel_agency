// COURSE: CSCI1620
// TERM: Spring 2020
//
// NAME: Brandon Cline
// RESOURCES: Checked the Calendar and DateFormat docs for formatting a Calendar date.

package triptypes;
import java.util.Calendar;

/**
 * This class represents a cruise package within the travel agency.
 * It stores required information about the cruise ship, including
 * departure and arrival times. It allows for optional off-ship
 * excursion packages to be added to a trip for an additional price.
 * 
 * @author bcline
 */
public class Cruise extends FlightOptionalPackage
{
	/**
	 * An arbitrary number of excursions to be allowed.
	 */
	private final int maxExcursions = 50;
	/**
	 * A percentage constant used for calculating 50% of
	 * the base price for the deposit amount.
	 */
	private final double half = 0.5;
	/**
	 * A percentage constant used for calculating 150% of
	 * the base price.
	 */
	private final double oceanConstant = 1.5;
	/**
	 * A percentage constant used for calculating 300% of
	 * the base price.
	 */
	private final double balconyConstant = 3.0;
	/**
	 * A percentage constant used for calculating 500% of
	 * the base price.
	 */
	private final double suiteConstant = 5.0;
	/**
	 * A string array representing excursions on this cruise.
	 */
	private String[] excursions = new String[this.maxExcursions];
	/**
	 * The number of excursions in the cruise.
	 */
	private int excursionNum;
	/**
	 * The total price of the cruise.
	 */
	private double totalPrice;
	/**
	 * Double containing the subtotal of excursion costs.
	 */
	private double excursionSubTotal;
	/**
	 * The type of the cabin on the cruise.
	 */
	private CabinType cabin;
	/**
	 * The city of which this cruise departs from.
	 */
	private String homePort = new String();
	/**
	 * The name of the ship the cruise is on.
	 */
	private String ship = new String();
	/**
	 * The date at which the cruise departs.
	 */
	private Calendar departure = Calendar.getInstance();
	/**
	 * The date at which the cruise returns.
	 */
	private Calendar arrival = Calendar.getInstance();
	/**
	 * Double to hold the base price of the cruise.
	 */
	private double basePrice;
	
	/**
	 * Creates a new Cruise trip with specified values for all data
	 * except optional excursions.
	 * 
	 * @param name The promotional name of the travel package.
	 * @param numDays The number of days for this travel package.
	 * @param vesselName The ship name for this Cruise.
	 * @param portCity The departure port for this Cruise.
	 * @param departs The time and date of departure from the port.
	 * @param returns The time and date of return to the port.
	 * @param basePrice The base price for the cheapest tier cabin (interior) on the ship.
	 * Cabin upgrades can be accommodated through a subsequent setter call.
	 */
	public Cruise(String name, int numDays, String vesselName, String portCity, Calendar departs,
			Calendar returns, double basePrice)
	{
		super(name, numDays);
		this.excursionNum = 0;
		this.totalPrice = basePrice;
		this.basePrice = basePrice;
		this.homePort = portCity;
		this.ship = vesselName;
		this.departure = departs;
		this.arrival = returns;
	}

	/**
	 * Retrieves the deposit amount required upfront for this Cruise.
	 * Cruise packages require that all applicable flight costs and 50%
	 * of expected lodging costs are paid at the time of booking.
	 * Optional excursion costs are not included in the deposit amount,
	 * but must be paid prior to departure.\
	 * 
	 * @return The deposit amount required in USD.
	 */
	public double getDepositAmount()
	{
		return this.getFlightCosts() + this.totalPrice * this.half;
	}

	/**
	 * Retrieves the lodging charge for this Cruise package, calculated
	 * from the base price scaled based on the cabin level according to
	 * the following fee schedule:
	 * 
	 * Interior Room: 	100% of base price
	 * Ocean View Room: 150% of base price
	 * Balcony Room: 	300% of base price
	 * Luxury Suite:	500% of base price
	 * 
	 * @return The lodging subtotal in USD.
	 */
	public double getLodgingCost()
	{
		double temp = 0.0;
		switch (this.cabin)
		{
			case INTERIOR: temp = this.basePrice;
			break;
			case OCEAN_VIEW: temp = this.basePrice * this.oceanConstant;
			break;
			case BALCONY: temp = this.basePrice * this.balconyConstant;
			break;
			case SUITE: temp = this.basePrice * this.suiteConstant;
			break;
			default: temp = this.basePrice;
		}
		return temp;
	}
	
	/**
	 * Retrieves the full price of this Cruise package, including pre-cruise
	 * flight arrangements (as applicable), full lodging costs, and any
	 * additional excursion costs.
	 * @return The price of a vacation package in USD.
	 */
	public double getPrice()
	{
		return this.totalPrice + this.getFlightCosts() + this.excursionSubTotal;
	}
	
	/**
	 * Adds a named excursion to this Cruise. Excursions may or may not
	 * have an additional charge associated.
	 * @param excursion The name of the excursion to add to this package.
	 * Empty excursion values are invalid and should not result in a change
	 * to the Cruise package configuration.
	 * @param price The price of the excursion. Prices must be >= 0, with
	 * any negative values being treated as equivalent to 0.
	 */
	public void addExcursion(String excursion, double price)
	{
		if (excursion != null && !excursion.equals(""))
		{
			this.excursionNum++;
			this.excursions[this.excursionNum] = excursion;
			if (price >= 0)
			{
				this.excursionSubTotal += price;
			}
		}
	}
	
	/**
	 * Retrieves an array containing all of the excursions which have been
	 * added to this Cruise, in the order in which they were added.
	 * @return
	 */
	public String[] getExcursions()
	{
		return this.excursions;
	}
	
	/**
	 * Updates the cabin configuration for this Cruise. This method can be
	 * used to upgrade a cruise from the base price corresponding to the
	 * cheapest cabin (an interior cabin without any ocean view).
	 * @param temp A valid alternate cabin level for this package.
	 */
	public void setCabinType(CabinType temp)
	{
		this.cabin = temp;
	}
	
	/**
	 * Retrieves the cabin level currently associated with this Cruise package.
	 * @return The current cabin level.
	 */
	public CabinType getCabinType()
	{
		return this.cabin;
	}
	
	/**
	 * Retrieves the home port of the cruise ship for this package.
	 * @return The city from which this Cruise departs.
	 */
	public String getHomePort()
	{
		return this.homePort;
	}
	
	/**
	 * Retrieves the date and time of departure for this Cruise.
	 * @return The departure time at the port.
	 */
	public Calendar getDepartureDate()
	{
		return this.departure;
	}
	
	/**
	 * Retrieves the date and time of arrival for this Cruise.
	 * @return The arrival time at the port.
	 */
	public Calendar getReturnDate()
	{
		return this.arrival;
	}
	
	/**
	 * Retrieves the name of ship sailing for this Cruise.
	 * @return The ship name.
	 */
	public String getVesselName()
	{
		return this.ship;
	}
	
	/**
	 * Retrieves the total cost for all added excursions on this Cruise.
	 * @return The excursion's total price in USD.
	 */
	public double getExcursionCosts()
	{
		return this.excursionSubTotal;
	}
	
	/**
	 * Retrieves the number of excursions which have been added to this Cruise package.
	 * @return The number of excursions.
	 */
	public int getNumExcursions()
	{
		return this.excursionNum;
	}
	
	/**
	 * Retrieves a formatted string summarizing this Cruise package. The required format
	 * for this string includes information as described by FlightOptionalPackage followed
	 * by cruise details on the next line. The second line should be prefixed with 11 blank
	 * spaces for alignment below the trip name. An example of this format is shown here:
	 * 
	 * $2499.99 Bermuda Triangle...and Maybe Back (Flight Not Included)
	 *            Cruising from Miami on the Caribbean Princess
	 * 
	 * If optional excursions have been added, a parenthetical suffix will be added indicating
	 * how many excursions are included:
	 * 
	 * $2499.99  Bermuda Triangle...and Maybe Back (Flight Not Included)
	 *            Cruising from Miami on Caribbean Princess (includes 4 excursions)
	 *            
	 * @return The formatted string summary.
	 */
	public String toString()
	{
		return String.format("%s...and Maybe Back (Flight Not Included)", super.toString())
				+ String.format("\n           Cruising from %s on ", this.getVesselName());
	}
}