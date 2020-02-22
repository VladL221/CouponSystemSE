package Exceptions;

@SuppressWarnings("serial")
public class CompanyIDMismatch extends Exception {

	public CompanyIDMismatch() {
		super("Company id mismatch, the company you have entered mismatched with companies id please enter a valid company");
	}
	
	
	
}
