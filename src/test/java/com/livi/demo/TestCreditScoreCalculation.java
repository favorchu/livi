package com.livi.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.math.NumberUtils;

import com.jayway.jsonpath.JsonPath;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestCreditScoreCalculation extends AbsTestCase {

	@When("The calculateCreditAssessmentScore API is called with {int}, {string}, {int} parameters")
	public void the_calculate_credit_assessment_score_api_is_called_with_parameters(Integer noOfEmployee,
			String companyType, Integer operatedYear) throws Exception {
		String accessToken = JsonPath.read(lastResult.andReturn().getResponse().getContentAsString(), "$.accessToken");
		lastResult = calculateCredit(accessToken, noOfEmployee, companyType, operatedYear);
	}

	@Then("The credit assessment score should match {int}")
	public void the_credit_assessment_score_should_match(Integer int1) throws UnsupportedEncodingException {
		Integer result = JsonPath.read(lastResult.andReturn().getResponse().getContentAsString(), "$.creditScore");
		assertEquals(int1, result);
	}

	@Then("The credit assessment score should return bad request")
	public void the_credit_assessment_score_should_return_bad_request() throws Exception {
		lastResult.andExpect(status().isBadRequest());
	}

}
