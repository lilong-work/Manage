package com.zb.service;

import com.zb.entity.Category;
import com.zb.entity.PageBean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface CategoryService {
	public int addcate(Category ca);
	public int delcate(Category ca);
	public List<Category> findAll();
	public Category findCaById(Category ca);
	public PageBean<Category> findAllByLimit(Map<String,Object> condition, int currentPage,int size);
	public int editCate(Category ca);
}
