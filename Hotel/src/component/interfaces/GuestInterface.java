package component.interfaces;

public interface GuestInterface {

	/**
	 * A method to check whether a Guest object exists within
	 * the system with a specific passport number.
	 * @param passportNr The passport number of the person in question
	 * @return true if there is a Guest object with that passportNr, false otherwise
	 */
	public boolean isGuest(String passportNr);
	
	/**
	 * A method for creating a Guest object representing a guest
	 * of the hotel. 
	 * @param passportNr The passport number of the person in question
	 * @param fName The first name of the person
	 * @param lName The last name of the person
	 * @param email The email address of the person
	 * @param phone The phone-number of the person
	 * @return True if a guest was successfully created, false otherwise
	 */
	public boolean createGuest(String passportNr, String fName, String lName, String email, String phone);
	
	/**
	 * Used to change the email address of a Guest object.
	 * @param passportNr The passport number of the guest
	 * @param email The new email address of the guest
	 * @return True if the email address was successfully changed, false otherwise
	 */
	public boolean changeEmail(String passportNr, String email);
	
	/**
	 * 
	 * @param passportNr The passport number of the guest
	 * @param phone The new phone number of the guest
	 * @return True if the phone number was successfully changed, false otherwise
	 */
	public boolean changePhone(String passportNr, String phone);
	
	/**
	 * Returns the name of the guest with corresponding passport number
	 * @param passportNr The passport number of the guest
	 * @return The name of the guest
	 */
	public String getName(String passportNr);
	
	/**
	 * Returns the email address of the guest with corresponding passport number
	 * @param passportNr The passport number of the guest
	 * @return The email address of the guest
	 */
	public String getEmail(String passportNr);
	
	/**
	 * Returns the phone number of the guest with corresponding passport number
	 * @param passportNr The passport number of the guest
	 * @return The phone number of the guest
	 */
	public String getPhone(String passportNr);
	
}
