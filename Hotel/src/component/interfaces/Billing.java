package component.interfaces;

public interface Billing {

	/**
	 * Notify the system that payment has been made for a booking.
	 * @param bookingNr The Id of the booking.
	 * @param verificationNr The Id received from the bank.
	 */
	public void payCredit(int bookingNr, String verificationNr);
}
