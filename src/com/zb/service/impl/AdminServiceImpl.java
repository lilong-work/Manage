package com.zb.service.impl;

import com.zb.dao.AdminDao;
import com.zb.dao.impl.AdminDaoImpl;
import com.zb.entity.Admin;
import com.zb.service.AdminService;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private static AdminDao adminDao = new AdminDaoImpl();

    @Override
    public int addAdmin(Admin admin) {
        // TODO Auto-generated method stub
        int result = 0;

        if (adminDao.findAdminBy(admin) == null) {
            result = adminDao.addAdmin(admin);
            return result;
        }

        return 0;
    }

    @Override
    public Admin findAdminBy(Admin admin) {
        // TODO Auto-generated method stub
        Admin ad = null;

        ad = adminDao.findAdminBy(admin);

        return ad;
    }
    @Override
    public Admin findAdminByUsername(Admin admin) {
        // TODO Auto-generated method stub
        Admin ad = null;

        ad = adminDao.findAdminByUsername(admin);

        return ad;
    }

    @Override
    public int login(Admin admin) {
        // TODO Auto-generated method stub

        Admin ad = adminDao.findAdminBy(admin);
        if (ad == null) {
            return -2;
        }
        if (!admin.getPassword().equals(ad.getPassword())) {
            return -1;
        }

        return 1;

    }

    @Override
    public List<Admin> findAll() {
        // TODO Auto-generated method stub

        List<Admin> list = new ArrayList<Admin>();

        list = adminDao.findAll();

        return list;
    }

    @Override
    public List<Admin> findAllByLimit(int page) {
        // TODO Auto-generated method stub

        List<Admin> list = new ArrayList<Admin>();

        list = adminDao.findAllByLimit(page);

        return list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        int count = 0;

        count = adminDao.getCount();

        return count;
    }

    @Override
    public int delAdm(Admin admin) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub

        int result = 0;

        if (adminDao.findAdminBy(admin) != null) {
            result = adminDao.delAdm(admin);

            return result;
        }

        return 0;
    }
}
