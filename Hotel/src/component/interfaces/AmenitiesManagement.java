package component.interfaces;

public interface AmenitiesManagement {

	/**
	 * Creates a new Amenity.
	 * @param name The name of the new Amenity.
	 * @param maxCapacity The max capacity of the new Amenity.
	 * @param description A description of the Amenity.
	 * @return True if successful.
	 */
	public boolean createAmenity(String name, int maxCapacity, String description);
	
	/**
	 * Removes an existing Amenity that has no future bookings.
	 * 
	 * @param name The name of the Amenity to remove.
	 * @return True if successful.
	 */
	public boolean removeAmenity(String name);
	
}
