package db;

import java.sql.SQLException;
import java.util.ArrayList;

import Exceptions.CompanyNotFoundException;
import beans.Company;

public interface CompanyDAO {
	
	boolean isCompanyExists(String email,String password) throws SQLException, CompanyNotFoundException;
	void addCompany(Company company) throws SQLException;
	void updateCompany(Company company) throws SQLException;
	void deleteCompany(int companyID) throws SQLException;
	ArrayList<Company>	getAllCompanies() throws SQLException;
	Company getOneCompany(int companyID) throws SQLException,CompanyNotFoundException;

}
