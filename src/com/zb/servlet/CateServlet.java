package com.zb.servlet;

import com.alibaba.fastjson.JSON;
import com.zb.entity.Category;
import com.zb.entity.PageBean;
import com.zb.proxy.ServiceProxyFactory;
import com.zb.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet( "/cateServlet")
public class CateServlet extends HttpServlet {
    private static CategoryService categoryService = ServiceProxyFactory.createCateService();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cmd = req.getParameter("cmd");
        if("list".equals(cmd)){
            list(req,resp);
        }else if("del".equals(cmd)){
            del(req,resp);
        }else if("edit".equals(cmd)){
            String modify = req.getParameter("modify");
            if("0".equals(modify)){
                add(req,resp);
            }else if ("1".equals(modify)){
                edit(req,resp);
            }
        }else if("getById".equals(cmd)){
            getById(req,resp);
        }else if ("getAllCate".equals(cmd)){
            getAllCate(req,resp);
        }

    }

    private void getAllCate(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Category> list = categoryService.findAll();
            String s = JSON.toJSONString(list);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().print(s);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Category category = new Category();
            int result = 0;
            if(req.getParameter("categoryid")!=null&&!req.getParameter("categoryid").equals("")){
                category.setCategoryid(Integer.parseInt(req.getParameter("categoryid")));
                Category findCate = categoryService.findCaById(category);
                String s = JSON.toJSONString(findCate);
                resp.setContentType("application/json; charset=utf-8");
                resp.getWriter().print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String category_name = req.getParameter("category_name");
            String category_desc = req.getParameter("category_desc");
            Category category = new Category();
            category.setCategory_name(category_name);
            category.setCategory_desc(category_desc);
            int result = categoryService.addcate(category);
            if (result>0){
                resp.getWriter().print(true);
            }else
                resp.getWriter().print(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int categoryid = Integer.parseInt(req.getParameter("categoryid"));
            String category_name = req.getParameter("category_name");
            String category_desc = req.getParameter("category_desc");
            Category category = new Category();
            category.setCategoryid(categoryid);
            category.setCategory_name(category_name);
            category.setCategory_desc(category_desc);
            int result = categoryService.editCate(category);
            if (result>0){
                resp.getWriter().print(true);
            }else
                resp.getWriter().print(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void del(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Category category = new Category();
            int result = 0;
            if(req.getParameter("categoryid")!=null&&!req.getParameter("categoryid").equals("")){
                category.setCategoryid(Integer.parseInt(req.getParameter("categoryid")));
                result = categoryService.delcate(category);
            }
            resp.getWriter().print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Map<String,Object>map = new HashMap();
            int currentPage=Integer.parseInt(req.getParameter("currentPage"));
            int size = Integer.parseInt(req.getParameter("size"));

            String cateidStr = req.getParameter("categoryid");
            if(cateidStr!=null&&!"".equals(cateidStr)){
                int cateid = Integer.parseInt(cateidStr);

                map.put("cateid",cateid);
            }

            String catenameStr = req.getParameter("category_name");
            if(catenameStr!=null&&!"".equals(catenameStr)){
                map.put("catenameStr",catenameStr);
            }
            PageBean<Category> pageBean = categoryService.findAllByLimit(map, currentPage, size);

            String json = JSON.toJSONString(pageBean);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
