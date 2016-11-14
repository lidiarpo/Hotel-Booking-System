package component.model;

import java.util.Date;
import access.User;

public class AmenitiesBooking {
	private Amenity bookedAmenity;
	private Guest bookingGuest;
	private User bookingReceptionist;
	private Date bookedTime;
	
	public AmenitiesBooking(Amenity amenity, Guest guest, User receptionist, Date time){
		setBookedAmenity(amenity);
		setBookingGuest(guest);
		setBookingReceptionist(receptionist);
	}

	public User getBookingReceptionist() {
		return bookingReceptionist;
	}

	public void setBookingReceptionist(User bookingReceptionist) {
		this.bookingReceptionist = bookingReceptionist;
	}

	public Guest getBookingGuest() {
		return bookingGuest;
	}

	public void setBookingGuest(Guest bookingGuest) {
		this.bookingGuest = bookingGuest;
	}

	public Amenity getBookedAmenity() {
		return bookedAmenity;
	}

	public void setBookedAmenity(Amenity bookedAmenity) {
		this.bookedAmenity = bookedAmenity;
	}
	
	public Date getBookedTime(){
		return bookedTime;
	}
	public void setBookedTime(Date time){
		bookedTime = time;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof AmenitiesBooking){
			AmenitiesBooking other = (AmenitiesBooking) o;
			
			result = bookedAmenity.equals(other.bookedAmenity) && bookingGuest.equals(other.bookingGuest) && bookingReceptionist.equals(other.bookingReceptionist);
		}
		
		return result;
	}
}
