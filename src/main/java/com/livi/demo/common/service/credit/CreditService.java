package com.livi.demo.common.service.credit;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livi.demo.common.model.enums.CompanyType;
import com.livi.demo.common.model.jpa.MockRepository;
import com.livi.demo.common.model.pojo.TaConfigWithRange;
import com.livi.demo.common.service.AbsService;
import com.livi.demo.common.utils.IntegerRangeMap;
import com.livi.demo.exception.BusinessRuntimeExcepion;

@Service
public class CreditService extends AbsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditService.class);

	@Autowired
	private MockRepository mockRepository;

	private IntegerRangeMap<Integer> scoringRuleEmployeeMap;
	private HashMap<String, Integer> scoringRuleCompanyType;
	private IntegerRangeMap<Integer> scoringRuleYearOperatedMap;

	@PostConstruct
	private void init() {
		LOGGER.info("Initing the {}", CreditService.class.getSimpleName());

		scoringRuleEmployeeMap = fillRange(mockRepository.getConfigsWithRangeByName("EMPLOYEE"));

		scoringRuleYearOperatedMap = fillRange(mockRepository.getConfigsWithRangeByName("YEAR_OPERATED"));

		scoringRuleCompanyType = new HashMap<String, Integer>();
		mockRepository.getConfigsWithCategoryByName("COMPANY_TYPE").forEach(//
				c -> scoringRuleCompanyType.put(StringUtils.trimToNull(c.getKey()), c.getValue()));
	}

	/**
	 * Read a single line of configuration and translate into a range map <br/>
	 * e.g., form-to:value; ....form:value
	 * 
	 * @param configStr
	 * @return
	 */
	private IntegerRangeMap<Integer> fillRange(List<TaConfigWithRange> configs) {
		IntegerRangeMap<Integer> rangeMap = new IntegerRangeMap<Integer>();
		for (TaConfigWithRange config : configs)
			rangeMap.addRange(config.getForm(), config.getTo(), config.getValue());
		return rangeMap;
	}

	public int calculate(int numOfEmployee, CompanyType companyType, int yearOperated) {
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
