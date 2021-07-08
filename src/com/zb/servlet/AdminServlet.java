package com.zb.servlet;

import com.zb.dao.AdminDao;
import com.zb.entity.Admin;
import com.zb.proxy.ServiceProxyFactory;
import com.zb.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;

@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet {
    private static AdminService service = ServiceProxyFactory.createAdminService();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String crm = req.getParameter("crm");
        if ("login".equals(crm)){
            login(req,resp);
        }else if("register".equals(crm)){
            System.out.println(1);
            register(req,resp);
        }else if("getbyUsername".equals(crm)){
            getbyUsername(req,resp);
        }
    }

    private void getbyUsername(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String username  = req.getParameter("username");
            Admin admin = new Admin();
            admin.setUsername(username);
            Admin adminBy = service.findAdminByUsername(admin);
            System.out.println(adminBy);
            if (adminBy!=null){
                resp.getWriter().print(false);
            }else
                resp.getWriter().print(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Date date = new Date();
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);
            admin.setDate(date);
            int result = service.addAdmin(admin);
            if(result>0){
                resp.getWriter().print(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            String  checked = (String) session.getAttribute("check");
            String  check =  req.getParameter("check");
            check=check.toUpperCase();
            if (checked.equals(check)){
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                Admin admin = new Admin();
                admin.setUsername(username);
                admin.setPassword(password);
                Admin adminBy = service.findAdminBy(admin);
                if(adminBy!=null){
                    session.setAttribute("admin",admin);
                    Cookie cookie = new Cookie("JSESSIONID", session.getId());
                    cookie.setMaxAge(60*60);
                    cookie.setPath(req.getContextPath());
                    resp.addCookie(cookie);
                    resp.getWriter().write("2");
                }else {
                    resp.getWriter().write("1");
                }
            }else{
                resp.getWriter().write("0");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
