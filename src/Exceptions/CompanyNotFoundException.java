package Exceptions;

@SuppressWarnings("serial")
public class CompanyNotFoundException extends Exception {
	
	public CompanyNotFoundException() {
		super("the company you have tried to get doesnt exist please try again with a valid company id");
	}

}
