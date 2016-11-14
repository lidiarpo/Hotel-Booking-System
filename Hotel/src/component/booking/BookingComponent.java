package component.booking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import component.interfaces.BookingInterface;
import component.interfaces.CheckInOut;
import component.interfaces.GuestInterface;
import component.model.Booking;
import component.model.Booking.BookingStatus;
import component.model.Guest;
import component.model.Hotel;
import component.model.HotelFullException;
import component.model.KeyCard;
import component.model.Room;
import component.model.RoomType;

public class BookingComponent implements BookingInterface, CheckInOut, GuestInterface {
	
	private Hotel hotel;
	
	public BookingComponent(Hotel hotel){
		this.hotel = hotel;
	}
	
	@Override
	public Map<String, Long> checkIn(long bookingNr) {
		Booking booking = hotel.getBookingById(bookingNr);
		
		if(booking.getStatus() == BookingStatus.BOOKED){
			hotel.specifyRoomForBooking(booking);
			Map<String, Long> map = new TreeMap<String, Long>();
			
			for(Room room : booking.getBookedRooms()){
				map.put(room.getRoomNumber(), hotel.addKeyCardToRoom(room));
			}
			
			booking.updateStatus(BookingStatus.IN);
			
			return map;
		}
		
		return null;
	}

	@Override
	public boolean checkOut(long bookingNr) {
		Booking booking = hotel.getBookingById(bookingNr);
		
		if(booking.getStatus() == BookingStatus.IN){
			clearKeyCards(bookingNr);
			
			booking.updateStatus(BookingStatus.OUT);
			
			for (Room room : booking.getBookedRooms()) {
				room.setDirty(Calendar.getInstance().getTime());
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean createKeyCard(long bookingNr) {
		Booking booking = hotel.getBookingById(bookingNr);
		Room[] rooms = booking.getBookedRooms();
		for(Room room : rooms){
			hotel.addKeyCard(new KeyCard(room));
		}
		return true;
	}

	@Override
	public boolean clearKeyCards(long bookingNr) {
		Booking booking = hotel.getBookingById(bookingNr);
		
		if(booking != null){
			for(Room room : booking.getBookedRooms()){
				hotel.removeAllKeyCardsFromRoom(room.getRoomNumber());
				room.setDirty(Calendar.getInstance().getTime());
			}
		}
		
		return true;
	}

	@Override
	public long createBooking(String passportNr, Date from, Date to, String roomType) {
		if (Booking.daysBetween(from, to) < 1) {
			return -1;
		}
		try {
			boolean wasFull = true;
			for (RoomType type : hotel.getAvailableRoomTypes(from, to)) {
				if (type.getRoomTypeName() == roomType) {
					wasFull = false;
				}
			}
			if (wasFull) {
				return -1;
			}
		} catch (HotelFullException e) {
			return -1;
		}
		Guest guest = hotel.getGuest(passportNr);
		RoomType rt = hotel.getRoomType(roomType);
		Booking booking = hotel.createBooking(guest, rt, from, to);
		return booking.getBookingId();
	}

	@Override
	public void cancelBooking(long bookingNr) {
		hotel.removeBooking(hotel.getBookingById(bookingNr));
	}

	@Override
	public List<Long> getBookings(String passportNr) {
		Guest guest = hotel.findGuestByPassport(passportNr);
		List<Long> temp = new ArrayList<>();
		for(Booking booking : hotel.findBookingsByGuest(guest)){
			temp.add(booking.getBookingId());
		}
		return temp;
		
		
	}

	@Override
	public String displayBookingInfo(long bookingNr) {
		Booking booking = hotel.getBookingById(bookingNr);
		
		if(booking != null){
			System.out.println("Booking ID: " + booking.getBookingId());
			System.out.println("Checkin date: " + booking.getStartDate());
			System.out.println("Checkout date: " + booking.getEndDate());
			System.out.println("Number of nights: " + booking.getBookedNights());
			if(booking.getStatus() == BookingStatus.BOOKED){
				System.out.println("Booked room type: " + booking.getReservedRoomType().getRoomTypeName());
			}
			else{
				System.out.println("Booked room numbers: \n");
				for(Room room : booking.getBookedRooms()){
					System.out.print(room.getRoomNumber() + ", ");
				}
			}
			
			return booking.toString();
		}
		
		return null;
	}

	@Override
	public boolean isFullyBooked(String roomType, Date day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.setTime(day);
		Set<RoomType> allTypes;
		try{
			allTypes = hotel.getAvailableRoomTypes(day,c.getTime());
		}catch(HotelFullException e){
			return true;
		}
		if(allTypes.contains(roomType)){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean isFullyBooked(Date day) {
		Calendar c = Calendar.getInstance();
		c.setTime(day);
		c.add(Calendar.DAY_OF_MONTH, 1);
		
		try{
			hotel.getAvailableRoomTypes(day,c.getTime());
		}catch(HotelFullException e){
			return true;
		}
		return false;
	}

	@Override
	public Set<String> availableTypes(Date from, Date to) {
		Set<RoomType> temp;
		try{
			temp = hotel.getAvailableRoomTypes(from, to);
		}catch(HotelFullException e){
			return new HashSet<String>();
		}
		Set<String> temp2 = new HashSet<String>();
		
		for(RoomType r:temp){
			temp2.add(r.getRoomTypeName());
		}
		return temp2;
	}


	@Override
	public int[] checkCost(long bookingNr) {
		
		int cost = hotel.getBookingById(bookingNr).getTotalCost();
		//cost-discount.
		int[] temp={cost,0}; // where the 0 is the discount
		return temp;
	}

	@Override
	public boolean isGuest(String passportNr) {
		Guest guest = hotel.getGuest(passportNr);
		if(guest == null)
			return false;
		return true;
	}

	@Override
	public boolean createGuest(String passportNr, String fName, String lName, String email, String phone) {
		hotel.addGuest(new Guest(fName, phone, passportNr,lName,email));
		return true;
	}

	@Override
	public boolean changeEmail(String passportNr, String email) {
		try{
			hotel.getGuest(passportNr).setEmail(email);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public boolean changePhone(String passportNr, String phone) {
		try{
			hotel.getGuest(passportNr).setGuestPhoneNumber(phone);
		}catch(Exception e){
			return false;
		}
		return true;
	}

	@Override
	public String getName(String passportNr) {
		try{
			return hotel.getGuest(passportNr).getGuestName();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public String getEmail(String passportNr) {
		try{
			return hotel.getGuest(passportNr).getEmail();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public String getPhone(String passportNr) {
		try{
			return hotel.getGuest(passportNr).getGuestPhoneNumber();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public void addCharges(long bookingNr,int amount){
		hotel.getBookingById(bookingNr).addCost(amount);
	}

	@Override
	public List<Long> getBookings() {
		List<Long> ids = new LinkedList<Long>();
		for (Booking book : hotel.getBookingsList()) {
			ids.add(book.getBookingId());
		}
		return ids;
	}
}
