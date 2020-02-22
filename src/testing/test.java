package testing;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;

import Exceptions.AdminNotExist;
import Exceptions.CompanyAlreadyExcist;
import Exceptions.CompanyIDMismatch;
import Exceptions.CompanyLoginMisMatch;
import Exceptions.CompanyNotFoundException;
import Exceptions.CouponAlreadyAdded;
import Exceptions.CouponNotFoundException;
import Exceptions.CustomerAlreadyExcist;
import Exceptions.CustomerDoesNotOwnCouponsException;
import Exceptions.CustomerLoginMisMatch;
import Exceptions.CustomerNotFoundException;
import Exceptions.companyHasNoCoupons;
import Exceptions.companyHasNoCouponsOfThisCategory;
import Login.ClientType;
import Login.LoginManager;
import Thread.Job;
import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import db.CompanyDBDAO;
import db.ConnectionPool;
import db.CouponDBDAO;
import db.CustomerDBDAO;
import facades.AdminFacade;
import facades.CompanyFacade;
import facades.CustomerFacade;

public class test {

	public static void main(String[] args) {

		Job job = new Job();

		job.start();

		// try catch block of admin type
		try {
			AdminFacade user = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin",
					ClientType.Admin);

			Calendar startcal = Calendar.getInstance();
			Calendar endcal = Calendar.getInstance();
			// set this for the end date of the coupon
			endcal.set(2020, 06, 16);
			// set this if you want to add start date to the coupon (default date is the
			// current date if you dont use this set.
			// startcal.set(year, month, date);
			Date start = new Date(endcal.getTimeInMillis());
			Date end = new Date(startcal.getTimeInMillis());
			// adds a new company
			user.addCompany(new Company("name", "emaill", "password"));
			// adds a new customer
			user.addCustomer(new Customer("firstName", "lastName", "email", "password"));
			// updates the company with the id that you enter i.e (companyID, email,
			// password)
			user.updateCompany(new Company(6, "email", "password"));
			// updates the customer with the id that you input i.e (customer id, first
			// name,lastname,email,password)
			user.updateCustomer(new Customer(6, "firstName", "lastName", "email", "password"));
			// deletes the company as well as the coupons and the coupons purchase history
			// with the id that you enter here
			user.deleteCompany(6);
			// deletes the customer as well as his coupon history purchae by his id
			user.deleteCustomer(6);
			// returns all the companies that exist (needs syso for it to print)
			user.getAllCompanies();
			// returns all the customers that exist (needs a syso for it to pring)
			user.getAllCustomers();
			// returns the company with the id given (needs syso to work)
			user.getOneCompany(6);
			// returns the customer with the id thats given (needs syso to print)
			user.getOneCustomer(6);

		} catch (SQLException | CompanyLoginMisMatch | AdminNotExist | CustomerLoginMisMatch e) {
			System.out.println(e.getMessage());
		} catch (CompanyAlreadyExcist e) {
			System.out.println(e.getMessage());
		} catch (CustomerAlreadyExcist e) {
			System.out.println(e.getMessage());
		} catch (CompanyNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (CustomerNotFoundException e) {
			System.out.println(e.getMessage());
		}

		/*
		 * //try catch block of company type try { CompanyFacade user = (CompanyFacade)
		 * LoginManager.getInstance().login("email", "password", ClientType.Company);
		 * 
		 * } catch (SQLException | CompanyLoginMisMatch | AdminNotExist |
		 * CustomerLoginMisMatch e) { System.out.println(e.getMessage()); }
		 * 
		 * //try catch block of customer type try { CustomerFacade user =
		 * (CustomerFacade) LoginManager.getInstance().login("email", "password",
		 * ClientType.Customer);
		 * 
		 * } catch (SQLException | CompanyLoginMisMatch | AdminNotExist |
		 * CustomerLoginMisMatch e) { System.out.println(e.getMessage()); }
		 * 
		 */

		// works user.getCompanyDetails();

		// Coupon coupon = new Coupon(29, "cahnged", "changed", new Date(2020 - 02 -
		// 18), d1, 5, 420, "none",
		// Category.Food);

		// CouponDBDAO db = new CouponDBDAO();

		// db.addCouponPurchase(3, 22);

		// !!!!!!!!!!!!!!!! not full works user.purchaseCoupon(coupon);

		// user.getAllCustomerCoupons();

		// works user.deleteCustomer(2);

		// * user.getAllCustomerCouponsByCategory(Category.Spa);

		// @SuppressWarnings("deprecation")
//			Date start1 = new Date(2020, 02, 19);
//			@SuppressWarnings("deprecation")
//			Date end1 = new Date(2020, 03, 15);
//			Coupon coupon = new Coupon(32, "xd", "5% discount on vanilla coke", start1, end1, 2, 69, "none", Category.Food);
//			
//			user.purchaseCoupon(coupon);
//			
		// works user.deleteCoupon();
		// works
		// System.out.println(user.getCompanyCouponsByCategory(Category.Clotthes));

		// works System.out.println(user.GetCompanyCouponsByMaxNumber());
		// user.getAllCustomerCouponsByMaxValue(50);
		// works System.out.println(user.getAllCustomerCouponsByMaxValue(68));
		// works System.out.println(user.getCustomerDetails());

		// works
		// System.out.println(user.getAllCustomerCouponsByCategory(Category.Food));
		// works user.getCustomerDetails();

		// user.addCoupon(new Coupon(Category.Food,"dx", "5 discount on vanilla
		// coke",new Date(start.getTimeInMillis()) , d1, 2, 69, "none"));

		// works user.deleteCoupon(20);

		// user.getCompanyCoupons();

		// works user.getCompanyCouponsByCategory(Category.Food);

		// works user.GetCompanyCouponsByMaxNumber(20);

		// works user.updateCoupon(new Coupon(21, "cahnged", "changed", new
		// Date(start.getTimeInMillis()), d1, 69,420, "none", Category.Restaurant));

//			CouponDBDAO coup = new CouponDBDAO();
//			coup.addCoupon(new Coupon(Category.Food,"vanilcoke5%", "5% discount on vanilla coke", date, date, 2, 69, "none"));

		// user.addCustomer(new Customer("hermon", "hermonovich", "hermono@walla.com",
//			 "123"));

		// works user.addCompany(new Company("choka lola", "choka@lola.com",
		// "password"));

		// works user.deleteCompany(2);

		// works user.deleteCustomer(2);

		// works user.getAllCompanies();

		// works user.getAllCustomers();

		// works user.getOneCompany(2);

		// works user.getOneCustomer(1);

		// works user.updateCompany(new Company(2,"test@changed.com", "testPassword"));

		// works user.updateCustomer(new Customer(3, "bob", "bobson", "bob@hotmail.com",
		// "bob123"));

		job.stopJob();
		ConnectionPool.getInstance().closeAllConnections();

	}

}
