package Exceptions;

@SuppressWarnings("serial")
public class CustomerAlreadyExcist extends Exception {
	
	public CustomerAlreadyExcist() {
		super("Customer already excists please change email.");
	}

}
