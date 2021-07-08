package com.zb.service.impl;


import com.zb.dao.CategoryDao;
import com.zb.dao.ProductDao;
import com.zb.dao.ProviderDao;
import com.zb.dao.impl.CategoryDaoImpl;
import com.zb.dao.impl.ProductDaoImpl;
import com.zb.dao.impl.ProviderDaoImpl;
import com.zb.entity.Category;
import com.zb.entity.PageBean;
import com.zb.entity.Product;
import com.zb.service.ProductService;
import com.zb.utils.BaseDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProductServiceImpl extends BaseDao implements ProductService {
	private static ProductDao productDao = new ProductDaoImpl();
	private static CategoryDao cateDao = new CategoryDaoImpl();
	private static ProviderDao providerDao = new ProviderDaoImpl();

	@Override
	public int addPro(Product pro) {
		// TODO Auto-generated method stub
		// 0 ���� -1 ��Ӧ�̲����� -2 ���Ͳ�����
		//�жϴ治����
		if (productDao.findProByName(pro) == null) {
			//�жϹ�Ӧ�̴治����
			if (providerDao.findProviderById(pro.getProvider()) != null) {
				//�ж����ʹ治����
				if (cateDao.findCaById(pro.getCategory()) != null) {
					productDao.addPro(pro);

					return 1;
				} else
					return -2;
			} else
				return -1;
		}

		return 0;
	}

	@Override
	public int delPro(Product pro) {
		// TODO Auto-generated method stub
		int result = 0;
		result = productDao.delPro(pro);
		return result;
	}

	@Override
	public Product findByid(Product pro) {
		Product product  = null;
		product=productDao.findProById(pro);
		return product;
	}

	@Override
	public PageBean<Product> findAllByLimit(Map<String, Object> condition, int currentPage, int size) {
		PageBean<Product> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);

		List list = new ArrayList();
		list = productDao.findAllByLimit(condition,currentPage,size);
		pageBean.setRows(list);

		int total  = productDao.getCount(condition);
		pageBean.setTotal(total);
		pageBean.setRow(size);
		int totalPage = total%size==0?(total/size):(total/size+1);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int count = 0;
		int countMax = 0;

		count = productDao.getCount();
		if (count % 3 == 0) {
			countMax = count / 3;
		} else
			countMax = count / 3 + 1;

		return countMax;
	}

	@Override
	public int edit(Product pro) {
		// TODO Auto-generated method stub

		int resulut = 0;
		if(productDao.findProByName(pro)!=null){
			if(productDao.findProByName(pro).getProductid().equals(pro.getProductid())){
				resulut=productDao.editPro(pro);
			}else{
				resulut=-2;
			}
		}else {
			    resulut=productDao.editPro(pro);
		}

		return resulut;
	}

}
