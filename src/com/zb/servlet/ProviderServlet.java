package com.zb.servlet;

import com.alibaba.fastjson.JSON;
import com.zb.entity.PageBean;
import com.zb.entity.Provider;
import com.zb.proxy.ServiceProxyFactory;
import com.zb.service.ProductService;
import com.zb.service.ProviderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet( "/providerServlet")
public class ProviderServlet extends HttpServlet {
    private static ProviderService providerService = ServiceProxyFactory.createProviderService();
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
        }else if ("getAllProvider".equals(cmd)){
            getAllPro(req,resp);
        }
    }

    private void getAllPro(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Provider> list = providerService.findAll();
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
            Provider provider = new Provider();
            int result = 0;
            if(req.getParameter("providerid")!=null&&!req.getParameter("providerid").equals("")){
                provider.setProviderid(Integer.parseInt(req.getParameter("providerid")));
                Provider findPro = providerService.findProById(provider);
                String s = JSON.toJSONString(findPro);
                resp.setContentType("application/json; charset=utf-8");
                resp.getWriter().print(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {
        try {
//            private int providerid;
//            private String  provider_name;
//            private String provider_add;
//            private String provider_tel;
//            private String account;
//            private String email;
            String provider_name = req.getParameter("provider_name");
            String provider_add = req.getParameter("provider_add");
            String provider_tel = req.getParameter("provider_tel");
            String account = req.getParameter("account");
            String email = req.getParameter("email");
            Provider provider = new Provider();
            provider.setProvider_name(provider_name);
            provider.setAccount(account);
            provider.setEmail(email);
            provider.setProvider_add(provider_add);
            provider.setProvider_tel(provider_tel);
            int result = providerService.addProvider(provider);
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
            int providerid = Integer.parseInt(req.getParameter("providerid"));
            String provider_name = req.getParameter("provider_name");
            String provider_add = req.getParameter("provider_add");
            String provider_tel = req.getParameter("provider_tel");
            String account = req.getParameter("account");
            String email = req.getParameter("email");
            Provider provider = new Provider();
            provider.setProviderid(providerid);
            provider.setProvider_name(provider_name);
            provider.setAccount(account);
            provider.setEmail(email);
            provider.setProvider_add(provider_add);
            provider.setProvider_tel(provider_tel);
            int result = providerService.editProvider(provider);
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
            Provider provider = new Provider();
            int result = 0;
            if(req.getParameter("providerid")!=null&&!req.getParameter("providerid").equals("")){
                provider.setProviderid(Integer.parseInt(req.getParameter("providerid")));
                result = providerService.delcateProvider(provider);
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

            String provideridStr = req.getParameter("providerid");
            if(provideridStr!=null&&!"".equals(provideridStr)){
                int providerid = Integer.parseInt(provideridStr);

                map.put("providerid",providerid);
            }

            String provider_nameStr = req.getParameter("provider_name");
            if(provider_nameStr!=null&&!"".equals(provider_nameStr)){
                map.put("catenameStr",provider_nameStr);
            }
            PageBean<Provider> pageBean = providerService.findAllByLimit(map, currentPage, size);

            String json = JSON.toJSONString(pageBean);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
