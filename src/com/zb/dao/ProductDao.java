package com.zb.dao;

import com.zb.entity.Category;
import com.zb.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface ProductDao {
	public int addPro(Product pro);
	public Product findProByName(Product pro);
	public Product findProById(Product pro);
	public List<Product> findProByCateId(int cateId);
	public List<Product> findProByProviderId(int providerId);
	public int delPro(Product pro);
	
	public int editPro(Product pro);
	public int editProQu(Product pro);
	public int getCount();

	public List<Product> findAllByLimit(Map<String, Object> condition, int currentPage, int size);
	public int getCount(Map<String, Object> condition);
}

