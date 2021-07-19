package com.livi.demo.controller.credircard;

import com.livi.demo.common.model.CompanyTypeEnum;
import com.livi.demo.common.model.RestRequestVO;

public class CalculatorReqVO implements RestRequestVO {
	private Integer numberOfEmployees;
	private CompanyTypeEnum companyType;
	private Integer numberOfYearsOperated;

	public Integer getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(Integer numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public CompanyTypeEnum getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyTypeEnum companyType) {
		this.companyType = companyType;
	}

	public Integer getNumberOfYearsOperated() {
		return numberOfYearsOperated;
	}

	public void setNumberOfYearsOperated(Integer numberOfYearsOperated) {
		this.numberOfYearsOperated = numberOfYearsOperated;
	}

}
