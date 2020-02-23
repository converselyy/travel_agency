// COURSE: CSCI1620
// TERM: Spring 2020
//
// NAME: Brandon Cline
// RESOURCES: No external resources were referenced or used.

package triptypes;

/**
 * This class represents a RoadTrip vacation that includes a rental car, overnight lodging, and
 * fuel cost estimation.
 * 
 * @author bcline
 */
public class RoadTrip extends VacationPackage
{
	/**
	 * The default assumed price per gallon in USD.
	 */
	private final double defaultPrice = 2.5;
	/**
	 * Constant base price for trip costs.
	 */
	private final double basePrice = 35.20;
	/**
	 * The total fuel price in USD.
	 */
	private double fuelCost;
	/**
	 * The total miles to be travelled.
	 */
	private int miles;
	/**
	 * The number of stars.
	 */
	private int stars;
	/**
	 * Array of strings representing the stops on the road trip.
	 */
	private String[] stops;
	/**
	 * The max number of people for the trip.
	 */
	private int maxPeople;
	/**
	 * The actual number of people for the trip.
	 */
	private int numPeople;
	/**
	 * The price per gallon of fuel in USD.
	 */
	private double pricePerGallon;
	
	/**
	 * Creates a newly initialized RoadTrip object using the parameter data.
	 * 
	 * @param name The promotional name to use for this RoadTrip in the travel agency system.
	 * @param numDays The number of days required for this RoadTrip.
	 * @param stops A list of destinations that will be visited along the way on this RoadTrip.
	 * @param fuelCost The estimated cost of fuel in US Dollars per Gallon based on current rates.
	 * @param miles The total number of miles for this RoadTrip, assuming people follow the intended route.
	 * @param maxPersons The number of people for whom this trip package will be budgeted.
	 * @param hotelStars The quality level of the hotels used during the RoadTrip, ranging from
	 * 1..5 stars, inclusive. Star values outside this range will be adjusted to the closest valid value.
	 */
	public RoadTrip(String name, int numDays, String[] stopsIn, double fuelCostIn,
			int milesIn, int maxPersons, int hotelStars)
	{
		super(name, numDays);
		this.stops = stopsIn;
		this.fuelCost = fuelCostIn;
		this.miles = milesIn;
		this.maxPeople = maxPersons;
		this.stars = hotelStars;
	}

	/**
	 * Provides the full price of this RoadTrip object. RoadTrip prices are computed
	 * based on the total rental car, lodging, and fuel estimated costs.
	 * @return The price of a vacation package in USD.
	 */
	public double getPrice()
	{
		return this.fuelCost; // add on the rental car and lodging costs
	}

	/**
	 * Provides the required deposit amount for this RoadTrip object. The required
	 * deposit for a Road trip includes the full lodging cost plus the full rental car
	 * cost.
	 * @return The deposit amount required in USD.
	 */
	public double getDepositAmount()
	{
		return this.getLodgingCost() + this.getCarCost();
	}

	/**
	 * Provides the total lodging cost for a RoadTrip object. Lodging is computed
	 * based on the length of the vacation, the quality of the hotel (in stars),
	 * the number of rooms needed for the party and a base charge of $35.20 per room
	 * per night. Lodging costs assume a maximum 2 person occupancy per room.
	 * For example, a 5 day road trip for 4 people in 2 star hotels is costed as:
	 * 
	 * $35.20 base x 2 star hotels x 4 nights x 2 rooms per night --> $563.20
	 * 
	 * @return The lodging subtotal in USD.
	 */
	public double getLodgingCost()
	{
		return this.basePrice * this.stars * super.getNumDays() * Math.ceil(this.maxPeople / 2);
	}
	
	/**
	 * Retrieves the hotel quality level attached to this RoadTrip package.
	 * @return The number of stars for hotel stays.
	 */
	public int getHotelStars()
	{
		return this.stars;
	}
	
