//package com.livi.demo.security;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.livi.demo.DemoConstant;
//import com.livi.demo.common.service.JwtServivce;
//
///**
// * This class is used to handle the Account preparation work e.g. decrypt the
// * JWT which help the following audit function to know the identity of the user.
// * some more information related to request will be prepared there
// * 
// * @author favorchu
// *
// */
//public class RequestAuditInterceptor extends HandlerInterceptorAdapter implements ApplicationContextAware {
//
//	private static JwtServivce jwtServivce;
//
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		if (RequestAuditInterceptor.jwtServivce == null) {
//			RequestAuditInterceptor.jwtServivce = applicationContext.getBean(JwtServivce.class);
//		}
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//
//		// Remove the thread object
//		SysRequestContext.endRequest();
//	}
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//
//		// Make sure the thread local is cleans
//		SysRequestContext.endRequest();
//
//		if (SecurityContextHolder.getContext().getAuthentication() != null
//				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
//			//This user is already authenticated, ignore the 
//			return true;
//		}
//		
//
//		String jwt = request.getHeader(DemoConstant.JWT_HEADER_ACCESS_TOKEN);
//		if (StringUtils.isNotBlank(jwt)) {
//			try {
//				DecodedJWT decodedJWT = jwtServivce.decodeAuthenJWT(jwt);
//				String userId = decodedJWT.getClaim(DemoConstant.JWT_CLAIM_CODE).asString();
//				SysRequestContext.createThreadLocal(StringUtils.trimToNull(userId));
//			} catch (Exception e) {
//				// give a proper error there. e.g 400
//				response.setStatus(400);
//				response.getWriter().print("Invalid access token");
//				return false;
//			}
//		} else {
//			// Not access token
//			SysRequestContext.createThreadLocal(null);
//		}
//		return true;
//	}
//
//}
