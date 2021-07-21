package com.livi.demo;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestAuthenication extends AbsTestCase {

	@When("Use Login with {string} and {string}")
	public void use_login_with_and(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("Get an OAuth token")
	public void get_an_o_auth_token() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("The Access Token can be refreshed with the Refresh Token")
	public void the_access_token_can_be_refreshed_with_the_refresh_token() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("Calculate the score with no token")
	public void calculate_the_score_with_no_token() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("A forbidden error returned")
	public void a_forbidden_error_returned() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("Calculate the score with no token with no required access right")
	public void calculate_the_score_with_no_token_with_no_required_access_right() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("A unauthorized error returned")
	public void a_unauthorized_error_returned() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

}
