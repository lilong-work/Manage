package com.zb.dao.impl;

import com.zb.dao.ProviderDao;
import com.zb.entity.Provider;
import com.zb.utils.BaseDao;
import com.zb.utils.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ProviderDaoImpl extends BaseDao implements ProviderDao {

	@Override
	public Provider findProviderById( Provider provider)  {
		// TODO Auto-generated method stub
		Provider pro = null;
		System.out.println(provider);
		String sql="select * from providers where providerid=?";
		pro=exeQueryone( sql,new ProviderRowMapper(), provider.getProviderid());

		return pro;
	}

	@Override
	public List<Provider> findAll()  {
		// TODO Auto-generated method stub
		List<Provider> list=new ArrayList<Provider>();
		String sql="select * from providers ";
		list=exeQuery( sql,new ProviderRowMapper());

		return list;
	}

	@Override
	public int addProvider( Provider pro)  {
		// TODO Auto-generated method stub
		
		int result=0;
		String sql="insert into providers values(null,?,?,?,?,?)";
		result=exeUpdate( sql,pro.getProvider_add(),pro.getProvider_add(),pro.getProvider_tel(),pro.getAccount(),pro.getEmail() );	
		return result;
	}

	@Override
	public int delProvider( Provider pro)  {
		// TODO Auto-generated method stub
		int result=0;
		String sql="delete from providers where providerid=? ";
		result=exeUpdate( sql,pro.getProviderid() );
		return result;
	}

	@Override
	public Provider findProviderByName( Provider provider)  {
		// TODO Auto-generated method stub
		Provider pro = null;
		ResultSet rs =null;
		String sql="select * from providers where provider_name=?";
		pro=exeQueryone( sql,new ProviderRowMapper(), provider.getProvider_name());
		return pro;
	}

	@Override
	public int editProvider( Provider pro)  {
		// TODO Auto-generated method stub
		int result=0;
		String sql = "update providers set provider_name=?,provider_add=?,provider_tel=?,account=?,email=? where providerid=? ";
		result=exeUpdate( sql,pro.getProvider_name(),pro.getProvider_add(),pro.getProvider_tel(),pro.getAccount(),pro.getEmail(),
				pro.getProviderid());
		return result;
	}
	@Override
	public int getCount(Map<String, Object> condition)  {
		// TODO Auto-generated method stub
		int result=0;
		String sql = "select count(providerid) count from providers  where 1=1 ";
		Set<String> keys=condition.keySet();
		List <Object> values = new ArrayList<>();
		for (String key : keys) {
			if("providerid".equals(key)){
				sql+="  and providerid = ? ";
				int value  = (int) condition.get(key);
				values.add(value);
			}
			if("providernameStr".equals(key)){
				sql+=" and provider_name like ? ";
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
	public List<Provider> findAllByLimit( Map<String, Object> condition, int currentPage, int size)  {
		// TODO Auto-generated method stub
		List<Provider> list = new ArrayList<>();
		String sql = "select * from providers where 1=1";
		Set<String> keys=condition.keySet();
		List <Object> values = new ArrayList<>();
		for (String key : keys) {
			if("providerid".equals(key)){
				sql+="  and providerid = ? ";
				int value  = (int) condition.get(key);
				values.add(value);
			}
			if("providernameStr".equals(key)){
				sql+=" and provider_name like ? ";
				String value  = "%"+(String) condition.get(key)+"%";
				values.add(value);
			}
		}
		sql+=" order by providerid limit ?,?";
		values.add((currentPage-1)*size);
		values.add(size);
		list=exeQuery( sql,new ProviderRowMapper(),values.toArray());

		return list;
	}

}
class ProviderRowMapper implements RowMapper<Provider>{
	@Override
	public Provider makeRow(ResultSet rs) {
		// TODO Auto-generated method stub
		 Provider pro=new Provider();
		 try {
			 pro.setProviderid(rs.getInt("providerid"));
			 pro.setAccount(rs.getString("account"));
			 pro.setEmail(rs.getString("email"));
			 pro.setProvider_name(rs.getString("provider_name"));
			 pro.setProvider_tel(rs.getString("provider_tel"));
			 pro.setProvider_add(rs.getString("provider_add"));	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		return pro;
	}
	
}