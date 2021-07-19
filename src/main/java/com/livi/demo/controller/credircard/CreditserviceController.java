package com.livi.demo.controller.credircard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.livi.demo.common.service.credit.CreditService;
import com.livi.demo.controller.AbsRestController;

@RestController
@RequestMapping("/creditservice/v1")
public class CreditserviceController extends AbsRestController {
	private static final Logger logger = LoggerFactory.getLogger(CreditserviceController.class);

	@Autowired
	private CreditService creditService;

	@RequestMapping(value = { "/calculator" }, method = { RequestMethod.POST })
	public CalculatorRespVO calculsator(@RequestBody CalculatorReqVO req) {
		CalculatorRespVO respVO = new CalculatorRespVO();
		respVO.setCreditScore(creditService.calculate(//
				req.getNumberOfEmployees(), req.getCompanyType(), //
				req.getNumberOfYearsOperated()//
		));
		return respVO;
	}
}
