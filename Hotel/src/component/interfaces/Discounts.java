package component.interfaces;

public interface Discounts {

	/**
	 * Calculates the final discount for a booking.
	 * 
	 * @param roomTypes The list of rooms in the booking.
	 * @param amenities The list of amenities in the booking.
	 * @return The amount that is discounted.
	 */
	public int calculateDiscount(String[] roomTypes, String[] amenities);
}
