package component.interfaces;

import java.util.Date;

public interface DiscountManagement {

	/**
	 * Create a discount for the hotel. A discount will be deducted from bookings that have
	 * at least the given room types and amenities added to their bill.
	 * <P>
	 * A discount is valid for bookings with a start date within the from-to range.
	 * Discounts can be disabled but default to being enabled.
	 * <P>
	 * The amount given is deducted from the final bill.
	 * 
	 * @param discountName The name given to the discount. Must be unique.
	 * @param roomTypes The types of rooms that must be on the bill for a discount.
	 * @param amenities The amenities that must be on the bill for a discount.
	 * @param amount The amount of money that is discounted.
	 * @param from The start time of the discount.
	 * @param to The end time of the discount.
	 * @return True if the discount was successfully created.
	 */
	public boolean createDiscount(String discountName, String[] roomTypes, String[] amenities
			, int amount, Date from, Date to);
	
	/**
	 * Temporarily or permanently disables a discount.
	 * <P>
	 * Any booking that is checked out while the discount is disabled will not receive a discount.
	 * 
	 * 
	 * @param discountName The name of the discount.
	 * @return True if the discount is disabled.
	 */
	public boolean disableDiscount(String discountName);
	
	/**
	 * Enable a discount.
	 * 
	 * @param discountName The name of the discount.
	 * @return True if the discount is enabled.
	 */
	public boolean enableDiscount(String discountName);
	
	/**
	 * Changes the time the discount is available, to shorten or extend.
	 * @param discountName The name of the discount.
	 * @param from The new start date.
	 * @param to The new end date.
	 * @return True if successful.
	 */
	public boolean changeDiscountRange(String discountName, Date from, Date to);
	
}
