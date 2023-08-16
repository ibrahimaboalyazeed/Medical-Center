package com.global.medical.error;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	 @Autowired
	    private ObjectMapper objectMapper; // Jackson ObjectMapper to convert CustomResponse to JSON

	    @Override
	    public void handle(HttpServletRequest request, HttpServletResponse response,
	                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
	        CustomResponse customResponse = new CustomResponse("Access Denied");
	        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
	        response.setContentType("application/json");
	        response.getWriter().write(objectMapper.writeValueAsString(customResponse));
	    }

}
