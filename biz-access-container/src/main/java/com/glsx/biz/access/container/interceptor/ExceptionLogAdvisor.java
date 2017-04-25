package com.glsx.biz.access.container.interceptor;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

public class ExceptionLogAdvisor implements ThrowsAdvice {
	
	private static Logger logger = Logger.getLogger(ExceptionLogAdvisor.class.getName());
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex)
	{
		logger.error("类名:" + target.getClass().getName() + ";方法:"
				+ method.getName() + ";异常信息："+ex.getMessage());
		ex.printStackTrace();
	}
}
