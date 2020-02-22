package Exceptions;

@SuppressWarnings("serial")
public class companyHasNoCoupons extends Exception {

	
	public companyHasNoCoupons() {
		super("this company has no coupons please make sure to add coupons");
	}
}
