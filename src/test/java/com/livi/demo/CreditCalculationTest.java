package com.livi.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class CreditCalculationTest {

	public Actionwords actionwords = new Actionwords();

	public void calculateACreditScore(int numberOfEmployees, String companyType, int numberOfYearsOperated,
			int creditScore) {
		actionwords.calculateAScore();
		// Then check the result
		actionwords.checkTheResult();
	}

	@Test
	public void testCalculateACreditScoreTest1() {
		calculateACreditScore(6, "\"Sole Proprietorship\"", 5, 7);
	}

	@Test
	public void testCalculateACreditScoreTest2() {
		calculateACreditScore(10, "\"Limited Liability Company\"", 8, 13);
	}
}