package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exceptions.CustomerNotFoundException;

import beans.Customer;

public class CustomerDBDAO implements CustomerDAO {

	private ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public boolean isCustomerExists(String email, String password) throws SQLException, CustomerNotFoundException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM customers where customer_email = ? AND customer_password = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

			throw new CustomerNotFoundException();

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void addCustomer(Customer customer) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into customers(first_name,last_name,customer_email,customer_password) values(?,?,?,?)");
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPassword());

			ps.execute();
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"update customers set first_name = ?, last_name =?, customer_email =?,customer_password=? where customer_id = ?");
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getPassword());
			ps.setInt(5, customer.getId());
			ps.execute();

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCustomer(int customerID) throws SQLException {

		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from customers where customer_id =?");
			ps.setInt(1, customerID);
			ps.execute();
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select * from customers");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				customers.add(new Customer(rs.getInt("customer_id"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("customer_email"), rs.getString("customer_password")));

			}
			return customers;

		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public Customer getOneCustomer(int customerID) throws SQLException, CustomerNotFoundException {
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement("select * from customers where customer_id =?");
			ps.setInt(1, customerID);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("customer_email"), rs.getString("customer_password"));
			}

			throw new CustomerNotFoundException();

		} finally {
			pool.restoreConnection(con);
		}
	}

}
