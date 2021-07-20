/**
 * 
 */
package com.livi.demo.security;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.livi.demo.common.service.AbsService;

/**
 * @author favorchu
 *
 */
@Aspect
@Component
public class RequestAuditAspect extends AbsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestAuditAspect.class);

	@Before("@annotation(com.livi.demo.security.RequestAudit)")
	public void auditRequest(JoinPoint joinPoint) throws Throwable {

		LOGGER.info("Hello");

		// TODO permission checking

		// TODO, perform audit action there

	}

}
