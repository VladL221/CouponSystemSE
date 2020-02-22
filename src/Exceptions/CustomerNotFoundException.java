package Exceptions;

@SuppressWarnings("serial")
public class CustomerNotFoundException extends Exception {
	
	
	public CustomerNotFoundException() {
		super("the custoemr with the id that you have entered does not exist please try again with a valid id");
	}

}
