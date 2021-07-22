package com.livi.demo.security.prehandle;

import org.aspectj.lang.JoinPoint;

import com.livi.demo.common.model.pojo.TaUser;

/**
 * This is abstract class to allow further implementation of request access
 * right checking. e.g. checking the user can access the request
 * data/records/account.
 * 
 * @author favorchu
 *
 */
public abstract class PreHandleCheck {

	public abstract void validate(TaUser user, JoinPoint joinPoint);
}
