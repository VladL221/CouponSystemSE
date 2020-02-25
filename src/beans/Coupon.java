package beans;

import java.sql.Date;

public class Coupon {

	private int couponID;
	private int companyID;
	private String title;
	private String description;
	private Date startDate, endDate;
	private int amount;
	private double price;
	private String image;
	private Category category;

	//for the couponDBDAO select methods
	public Coupon(int couponID, int companyID, String title, String description, Date startDate, Date endDate,
			int amount, double price, String image, Category category) {
		super();
		this.couponID = couponID;
		this.companyID = companyID;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.category = category;
	}
	// for the company add method methods
	public Coupon(Category category, String title, String description, Date startDate, Date endDate, int amount,
			double price, String image) {
		super();
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.category = category;
	}
	//for the company update method
	public Coupon(int couponID, String title, String description, Date startDate, Date endDate, int amount,
			double price, String image, Category category) {
		super();
		this.couponID = couponID;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.category = category;
	}

	public Coupon() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getCouponID() {
		return couponID;
	}

	public int getCompanyID() {
		return companyID;
	}

	@Override
	public String toString() {
		return "Coupon [couponID=" + couponID + ", companyID=" + companyID + ", title=" + title + ", description="
				+ description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount + ", price="
				+ price + ", image=" + image + ", category=" + category + "]";
	}

}
