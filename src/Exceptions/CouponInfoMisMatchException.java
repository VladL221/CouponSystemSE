package Exceptions;

@SuppressWarnings("serial")
public class CouponInfoMisMatchException extends Exception {

	public CouponInfoMisMatchException() {
		super("the info you have entred does not match the coupon please double check all coupon info.");
	}

}
