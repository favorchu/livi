Feature: Credit Assessment Calculator
  This is a simple credit assessment calculator feature
     
  
  Scenario: Login with Username Password
  	When User Login with 'favorchu@gmail.com' and 'password'
  	Then Get an OAuth token
  	Then The Access Token can be refreshed with the Refresh Token
  	
  Scenario:  Reject the non-authenicated user
    When Calculate the score with no token
    Then A forbidden error returned   
    
  Scenario:  Reject the non-authorized user
    When User Login with 'yschu711@gmail.com' and 'password'
 		When The calculateCreditAssessmentScore API is called with <numberOfEmployees>, <companyType>, <numberOfYearsOperated> parameters
    Then A unauthorized error returned   
    
  Examples:
    | numberOfEmployees | companyType | numberOfYearsOperated | creditScore |
    | 6 | "SOLE_PROPRIETORSHIP" | 5 | 7 |
     
  Scenario Outline: Calculate Credit Assessment Score
  	When User Login with 'favorchu@gmail.com' and 'password'
    When The calculateCreditAssessmentScore API is called with <numberOfEmployees>, <companyType>, <numberOfYearsOperated> parameters
    Then The credit assessment score should match <creditScore>

  Examples:
    | numberOfEmployees | companyType | numberOfYearsOperated | creditScore |
    | 6 | "SOLE_PROPRIETORSHIP" | 5 | 7 |
    | 10 | "LIMITED_LIABILITY_COMPANY" | 8 | 13 |    
    
  Scenario Outline: Calculate Credit Assessment Score with wrong input
  	When User Login with 'favorchu@gmail.com' and 'password'
    When The calculateCreditAssessmentScore API is called with <numberOfEmployees>, <companyType>, <numberOfYearsOperated> parameters
    Then The credit assessment score should return bad request

  Examples:
    | numberOfEmployees | companyType | numberOfYearsOperated | creditScore |
    | 6 | "ABC" | 5 | 7 |
    | 0 | "LIMITED_LIABILITY_COMPANY" | 8 | 13 |   