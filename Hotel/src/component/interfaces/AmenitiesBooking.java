package component.interfaces;

import java.util.Date;

public interface AmenitiesBooking {

	/**
	 * Book an Amenity to a guest
	 * @param name The name of the Amenity.
	 * @param passportNr The passport number of the guest.
	 * @param from The start day and time.
	 * @param to  The end day and time.
	 * @return True if the Amenity was successfully booked for the time.
	 */
	public boolean bookAmenity(String name, String passportNr, Date from, Date to);
	
	/**
	 * Cancels the booking of an amenity by the Id.
	 * @param Id The Id of the booking that should be cancelled.
	 * @return True if the booking was successfully cancelled.
	 */
	public boolean cancelAmenityBooking(int Id);
	
	/**
	 * Returns the set of booking Id:s that are registered to a Guest.
	 * @param passportNr The guest's passport number. 
	 * @return An array with the Id:s
	 */
	public int[] getBookingIdByGuest(String passportNr);
	
	/**
	 * Returns the set of booking Id:s that are registered to an Amenity.
	 * @param name The name of the Amenity.
	 * @return An array with the Id:s
	 */
	public int[] getBookingIdByAmenity(String name);
	
	/**
	 * Returns a String with information that represents the booking.
	 * @param Id The Id of the Amenity booking.
	 * @return The Amenity booking info as a human readable String.
	 */
	public String displayBookingInfo(int Id);
	
	/**
	 * Checks the number of available slots for an Amenity between two given dates.
	 * @param name The name of the Amenity.
	 * @param from The start date of the period to check.
	 * @param to The end date of the period to check.
	 * @return The number of available slots.
	 */
	public int checkAvailability(String name, Date from, Date to);
	
}