	/**
	 * Provides the total cost for the rental car based on the trip duration and the
	 * size of car needed. Rental cars are billed based on full days, with no partial
	 * day rentals allowed. Further, the travel agency uses a standard daily rental
	 * car charge based on the number of occupants riding along:
	 * 
	 * $ 36.75   1-2 passengers
	 * $ 50.13   3-4 passengers
	 * $ 60.25   5-6 passengers
	 * $ 70.50   7-8 passengers
	 * $150.00    9+ passengers (since you'll need a bus)
	 * 
	 * @return
	 */
	public double getCarCost()
	{
		double temp = 0.0;
		if (this.numPeople >= 9)
		{
			temp = 150.0;
		}
		else if (this.numPeople >= 7)
		{
			temp = 70.5;
		}
		else if (this.numPeople >= 5)
		{
			temp = 60.25;
		}
		else if (this.numPeople >= 3)
		{
			temp = 50.13;
		}
		else
		{
			temp = 36.75;
		}
		return temp;
	}
	
	/**
	 * Retrieves the number of stops along the route for this RoadTrip.
	 * @return The number of intermediate destinations.
	 */
	public int getNumStops()
	{
		return this.stops.length;
	}
	
	/**
	 * Updates the number of people to be used for budgeting this RoadTrip within the
	 * travel agency.
	 * @param maxPeople The new number of people to use in calculations.
	 */
	public void setPersons(int maxPeopleIn)
	{
		if (maxPeopleIn > 0)
		{
			this.maxPeople = maxPeopleIn;
		}
	}
	
	/**
	 * Retrieves the number of people included for budget calculations by this RoadTrip.
	 * @return The number of persons.
	 */
	public int getNumPersons()
	{
		return this.numPeople;
	}
	
	/**
	 * Updates the cost of fuel in US dollars per gallon. This value is used
	 * for projecting out costs for this RoadTrip. Prices must be positive values,
	 * and a default assumption of $2.50 per gallon will be used if an invalid price
	 * is specified.
	 * 
	 * @param fuelIn
	 */
	public void setFuelPrice(double fuelIn)
	{
		if (fuelIn > 0)
		{
			this.pricePerGallon = fuelIn;
		}
		else
		{
			this.pricePerGallon = this.defaultPrice;
		}
	}
	
	/**
	 * Retrieves the current fuel price used for cost projections.
	 * @return The fuel price in USD per gallon.
	 */
	public double getFuelPrice()
	{
		return this.pricePerGallon;
	}
	
	/**
	 * Provides a projection of the total fuel cost for this trip based on the total
	 * number of miles to be traveled, the fuel efficiency of the rental car, and the
	 * cost of fuel. Standard rental cars used have decreasing fuel efficiency as the
	 * size gets bigger. Thus, efficiency is a function of passenger count. We assume
	 * the following projections:
	 * 
	 * 45mpg   1-2 passengers
	 * 32mpg   3-4 passengers
	 * 28mpg   5-6 passengers
	 * 22mpg   7-8 passengers
	 * 15mpg    9+ passengers
	 * 
	 * @return The projected fuel cost in USD.
	 */
	public double getEstimatedFuelCost()
	{
		double temp = 0.0;
		if (this.numPeople >= 9)
		{
			temp = 15.0;
		}
		else if (this.numPeople >= 7)
		{
			temp = 22.0;
		}
		else if (this.numPeople >= 5)
		{
			temp = 28.0;
		}
		else if (this.numPeople >= 3)
		{
			temp = 32.0;
		}
		else
		{
			temp = 45.0;
		}
		return temp * (double)this.miles;
	}
	
	/**
	 * Retrieves the list of stops on this RoadTip as a single string with values
	 * separated by a comma and a single space. The last stop has no punctuation after
	 * it.
	 * 
	 * @return The list of stops.
	 */
	public String getStops()
	{
		String temp = "";
		
		for (int i = 0; i < this.stops.length - 1; i++)
		{
			temp += String.format("%s, ");
		}
		temp += this.stops[this.stops.length - 1];
		return temp;
	}
	
	/**
	 * Provides a string summary of this RoadTrip. Promotional details and trip duration
	 * are provided as with all VacationPackages, followed by a summary message about the
	 * stops to be made on the following line with each stop separated by a comma. The
	 * second line should be prefixed with 11 blank spaces for alignment below the trip name.
	 * For example:
	 * 
	 * $  150.99  Rustic Backpacking at Mt. Rushmore
	 *           A road trip with stops at Carhenge, Chimney Rock, The Corn Palace, Mt. Rushmore
	 *           
	 * @return The formatted string summary.
	 */
	public String toString()
	{
		return String.format("");
	}
}
