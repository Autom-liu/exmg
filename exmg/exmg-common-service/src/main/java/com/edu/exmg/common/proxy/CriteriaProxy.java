package com.edu.exmg.common.proxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.StringUtils;

/**
 * Criteria 类的代理类生成工具，可以使得Criteria很方便链式调用构造查询条件
 * @author Autom
 *
 */
public class CriteriaProxy implements MethodInterceptor {
	
	private Object target;
	
	private Object proxyTarget;
	
	private static CriteriaProxy instance = new CriteriaProxy();
	
	private CriteriaProxy() {
		 
	}
	
	public static Object getInstance(Object target) {
		return instance.getInstanceProxy(target);
	}
	
	private Object getInstanceProxy(Object target) {
		this.target = target;
		
		Enhancer enhancer = new Enhancer();
		
		enhancer.setSuperclass(target.getClass());
		
		enhancer.setCallback(this);
		
		proxyTarget = enhancer.create();
		
		return proxyTarget;
	}

	@Override
	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		for (Object arg : args) {
			if(StringUtils.isEmpty(arg)) {
				return proxyTarget;				
			}
		}
		method.invoke(target, args);
		return proxyTarget;
	}
}
