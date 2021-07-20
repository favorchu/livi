package com.livi.demo.controller.authen;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.livi.demo.controller.AbsRestController;
import com.livi.demo.security.RequestAudit;

@RestController
@RequestMapping("/auth/v1")
public class AuthenController extends AbsRestController {

	@RequestAudit
	@RequestMapping(value = { "/login" }, method = { RequestMethod.POST })
	public LoginRespVO login(@RequestBody LoginReqVO logReq) {

		return new LoginRespVO();
	}
}
