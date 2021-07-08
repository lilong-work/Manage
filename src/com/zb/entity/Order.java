package com.zb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
	private String orderid;
	private int userid;
	private double total;
	private Date date;
	private String username;
	private Map<Product,Integer> map = new HashMap();
	public Order() {

	}
	public Order(String orderid, int userid, double total, Date date, Map<Product,Integer> map) {

		this.orderid = orderid;
		this.userid = userid;
		this.total = total;
		this.date = date;
		this.map = map;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Map<Product, Integer> getMap() {
		return map;
	}
	public void setMap(Map<Product, Integer> map) {
		this.map = map;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", userid=" + userid + ", total=" + total + ", date=" + date
				+ ", username=" + username + ", map=" + map + "]";
	}
	

	
}
