package com.livi.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestAuthenication extends AbsTestCase {

	@When("User Login with {string} and {string}")
	public void use_login_with_and(String userID, String password) throws Exception {
		lastResult = login(userID, password);
		lastResult.andExpect(status().isOk());
	}

	@Then("Get an OAuth token")
	public void get_an_o_auth_token() throws Exception {

		lastResult.andExpect(jsonPath("$.accessToken", is(notNullValue())));

	}

	@Then("The Access Token can be refreshed with the Refresh Token")
	public void the_access_token_can_be_refreshed_with_the_refresh_token() throws Exception {

		String refreshToken = JsonPath.read(lastResult.andReturn().getResponse().getContentAsString(), "$.refreshToken");

		lastResult = mockMvc.perform(post("/auth/v1/refresh").param("token", refreshToken)).andExpect(status().isOk());

	}

	@When("Calculate the score with no token")
	public void calculate_the_score_with_no_token() throws Exception {
		lastResult = calculateCredit("", 0, "SOLE_PROPRIETORSHIP", 0);
	}

	@Then("A forbidden error returned")
	public void a_forbidden_error_returned() throws Exception {
		lastResult.andExpect(status().isForbidden());
	}

//	@When("Calculate the score with no token with no required access right")
//	public void calculate_the_score_with_no_token_with_no_required_access_right() throws Exception {
//		String accessToken = JsonPath.read(lastResult.andReturn().getResponse().getContentAsString(), "$.accessToken");
//		lastResult = calculateCredit(accessToken, 1, "SOLE_PROPRIETORSHIP", 1);
//	}

	@Then("A unauthorized error returned")
	public void a_unauthorized_error_returned() throws Exception {
		lastResult.andExpect(status().isUnauthorized());
	}

	
}
