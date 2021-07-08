package com.zb.entity;

import java.util.ArrayList;
import java.util.List;

public class Order_Product {
	private int id ;
	private String orderid;
	private int num;
	private List<Product> products=new ArrayList<>();
	public Order_Product(int id, String orderid, int num, List<Product> products) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.num = num;
		this.products = products;
	}
	public Order_Product() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Order_Product [id=" + id + ", orderid=" + orderid + ", num=" + num + ", products=" + products + "]";
	}
	
}
