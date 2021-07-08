package com.zb.proxy;



import com.zb.utils.DButils;
import com.zb.utils.DateUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;
/*
		代理,装饰者--增强
		增强的方式：
			1.增强参数列表
			2.增强返回值类型
			3.增强方法体执行逻辑
 */

public class MyInvocationHandler<T> implements InvocationHandler{
	private T t;
	//因为有很多service接口实现类要被代理，所以这里用到了泛型
	public MyInvocationHandler(T t) {
		this.t = t;
	}
	//代理的方式
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println(DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+"执行了"+t.getClass().getName()+method.getClass().getName());
		Object result=null;
		try {
			//设置手动提交，创建连接是在BaseDao里面创建的，第二次调用方法的时候，因为第一次已经close了，所以这里就报空了
			DButils.startTransaction();
			result=method.invoke(t, args);
			DButils.commitTransaction();
		}catch(Exception e){
			e.printStackTrace();
			DButils.commitRollback();
		}finally {
			DButils.release();
		}
		System.out.println(DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss")+" "+t.getClass().getName()+"."+method.getName()+"执行结束");
		return result;
	}
	//生成代理
	public T creatProxy() {
		T proxyT= null;
		proxyT=(T) Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), this);
		return proxyT;
	}
}
