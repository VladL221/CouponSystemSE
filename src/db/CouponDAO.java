package db;

import java.sql.SQLException;
import java.util.ArrayList;

import beans.Category;
import beans.Coupon;

public interface CouponDAO {
	
	void addCoupon(Coupon coupon) throws SQLException;
	void updateCoupon(Coupon coupon) throws SQLException;
	void deleteCoupon(int CouponID) throws SQLException;
	ArrayList<Coupon> getAllCoupons() throws SQLException;
	Coupon getOneCoupon(int CouponID) throws SQLException;
	void addCouponPurchase(int customerID,int CouponID) throws SQLException;
	void deleteCouponPurchase(int customerID,int CouponID) throws SQLException;
	void deleteCouponPurchase(int CustomerID) throws SQLException;
	Coupon getOneCouponByCompID(int CompanyID) throws SQLException;
	ArrayList<Coupon> getAllCouponsByCompID(int CompanyID) throws SQLException;
	ArrayList<Coupon> getAllCouponsByCompIDAndCategory(int CompanyID, Category category) throws SQLException;
	ArrayList<Coupon> getAllCouponsByCompIDAndMaxValue(int CompanyID, double maxVal) throws SQLException;
	ArrayList<Coupon> getAllCouponsByCustID(int CustomerID) throws SQLException;
	ArrayList<Coupon> getAllCouponsByCustIDAndCategory(int CustomerID, Category category) throws SQLException;
	void deleteCouponPurchaseByCoupID(int CouponID) throws SQLException;
	void addCouponToCompany(Coupon coupon, int CompanyID) throws SQLException;
	ArrayList<Coupon> getAllCouponsByCustIDAndMaxValue(int CustomerID, double maxVal) throws SQLException;
	

}
