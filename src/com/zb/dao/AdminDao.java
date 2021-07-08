package com.zb.dao;

import com.zb.entity.Admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



public interface AdminDao {
	public int addAdmin(Admin admin);
	public Admin findAdminBy(Admin admin);
	public Admin findAdminByUsername( Admin admin);
	public List<Admin> findAll();
	public List<Admin> findAllByLimit(int page);
	public int getCount();
	public int delAdm(Admin admin);
} 
