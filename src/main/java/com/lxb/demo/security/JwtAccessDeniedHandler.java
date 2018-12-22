package com.lxb.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component("accessDeniedHandler")
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// 返回json形式的错误信息
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", 403);
		jsonObject.put("message", "没有权限访问");
		jsonObject.put("data", accessDeniedException.getMessage());
		jsonObject.put("error", accessDeniedException);

		response.getWriter().println(jsonObject.toString());
		response.getWriter().flush();
	}
}
