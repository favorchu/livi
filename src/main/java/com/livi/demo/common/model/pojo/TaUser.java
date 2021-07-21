package com.livi.demo.common.model.pojo;

public class TaUser {

	private String userId;
	private String oauthToken;
	private String role;
	private String password;

	public TaUser(String userId, String oauthToken, String role, String password) {
		super();
		this.userId = userId;
		this.oauthToken = oauthToken;
		this.role = role;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOauthToken() {
		return oauthToken;
	}

	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
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
