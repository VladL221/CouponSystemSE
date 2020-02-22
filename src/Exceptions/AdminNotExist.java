package Exceptions;

@SuppressWarnings("serial")
public class AdminNotExist extends Exception {

	public AdminNotExist() {
		super("the admin login is incorrect please try again with a valid admin login");
	}
	
}
