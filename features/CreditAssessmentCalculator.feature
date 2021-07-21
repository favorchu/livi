Feature: Credit Assessment Calculator
  This is a simple credit assessment calculator feature
     
  
  Scenario: Login with Username Password
  	When Use Login with 'favorchu@gmail.com' and 'pasword'
  	Then Get an OAuth token
  	Then The Access Token can be refreshed with the Refresh Token
  	
     
  Scenario Outline: Calculate Credit Assessment Score
    When The calculateCreditAssessmentScore API is called with <numberOfEmployees>, <companyType>, <numberOfYearsOperated> parameters
    Then The credit assessment score should match <creditScore>

  Examples:
    | numberOfEmployees | companyType | numberOfYearsOperated | creditScore |
    | 6 | "Sole Proprietorship" | 5 | 7 |
    | 10 | "Limited Liability Company" | 8 | 13 |    
    
  Scenario:  Reject the non-authenicated user
    When Calculate the score with no token
    Then A forbidden error returned   
    
  Scenario:  Reject the non-authorized user
    When Calculate the score with no token with no required access right
    Then A unauthorized error returned   