package component.model;

import java.util.LinkedList;
import java.util.List;

import component.model.Hotel;

public class KeyCard {
	private Room room;
	private long currentUniqueId;
	
	private static long uniqueKeyCardId = 1;
	
	public KeyCard(){
		currentUniqueId = uniqueKeyCardId++;
	}
	
	public KeyCard(Room room){
		setRoom(room);
	}
	
	public Room getRoom(){
		return room;
	}
	
	/**
	 * Set which room this keycard should be able to open
	 * @param room The Room object of the room which this keycard is able to open
	 * @return True if the room was successfully changed, false otherwise
	 */
	public boolean setRoom(Room room){
		this.room = room;
		currentUniqueId = uniqueKeyCardId++;
		return true;
	}
	
	public long getUniqueId(){
		return currentUniqueId;
	}
	
	/**
	 * Returns a list for all cards that are registered to a room number, given the list of cards
	 * and a room number.
	 * @param allCards A List of all Key Cards.
	 * @param roomNr The room Number to check for Cards.
	 * @return The Key Cards that were registered to the Room.
	 */
	public static List<KeyCard> cardForRoom(List<KeyCard> allCards, String roomNr, Hotel hotel) {
		Room room = hotel.getRoomByNumber(roomNr);
		
		List<KeyCard> cardsForRoom = new LinkedList<KeyCard>();
		
		for (KeyCard keyCard : allCards) {
			Room r = keyCard.getRoom();
			if(r != null){
				if (r.equals(room)) {
					cardsForRoom.add(keyCard);
				}
			}
		}
		
		return cardsForRoom;
	}
}
