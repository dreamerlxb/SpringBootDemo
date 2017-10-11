package com.lxb.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component("accessDeniedHandler")
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		//返回json形式的错误信息
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		response.getWriter().println("{\"code\":403,\"message\":\"没有权限访问\",\"data\":\"\"}");
		response.getWriter().flush();
	}
}
