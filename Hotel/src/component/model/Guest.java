package component.model;

public class Guest {
	private String guestName, guestPhoneNumber, guestPassPortNumber, guestLastName, guestEmail;
	
	public Guest(String name, String phoneNumber, String passportNumber,String lastName,String email){
		guestName = name;
		guestPhoneNumber = phoneNumber;
		guestPassPortNumber = passportNumber;
		guestLastName=lastName;
		guestEmail=email;
	}
	
	public String getLastName(){
		return guestLastName;
	}
	public String getEmail(){
		return guestEmail;
	}
	public void setEmail(String email){
		this.guestEmail=email;
	}
	public void setLastName(String name){
		this.guestLastName=name;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestPhoneNumber() {
		return guestPhoneNumber;
	}

	public void setGuestPhoneNumber(String guestPhoneNumber) {
		this.guestPhoneNumber = guestPhoneNumber;
	}

	public String getGuestPassPortNumber() {
		return guestPassPortNumber;
	}

	public void setGuestPassPortNumber(String guestPassPortNumber) {
		this.guestPassPortNumber = guestPassPortNumber;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof Guest){
			Guest other = (Guest) o;
			
			result = guestName.equals(other.guestName) && guestPassPortNumber.equals(other.guestPassPortNumber) && guestPhoneNumber.equals(other.guestPhoneNumber);
		}
		
		return result;
	}
}
