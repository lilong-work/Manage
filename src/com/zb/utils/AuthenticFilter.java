package com.zb.utils;//package utils;

import com.zb.entity.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebFilter(urlPatterns = "*.html",dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD})
@WebFilter("*.html")
public class AuthenticFilter implements Filter {
    private static List<String> adminAuths = new ArrayList<String>();


    static {
        adminAuths.add("/");
        adminAuths.add("/check");
        adminAuths.add("/adminServlet");
        adminAuths.add("/login.html");
    }

    public void destroy() {
        System.out.println("身份验证过滤器销毁");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpreq	 = (HttpServletRequest) request;
        HttpSession session = httpreq.getSession();
        String requestURI = httpreq.getRequestURI();
        requestURI = requestURI.substring(requestURI.lastIndexOf("/"));
        System.out.println(requestURI);
        if (!adminAuths.contains(requestURI)) {

            Admin admin = (Admin) session.getAttribute("admin");
            System.out.println(admin);
            if (admin == null) {
                request.getRequestDispatcher("/admin/login.html").forward(httpreq,
                        response);
            } else  {

                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }

    }

    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("身份验证过滤器启动");
    }

}
