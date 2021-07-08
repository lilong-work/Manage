package com.zb.dao.impl;

import com.zb.dao.CategoryDao;
import com.zb.entity.Category;
import com.zb.utils.BaseDao;
import com.zb.utils.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CategoryDaoImpl extends BaseDao implements CategoryDao {

	@Override
	public int addCate( Category ca)  {
		// TODO Auto-generated method stub
		
		int result=0;
		String sql="insert into categorys values(null,?,?)";
		result=exeUpdate( sql,ca.getCategory_name(),ca.getCategory_desc() );	
		return result;
	}


	@Override
	public List<Category> findAll()  {
		// TODO Auto-generated method stub
		List<Category> list =new ArrayList<>();

		String sql = "select * from categorys";
		list=exeQuery( sql,new CateRowMapper());	
		return list;
	}

	@Override
	public Category findCaByName( Category ca)  {
		// TODO Auto-generated method stub
		Category cate = null;

		String sql="select * from categorys where category_name=?";
		cate=exeQueryone( sql,new CateRowMapper(), ca.getCategory_name());

		return cate;
	}


	@Override
	public int delCate( Category ca)  {
		// TODO Auto-generated method stub
		int result=0;
		String sql="delete from categorys where categoryid=? ";
		result=exeUpdate( sql,ca.getCategoryid() );
		return result;
	}





	@Override
	public int getCount(Map<String, Object> condition)  {
		// TODO Auto-generated method stub
		int result=0;
		String sql = "select count(categoryid) count from categorys where 1=1 ";
		Set<String> keys=condition.keySet();
		List <Object> values = new ArrayList<>();
		for (String key : keys) {
			if("cateid".equals(key)){
				sql+="  and categoryid = ? ";
				int value  = (int) condition.get(key);
				values.add(value);
			}
			if("catenameStr".equals(key)){
				sql+=" and category_name like ? ";
				String value  = "%"+(String) condition.get(key)+"%";
				values.add(value);
			}
		}

		result=exeQueryone( sql, new RowMapper<Integer>(){
			@Override
			public Integer makeRow(ResultSet rs) {
				// TODO Auto-generated method stub
				int result = -1;
				try {
					result=rs.getInt("count");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException();
				}
				return result;
			}

		},values.toArray());
		return result;
	}


	@Override
	public int editCate( Category newCa)  {
		// TODO Auto-generated method stub
		int result=0;
		String sql = "update categorys set category_name=?,category_desc=? where categoryid=? ";
		result=exeUpdate( sql, newCa.getCategory_name(),newCa.getCategory_desc(),newCa.getCategoryid());
		return result;
	}


	@Override
	public Category findCaById( Category ca)  {
		// TODO Auto-generated method stub
		Category cate = null;

		String sql="select * from categorys where categoryid=?";
		cate=exeQueryone( sql,new CateRowMapper(), ca.getCategoryid());

		return cate;
	}

	@Override
	public List<Category> findAllByLimit(Map<String, Object> condition, int currentPage, int size) {
		// TODO Auto-generated method stub
		List<Category> list =new ArrayList<>();

//		String sql = "select * from categorys order by categoryid limit ?,3";
		String sql = "select * from categorys where 1=1";
		Set<String> keys=condition.keySet();
		List <Object> values = new ArrayList<>();
		for (String key : keys) {
			if("cateid".equals(key)){
				sql+="  and categoryid = ? ";
				int value  = (int) condition.get(key);
				values.add(value);
			}
			if("catenameStr".equals(key)){
				sql+=" and category_name like ? ";
				String value  = "%"+(String) condition.get(key)+"%";
				values.add(value);
			}
		}
		sql+=" order by categoryid limit ?,?";
		values.add((currentPage-1)*size);
		values.add(size);
		list=exeQuery( sql,new CateRowMapper(),values.toArray());

		return list;
	}


}
class CateRowMapper implements RowMapper<Category>{

	@Override
	public Category makeRow(ResultSet rs) {
		// TODO Auto-generated method stub
		  Category cate=new Category();
		 try {
			cate.setCategoryid(rs.getInt("categoryid"));
			 cate.setCategory_name(rs.getString("category_name"));
			 cate.setCategory_desc(rs.getString("category_desc"));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		 return cate;
	}
	
}