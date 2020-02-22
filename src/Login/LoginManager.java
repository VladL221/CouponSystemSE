package Login;

import java.sql.SQLException;

import Exceptions.AdminNotExist;
import Exceptions.CompanyLoginMisMatch;
import Exceptions.CustomerLoginMisMatch;
import facades.AdminFacade;
import facades.ClientFacade;
import facades.CompanyFacade;
import facades.CustomerFacade;

public class LoginManager {

	private static final LoginManager instance = new LoginManager();

	private LoginManager() {

	}

	public static LoginManager getInstance() {
		return instance;
	}

	public ClientFacade login(String email, String password, ClientType ct)
			throws SQLException, CompanyLoginMisMatch, AdminNotExist, CustomerLoginMisMatch {
		AdminFacade adminf = new AdminFacade();
		CompanyFacade compf = new CompanyFacade();
		CustomerFacade custf = new CustomerFacade();

		switch (ct) {
		case Admin:
			if (adminf.login(email, password)) {
				return adminf;
			}

			throw new AdminNotExist();
		case Company:

			if (compf.login(email, password)) {
				return compf;
			}
			throw new CompanyLoginMisMatch();

		case Customer:

			if (custf.login(email, password)) {
				return custf;
			}

			throw new CustomerLoginMisMatch();

		}

		return null;

	}

}
