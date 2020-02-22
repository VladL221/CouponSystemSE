package beans;

import java.util.ArrayList;

public class Company {

	private int CompanyID;
	private String name;
	private String email;
	private String password;
	private ArrayList<Coupon> coupons;

	public Company(int companyID, String name, String email, String password) {
		super();
		CompanyID = companyID;
		this.name = name;
		this.email = email;
		this.password = password;

	}

	public Company(int companyID, String email, String password) {
		super();
		CompanyID = companyID;
		this.email = email;
		this.password = password;
	}

	public Company(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	public int getCompanyID() {
		return CompanyID;
	}

	@Override
	public String toString() {
		return "Company [CompanyID=" + CompanyID + ", name=" + name + ", email=" + email + ", password=" + password
				+ "]";
	}

}
