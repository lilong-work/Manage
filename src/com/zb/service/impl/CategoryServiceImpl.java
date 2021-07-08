package com.zb.service.impl;

import com.zb.dao.CategoryDao;
import com.zb.dao.ProductDao;
import com.zb.dao.impl.CategoryDaoImpl;
import com.zb.dao.impl.ProductDaoImpl;
import com.zb.entity.Category;
import com.zb.entity.PageBean;
import com.zb.service.CategoryService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CategoryServiceImpl implements CategoryService {
	private static CategoryDao cateDao = new CategoryDaoImpl();
	private static ProductDao proDao = new ProductDaoImpl();

	@Override
	public int addcate(Category ca) {
		// TODO Auto-generated method stub
		int result = 0;
		//���û�ҵ��������
		if (cateDao.findCaByName(ca) == null) {
			result = cateDao.addCate(ca);
			return result;
		}

		return -2;
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub

		List<Category> list = new ArrayList<Category>();

		list = cateDao.findAll();

		return list;
	}

	@Override
	public Category findCaById(Category ca) {
		// TODO Auto-generated method stub
		Category cate = new Category();
        cate = cateDao.findCaById(ca);
        return cate;
	}

	@Override
	public PageBean<Category> findAllByLimit(Map<String, Object> condition, int currentPage,int size) {
		PageBean<Category> pageBean = new PageBean<>();
		pageBean.setCurrentPage(currentPage);

		List list = new ArrayList();
		list = cateDao.findAllByLimit(condition,currentPage,size);
		pageBean.setRows(list);

		int total  = cateDao.getCount(condition);
		pageBean.setTotal(total);
		pageBean.setRow(size);
		int totalPage = total%size==0?(total/size):(total/size+1);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public int delcate(Category ca) {
		// TODO Auto-generated method stub

		int result = 0;
		if (proDao.findProByCateId(ca.getCategoryid()).size()==0) {
			result = cateDao.delCate(ca);
			return result;
		} else
			return -1;


	}




	@Override
	public int editCate(Category ca) {
		// TODO Auto-generated method stub

		int result = 0;
        if (cateDao.findCaByName(ca) != null) {
            if (cateDao.findCaByName(ca).getCategoryid() != ca.getCategoryid()) {
                return -1;
            } else {
                result = cateDao.editCate(ca);

                return result;
            }
        } else {
            result = cateDao.editCate(ca);

            return result;
        }

	}

}
