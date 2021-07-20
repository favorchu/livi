package com.livi.demo.controller.credircard;

import com.livi.demo.common.model.RestRequestVO;
import com.livi.demo.common.model.enums.CompanyType;

public class CalculatorReqVO implements RestRequestVO {
	private Integer numberOfEmployees;
	private CompanyType companyType;
	private Integer numberOfYearsOperated;

	public Integer getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(Integer numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public Integer getNumberOfYearsOperated() {
		return numberOfYearsOperated;
	}

	public void setNumberOfYearsOperated(Integer numberOfYearsOperated) {
		this.numberOfYearsOperated = numberOfYearsOperated;
	}

}
