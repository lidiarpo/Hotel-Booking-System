package component.model;

public class RoomType {
	private String roomTypeName;
	private int basePrice;
	
	public RoomType(String name){
		setRoomTypeName(name);
		basePrice = 1000;
	}
	
	public RoomType(String name, int price){
		setRoomTypeName(name);
		basePrice = price;
	}
	
	public boolean setRoomTypePrice(int price){
		basePrice = price;
		return true;
	}
	
	public int getRoomTypePrice(){
		return basePrice;
	}
	
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	
	public String getRoomTypeName() {
		return roomTypeName;
	}
	
	@Override
	public boolean equals(Object o){
		boolean result = false;
		
		if(o instanceof RoomType){
			RoomType other = (RoomType) o;
			
			result = roomTypeName.equals(other.roomTypeName);
		}
		
		return result;
	}
}
