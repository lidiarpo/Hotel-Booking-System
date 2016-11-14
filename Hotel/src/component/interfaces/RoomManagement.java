package component.interfaces;

import java.util.Date;

public interface RoomManagement {
	
	/**
	 * Set the flag that the room has been cleaned.
	 * @param when Date when the room was cleaned.
	 * @param roomNr What room is to be set to clean.
	 * @return True if successful.
	 */
	public boolean cleanRoom(Date when, String roomNr);
	
	/**
	 * Set a flag that the room requires cleaning.
	 * @param when Date when the flag was set (or when the room was checked out).
	 * @param roomNr What room is to be set to unclean.
	 * @return True if successful.
	 */
	public boolean setRoomUnclean(Date when, String roomNr);
	
	/**
	 * Check if the room is clean and checked out.
	 * @param roomNr What room is to be checked.
	 * @return True if the room is available for check in.
	 */
	public boolean checkRoomStatus(String roomNr);
	
}