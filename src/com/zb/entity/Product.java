package com.zb.entity;

import java.util.Date;

public class Product {
	private String productid;
	private String productname;
	private double income_price;
	private Provider provider;
	private int quantity;
	private double sales_price;
	private Category category;
	private Date income_time;
	
	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productname=" + productname + ", income_price=" + income_price
				+ ", provider=" + provider + ", quantity=" + quantity + ", sales_price=" + sales_price + ", category="
				+ category + ", income_time=" + income_time + "]";
	}
	public Product(String productid, String productname, double income_price, Provider provider, int quantity,
			double sales_price, Category category, Date income_time) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.income_price = income_price;
		this.provider = provider;
		this.quantity = quantity;
		this.sales_price = sales_price;
		this.category = category;
		this.income_time = income_time;
	}
	public Product() {
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public double getIncome_price() {
		return income_price;
	}
	public void setIncome_price(double income_price) {
		this.income_price = income_price;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSales_price() {
		return sales_price;
	}
	public void setSales_price(double sales_price) {
		this.sales_price = sales_price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Date getIncome_time() {
		return income_time;
	}
	public void setIncome_time(Date income_time) {
		this.income_time = income_time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productid == null) ? 0 : productid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productid == null) {
			if (other.productid != null)
				return false;
		} else if (!productid.equals(other.productid))
			return false;
		return true;
	}
	
	
}
