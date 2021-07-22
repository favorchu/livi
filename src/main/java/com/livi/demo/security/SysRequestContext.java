package com.livi.demo.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysRequestContext {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysRequestContext.class);

	private static final ThreadLocal<SysRequestContext> THREAD_LOCAL = new ThreadLocal<SysRequestContext>();

	public static SysRequestContext createThreadLocal() {
		THREAD_LOCAL.remove();

		SysRequestContext info = new SysRequestContext();
		info.setStartTime(new Date());
		THREAD_LOCAL.set(info);
		return info;
	}

	public static SysRequestContext currentRequestContext() {
		return THREAD_LOCAL.get();
	}

	public static void endRequest() {
		THREAD_LOCAL.remove();
	}

	private Date startTime;
	private String currentUserId;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public static ThreadLocal<SysRequestContext> getThreadLocal() {
		return THREAD_LOCAL;
	}

}
