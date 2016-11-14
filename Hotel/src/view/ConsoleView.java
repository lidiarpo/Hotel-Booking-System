package view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import access.UserType;
import component.HotelSystemComponent;
import access.User;

public class ConsoleView {

	private List<User> users = new LinkedList<User>();

	private User currentUser = null;

	private Scanner scanner;
	private HotelSystemComponent comp;

	public ConsoleView() {
		User mainAdmin = new User("admin", "admin", UserType.Admin);
		User receptionist = new User("rec", "rec", UserType.Receptionist);
		User manager = new User("man", "man", UserType.HotelManager);
		User cleaner = new User("clean", "clean", UserType.Cleaner);

		users.add(mainAdmin);
		users.add(receptionist);
		users.add(manager);
		users.add(cleaner);
		
		comp = new HotelSystemComponent();

		scanner = new Scanner(System.in);

		while (true) { // login loop
			login();
			while (true) { // program loop
				System.out.println(currentUser.getUsername() + ": ");
				String command = scanner.next();

				if (command.equals("logout")) {
					break;
				}
				if (getAccess().equals(UserType.Admin)) {
					if ("createRoomType".equals(command)) {
						createRoomType();
					} else if ("createRoom".equals(command)) {
						createRoom();
					} else if ("changeRoomPrice".equals(command)) {
						changeRoomPrice();
					} else if ("removeRoom".equals(command)) {
						removeRoom();
					} else if ("removeRoomType".equals(command)) {
						removeRoomType();
					} else if ("createAmenity".equals(command)) {
						//createAmenity();
					} else if ("removeAmenity".equals(command)) {
						//removeAmenity();
					}
				}
				if (getAccess().equals(UserType.HotelManager)) {
					if ("createDiscount".equals(command)) {
						// CODE;
					}
				}
				if (getAccess().equals(UserType.Receptionist) || getAccess().equals(UserType.HotelManager)) {
					if ("bookAmenity".equals(command)) {
						// CODE;
					} else if ("cancelAmenity".equals(command)) {
						// CODE;
					} else if ("availableAmenity".equals(command)) {
						// CODE;
					} else if ("bookRoom".equals(command)) {
						bookRoom();
					} else if ("cancelBooking".equals(command)) {
						cancelBooking();
					} else if ("checkCost".equals(command) || "printReceipt".equals(command)) {
						printReceipt();
						// comp.getDiscounts();
					} else if ("checkIn".equals(command)) {
						checkIn();
					} else if ("checkOut".equals(command)) {
						checkOut();
						// billing is used inside checkout
						// comp.getBilling()
					} else if ("createGuest".equals(command)) {
						// CODE;
					} else if ("changeEmail".equals(command)) {
						// CODE;
					} else if ("changePhone".equals(command)) {
						// CODE;
					} else if ("addCharges".equals(command)) {
						addCharges();
					} else if ("viewGuest".equals(command)) {
						// CODE;
					} else if ("lastCleaned".equals(command)) {
						// CODE;
					} else if ("checkPayment".equals(command)) {
						// CODE;
					} else if ("checkAvailableRooms".equals(command)) {
						checkAvailableRooms();
					} else if ("checkNumberOfGuests".equals(command)) {
						// for any one day.
					}
				}
				if (getAccess().equals(UserType.Cleaner)) {
					if ("cleanRoom".equals(command)) {
						cleanRoom();
					}
				}
			}
		}
	}
	
