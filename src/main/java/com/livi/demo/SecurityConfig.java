//package com.livi.demo;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	private static final String[] AUTH_WHITELIST = { //
//			// -- Swagger UI v2 //
//			"/api-docs", //
//			"/api-docs/**", //
//			"/swagger-resources", //
//			"/swagger-resources/**", //
//			"/swagger-ui.html", //
//			"/webjars/**", //
//			// -- Swagger UI v3 (OpenAPI) //
//			"/v3/api-docs/**", //
//			"/swagger-ui/**", //
//			"/auth/v1/**"
//			// other public endpoints of your API may be appended to this array //
//	}; //
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http//
//				.authorizeRequests()//
//				.antMatchers(AUTH_WHITELIST).permitAll()//
//				.anyRequest().fullyAuthenticated()//
//				.and().csrf().disable()//
//				.headers().xssProtection();//
//	}
//
//}
