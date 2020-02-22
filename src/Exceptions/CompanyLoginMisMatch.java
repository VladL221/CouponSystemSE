package Exceptions;

@SuppressWarnings("serial")
public class CompanyLoginMisMatch extends Exception {

	
	public CompanyLoginMisMatch() {
		super("company with the following login does not exist please try again with a valid company login");
	}
}
