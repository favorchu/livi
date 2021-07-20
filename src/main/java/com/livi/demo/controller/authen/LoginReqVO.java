package com.livi.demo.controller.authen;

import com.livi.demo.common.model.RestRequestVO;

public class LoginReqVO implements RestRequestVO {
	private String userId;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
