package component.model;

public class Amenity {
	private String name;
	
	public Amenity(String name){
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof Amenity){
			Amenity other = (Amenity) o;
			
			result = name.equals(other.name);
		}
		
		return result;
	}
}
