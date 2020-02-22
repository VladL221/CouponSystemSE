package db;

import java.sql.SQLException;
import java.util.ArrayList;


import Exceptions.CustomerNotFoundException;
import beans.Customer;

public interface CustomerDAO {
	
	boolean isCustomerExists(String email,String password) throws SQLException, CustomerNotFoundException;
	void addCustomer(Customer customer) throws SQLException;
	void updateCustomer(Customer customer) throws SQLException;
	void deleteCustomer(int customerID) throws SQLException;
	ArrayList<Customer> getAllCustomers() throws SQLException;
	Customer getOneCustomer(int customerID) throws SQLException, CustomerNotFoundException;
	

}
