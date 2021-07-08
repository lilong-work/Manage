package com.zb.service.impl;


import com.zb.dao.ProductDao;
import com.zb.dao.ProviderDao;
import com.zb.dao.impl.ProductDaoImpl;
import com.zb.dao.impl.ProviderDaoImpl;
import com.zb.entity.PageBean;
import com.zb.entity.Provider;
import com.zb.service.ProviderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProviderServiceImpl implements ProviderService {
	private static ProviderDao providerDao = new ProviderDaoImpl();
	private static ProductDao proDao = new ProductDaoImpl();

	@Override
	public List<Provider> findAll() {
		// TODO Auto-generated method stub

		List<Provider> list = new ArrayList<Provider>();

		list = providerDao.findAll();

		return list;
	}

	@Override
	public Provider findProById(Provider pro) {
		Provider provider=null;
		provider=providerDao.findProviderById(pro);
		return provider;
	}

	@Override
	public int addProvider(Provider pro) {
		// TODO Auto-generated method stub

		int result = 0;

		if (providerDao.findProviderByName(pro) == null) {
			result = providerDao.addProvider(pro);

			return result;
		}

		return 0;
	}

	@Override
	public int delcateProvider(Provider pro) {
		// TODO Auto-generated method stub
		int result = 0;


			if (proDao.findProByProviderId(pro.getProviderid()).size()==0) {
				result = providerDao.delProvider(pro);
				return result;
			} else
				return -1;

	}

	@Override
	public PageBean<Provider> findAllByLimit(Map<String, Object> condition, int currentPage, int size) {
		PageBean<Provider> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);

		List list = new ArrayList();
		list = providerDao.findAllByLimit(condition,currentPage,size);
		pageBean.setRows(list);

		int total  = providerDao.getCount(condition);
		pageBean.setTotal(total);
		pageBean.setRow(size);
		int totalPage = total%size==0?(total/size):(total/size+1);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}



	@Override
	public int editProvider(Provider pro) {
		// TODO Auto-generated method stub

		int result=0;

			//����������һ�£���ͨ��id�ҿ��ܲ����ҵ������ж���û�иù�Ӧ���ṩ�Ĳ�Ʒ������ж��޸ĺ�������ܷ��ҵ�
			if(providerDao.findProviderById( pro)!=null) {
				if(providerDao.findProviderByName( pro)!=null) {
					if(providerDao.findProviderByName(pro).getProviderid()!=pro.getProviderid()) {
						return -1;
					}else {
						result=providerDao.editProvider( pro);
						return result;
					}
				}else {
					result=providerDao.editProvider( pro);

						return result;
				}
			}

		return 0;
	}

}
