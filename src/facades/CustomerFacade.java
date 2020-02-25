package facades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Exceptions.CouponAlreadyExistException;
import Exceptions.CouponInfoMisMatchException;
import Exceptions.CustomerDoesNotOwnCouponsException;
import Exceptions.CustomerNotFoundException;
import Exceptions.ExpiredCouponDate;
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

	// can't have more than 1 of the same coupon
	// can't purchase coupon if amount (stock) is 0
	// can't purchase coupon if the end date is due
	// after purchasing coupon the amount(stock) decreases by 1

	// i became paranoid so now the user wont be able to alter the rows when adding
	// the purchase :) it only took me 3 hours :) not even mad :)
	public void purchaseCoupon(Coupon coupon)
			throws SQLException, CouponAlreadyExistException, ExpiredCouponDate, CouponInfoMisMatchException {

		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCustID(CustomerID);
		ArrayList<Coupon> allCoup = couponDB.getAllCoupons();
		Calendar cend = Calendar.getInstance();
		Calendar cstart = Calendar.getInstance();
		boolean empty = true;
		if (!coupons.isEmpty()) {
			for (Coupon coupon2 : coupons) {
				if (coupon.getCouponID() == coupon2.getCouponID()) {
					throw new CouponAlreadyExistException();
				}
			}

		}
		if (empty) {

			for (Coupon coup : allCoup) {
				if (coup.getCouponID() == coupon.getCouponID() && coup.getAmount() > 0) {

					cend.setTime(coup.getEndDate());
					cstart.setTime(coup.getStartDate());
					Date end = new Date(cend.getTimeInMillis());
					Date start = new Date(cstart.getTimeInMillis());
					if (cend.after(Calendar.getInstance())) {

						if (coup.getAmount() == coupon.getAmount()
								&& coup.getCategory().ordinal() + 1 == coupon.getCategory().ordinal() + 1
								&& coup.getDescription().equals(coupon.getDescription())
								&& coup.getImage().equals(coupon.getImage()) && coup.getPrice() == coupon.getPrice()
								&& coup.getTitle().equals(coupon.getTitle()) && coup.getEndDate().equals(end)
								&& coup.getStartDate().equals(start)) {
							couponDB.addCouponPurchase(CustomerID, coup.getCouponID());
							coup.setAmount(coup.getAmount() - 1);
							couponDB.updateCoupon(coup);

						} else {
							throw new CouponInfoMisMatchException();
						}

					} else {
						throw new ExpiredCouponDate();

					}
				}
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
