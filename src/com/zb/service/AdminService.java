package com.zb.service;

import com.zb.entity.Admin;

import java.util.List;

public interface AdminService {
    public int addAdmin(Admin admin);
    public Admin findAdminBy(Admin admin);
    public Admin findAdminByUsername( Admin admin);
    public int login(Admin admin);
    public List<Admin> findAll();
    public List<Admin> findAllByLimit(int page);
    public int getCount();
    public int delAdm(Admin admin);
}
