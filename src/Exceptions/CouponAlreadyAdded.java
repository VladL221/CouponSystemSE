package Exceptions;

@SuppressWarnings("serial")
public class CouponAlreadyAdded extends Exception {

	
	public CouponAlreadyAdded() {
		super("The coupon you are trying to add already exist please try another one.");
	}
	
}
