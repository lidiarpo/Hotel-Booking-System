package component.interfaces;

import java.util.Set;

public interface ViewFacilities {

	/**
	 * Returns the room numbers of all rooms.
	 * @return An array of all room numbers.
	 */
	public Set<String> getRooms();
	
	/**
	 * Returns the room numbers of all rooms of one type.
	 * @param roomType The type of room.
	 * @return An array of all room numbers of type roomType.
	 */
	public Set<String> getRooms(String roomType);
	
	/**
	 * Return the name of the room type for a room.
	 * @param roomNr The room number.
	 * @return The name of the type, null if the room does not exist.
	 */
	public String getRoomType(String roomNr);

	/**
	 * Return the set of types specified for the hotel.
	 * @return A set of names.
	 */
	public Set<String> getTypeList();

	/**
	 * Returns the names of all the created amenities.
	 * @return The names of all amenities.
	 */
	public Set<String> getAmenities();
	
	/**
	 * Retrieve the max capacity of the amenity.
	 * @param amenityName The name of the amenity.
	 * @return The max capacity.
	 */
	public int getAmenityCapacity(String amenityName);
	
	/**
	 * Return a String description of the amenity.
	 * @param amenityName The name of the amenity.
	 * @return A String description.
	 */
	public String getAmenityDescription(String amenityName);
	
}
