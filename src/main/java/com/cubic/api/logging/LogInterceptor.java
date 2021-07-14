package com.cubic.api.logging;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class LogInterceptor implements HandlerInterceptor {

	@Autowired
	LoggingService loggingService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())) {
			loggingService.logRequest(request, null);
		}

		return true;
	}

}