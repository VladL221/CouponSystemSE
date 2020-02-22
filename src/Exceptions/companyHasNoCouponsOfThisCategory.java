package Exceptions;

@SuppressWarnings("serial")
public class companyHasNoCouponsOfThisCategory extends Exception {

	public companyHasNoCouponsOfThisCategory() {
		super("this company has no coupons of this category please try with a different category");
	}
}
