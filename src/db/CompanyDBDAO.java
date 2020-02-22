package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exceptions.CompanyNotFoundException;
import beans.Company;

public class CompanyDBDAO implements CompanyDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public boolean isCompanyExists(String email, String password) throws SQLException, CompanyNotFoundException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM companies where company_email = ? AND company_password = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

			throw new CompanyNotFoundException();

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void addCompany(Company company) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into companies(company_name,company_password,company_email) values(?,?,?)");
			ps.setString(1, company.getName());
			ps.setString(2, company.getPassword());
			ps.setString(3, company.getEmail());

			ps.execute();
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void updateCompany(Company company) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"update companies set company_password =?, company_email =? where company_id = ?");
			ps.setString(1, company.getPassword());
			ps.setString(2, company.getEmail());
			ps.setInt(3, company.getCompanyID());
			ps.execute();

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCompany(int companyID) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from companies where company_id =?");
			ps.setInt(1, companyID);
			ps.execute();
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public ArrayList<Company> getAllCompanies() throws SQLException {
		ArrayList<Company> companies = new ArrayList<Company>();
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select * from companies");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				companies.add(new Company(rs.getInt("company_id"), rs.getString("company_name"),
						rs.getString("company_email"), rs.getString("company_password")));

			}
			return companies;

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public Company getOneCompany(int companyID) throws SQLException, CompanyNotFoundException {
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select * from companies where company_id =?");
			ps.setInt(1, companyID);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Company(rs.getInt("company_id"), rs.getString("company_name"), rs.getString("company_email"),
						rs.getString("company_password"));
			}

			throw new CompanyNotFoundException();

		} finally {
			pool.restoreConnection(con);
		}

	}

}
