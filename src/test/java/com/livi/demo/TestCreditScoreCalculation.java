package com.livi.demo;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestCreditScoreCalculation extends AbsTestCase {
	@When("The calculateCreditAssessmentScore API is called with {int}, {string}, {int} parameters")
	public void the_calculate_credit_assessment_score_api_is_called_with_parameters(Integer int1, String string,
			Integer int2) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("The credit assessment score should match {int}")
	public void the_credit_assessment_score_should_match(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
}
