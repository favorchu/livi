/**
 * 
 */
package com.livi.demo.security;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.livi.demo.DemoConstant;
import com.livi.demo.common.model.enums.SysFunc;
import com.livi.demo.common.model.jpa.MockRepository;
import com.livi.demo.common.model.pojo.TaUser;
import com.livi.demo.common.service.AbsService;
import com.livi.demo.common.service.JwtServivce;
import com.livi.demo.exception.UnauthenicatedException;
import com.livi.demo.exception.UnauthorizedException;

/**
 * @author favorchu
 *
 */
@Aspect
@Component
public class RequestAuditAspect extends AbsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestAuditAspect.class);

	@Autowired
	private JwtServivce jwtServivce;
	@Autowired
	private MockRepository mockRepository;

	@Before("@annotation(com.livi.demo.security.RequestAudit)")
	public void auditRequest(JoinPoint joinPoint) throws Throwable {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		// Set the function
		RequestAudit auditRequest = method.getAnnotation(RequestAudit.class);
		// TODO, perform audit action there

		// permission checking only if function code is specified
		if (auditRequest.permission() == SysFunc.NONE)
			return;

		// Recover the user from JWT

		TaUser user = recoverUserFromJWT();
		if (user == null)
			throw new UnauthenicatedException("User not found");

		// Permission check
		checkPermission(user, auditRequest.permission());

	}

	private TaUser recoverUserFromJWT() {
		try {
			String jwt = getCurrentHttpRequest().getHeader(DemoConstant.JWT_HEADER_ACCESS_TOKEN);
			String userId = jwtServivce.decryptSecretJwt(jwt);
			TaUser user = mockRepository.getUserByIDWithCache(userId);// Use cache to reduce the loading of DB
			return user;
		} catch (Exception ex) {
			throw new UnauthenicatedException("Invalid access token");
		}
	}

	/**
	 * Dummy logic to chekc permission, suppose should be a cached list of
	 * permission stick to the user
	 * 
	 * @param user
	 * @param permission
	 */
	private void checkPermission(TaUser user, SysFunc permission) {

		if (SysFunc.CALCULATE_CREDIT.equals(permission)) {
			// hard code to check role for demo only
			if (!StringUtils.equalsAnyIgnoreCase("SENIOR_USER", user.getRole()))
				throw new UnauthorizedException("Unauthorized.");
		}

	}

	private static HttpServletRequest getCurrentHttpRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			return request;
		}
		return null;
	}
}
