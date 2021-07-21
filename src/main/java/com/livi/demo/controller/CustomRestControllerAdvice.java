package com.livi.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.livi.demo.exception.BusinessRuntimeExcepion;
import com.livi.demo.exception.UnauthenicatedException;
import com.livi.demo.exception.UnauthorizedException;

@RestControllerAdvice(annotations = RestController.class)
public class CustomRestControllerAdvice extends AbsRestController {

	@ExceptionHandler(value = { UnauthenicatedException.class })
	public void commence(HttpServletRequest request, HttpServletResponse response, UnauthenicatedException ex)
			throws IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Request Failed : " + ex.getMessage());
	}

	@ExceptionHandler(value = { UnauthorizedException.class })
	public void commence(HttpServletRequest request, HttpServletResponse response, UnauthorizedException ex)
			throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Request Failed : " + ex.getMessage());
	}

	@ExceptionHandler(value = { BusinessRuntimeExcepion.class })
	public void commence(HttpServletRequest request, HttpServletResponse response, BusinessRuntimeExcepion ex)
			throws IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request Failed : " + ex.getMessage());
	}

}
