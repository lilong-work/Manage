package com.zb.dao;

import com.zb.entity.Order;
import com.zb.entity.Product;

import java.util.List;
import java.util.Map;



public interface OrderDao {
	public int addOrder(Order order);
	public Order findOrderById(Order order);
	public int addProduct(String orderid, Product product, int num);
	public Map<Product, Integer> findProductById(Order order);
}
