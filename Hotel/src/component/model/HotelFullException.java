package component.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class HotelFullException extends Exception {

	private List<Date> fullDates = new LinkedList<Date>();
	
	
	public HotelFullException(List<Date> dates) {
		fullDates.addAll(dates);
	}
	
	public List<Date> getFullDates() {
		return fullDates;
	}
	
}
