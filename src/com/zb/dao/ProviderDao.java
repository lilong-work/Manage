package com.zb.dao;

import com.zb.entity.Provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface ProviderDao {
	public Provider findProviderById(Provider provider);
	public Provider findProviderByName(Provider provider);
	public List<Provider> findAll();
	public int addProvider(Provider pro);
	public int delProvider(Provider pro);
	
	public int editProvider(Provider pro);
	public List<Provider> findAllByLimit(Map<String, Object> condition, int currentPage, int size);
	public int getCount(Map<String, Object> condition);
}
