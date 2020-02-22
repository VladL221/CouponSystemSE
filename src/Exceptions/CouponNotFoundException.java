package Exceptions;

@SuppressWarnings("serial")
public class CouponNotFoundException extends Exception {

	
	public CouponNotFoundException() {
		super("The coupon id you have entered does not excist try again with a valid id");
	}
	
}
