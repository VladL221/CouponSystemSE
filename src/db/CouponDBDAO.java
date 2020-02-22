package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Category;
import beans.Coupon;

public class CouponDBDAO implements CouponDAO {

	ConnectionPool pool = ConnectionPool.getInstance();

	@Override
	public void addCoupon(Coupon coupon) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into coupons(company_id,category_id,title ,description ,start_date ,end_date ,amount ,price ,image ) "
							+ "values(?,?,?,?,?,?,?,?,?)");

			ps.setInt(1, coupon.getCompanyID());
			ps.setInt(2, coupon.getCategory().ordinal() + 1);
			ps.setString(3, coupon.getTitle());
			ps.setString(4, coupon.getDescription());
			ps.setDate(5, coupon.getStartDate());
			ps.setDate(6, coupon.getEndDate());
			ps.setInt(7, coupon.getAmount());
			ps.setDouble(8, coupon.getPrice());
			ps.setString(9, coupon.getImage());
			ps.execute();

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void addCouponToCompany(Coupon coupon, int CompanyID) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO coupons(company_id,category_id,title,description,start_date,end_date,amount,price,image) "
							+ "VALUES(?,?,?,?,?,?,?,?,?)");

			ps.setInt(1, CompanyID);
			ps.setInt(2, coupon.getCategory().ordinal() + 1);
			ps.setString(3, coupon.getTitle());
			ps.setString(4, coupon.getDescription());
			ps.setDate(5, coupon.getStartDate());
			ps.setDate(6, coupon.getEndDate());
			ps.setInt(7, coupon.getAmount());
			ps.setDouble(8, coupon.getPrice());
			ps.setString(9, coupon.getImage());
			ps.execute();

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void updateCoupon(Coupon coupon) throws SQLException {
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(
					"update coupons set title =?, description =?, start_date=?,end_date=?,amount=?,price=?,image=? where coupon_id=?");

			ps.setString(1, coupon.getTitle());
			ps.setString(2, coupon.getDescription());
			ps.setDate(3, coupon.getStartDate());
			ps.setDate(4, coupon.getEndDate());
			ps.setInt(5, coupon.getAmount());
			ps.setDouble(6, coupon.getPrice());
			ps.setString(7, coupon.getImage());
			ps.setInt(8, coupon.getCouponID());
			ps.execute();

		} finally {
			pool.restoreConnection(con);

		}
	}

	@Override
	public void deleteCoupon(int CouponID) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from coupons where coupon_id=?");
			ps.setInt(1, CouponID);
			ps.execute();
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public ArrayList<Coupon> getAllCoupons() throws SQLException {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con
					.prepareStatement("select * from coupons join categories on coupons.category_id = categories.id");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				coupons.add(new Coupon(rs.getInt("coupon_id"), rs.getInt("company_id"), rs.getString("title"),
						rs.getString("description"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("amount"), rs.getDouble("price"), rs.getString("image"),
						Category.values()[rs.getInt("category_id") - 1]));

			}
			return coupons;

		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public ArrayList<Coupon> getAllCouponsByCustID(int CustomerID) throws SQLException {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(
					"select * from coupons join customers_vs_coupons on coupons.coupon_id = customers_vs_coupons.coupon_id join customers on customers_vs_coupons.customer_id = customers.customer_id where customers.customer_id=?");
			ps.setInt(1, CustomerID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				coupons.add(new Coupon(rs.getInt("coupon_id"), rs.getInt("company_id"), rs.getString("title"),
						rs.getString("description"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("amount"), rs.getDouble("price"), rs.getString("image"),
						Category.values()[rs.getInt("category_id") - 1]));

				rs.getInt("customers.customer_id");

			}
			return coupons;

		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public ArrayList<Coupon> getAllCouponsByCustIDAndCategory(int CustomerID, Category category) throws SQLException {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(
					"select * from coupons join customers_vs_coupons on coupons.coupon_id = customers_vs_coupons.coupon_id join customers on customers_vs_coupons.customer_id = customers.customer_id join categories on categories.id = coupons.category_id where customers.customer_id=? and categories.id=?");

			ps.setInt(1, CustomerID);
			ps.setInt(2, category.ordinal() + 1);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				coupons.add(new Coupon(rs.getInt("coupon_id"), rs.getInt("company_id"), rs.getString("title"),
						rs.getString("description"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("amount"), rs.getDouble("price"), rs.getString("image"),
						Category.values()[rs.getInt("category_id") - 1]));

				rs.getInt("customers.customer_id");

			}
			return coupons;

		} finally {
			pool.restoreConnection(con);
		}
	}
	
	
	
	@Override
	public ArrayList<Coupon> getAllCouponsByCustIDAndMaxValue(int CustomerID, double maxVal) throws SQLException {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(
					"select * from coupons join customers_vs_coupons on coupons.coupon_id = customers_vs_coupons.coupon_id join customers on customers_vs_coupons.customer_id = customers.customer_id where customers_vs_coupons.customer_id=? and price<=?");
			ps.setInt(1, CustomerID);
			ps.setDouble(2, maxVal);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				coupons.add(new Coupon(rs.getInt("coupon_id"), rs.getInt("company_id"), rs.getString("title"),
						rs.getString("description"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("amount"), rs.getDouble("price"), rs.getString("image"),
						Category.values()[rs.getInt("category_id") - 1]));

			}
			return coupons;

		} finally {
			pool.restoreConnection(con);
		}
	}
	
	
	

	@Override
	public ArrayList<Coupon> getAllCouponsByCompID(int CompanyID) throws SQLException {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(
					"select * from coupons join categories on coupons.category_id = categories.id where company_id=?");
			ps.setInt(1, CompanyID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				coupons.add(new Coupon(rs.getInt("coupon_id"), rs.getInt("company_id"), rs.getString("title"),
						rs.getString("description"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("amount"), rs.getDouble("price"), rs.getString("image"),
						Category.values()[rs.getInt("category_id") - 1]));

			}
			return coupons;

		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public ArrayList<Coupon> getAllCouponsByCompIDAndCategory(int CompanyID, Category category) throws SQLException {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(
					"select * from coupons join categories on coupons.category_id = categories.id where company_id=? and category_id=?");
			ps.setInt(1, CompanyID);
			ps.setInt(2, category.ordinal() + 1);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				coupons.add(new Coupon(rs.getInt("coupon_id"), rs.getInt("company_id"), rs.getString("title"),
						rs.getString("description"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("amount"), rs.getDouble("price"), rs.getString("image"),
						Category.values()[rs.getInt("category_id") - 1]));

			}
			return coupons;

		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public ArrayList<Coupon> getAllCouponsByCompIDAndMaxValue(int CompanyID, double maxVal) throws SQLException {
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		Connection con = pool.getConnection();

		try {
			PreparedStatement ps = con.prepareStatement(
					"select * from coupons join categories on coupons.category_id = categories.id where company_id=? and price<=?");
			ps.setInt(1, CompanyID);
			ps.setDouble(2, maxVal);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				coupons.add(new Coupon(rs.getInt("coupon_id"), rs.getInt("company_id"), rs.getString("title"),
						rs.getString("description"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("amount"), rs.getDouble("price"), rs.getString("image"),
						Category.values()[rs.getInt("category_id") - 1]));

			}
			return coupons;

		} finally {
			pool.restoreConnection(con);
		}
	}

	@Override
	public Coupon getOneCoupon(int CouponID) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"select * from coupons join categories on coupons.category_id = categories.id where coupon_id=? ");
			ps.setInt(1, CouponID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Coupon(rs.getInt("coupon_id"), rs.getInt("company_id"), rs.getString("title"),
						rs.getString("description"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("amount"), rs.getDouble("price"), rs.getString("image"),
						Category.values()[rs.getInt("category_id") - 1]);

			} else {
				return null;
			}
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public Coupon getOneCouponByCompID(int CompanyID) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"select * from coupons join categories on coupons.category_id = categories.id where company_id=? ");
			ps.setInt(1, CompanyID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Coupon(rs.getInt("coupon_id"), rs.getInt("company_id"), rs.getString("title"),
						rs.getString("description"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("amount"), rs.getDouble("price"), rs.getString("image"),
						Category.values()[rs.getInt("category_id") - 1]);

			} else {
				return null;
			}
		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void addCouponPurchase(int customerID, int CouponID) throws SQLException {
		Connection con = pool.getConnection();
		
		try {
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO customers_vs_coupons(customer_id,coupon_id) VALUES(?, ?)");
			ps.setInt(1, customerID);
			ps.setInt(2, CouponID);
			ps.execute();

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCouponPurchase(int customerID, int CouponID) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement("delete from customers_vs_coupons where customer_id =? and coupon_id =?");
			ps.setInt(1, customerID);
			ps.setInt(2, CouponID);
			ps.execute();

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCouponPurchaseByCoupID(int CouponID) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from customers_vs_coupons where coupon_id = ?");
			ps.setInt(1, CouponID);
			ps.execute();

		} finally {
			pool.restoreConnection(con);
		}

	}

	@Override
	public void deleteCouponPurchase(int CustomerID) throws SQLException {
		Connection con = pool.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("delete from customers_vs_coupons where customer_id =?");

			ps.setInt(1, CustomerID);
			ps.execute();

		} finally {
			pool.restoreConnection(con);
		}

	}

}
