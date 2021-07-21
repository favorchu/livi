package com.livi.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.livi.demo.exception.UnauthenicatedException;
import com.livi.demo.exception.UnauthorizedException;

@RestControllerAdvice(annotations = RestController.class)
public class CustomRestControllerAdvice extends AbsRestController {

	@ExceptionHandler(value = { UnauthenicatedException.class })
	public void commence(HttpServletRequest request, HttpServletResponse response, UnauthenicatedException ex)
			throws IOException {
		// 403
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + ex.getMessage());
	}

	@ExceptionHandler(value = { UnauthorizedException.class })
	public void commence(HttpServletRequest request, HttpServletResponse response, UnauthorizedException ex)
			throws IOException {
		// 403
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization Failed : " + ex.getMessage());
	}

}
