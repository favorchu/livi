package com.livi.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenicationTest {

	public Actionwords actionwords = new Actionwords();

	@Test
	public void testRejectBlankLogin() {
		// TODO: Implement action: "Login With Blank Input"
		// TODO: Implement result: "The request is invalid"

	}

	@Test
	public void testLoginAndFreshAccessToken() {
		// TODO: Implement action: "Log in with User name password
		// "
		// TODO: Implement action: "Refresh the access token with the refresh token"

	}
}