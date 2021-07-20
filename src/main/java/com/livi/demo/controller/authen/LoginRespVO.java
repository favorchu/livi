package com.livi.demo.controller.authen;

import com.livi.demo.common.model.RestResponseVO;

public class LoginRespVO implements RestResponseVO {

	private String accessToken;
	private String refreshToken;
	private int expires;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

}
