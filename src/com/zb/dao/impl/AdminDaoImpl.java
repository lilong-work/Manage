package com.zb.dao.impl;

import com.zb.dao.AdminDao;
import com.zb.entity.Admin;
import com.zb.utils.BaseDao;
import com.zb.utils.DateUtils;
import com.zb.utils.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl extends BaseDao implements AdminDao {

    @Override
    public int addAdmin(Admin admin)  {
        // TODO Auto-generated method stub
        int result=0;
        String sql="insert into admin values(null,?,?,?)";
        result=exeUpdate( sql,admin.getUsername(),admin.getPassword(),
                DateUtils.dateToString(admin.getDate(),"yyyy-MM-dd"));
        return result;
    }



    @Override
    public Admin findAdminBy( Admin admin)  {
        // TODO Auto-generated method stub
        Admin ad = null;
        ResultSet rs =null;
        String sql="select * from admin where username=? and password=?";
        ad=exeQueryone( sql, new AdminRowMapper(), admin.getUsername(),admin.getPassword());

        return ad;
    }
    @Override
    public Admin findAdminByUsername( Admin admin)  {
        // TODO Auto-generated method stub
        Admin ad = null;
        ResultSet rs =null;
        String sql="select * from admin where username=? ";
        ad=exeQueryone( sql, new AdminRowMapper(), admin.getUsername());

        return ad;
    }


    @Override
    public List<Admin> findAll()  {
        // TODO Auto-generated method stub

        List<Admin> list =new ArrayList<>();
        ResultSet rs =null;
        String sql = "select * from admin";
        list=exeQuery( sql, new AdminRowMapper());
        return list;
    }



    @Override
    public List<Admin> findAllByLimit( int page)  {
        // TODO Auto-generated method stub
        List<Admin> list =new ArrayList<>();
        ResultSet rs =null;
        String sql = "select * from admin order by id limit ?,3";
        list=exeQuery( sql, new AdminRowMapper(),(page-1)*3);
        return list;
    }



    @Override
    public int getCount()  {
        // TODO Auto-generated method stub
        int result=0;
        ResultSet rs =null;
        String sql = "select count(id) count from admin ";
        result=exeQueryone( sql, new RowMapper<Integer>() {
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

        });

        return result;
    }



    @Override
    public int delAdm( Admin admin)  {
        // TODO Auto-generated method stub
        int result=0;
        String sql="delete from admin where username=?";
        result=exeUpdate( sql, admin.getUsername());
        return result;
    }

}
class AdminRowMapper implements RowMapper<Admin> {


    @Override
    public Admin makeRow(ResultSet rs) {
        // TODO Auto-generated method stub
        Admin admin = new Admin();
        try {
            admin.setId(rs.getInt("id"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new RuntimeException();
        }

        return admin;
    }

}
