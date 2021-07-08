package com.zb.proxy;


import com.zb.service.AdminService;
import com.zb.service.CategoryService;
import com.zb.service.ProductService;
import com.zb.service.ProviderService;
import com.zb.service.impl.AdminServiceImpl;
import com.zb.service.impl.CategoryServiceImpl;
import com.zb.service.impl.ProductServiceImpl;
import com.zb.service.impl.ProviderServiceImpl;
import com.zb.servlet.ProductServlet;

public class ServiceProxyFactory {
	public static CategoryService createCateService() {
		CategoryService service = new CategoryServiceImpl();
		return (CategoryService) new MyInvocationHandler<>(service).creatProxy();
	}
	public static AdminService createAdminService() {
		AdminService service = new AdminServiceImpl();
		return (AdminService) new MyInvocationHandler<>(service).creatProxy();
	}
	public static ProductService createProductService() {
		ProductService service = new ProductServiceImpl();
		return (ProductService) new MyInvocationHandler<>(service).creatProxy();
	}
	public static ProviderService createProviderService() {
		ProviderService service = new ProviderServiceImpl();
		return (ProviderService) new MyInvocationHandler<>(service).creatProxy();
	}
}
