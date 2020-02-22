package facades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Exceptions.CouponAlreadyExistException;
import Exceptions.CustomerDoesNotOwnCouponsException;
import Exceptions.CustomerNotFoundException;
import beans.Category;
import beans.Coupon;
import beans.Customer;

public class CustomerFacade extends ClientFacade {

	private int CustomerID;

	public CustomerFacade() {

	}

	@Override
	public boolean login(String email, String password) throws SQLException {
		ArrayList<Customer> customers = custDB.getAllCustomers();

		for (Customer cust : customers) {
			if (cust.getEmail().equals(email) && cust.getPassword().equals(password)) {
				CustomerID = cust.getId();
				return true;
			}
		}

		return false;
	}

	// cant have more than 1 of the same coupon
	// cant purcahse coupon if amount (stock) is 0
	// cant purcahse coupon if the end date is due
	// after purchasing coupon the amount(stock) needs to decrease by 1
	// ************************************************************************need
	// to add exceptions
	public void purchaseCoupon(Coupon coupon) throws SQLException, CouponAlreadyExistException {
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCustID(CustomerID);
		Calendar cal = Calendar.getInstance();
		cal.setTime(coupon.getEndDate());

		for (Coupon coupon2 : coupons) {
			if (coupon.getCouponID() != coupon2.getCouponID()) {

				if (coupon.getAmount() > 0 && cal.after(Calendar.getInstance())) {
					couponDB.addCouponPurchase(CustomerID, coupon.getCouponID());
					coupon.setAmount(coupon.getAmount() - 1);
					couponDB.updateCoupon(coupon);

				}

			} else {
				throw new CouponAlreadyExistException();
			}
		}
	}

	public ArrayList<Coupon> getAllCustomerCoupons() throws SQLException, CustomerDoesNotOwnCouponsException {
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCustID(CustomerID);

		if (!coupons.isEmpty()) {

			return coupons;
		}

		throw new CustomerDoesNotOwnCouponsException();

	}

	public ArrayList<Coupon> getAllCustomerCouponsByCategory(Category category)
			throws SQLException, CustomerDoesNotOwnCouponsException {
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCustIDAndCategory(CustomerID, category);

		if (!coupons.isEmpty()) {
			return coupons;
		}

		throw new CustomerDoesNotOwnCouponsException();

	}

	public ArrayList<Coupon> getAllCustomerCouponsByMaxValue(double maxVal)
			throws SQLException, CustomerDoesNotOwnCouponsException {
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCustIDAndMaxValue(CustomerID, maxVal);

		if (!coupons.isEmpty()) {
			return coupons;
		}

		throw new CustomerDoesNotOwnCouponsException();

	}

	public Customer getCustomerDetails() throws SQLException, CustomerNotFoundException {
		Customer customer = custDB.getOneCustomer(CustomerID);

		return customer;

	}

}
