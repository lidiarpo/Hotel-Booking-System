package test;

import java.util.Date;

import component.HotelSystemComponent;



public class TestStart {

	HotelSystemComponent hc = new HotelSystemComponent();
	
	public void start(){
		hc.getAdminRoomManagement().createRoomType("single", 500);
		hc.getAdminRoomManagement().createRoomType("double", 1000);
		hc.getAdminRoomManagement().createRoomType("tripple", 1200);
		hc.getAdminRoomManagement().createRoomType("penthouse", 4000);
		hc.getAdminRoomManagement().createRoom("single", "101");
		hc.getAdminRoomManagement().createRoom("single", "102");
		hc.getAdminRoomManagement().createRoom("single", "103");
		hc.getAdminRoomManagement().createRoom("double", "201");
		hc.getAdminRoomManagement().createRoom("double", "202");
		hc.getAdminRoomManagement().createRoom("tripple", "203");
		hc.getAdminRoomManagement().createRoom("tripple", "204");
		hc.getAdminRoomManagement().createRoom("penthouse", "301");
		hc.getGuestInterface().createGuest("123456", "Tobbe", "Foughman", "tobbe@lalala.com", "0707123456");
		hc.getGuestInterface().createGuest("234567", "Rasti", "Tengman", "rasti@lalala.com", "0736332151");
		hc.getGuestInterface().createGuest("345678", "Tom", "Foughman", "tobbe@lalala.com", "0707111111");
		hc.getGuestInterface().createGuest("456789", "Alex", "Kerr", "alex@lalala.com", "0708111111");
		/*
		 *  Test what happens if we try to book a room that is not available 
		 */
		System.out.println(hc.getBookingInterface().availableTypes(new Date(2016,01,15),new Date(2016,01,20)));
		System.out.println();
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("123456", new Date(2016,01,07), new Date(2016,01,14), "double")));
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("234567", new Date(2016,01,07), new Date(2016,01,14), "double")));
		System.out.println(hc.getBookingInterface().availableTypes(new Date(2016,01,07),new Date(2016,01,14)));
		System.out.println();
		System.out.println();
		System.out.println("This room should not be bookable because not enough double rooms:");
		System.out.println();
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("345678", new Date(2016,01,07), new Date(2016,01,14), "double")));
		System.out.println();
		System.out.println("This one should");
		System.out.println();
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("456789", new Date(2016,01,07), new Date(2016,01,14), "single")));
		/*
		 * What happens if we try to cancel a booking and then book with another guest
		 */
		System.out.println();
		System.out.println("Try to cancel a booking, then you should be able to book a double room");
		System.out.println();
		hc.getBookingInterface().cancelBooking(hc.getBookingInterface().getBookings("123456").get(0));
		System.out.println(hc.getBookingInterface().availableTypes(new Date(2016,01,07),new Date(2016,01,14)));
		System.out.println();
		System.out.println("Here we are trying to book the double room again ");
		System.out.println();
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("345678", new Date(2016,01,07), new Date(2016,01,14), "double")));
		
		/*
		 * What happens if the start date is later than end date
		 */
		System.out.println();
		System.out.println("Three different cases where the check-in date is later then the check-out date:");
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("123456", new Date(2017,01,07), new Date(2016,01,14), "single")));
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("123456", new Date(2016,02,07), new Date(2016,01,14), "single")));
		System.out.println(hc.getBookingInterface().displayBookingInfo(hc.getBookingInterface().createBooking("123456", new Date(2016,01,15), new Date(2016,01,14), "single")));
		
		/*
		 * test check-in a person after it already checked out  
		 */
		System.out.println();
		System.out.println("Check in the guest:");
		System.out.println();
		System.out.println(hc.getCheckInOut().checkIn(hc.getBookingInterface().getBookings("234567").get(0)));
		System.out.println();
		System.out.println("Check out the guest");
		System.out.println();
		System.out.println(hc.getCheckInOut().checkOut(hc.getBookingInterface().getBookings("234567").get(0)));
		System.out.println();
		System.out.println("Try to check in the same guest:");
		System.out.println();
		System.out.println(hc.getCheckInOut().checkIn(hc.getBookingInterface().getBookings("234567").get(0)));
		
		/*
		 * Test if rooms is available again after the stay
		 */
		System.out.println();
		System.out.println("Now the stay period for the last bookings are over so all rooms should be available again:");
		System.out.println();
		System.out.println(hc.getBookingInterface().availableTypes(new Date(2016,01,15),new Date(2016,01,20)));
		
		/*
		 * Test to check-in someone twice and checkout someone twice
		 */
		System.out.println();
		System.out.println("Check in a guest twice and then check out the guest twice");
		System.out.println();
		System.out.println(hc.getCheckInOut().checkIn(hc.getBookingInterface().getBookings("345678").get(0)));
		System.out.println(hc.getCheckInOut().checkIn(hc.getBookingInterface().getBookings("345678").get(0)));
		System.out.println(hc.getCheckInOut().checkOut(hc.getBookingInterface().getBookings("345678").get(0)));
		System.out.println(hc.getCheckInOut().checkOut(hc.getBookingInterface().getBookings("345678").get(0)));
		
		/*
		 * Test to check out guest before checking in
		 */
		System.out.println();
		System.out.println("Check out a guest before we have checked it in:");
		System.out.println(hc.getCheckInOut().checkOut(hc.getBookingInterface().getBookings("456789").get(0)));
	
	}
}
