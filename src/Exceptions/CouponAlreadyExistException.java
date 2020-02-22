package Exceptions;

@SuppressWarnings("serial")
public class CouponAlreadyExistException extends Exception {

	
	public CouponAlreadyExistException() {
		super("the coupon already exist please try another coupon");
	}
	
}
