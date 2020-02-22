package Thread;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import beans.Coupon;
import db.CouponDAO;
import db.CouponDBDAO;

public class Job extends Thread {

	private CouponDAO couponDB = new CouponDBDAO();

	public Job() {

	}

	private boolean quit = true;

	@Override
	public void run() {
		Calendar now = Calendar.getInstance();
		Date date = new Date(now.getTimeInMillis());

		while (quit) {

			try {
				ArrayList<Coupon> coup = couponDB.getAllCoupons();

				for (Coupon cp : coup) {
					cp = couponDB.getOneCoupon(cp.getCouponID());
					if (cp.getEndDate().before(date)) {
						System.out.println("the following coupon have been expired and deleted : "+couponDB.getOneCoupon(cp.getCouponID()));
						couponDB.deleteCouponPurchaseByCoupID(cp.getCouponID());
						couponDB.deleteCoupon(cp.getCouponID());

					}

				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void stopJob() {
		quit = false;
	}
}
