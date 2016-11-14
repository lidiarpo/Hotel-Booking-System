package component.interfaces;

public interface PVerification {

	/**
	 * Requests a payment of the given sum.
	 * <P>
	 * The system prompts the user to handle payment and waits for payment to complete.
	 * 
	 * @param sum The sum to be paid.
	 * @return A Verification Number if the sum was paid, A Bill reference if it was not.
	 */
	public String requestPayment(int sum);
}
