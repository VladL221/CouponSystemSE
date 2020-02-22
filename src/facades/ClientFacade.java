package facades;

import java.sql.SQLException;

import db.CompanyDBDAO;
import db.CouponDBDAO;
import db.CustomerDBDAO;

public abstract class ClientFacade  {

	
	protected CompanyDBDAO compDB = new CompanyDBDAO();
	protected CouponDBDAO couponDB = new CouponDBDAO();
	protected CustomerDBDAO custDB = new CustomerDBDAO();
	public abstract boolean login(String email,String password) throws SQLException;
	
}
