package component.model;

import java.util.Date;

public class Room implements Comparable{
	private String roomNumber;
	private RoomType roomType;
	private boolean clean = true;
	private Date lastcleaned;
	private Date lastcheckout;
	//private int costOfRoom;
	

	public Room(String number, RoomType RoomType){
		roomNumber = number;
		roomType = RoomType;
		//cost of room sets to 1000 if nothing else is stated
		//costOfRoom=1000;
	}
	public Room(String number, RoomType RoomType, int cost){
		roomNumber = number;
		roomType = RoomType;
		//costOfRoom=cost;
	}
	/*
	public void setCostOfRoom(int cost){
		this.costOfRoom=cost;
	}
	
	public int getCostOfRoom(){
		return this.costOfRoom;
	}
	*/
	
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof Room){
			Room other = (Room) o;
			
			result = roomNumber == other.roomNumber && roomType.equals(other.roomType);
		}
		
		return result;
	}

	public Date getLastCleaned() {
		return lastcleaned;
	}
	
	/**
	 * Check clean status of room.
	 * @return True if the room is clean, otherwise false
	 */
	public boolean getStatus(){
		return clean;
	}
	
	/**
	 * Set room to clean, call after cleaning.
	 * @date The Date of the cleaning.
	 * @return True if the room was cleaned.
	 */
	public boolean setClean(Date date) {
		if (!clean && date.after(lastcheckout)) {
			clean = true;
			lastcleaned = date;
			return true;
		}
		return false;
	}
	
	/**
	 * Set room to dirty, call after check out.
	 * @date the Date of the checkout.
	 */
	public boolean setDirty(Date date) {
		if(clean){
			clean = false;
			lastcheckout = date;
			return true;
		}
		return false;
	}
	@Override
	public int compareTo(Object other) {
		if(other.getClass()!=this.getClass()){
			return 0;
		}
		if(Integer.parseInt(this.getRoomNumber())==Integer.parseInt(((Room) other).getRoomNumber())){
			return 0;
		}
		else if(Integer.parseInt(this.getRoomNumber())>Integer.parseInt(((Room) other).getRoomNumber())){
			return 1;
		}
		else{
			return -1;
		}
	}
}
