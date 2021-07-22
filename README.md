# Overview
This is a backend code challenge from Livi Bank. Please find the below assumptions

 
1. This designed application is a backend API service, there is no frontend function.
2. The implementation is simplified and just enough for proof of concept only.
3. This application requires JDK11 and Maven installed

# How to use


To run it locally, check out this repository, then use a terminal to execute the below script, and wait for 1 minute until the service is up.

	./mvnw package && java -jar target/demo-0.0.1.jar

After you have completed the previous step, you may run in the docker with the below script.

	docker build -t livi/demo .
	docker run -p 8080:8080 -t livi/demo
	
Once you have already started the service, you can get the generated API Documentation with the below link:

	http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/
	
There is a global optional header **access-token** in each API, which is used to demonstrate the simplified OAuth 2 design. This **access-token** can be obtained by logging in with */auth/v1/login*.


# Gherkins feature file 
All feature file(s) is/are saved inside the **features** folder.

# Implementation Notes

1. The calculation rule in **com.livi.demo.common.service.credit.CreditService** are imported from **com.livi.demo.common.model.jpa.MockRepository**
2. Instead of using **HandlerInterceptor** for authorization, **com.livi.demo.security.RequestAuditInterceptor** is used to specify more detailed authorization requirement per API.
3. The **com.livi.demo.security.RequestAudit** is an annotation on all APIs to specify the permission required and further checking required before any actions.
4. It is assumed the service is stateless, and an authorization token will be provided. Spring security is NOT used as there is already protection on each API.
5. As the access control are maintained on top of each API, it is highly flexible and easier to maintain.



