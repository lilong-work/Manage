package com.zb.dao.impl;

import com.zb.dao.ProductDao;
import com.zb.entity.Category;
import com.zb.entity.Product;
import com.zb.entity.Provider;
import com.zb.utils.BaseDao;
import com.zb.utils.DateUtils;
import com.zb.utils.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ProductDaoImpl extends BaseDao implements ProductDao {

	@Override
	public int addPro(Product pro) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "insert into products values(?,?,?,?,?,?,?,?)";
		result = exeUpdate(sql, pro.getProductid(), pro.getProductname(), pro.getIncome_price(),
				pro.getProvider().getProviderid(), pro.getQuantity(), pro.getSales_price(),
				pro.getCategory().getCategoryid(), DateUtils.dateToString(pro.getIncome_time(), "yyyy-MM-dd"));
		return result;
	}

	@Override
	public Product findProByName(Product pro) {
		Product product = null;
		String sql = "select * from products where productname=?";
		product = exeQueryone(sql, new ProductRowMapper(), pro.getProductname());
		return product;
	}

	@Override
	public Product findProById(Product pro) {
		// TODO Auto-generated method stub
		Product product = null;
		ResultSet rs = null;
		String sql = "select * from products where productid=?";
		product = exeQueryone(sql, new ProductRowMapper(), pro.getProductid());
		return product;
	}

	@Override
	public List<Product> findProByCateId(int cateId) {
		// TODO Auto-generated method stub
		Product product = null;
		List<Product> list = null;
		String sql = "select * from products where categoryid=?";
		list = exeQuery(sql, new ProductRowMapper(), cateId);

		return list;
	}

	@Override
	public List<Product> findProByProviderId(int providerId) {
		// TODO Auto-generated method stub
		Product product = null;
		List<Product> list = null;
		String sql = "select * from products where providerid=?";
		list = exeQuery(sql, new ProductRowMapper(), providerId);
		return list;
	}

	@Override
	public int delPro(Product pro) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "delete from products where productid =? ";
		result = exeUpdate(sql, pro.getProductid());
		return result;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "select count(productid) count from products ";
		//��ѯ�����һ�����������ڲ�������дmakeRow����
		result = exeQueryone(sql, new RowMapper<Integer>() {
			@Override
			public Integer makeRow(ResultSet rs) {
				// TODO Auto-generated method stub
				int result = -1;
				try {
					result = rs.getInt("count");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException();
				}
				return result;
			}

		});
		return result;
	}





	@Override
	public int editPro(Product pro) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "update products set productname=?,income_price=? ,providerid=?,quantity=?,sales_price=?,categoryid=?,income_time=? where productid=? ";
		result = exeUpdate(sql, pro.getProductname(), pro.getIncome_price(), pro.getProvider().getProviderid(),
				pro.getQuantity(), pro.getSales_price(), pro.getCategory().getCategoryid(),
				DateUtils.dateToString(pro.getIncome_time(), "yyyy-MM-dd"), pro.getProductid());
		return result;
	}

	@Override
	public int editProQu(Product pro) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "update products set quantity=? where productid=? ";
		result = exeUpdate(sql, pro.getQuantity(),pro.getProductid());
		return result;
	}
	@Override
	public List<Product> findAllByLimit(Map<String, Object> condition, int currentPage, int size) {
		// TODO Auto-generated method stub
		List<Product> list =new ArrayList<>();

		String sql = "select products.* ,category_name,provider_name " +
				" from products left join categorys on products.categoryid=categorys.categoryid left join " +
				" providers on products.providerid=providers.providerid  where 1=1 ";
		Set<String> keys=condition.keySet();
		List <Object> values = new ArrayList<>();
		for (String key : keys) {
			if("productid".equals(key)){
				sql+="  and productid like ? ";
				String value  = "%"+(String) condition.get(key)+"%";
				values.add(value);
			}
			if("productname".equals(key)){
				sql+=" and productname like ? ";
				String value  = "%"+(String) condition.get(key)+"%";
				values.add(value);
			}
		}
		sql+=" order by productid limit ?,?";
		values.add((currentPage-1)*size);
		values.add(size);
		list = exeQuery(sql, new RowMapper<Product>() {

			@Override
			public Product makeRow(ResultSet rs) {
				// TODO Auto-generated method stub
				Product product = new Product();
				Category cate = new Category();
				Provider provider = new Provider();
				try {
					product.setProductname(rs.getString("productname"));
					product.setProductid(rs.getString("productid"));
					cate.setCategory_name(rs.getString("category_name"));
					cate.setCategoryid(rs.getInt("categoryid"));
					product.setCategory(cate);
					provider.setProviderid(rs.getInt("providerid"));
					provider.setProvider_name(rs.getString("provider_name"));
					product.setProvider(provider);
					product.setIncome_price(rs.getDouble("income_price"));
					product.setIncome_time(rs.getDate("income_time"));
					product.setQuantity(rs.getInt("quantity"));
					product.setSales_price(rs.getDouble("sales_price"));
					System.out.println(product);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException();
				}
				return product;
			}
		},values.toArray());
		return list;
	}
	@Override
	public int getCount(Map<String, Object> condition)  {
		// TODO Auto-generated method stub
		int result=0;
		String sql = "select count(productid) count from products where 1=1 ";
		Set<String> keys=condition.keySet();
		List <Object> values = new ArrayList<>();
		for (String key : keys) {
			if("productid".equals(key)){
				sql+="  and productid like ? ";
				String value  = "%"+(String) condition.get(key)+"%";
				values.add(value);
			}
			if("productname".equals(key)){
				sql+=" and productname like ? ";
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
}

class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product makeRow(ResultSet rs) {
		// TODO Auto-generated method stub

		Product product = new Product();
		Category cate = new Category();
		Provider provider = new Provider();
		try {
			product.setProductname(rs.getString("productname"));
			product.setProductid(rs.getString("productid"));
			cate.setCategoryid(rs.getInt("categoryid"));
			product.setCategory(cate);
			provider.setProviderid(rs.getInt("providerid"));
			product.setProvider(provider);
			product.setIncome_price(rs.getDouble("income_price"));
			product.setIncome_time(rs.getDate("income_time"));
			product.setQuantity(rs.getInt("quantity"));
			product.setSales_price(rs.getDouble("sales_price"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}

		return product;
	}

}
