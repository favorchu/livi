package com.livi.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.livi.demo.controller.authen.AuthenController;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationBasicTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthenController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
