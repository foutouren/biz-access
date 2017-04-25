package com.glsx.biz.access.container.interceptor;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import com.alibaba.dubbo.rpc.RpcContext;

public class OperateLogAdvisor implements MethodBeforeAdvice {
	
	private static Logger logger = Logger.getLogger(OperateLogAdvisor.class.getName());

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		StringBuffer argsSb = new StringBuffer();
		if (args != null) {
			Class<?>[] c = method.getParameterTypes();
			int len = args.length;
			for (int i = 0; i < len; i++) {
				argsSb.append(c[i].getName() + ":" 
						+ (args[i] != null ? args[i].toString() : "null") + "|");
			}

		}
		String remoteIp = RpcContext.getContext().getRemoteAddressString();
		logger.warn("调用来源IP："+remoteIp+"：类名:" + target.getClass().getName() + ";方法:"
				+ method.getName() + ";参数:" + argsSb.toString() + ";");
	}

}
