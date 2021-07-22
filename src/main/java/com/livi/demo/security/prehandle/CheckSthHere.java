package com.livi.demo.security.prehandle;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.livi.demo.common.model.pojo.TaUser;

/**
 * A sample class to show how the application can further validate the access
 * right of the data
 * 
 * @author favorchu
 *
 */
@Component
public class CheckSthHere extends PreHandleCheck {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckSthHere.class);

	@Override
	public void validate(TaUser user, JoinPoint joinPoint) {

		LOGGER.debug("Dummy checking is here.");

	}

}
