package com.livi.demo.security.prehandle;

import org.aspectj.lang.JoinPoint;

import com.livi.demo.common.model.pojo.TaUser;

/**
 * A dummy class for no validation required
 * 
 * @author favorchu
 *
 */
public class NoValidation extends PreHandleCheck {

	@Override
	public void validate(TaUser user, JoinPoint joinPoint) {

	}

}
