package com.livi.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class AbsTestCase {
	private static final Logger LOG = LoggerFactory.getLogger(AbsTestCase.class);

	@Autowired
	protected MockMvc mockMvc;

	// A common last result for easier debug
	protected static ResultActions lastResult = null;

	/**
	 * Need this method so the cucumber will recognize this class as glue and load
	 * spring context configuration
	 */
	@Before
	public void setUp() {
		LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
	}

	protected ResultActions login(String userID, String password) throws Exception {
		String reqJson = String.format("{ \"userId\": \"%s\", \"password\": \"%s\"}", userID, password);
		return mockMvc.perform(post("/auth/v1/login").contentType(MediaType.APPLICATION_JSON)//
				.content(reqJson));
	}

	protected ResultActions calculateCredit(String token, int noOfEmployee, String companyType, int operatedYear)
			throws Exception {
		String reqJson = String.format(
				"{  \"numberOfEmployees\": %d, \"companyType\": \"%s\", \"numberOfYearsOperated\": %d }}", noOfEmployee,
				companyType, operatedYear);
		return mockMvc.perform(post("/creditservice/v1/calculator").header(DemoConstant.JWT_HEADER_ACCESS_TOKEN, token)
				.contentType(MediaType.APPLICATION_JSON)//
				.content(reqJson));
	}
}
