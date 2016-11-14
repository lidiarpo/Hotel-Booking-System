package component.model;

public class Discount {
	private Amenity[] discountedAmenities;
	private RoomType[] discountedRoomTypes;
	
	private int discount;
	
	public Discount(Amenity[] amenities, RoomType[] roomTypes, int discount){
		discountedAmenities = amenities;
		discountedRoomTypes = roomTypes;
		this.discount = discount;
	}
	
	public boolean checkCompatibility(Booking booking){
		
		return true;
	}
	
	
}
