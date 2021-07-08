package com.zb.entity;

public class Category {
	private int categoryid;
	private String category_name;
	private String category_desc;
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_desc() {
		return category_desc;
	}
	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}
	public Category() {
	}
	public Category(int categoryid, String category_name, String category_desc) {
		this.categoryid = categoryid;
		this.category_name = category_name;
		this.category_desc = category_desc;
	}
	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", category_name=" + category_name + ", category_desc="
				+ category_desc + "]";
	}
	
}
