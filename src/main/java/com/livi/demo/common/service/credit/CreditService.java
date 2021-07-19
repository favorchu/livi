package com.livi.demo.common.service.credit;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.livi.demo.common.Utils.IntegerRangeMap;
import com.livi.demo.common.model.CompanyTypeEnum;
import com.livi.demo.common.service.AbsService;
import com.livi.demo.exception.BusinessRuntimeExcepion;

@Service
public class CreditService extends AbsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditService.class);

	/**
	 * Parameter from application.properties : Number of employees
	 */
	@Value("${credit.scoring.rules.employee}")
	private String scoringRuleEmployeeConfig;
	/**
	 * Parameter from application.properties : Company Type
	 */
	@Value("${credit.scoring.rules.company-type}")
	private String scoringRuleCompanyTypeConfig;
	/**
	 * Parameter from application.properties :Number of years operated
	 */
	@Value("${credit.scoring.rules.year-operated}")
	private String scoringRuleYearOperatedConfig;

	private IntegerRangeMap<Integer> scoringRuleEmployeeMap;
	private HashMap<String, Integer> scoringRuleCompanyType;
	private IntegerRangeMap<Integer> scoringRuleYearOperatedMap;

	@PostConstruct
	private void init() {
		LOGGER.info("Initing the {}", CreditService.class.getSimpleName());

		LOGGER.info("scoringRuleEmployeeConfig: {}", scoringRuleEmployeeConfig);
		if (StringUtils.isNotBlank(scoringRuleEmployeeConfig)) {
			scoringRuleEmployeeMap = fillRange(scoringRuleEmployeeConfig);
		} else {
			throw new BusinessRuntimeExcepion("Missing config: scoringRuleEmployeeConfig");
		}

		LOGGER.info("scoringRuleCompanyTypeConfig: {}", scoringRuleCompanyTypeConfig);
		if (StringUtils.isNotBlank(scoringRuleCompanyTypeConfig)) {

			scoringRuleCompanyType = new HashMap<String, Integer>();
			for (String entry : StringUtils.split(scoringRuleCompanyTypeConfig, ";")) {
				String key = StringUtils.substringBefore(entry, ":");
				String value = StringUtils.substringAfter(entry, ":");

				scoringRuleCompanyType.put(//
						StringUtils.trimToEmpty(key).toUpperCase(), // Key
						Integer.parseInt(value.trim()));// Value
			}
		} else {
			throw new BusinessRuntimeExcepion("Missing config: scoringRuleCompanyTypeConfig");
		}

		LOGGER.info("scoringRuleYearOperatedConfig: {}", scoringRuleYearOperatedConfig);
		if (StringUtils.isNotBlank(scoringRuleYearOperatedConfig)) {
			scoringRuleYearOperatedMap = fillRange(scoringRuleYearOperatedConfig);
		} else {
			throw new BusinessRuntimeExcepion("Missing config: scoringRuleYearOperatedConfig");
		}

	}

	/**
	 * Read a single line of configuration and translate into a range map <br/>
	 * e.g., form-to:value; ....form:value
	 * 
	 * @param configStr
	 * @return
	 */
	private IntegerRangeMap<Integer> fillRange(String configStr) {
		IntegerRangeMap<Integer> rangeMap = new IntegerRangeMap<Integer>();

		for (String entry : StringUtils.split(configStr, ";")) {
			String range = StringUtils.substringBefore(entry, ":");
			String value = StringUtils.substringAfter(entry, ":");

			if (StringUtils.contains(range, "-"))
				rangeMap.addRange(// Complete range
						Integer.parseInt(StringUtils.substringBefore(range, "-").trim()), // From
						Integer.parseInt(StringUtils.substringAfter(range, "-").trim()), // To
						Integer.parseInt(value.trim()));// Value
			else
				rangeMap.addRange(// Complete range
						Integer.parseInt(range.trim()), // From
						null, //
						Integer.parseInt(value.trim()));// Value
		}
		return rangeMap;
	}

	public int calculate(int numOfEmployee, CompanyTypeEnum companyType, int yearOperated) {
		int score = 0;
		{
			Integer tmp = scoringRuleEmployeeMap.get(numOfEmployee);
			if (tmp == null)
				throw new BusinessRuntimeExcepion("Number of emplyee out of range :" + numOfEmployee);
			else
				score += tmp;
		}
		{
			Integer tmp = scoringRuleCompanyType.get(companyType.name().toUpperCase());
			if (tmp == null)
				throw new BusinessRuntimeExcepion("Companny type no found :" + String.valueOf(companyType));
			else
				score += tmp;
		}
		{
			Integer tmp = scoringRuleYearOperatedMap.get(yearOperated);
			if (tmp == null)
				throw new BusinessRuntimeExcepion("Number of year of operated out of range :" + yearOperated);
			else
				score += tmp;
		}

		return score;
	}

}