	private void checkAvailableRooms() {
		System.out.print("enter start date (yyyy-mm-dd):");
		String sDate = scanner.next();
		System.out.print("enter end date (yyyy-mm-dd):");
		String eDate = scanner.next();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = format.parse(sDate);
			endDate = format.parse(eDate);
		} catch (ParseException e) {
			System.out.println("something went wrong");
			return;
		}
		Set<String> temp = comp.getBookingInterface().availableTypes(startDate, endDate);
		if(temp==null){
			System.out.println("no roomtypes free");
		}else{
			for(String s:temp){
				System.out.println(s);
			}
		}
	}

	private void addCharges() {
		System.out.println("enter booking id: ");
		long nr=0;
		String bookingId= scanner.next();
		try{
			nr=Long.parseLong(bookingId);
		}catch(NumberFormatException e){
			System.out.println("something went wrong, no charges added");
			return;
		}
		System.out.println("please enter amount to add(- to remove)");
		String amount = scanner.next();
		int a;
		try{
			a=Integer.parseInt(bookingId);
		}catch(NumberFormatException e){
			System.out.println("something went wrong, no charges added");
			return;
		}
		comp.getBookingInterface().addCharges(nr, a);
		System.out.println("charges added");
	}

	private void checkIn() {
		long bookingNr = -1;
		while (bookingNr < 0) {
			System.out.print("Provide Booking Id: ");
			
			try {
				bookingNr = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format
			
			
			if (comp.getBookingInterface().displayBookingInfo(bookingNr) == null) {
				System.out.println("No booking exists with this ID");
				bookingNr = -1;
			}
		}
		
		System.out.print("Input the passport number: ");
		String passportNr = scanner.next();
		
		comp.getBookingInterface().getBookings(passportNr).contains(bookingNr);
		
		Map<String, Long> map = comp.getCheckInOut().checkIn(bookingNr);
		
		if(map != null){
			
			System.out.println("RoomNr" + "," + "KeyCardId");
			System.out.println("------------------");
			
			for (String key : map.keySet()) {
		        System.out.println(key + ",     " + map.get(key));
		    }
		}
		else{
			System.out.println("Unable to checkin a booking that has been checked out!");
		}
	}

	private void checkOut() {
		int bookingNr = -1;
		while (bookingNr < 0) {
			System.out.print("Provide Booking Id: ");
			
			try {
				bookingNr = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format
			
			
			if (comp.getBookingInterface().displayBookingInfo(bookingNr) == null) {
				System.out.println("No booking exists with this ID");
				bookingNr = -1;
			}
		}
		
		// auto mark room for cleaning
		// key cards auto removed
		if(comp.getCheckInOut().checkOut(bookingNr)){
			int price[] = comp.getBookingInterface().checkCost(bookingNr);
			int totalCost = price[0] - price[1];
			
			System.out.println("Cost for the stay: " +  totalCost + " Kr.");
	
			System.out.println("Enter verification number from external system:");
			String ver = scanner.next();
			
			comp.getBilling().payCredit(bookingNr, ver);
			
			System.out.println("Payment verified.");
			System.out.println("Room set to require cleaning.");
		}
		else{
			System.out.println("Unable to check out a booking that is not checked in!");
		}
	}

	private void bookRoom() {
		Date startDate = new Date();
		Date endDate = new Date();

		Set<String> available = new HashSet<String>();

		while (available.isEmpty()) {
			System.out.print("enter start date (yyyy-mm-dd):");
			String sDate = scanner.next();
			System.out.print("enter end date (yyyy-mm-dd):");
			String eDate = scanner.next();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			try {
				startDate = format.parse(sDate);
				endDate = format.parse(eDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// Start must be at least 1 day before end date.
			if (startDate.getTime() >= endDate.getTime()) {
				System.out.println("Start must be at least 1 day before End.");
				continue;
			}
			
			Set<String> temp = comp.getBookingInterface().availableTypes(startDate, endDate);
			
			available.addAll(temp);
				
			if (available.isEmpty()) {
				// Hotel is full some day.
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			
				Calendar day = Calendar.getInstance();
				day.setTime(startDate);
				
				Calendar end = Calendar.getInstance();
				end.setTime(endDate);
				
				System.out.println("Hotel full for the following dates: ");
				while (day.before(end)) {
					if (comp.getBookingInterface().isFullyBooked(day.getTime())) {
						System.out.println(ft.format(day.getTime()));
					}					
					day.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
		}

		// Set check-in and check-out hours
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 15);
		startDate = cal.getTime();

		cal = Calendar.getInstance();
		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		endDate = cal.getTime();

		System.out.println("Select a room type, the available types are: ");
		for (String type : available) {
			System.out.println(type);
		}

		String validRoomType = null;
		while (validRoomType == null) {
			System.out.println("Enter your RoomType: ");
			String rType = scanner.next();
			for (String type : available) {
				if (type.equals(rType)) {
					validRoomType = type;
					break;
				}
			}
			if (validRoomType == null) {
				System.out.println("Room type not valid!");
			}
		}
		
		System.out.print("Input the passport number: ");
		String passportNr = scanner.next();
		
		if (!comp.getGuestInterface().isGuest(passportNr)) {
			System.out.print("Input the first name: ");
			String fName = scanner.next();
		
			System.out.print("Input the last name: ");
			String lName = scanner.next();
		
			System.out.print("Input the email: ");
			String email = scanner.next();
		
			System.out.print("Input the phone number: ");
			String phone = scanner.next();
			
			comp.getGuestInterface().createGuest(passportNr, fName, lName, email, phone);
		}
		
		long id = comp.getBookingInterface().createBooking(passportNr, startDate, endDate, validRoomType);
		
		System.out.println("Booking process completed for booking with ID:" + id);
	}
	
	private void cancelBooking(){
		System.out.println("enter bookingid for the room to cancel: ");
		String bookingId = scanner.next();
		long nr=0;
		try{
			nr = Long.parseLong(bookingId);
		}catch(NumberFormatException e){
			System.out.println("something went wrong, nothing canceld");
			return;
		}
		comp.getBookingInterface().cancelBooking(nr);
	}

	private void createAmenity() {
		String name = null;
		while (name == null) {
			System.out.print("Input the amenity name: ");
			name = scanner.next();

			if (comp.getViewFacilities().getAmenities().contains(name)) {
				System.out.println("An amenity of that name already exists.");
			}
		}
		
		int maxCapacity = 0;
		while (maxCapacity < 1) {
			System.out.print("Input the amenity capacity: ");
			try {
				maxCapacity = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format
		}
		
		String description = null;
		while (description == null) {
			System.out.print("Input the amenity description: ");
			description = scanner.next();
		}
		
		comp.getAmenitiesManagement().createAmenity(name, maxCapacity, description);
	}

	private void removeAmenity() {
		if (comp.getViewFacilities().getAmenities().isEmpty()) {
			System.out.println("There are no amenities, use <createAmenity>.");
			return;
		}
		
		String name = null;
		while (name == null) {
			System.out.print("Input the amenity name: ");
			name = scanner.next();

			if (!comp.getViewFacilities().getAmenities().contains(name)) {
				System.out.println("An amenity of that name does not exists.");
			}
		}
		
		comp.getAmenitiesManagement().removeAmenity(name);
	}

	private void removeRoom() {
		if (comp.getViewFacilities().getRooms().isEmpty()) {
			System.out.println("There are no rooms, use <createRoom>.");
			return;
		}
		if (!comp.getBookingInterface().getBookings().isEmpty()) {
			System.out.println("Can't remove rooms when there are bookings.");
			return;
		}
		int roomNumber = 0;
		while (roomNumber < 1) {
			System.out.print("Input the room number: ");
			try {
				roomNumber = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format

			if (comp.getViewFacilities().getRoomType(String.valueOf(roomNumber)) == null) {
				System.out.println("Room number does not exists, choose another number.");
			}
		}

		boolean result = comp.getAdminRoomManagement().removeRoom(String.valueOf(roomNumber));
		
		System.out.println("Room number " + roomNumber + " was " + (result ? "" : "NOT") +  " removed.");
	}

	private void removeRoomType() {
		if (comp.getViewFacilities().getTypeList().isEmpty()) {
			System.out.println("There are no room types, use <createRoomType>.");
			return;
		}
		if (!comp.getBookingInterface().getBookings().isEmpty()) {
			System.out.println("Can't remove room types when there are bookings.");
			return;
		}
		String roomType = null;
		while (roomType == null) {
			System.out.print("Input the room type: ");
			roomType = scanner.next();

			if (!comp.getViewFacilities().getTypeList().contains(roomType)) {
				System.out.println("Room type does not exists.");
			}
		}
		
		if (!comp.getViewFacilities().getRooms(roomType).isEmpty()) {
			System.out.println("There are rooms of this type, remove them first.");
			return;
		}
		
		if (comp.getAdminRoomManagement().removeRoomType(roomType)) {
			System.out.println("RoomType: <" + roomType + "> removed.");
		}
	}

	private void createRoomType() {
		String roomType = null;
		while (roomType == null) {
			System.out.print("Input the room type: ");
			roomType = scanner.next();

			if (comp.getViewFacilities().getTypeList().contains(roomType)) {
				System.out.println("Room type already exists, choose another name.");
			}
		}
		
		int roomPrice = -1;
		while (roomPrice < 0) {
			System.out.print("Input the room price: ");
			try {
				roomPrice = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format
		}
		
		if(comp.getAdminRoomManagement().createRoomType(roomType, roomPrice)){
			System.out.println("A RoomType was created with the type <" + roomType + "> and the price <" + roomPrice + ">.");
		}
		else{
			System.out.println("An error occured while creating the object! Please try again!");
		}
	}
	
	private void changeRoomPrice() {
		if (comp.getViewFacilities().getTypeList().isEmpty()) {
			System.out.println("There are no room types, use <createRoomType>.");
			return;
		}
		
		String roomType = null;
		while (roomType == null) {
			System.out.print("Input the room type: ");
			roomType = scanner.next();

			if (!comp.getViewFacilities().getTypeList().contains(roomType)) {
				System.out.println("Room type does not exists, choose another type.");
			}
		}
		
		int roomPrice = -1;
		while (roomPrice < 0) {
			System.out.print("Input the new room price: ");
			try {
				roomPrice = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format
		}
		
		comp.getAdminRoomManagement().changeRoomPrice(roomType, roomPrice);
	}
	
	private void createRoom() {
		if (comp.getViewFacilities().getTypeList().isEmpty()) {
			System.out.println("There are no room types, use <createRoomType>.");
			return;
		}
		int roomNumber = 0;
		while (roomNumber < 1) {
			System.out.print("Input the room number: ");
			try {
				roomNumber = Integer.parseInt(scanner.next());
			} catch (NumberFormatException e) {
				System.out.println("Incorrect format.");
			} // Do not update if incorrect format

			if (comp.getViewFacilities().getRoomType(String.valueOf(roomNumber)) != null) {
				System.out.println("Room number already exists, choose another number.");
			}
		}

		System.out.print("Input the room type: ");
		String stype = scanner.next();

		if (!comp.getViewFacilities().getTypeList().contains(stype)) {
			System.out.println("This type does not exist, first use <createRoomType>.");
			return;
		}
		
		comp.getAdminRoomManagement().createRoom(stype, String.valueOf(roomNumber));
		
		System.out.println("Room number " + roomNumber + " is now created.");
	}

	private UserType getAccess() {
		return currentUser.getUserType();
	}

	private void login() {
		User temp = null;
		boolean rdyToGo = false;
		String username = "";
		while (!rdyToGo) {
			System.out.println("Please insert username: ");
			username = scanner.next();
			System.out.println("Please insert password: ");
			String pass = scanner.next();

			for (User user : users) {
				if (username.equals(user.getUsername())) {
					temp = user;
					break;
				}
			}
			if (temp == null) {
				System.out.println("cant find username");
			} else if (temp.getPassword().equals(pass)) {
				rdyToGo = true;
			}
		}
		currentUser = temp;
	}
	
	private void cleanRoom(){
		System.out.println("What is the number of the cleaned room?");
		int roomNr = Integer.parseInt(scanner.next());
		
		comp.getRoomManagement().cleanRoom(Calendar.getInstance().getTime(), String.valueOf(roomNr));
		
		System.out.println("Room " + roomNr + " has now been set to cleaned!");
	}
	
	private void printReceipt(){
		long id = -1;
		while(id == -1){
			System.out.println("Please enter the booking-Id to print its receipt!");
			String s = scanner.next();
			
			
			try{
				id = Long.parseLong(s);
			}
			catch(NumberFormatException e){
				System.out.println("Please enter a number!");
				continue;
			}
		}
		
		int[] cost = comp.getBookingInterface().checkCost(id);
		
		comp.getBookingInterface().displayBookingInfo(id);
		System.out.println("The cost for this booking will be:");
		System.out.println(cost[0] + " SEK. (Discount = " + cost[1] + " SEK). Final price = " + (cost[0]-cost[1]) + " SEK.");
	}
}
