package com.zb.service;

import com.zb.entity.Category;
import com.zb.entity.PageBean;
import com.zb.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface ProductService {
	public int addPro(Product pro);
	public int delPro(Product pro);

	public Product findByid(Product pro);
	public PageBean<Product> findAllByLimit(Map<String,Object> condition, int currentPage, int size);
	public int getCount();
	public int edit(Product pro);
}
