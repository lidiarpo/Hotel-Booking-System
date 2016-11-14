package component.model;

import java.util.Date;

import access.User;

public class Booking {

	public enum BookingStatus {
		BOOKED, IN, OUT;
	}
	private BookingStatus status = BookingStatus.BOOKED;
	
	private static long latestBookingId = 1;
	private final long bookingId;

	private Guest bookingGuest;
	private User bookingReceptionist;
	
	private RoomType reservedRoomType;
	private Room[] bookedRooms;
	private Date startDate;
	private Date endDate;
	private int addedCost;
	
	public Booking(RoomType room, Guest guest, Date startDay, Date endDay){
		bookingId = getNewUniqueBoookingId();
		reservedRoomType = room;
		bookingGuest = guest;
		startDate = startDay;
		endDate = endDay;
	}
	
	/**
	 * Set the status of this booking. Status can only change in the order
	 * BOOKED -> IN -> OUT
	 * @param newStatus The new status
	 * @return True if status was changed.
	 */
	public boolean updateStatus(BookingStatus newStatus) {
		switch (status) {
		case BOOKED:
			if (newStatus == BookingStatus.IN) status = BookingStatus.IN;
			return true;
		case IN:
			if (newStatus == BookingStatus.OUT) status = BookingStatus.OUT;
			return true;
		default:
			return false;
		}
	}
	
	public BookingStatus getStatus() {
		return status;
	}
	
	private static synchronized long getNewUniqueBoookingId(){
		return latestBookingId++;
	}
	
	public static long daysBetween(Date from, Date to){
		long difference = (to.getTime() - from.getTime())/86400000; 
		return difference; 
	}
	
	public int getBookedNights(){
		return (int) daysBetween(startDate,endDate) + 1;
	}
	
	public void addCost(int amount){
		this.addedCost += amount;
	}
	
	public int getTotalCost(){
		return getBookedNights() * reservedRoomType.getRoomTypePrice() + addedCost;
	}
	
	public long getBookingId(){
		return bookingId;
	}
	
	public Guest getBookingGuest() {
		return bookingGuest;
	}

	public void setBookingGuest(Guest bookingGuest) {
		this.bookingGuest = bookingGuest;
	}

	public Room[] getBookedRooms() {
		return bookedRooms;
	}

	public void setReservedRoomType(RoomType reservedTypes){
		reservedRoomType = reservedTypes;
	}
	
	public RoomType getReservedRoomType(){
		return reservedRoomType;
	}
	
	public void setBookedRooms(Room[] bookedRooms) {
		this.bookedRooms = bookedRooms;
	}

	public User getBookingReceptionist() {
		return bookingReceptionist;
	}

	public void setBookingReceptionist(User bookingReceptionist) {
		this.bookingReceptionist = bookingReceptionist;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof Booking){
			Booking other = (Booking) o;
			
			result = bookingGuest.equals(other.bookingGuest) && //the equals on the list fucked up.
					 startDate.equals(other.startDate) && endDate.equals(other.endDate) &&/* bookedRooms.equals(other.bookedRooms) &&*/ bookingId == other.bookingId;
		}
		
		return result;
	}
	
	@Override
	public String toString(){
		return "Booking ID: "+this.bookingId+"\nGuest: "+this.bookingGuest.getGuestName()
		+"\nRoom: "+reservedRoomType.getRoomTypeName();
	}
}
