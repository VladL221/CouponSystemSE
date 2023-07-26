package Exceptions;

@SuppressWarnings("serial")
public class CompanyAlreadyExcist extends Exception {

	public CompanyAlreadyExcist() {
		super("The company already excist with the following email or name please try again");
	}

	
	
}
