package com.zb.dao;

import com.zb.entity.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface CategoryDao {
	public int addCate(Category ca);
	public int delCate(Category ca);
	public int editCate(Category newCa);
	public List<Category> findAll();
	public Category findCaByName(Category ca);
	public Category findCaById(Category ca);
	public List<Category> findAllByLimit(Map<String, Object> condition, int currentPage, int size);
	public int getCount(Map<String, Object> condition);
}
