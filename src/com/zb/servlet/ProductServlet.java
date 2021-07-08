package com.zb.servlet;

import com.alibaba.fastjson.JSON;
import com.zb.entity.Category;
import com.zb.entity.PageBean;
import com.zb.entity.Product;
import com.zb.entity.Provider;
import com.zb.proxy.ServiceProxyFactory;
import com.zb.service.CategoryService;
import com.zb.service.ProductService;
import com.zb.service.ProviderService;
import com.zb.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/productServlet")
public class ProductServlet extends HttpServlet {
    private static ProductService productService = ServiceProxyFactory.createProductService();
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
        }
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Product product = new Product();
            int result = 0;
            if(req.getParameter("productid")!=null&&!req.getParameter("productid").equals("")){
                product.setProductid(req.getParameter("productid"));
                Product byid = productService.findByid(product);
                String s = JSON.toJSONString(byid);
                resp.setContentType("application/json; charset=utf-8");
                resp.getWriter().print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Date date = new Date();
            String productname = req.getParameter("productname");
            double income_price = Double.parseDouble(req.getParameter("income_price"));
            String providerid = req.getParameter("providerid");
            String categoryid = req.getParameter("categoryid");
            System.out.println("categoryid"+categoryid);
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            double sales_price = Double.parseDouble(req.getParameter("sales_price"));
            String productid=new String(DateUtils.dateToString(date, "yyyyMMddHHmmssSS")+(int)(Math.random()*9000+1000));
            Category category = new Category();
            category.setCategoryid(Integer.parseInt(categoryid));
            Provider provider = new Provider();
            provider.setProviderid(Integer.parseInt(providerid));


            Product product = new Product(productid,productname,income_price,provider,quantity,sales_price,category,date);
            System.out.println(product);
            int result = productService.addPro(product);
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
            String productid = req.getParameter("productid");
            String productname = req.getParameter("productname");
            double income_price = Double.parseDouble(req.getParameter("income_price"));
            String providerid = req.getParameter("providerid");
            String categoryid = req.getParameter("categoryid");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            double sales_price = Double.parseDouble(req.getParameter("sales_price"));
            Date income_time = DateUtils.stringToDate(req.getParameter("income_time"),"yyyy-MM-dd");
            Category category = new Category();
            category.setCategoryid(Integer.parseInt(categoryid));
            Provider provider = new Provider();
            provider.setProviderid(Integer.parseInt(providerid));
            Product product = new Product(productid,productname,income_price,provider,quantity,sales_price,category,income_time);
            System.out.println(product);
            int result = productService.edit(product);
            if (result>0){
                resp.getWriter().print(true);
            }else
                resp.getWriter().print(false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void del(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Product product = new Product();
            int result = 0;
            if(req.getParameter("productid")!=null&&!req.getParameter("productid").equals("")){
                product.setProductid(req.getParameter("productid"));

                result = productService.delPro(product);
            }
            if(result>0){
                resp.getWriter().print(true);
            }else
                resp.getWriter().print(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Map<String,Object> map = new HashMap();
            int currentPage=Integer.parseInt(req.getParameter("currentPage"));
            int size = Integer.parseInt(req.getParameter("size"));

            String productid = req.getParameter("productid");
            if(productid!=null&&!"".equals(productid)){
                System.out.println(productid);
                map.put("productid",productid);
            }

            String productname = req.getParameter("productname");
            if(productname!=null&&!"".equals(productname)){
                map.put("productname",productname);
            }
            PageBean<Product> pageBean = productService.findAllByLimit(map, currentPage, size);

            String json = JSON.toJSONString(pageBean);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
