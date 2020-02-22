package Exceptions;

@SuppressWarnings("serial")
public class CustomerDoesNotOwnCouponsException extends Exception {

	
	public CustomerDoesNotOwnCouponsException() {
		super("You do not own any coupons please purchase some");
	}
}
