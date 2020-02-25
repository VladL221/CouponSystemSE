package Exceptions;

@SuppressWarnings("serial")
public class ExpiredCouponDate extends Exception {

	public ExpiredCouponDate() {
		super("the coupon you are trying to add has expired. please try another coupon with a valid date");
	}
}
