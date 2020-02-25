package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import Exceptions.CompanyNotFoundException;
import Exceptions.CouponAlreadyAdded;
import Exceptions.CouponNotFoundException;
import Exceptions.companyHasNoCoupons;
import Exceptions.companyHasNoCouponsOfThisCategory;
import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;

public class CompanyFacade extends ClientFacade {

	private int CompanyID;

	public CompanyFacade() {

	}

	@Override
	public boolean login(String email, String password) throws SQLException {
		ArrayList<Company> companies = compDB.getAllCompanies();

		for (Company company : companies) {
			if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
				CompanyID = company.getCompanyID();
				return true;
			}
		}

		return false;
	}

//cant add a coupon with the asme title as an exixting one
	// can add excisting title of a different company to this company.
	// this method checks if the coupon title doesnt match an existing coupon with
	// the same title
	public void addCoupon(Coupon coupon) throws SQLException, CouponAlreadyAdded, CompanyNotFoundException {
		// this method already calls for all the coupons with the company id therefore
		// no need to check for title of different company
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCompID(CompanyID);

		boolean exist = false;
		for (Coupon coup : coupons) {

			if (coup.getTitle().equalsIgnoreCase(coupon.getTitle())) {
				exist = true;
				throw new CouponAlreadyAdded();

			}

		}
		if (!exist) {

			couponDB.addCouponToCompany(coupon, CompanyID);

		}

	}

	// updates an exciting coupon
	// **************************************************************************
	// need to add categories update to the couponDBDAO updatecoupon***
	public void updateCoupon(Coupon coupon) throws SQLException, CouponNotFoundException {
		// this method already pulls all the coupons by company id therefore wont be
		// able to update a coupon with a different company id
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCompID(CompanyID);
		boolean exist = false;
		if (!coupons.isEmpty()) {
			for (Coupon coupon2 : coupons) {
				if (coupon2.getCouponID() == coupon.getCouponID()) {
					couponDB.updateCoupon(coupon);
					System.out.println("Coupon updated successfully");
					exist = true;
					break;

				}
			}
		}
		if (!exist) {
			throw new CouponNotFoundException();
		}

	}

	// needs to also delete customer coupon history purcahse
	// this method deletes all history of coupons purchase and the coupon
	public void deleteCoupon(int CouponID) throws SQLException, CouponNotFoundException {
		boolean notFound = true;
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCompID(CompanyID);
		ArrayList<Customer> customers = custDB.getAllCustomers();

		for (Coupon coup : coupons) {
			if (coup.getCouponID() == CouponID) {
				for (Customer cust : customers) {
					couponDB.deleteCouponPurchase(cust.getId(), CouponID);

				}

				couponDB.deleteCoupon(CouponID);
				notFound = false;
				break;
			}
		}

		if (notFound) {
			throw new CouponNotFoundException();
		}

	}

	// needs to return the coupons of all the coupons by the logged in company id
	public ArrayList<Coupon> getCompanyCoupons() throws SQLException, companyHasNoCoupons {
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCompID(CompanyID);

		if (!coupons.isEmpty()) {

			return coupons;

		}

		throw new companyHasNoCoupons();

	}

	public ArrayList<Coupon> getCompanyCouponsByCategory(Category category)
			throws SQLException, companyHasNoCouponsOfThisCategory {
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCompIDAndCategory(CompanyID, category);

		if (!coupons.isEmpty()) {
			return coupons;

		}

		throw new companyHasNoCouponsOfThisCategory();

	}

	public ArrayList<Coupon> GetCompanyCouponsByMaxNumber(double maxVal) throws SQLException, companyHasNoCoupons {
		ArrayList<Coupon> coupons = couponDB.getAllCouponsByCompIDAndMaxValue(CompanyID, maxVal);

		if (!coupons.isEmpty()) {
			return coupons;
		}
		throw new companyHasNoCoupons();

	}

	public Company getCompanyDetails() throws SQLException, CompanyNotFoundException {
		Company company = compDB.getOneCompany(CompanyID);
		if (company != null) {
			return company;
		}
		return null;

	}

}
