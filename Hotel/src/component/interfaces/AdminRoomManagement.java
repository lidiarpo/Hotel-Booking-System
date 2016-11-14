package component.interfaces;

import component.model.Room;

public interface AdminRoomManagement {

	/**
	 * Create a new type of room.
	 * @param type The name of the type.
	 * @param basePrice The price of the room type.
	 * @return True if successful.
	 */
	public boolean createRoomType(String type, int basePrice);
	
	/**
	 * Change the price for a type of room.
	 * @param type The name of the type.
	 * @param basePrice The price of the room type.
	 * @return True if successful.
	 */
	public boolean changeRoomPrice(String type, int newPrice);
	
	/**
	 * Remove a type of room, there must be no rooms in the system that have this type.
	 * @param type The name of the type.
	 * @return True if successful.
	 */
	public boolean removeRoomType(String type);
	
	/**
	 * Creates a room in the system.
	 * @param type The name of the room type.
	 * @param number the room number.
	 * @return True if the room was successfully created.
	 */
	public boolean createRoom(String type, String number);
	
	/**
	 * Removes a room from the system.
	 * @param number The room number.
	 * @return 
	 */
	public boolean removeRoom(String number);
}
