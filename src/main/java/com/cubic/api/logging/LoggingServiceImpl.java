package com.cubic.api.logging;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoggingServiceImpl implements LoggingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingServiceImpl.class);

	@Override
	public void logRequest(HttpServletRequest httpServletRequest, Object body) {
		StringBuilder stringBuilder = new StringBuilder();
		Map<String, String> parameters = buildParametersMap(httpServletRequest);
		MDC.put("httpMethod", httpServletRequest.getMethod());
		MDC.put("messageType", "Request");
		stringBuilder.append("Request: ");
		stringBuilder.append(" ").append(httpServletRequest.getMethod()).append(" ");
		stringBuilder.append(httpServletRequest.getRequestURI()).append(" ");

		if (!parameters.isEmpty()) {
			stringBuilder.append("parameters=[").append(parameters).append("] ");
		}

		if (body != null) {
			stringBuilder.append("body=[" + body + "]");
		}

		LOGGER.info(stringBuilder.toString());
	}

	@Override
	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object body) {
		StringBuilder stringBuilder = new StringBuilder();
		MDC.put("httpResponseCode", String.valueOf(httpServletResponse.getStatus()));
		MDC.put("httpMethod", httpServletRequest.getMethod());
		MDC.put("messageType", "Response");
		stringBuilder.append("Response:");
		stringBuilder.append(" ").append(httpServletRequest.getMethod()).append(" ");
		stringBuilder.append(httpServletRequest.getRequestURI()).append(" \n");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		try {
			stringBuilder.append("responseBody=")
					.append(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body)).append(" ");

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		LOGGER.info(stringBuilder.toString());
	}

	private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
		Map<String, String> resultMap = new HashMap<>();
		Enumeration<String> parameterNames = httpServletRequest.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = httpServletRequest.getParameter(key);
			resultMap.put(key, value);
		}

		return resultMap;
	}

}