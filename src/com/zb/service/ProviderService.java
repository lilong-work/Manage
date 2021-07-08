package com.zb.service;

import com.zb.entity.PageBean;
import com.zb.entity.Provider;

import java.util.List;
import java.util.Map;


public interface ProviderService {
	public List<Provider> findAll();
	public Provider findProById(Provider pro);
	public int addProvider(Provider pro);
	public int delcateProvider(Provider pro);
	public PageBean<Provider> findAllByLimit(Map<String,Object> condition, int currentPage, int size);

	public int editProvider(Provider pro);
}
