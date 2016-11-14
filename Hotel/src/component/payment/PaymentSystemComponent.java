package component.payment;

import java.util.Date;

import component.interfaces.Billing;
import component.interfaces.DiscountManagement;
import component.interfaces.Discounts;
import component.interfaces.PVerification;
import component.model.Hotel;

public class PaymentSystemComponent implements PVerification, DiscountManagement, Billing, Discounts {
	
	private Hotel hotel;
	
	public PaymentSystemComponent(Hotel hotel){
		this.hotel = hotel;
	}
	
	@Override
	public void payCredit(int bookingNr, String verificationNr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean createDiscount(String discountName, String[] roomTypes, String[] amenities, int amount, Date from,
			Date to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean disableDiscount(String discountName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean enableDiscount(String discountName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeDiscountRange(String discountName, Date from, Date to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String requestPayment(int sum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int calculateDiscount(String[] roomTypes, String[] amenities) {
		// TODO Auto-generated method stub
		return 0;
	}


}
