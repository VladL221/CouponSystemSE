package Exceptions;

@SuppressWarnings("serial")
public class CustomerLoginMisMatch extends Exception {

	public CustomerLoginMisMatch() {
		super("The customer login you have entered isnt valid please try again with a valid customer login");
	}
}
