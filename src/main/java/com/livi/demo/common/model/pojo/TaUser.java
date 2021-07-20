package com.livi.demo.common.model.pojo;

public class TaUser {

	private String userId;
	private String oauthToekn;
	private String role;
	private String password;

	public TaUser(String userId, String oauthToekn, String role, String password) {
		super();
		this.userId = userId;
		this.oauthToekn = oauthToekn;
		this.role = role;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOauthToekn() {
		return oauthToekn;
	}

	public void setOauthToekn(String oauthToekn) {
		this.oauthToekn = oauthToekn;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
