package com.livi.demo.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

//	@Autowired
//	private SecurityService securityService;
//
//	@Autowired
//	private SysAuditService sysAuditService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		//TODO review the logic
//		try {
//			JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
//
//			if (StringUtils.isBlank(token.getJwt()))
//				throw new JwtTokenException("Blank Auth Token");
//
//			sysAuditService.appendBackEndSysMsg("Token : " + token.getJwt());
//			PortalAuthenticationToken newToken = securityService.authencateWithHeaderJWT(token.getJwt());
//			return newToken;
//		} catch (Exception e) {
//			SessionAuthenticationException e2 = new SessionAuthenticationException(e.getMessage());
//			e2.initCause(e);
//			throw e2;
//		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
