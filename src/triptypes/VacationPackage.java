// COURSE: CSCI1620
// TERM: Spring 2020
//
// NAME: Brandon Cline, Nate Tyler
// RESOURCES: Referenced Stack Overflow for the toString() price formatting.

package triptypes;

/**
 * This class serves as the base class of all types of vacation packages within the
 * travel agency program.
 * 
 * @author bcline, ntyler
 */
public abstract class VacationPackage
{
	/**
	 * Name of the vacation package.
	 */
	private String name = new String();
	/**
	 * The number of days included in this VacationPackage trip.
	 */
	private int days;
	
	/**
	 * Initialises a VacationPackage with provided values.
	 * 
	 * @param name The promotional marketing name for this package.
	 * @param days The number of days included in this VacationPackage trip.
	 */
	public VacationPackage(String nameIn, int daysIn)
	{
		this.setName(nameIn);
		this.setLength(daysIn);
	}
	
	/**
	 * Updates the VacationPackage's externally facing promotional
	 * name. Due to travel agency policy, this name is never allowed to
	 * be empty as it would be confusing for agents and customers interacting
	 * with the system. Names that do not comply with this policy will be
	 * ignored and the package will be given the name "PACKAGE NAME TBD" as
	 * a placeholder.
	 * 
	 * @param nameIn The updated name to use for this package.
	 */
	public void setName(String nameIn)
	{
		if (nameIn != null && !nameIn.equals(""))
		{
			this.name = nameIn;
		}
		else
		{
			this.name = "PACKAGE NAME TBD";
		}
	}
	
	/**
	 * Updates the length of this VacationPackage.
	 * All packages must havea minimum of one day.
	 * @param daysIn The new number of days for this package.
	 */
	public void setLength(int daysIn)
	{
		if (daysIn > 0)
		{
			this.days = daysIn;
		}
		else
		{
			this.days = 1;
		}
	}
	
	/**
	 * Retrieves the promotional name of this package.
	 * @return The name.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Retrieves the number of days included in this package.
	 * @return The number of days for this trip.
	 */
	public int getNumDays()
	{
		return this.days;
	}
	
	// 3 total abstract methods.
	/**
	 * This method provides the full price of a vacation package, which
	 * is must be computed based on the specific kind of trip being booked.
	 * @return The price of a vacation package in USD.
	 */
	public abstract double getPrice();
	/**
	 * This method provides the required up front deposit amount for a
	 * given vacation. This must be computed based on rules determined
	 * by specific package types, per travel agency policies.
	 * @return The deposit amount required in USD.
	 */
	public abstract double getDepositAmount();
	/**
	 * This method provides the subtotal for a trip related to lodging
	 * expenses (ie, not including flights, meals, and incidentals).
	 * Lodging rates are determined by specific package types.
	 * @return The lodging subtotal in USD.
	 */
	public abstract double getLodgingCost();
	
	/**
	 * This method provides the remaining amount due to the travel agent
	 * for this trip less any deposit made upfront.
	 * @return The remaining balance to pay the travel agency in USD.
	 */
	public double getAmountDue()
	{
		return this.getPrice() - this.getDepositAmount();
	}
	
	/**
	 * This method produces a String summary of a VacationPackage. Strings
	 * will be prefixed with the $ symbol, followed by trip total price rounded
	 * to two decimal places in a 8 character wide field. Price details should be
	 * followed by two spaces and the promotional name of this trip. For example:
	 * 
	 * $ 1234.56  Spring Break at the Beach
	 * $  150.99  Rustic Backpacking at Mt. Rushmore
	 * 
	 * @return The formatted string summary.
	 */
	public String toString()
	{
		return String.format("$%8.2f  %s", this.getPrice(), this.getName());
	}
	
	/**
	 * Provides a logical equality comparison for VacationPackages and any other
	 * object type.
	 * @param other A reference to another object to be compared with this one.
	 * @return True if and only if this VacationPackage shares the same
	 * promotional name as one referred to by other. false when other is
	 * not a valid VacationPackage object or the names do not match
	 */
	public boolean equals(VacationPackage other)
	{
		return this.name.equals(other.getName());
	}
}
