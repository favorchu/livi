/**
 * 
 */
package com.livi.demo.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.livi.demo.common.model.enums.SysFunc;
import com.livi.demo.security.prehandle.NoValidation;
import com.livi.demo.security.prehandle.PreHandleCheck;

/**
 * This annotation is going to be added at each request.<br />
 * The information will be saved at the audit table of request <br />
 * multiple security features are controlled by this annotation
 * 
 * @author favorchu
 *
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestAudit {

	/**
	 * Permission needed to perform the following actions
	 * 
	 * @return
	 */
	SysFunc permission() default SysFunc.NONE;

	Class<? extends PreHandleCheck> validator() default NoValidation.class;

}
