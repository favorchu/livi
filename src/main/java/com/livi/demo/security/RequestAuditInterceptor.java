package com.livi.demo.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.livi.demo.common.service.JwtServivce;

/**
 * This is part of the audit trial implementation, to record the requesting uri,
 * time, and any other generic action
 * 
 * @author favorchu
 *
 */
public class RequestAuditInterceptor extends HandlerInterceptorAdapter implements ApplicationContextAware {

	private static JwtServivce jwtServivce;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (RequestAuditInterceptor.jwtServivce == null) {
			RequestAuditInterceptor.jwtServivce = applicationContext.getBean(JwtServivce.class);
		}
	}

	/**
	 * Generic action before all request, e.g. mark the start time
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		SysRequestContext.createThreadLocal();
		return true;
	}

	/**
	 * Generic action for all requests after request, e.g., Mark the execution time
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		SysRequestContext.endRequest();
	}

}
