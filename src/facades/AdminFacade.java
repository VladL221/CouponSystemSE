package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import Exceptions.CompanyAlreadyExcist;
import Exceptions.CompanyNotFoundException;
import Exceptions.CustomerAlreadyExcist;
import Exceptions.CustomerNotFoundException;
import beans.Company;
import beans.Coupon;
import beans.Customer;

public class AdminFacade extends ClientFacade {

	@Override
	public boolean login(String email, String password) {
		if (email.contentEquals("admin@admin.com") && password.contentEquals("admin")) {
			return true;
		}

		return false;
	}

	public void addCompany(Company company) throws SQLException, CompanyAlreadyExcist {

		ArrayList<Company> companies = compDB.getAllCompanies();
		for (Company comp : companies) {
			if (comp.getName().equals(company.getName()) || comp.getEmail().equals(company.getEmail())) {
				throw new CompanyAlreadyExcist();

			}
		}

		compDB.addCompany(company);

	}

	public void updateCompany(Company company) throws SQLException, CompanyNotFoundException {
		Company companies = compDB.getOneCompany(company.getCompanyID());
		boolean exist = false;

		if (companies.getCompanyID() == company.getCompanyID()) {
			compDB.updateCompany(company);
			exist = true;

		}

		if (!exist) {
			throw new CompanyNotFoundException();
		}

	}

	public void deleteCompany(int companyId) throws SQLException, CompanyNotFoundException {
		Company company = compDB.getOneCompany(companyId);
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCompID(companyId);
		if (company != null) {
			for (Coupon coupon : coupons) {
				couponDB.deleteCouponPurchaseByCoupID(coupon.getCouponID());
			}
			if (!coupons.isEmpty()) {
				for (Coupon coupon : coupons) {
					couponDB.deleteCoupon(coupon.getCouponID());
				}
			}
			compDB.deleteCompany(companyId);

		}
		throw new CompanyNotFoundException();

	}

	public ArrayList<Company> getAllCompanies() throws SQLException, CompanyNotFoundException {
		ArrayList<Company> companies = compDB.getAllCompanies();
		if (!companies.isEmpty()) {
			return companies;
		}

		throw new CompanyNotFoundException();
	}

	// checks if the company that you are trying to get is exists
	public Company getOneCompany(int companyId) throws SQLException, CompanyNotFoundException {
		Company company = compDB.getOneCompany(companyId);

		if (company != null) {
			return company;

		}

		throw new CompanyNotFoundException();
	}

	// adds a customer and check if it already exists in the database
	public void addCustomer(Customer customer) throws SQLException, CustomerAlreadyExcist {
		ArrayList<Customer> customers = custDB.getAllCustomers();
		for (Customer cust : customers) {
			if (cust.getEmail().equals(customer.getEmail())) {
				throw new CustomerAlreadyExcist();
			}
		}

		custDB.addCustomer(customer);

	}

	public void updateCustomer(Customer customer) throws SQLException, CustomerNotFoundException {
		Customer customerr = custDB.getOneCustomer(customer.getId());
		boolean exist = false;
		if (customer.getId() == customerr.getId()) {
			custDB.updateCustomer(customer);
			exist = true;
		}
		if (!exist) {
			throw new CustomerNotFoundException();
		}

	}

	// deletes the customer purchase history as well as the customer
	public void deleteCustomer(int customerId) throws SQLException, CustomerNotFoundException {

		Customer customer = custDB.getOneCustomer(customerId);
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCustID(customerId);

		if (customer != null) {
			for (Coupon coupon : coupons) {
				couponDB.deleteCouponPurchase(customerId, coupon.getCouponID());
			}
			custDB.deleteCustomer(customerId);

		} else {
			throw new CustomerNotFoundException();
		}

	}

	// add exception*!
	public ArrayList<Customer> getAllCustomers() throws SQLException {
		ArrayList<Customer> customers = custDB.getAllCustomers();
		return customers;

	}

	// return by its id
	public Customer getOneCustomer(int id) throws SQLException, CustomerNotFoundException {

		Customer customer = custDB.getOneCustomer(id);
		if (customer.getId() == id) {
			return customer;

		}
		throw new CustomerNotFoundException();

	}

}
