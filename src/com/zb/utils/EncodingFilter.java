//package com.zb.utils;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
///**
// * 转码过滤器
// * @author Zb
// *
// */
//
//@WebFilter("/*")
//public class EncodingFilter implements Filter {
//
//	public void destroy() {
//	}
//
//	public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain chain) throws IOException, ServletException {
//		request.setCharacterEncoding("utf-8");
//		chain.doFilter(request, response);
//		response.setContentType("text/html;charset=utf-8");
//
//	}
//
//	public void init(FilterConfig fConfig) throws ServletException {
//	}
//
//}
