package com.example.security.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.security.model.dto.UserDTO;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		UserDTO dto=(UserDTO)auth.getPrincipal();
		String msg=auth.getName()+"님 환영합니다.";
		request.setAttribute("msg", msg);
		RequestDispatcher rd=request.getRequestDispatcher("/");
		rd.forward(request, response);
	}

}
