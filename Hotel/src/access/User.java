package access;

public class User {
	private String Username;
	private String Password;
	private boolean loggedIn;
	
	private UserType userType;
	
	public User(String username, String password, UserType userType){
		Username = username;
		Password = password;
		this.userType = userType;
	}
	
	public boolean logIn(String username, String password){
		loggedIn = username.equals(Username) && password.equals(Password);
		return loggedIn;
	}

	public String getUsername(){
		return Username;
	}

	public UserType getUserType(){
		return userType;
	}
	
	public String getPassword(){
		return Password;
	}
	
	public void logOut(){
		loggedIn = false;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof User){
			User other = (User) o;
			
			result = Username.equals(other.Username) && Password.equals(other.Password) && userType.equals(other.userType);
		}
		
		return result;
	}
}
