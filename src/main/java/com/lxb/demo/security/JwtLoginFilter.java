package com.lxb.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 登录拦截器
 * 
 * @author lion
 *
 */
//@Component
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	protected JwtLoginFilter() {
		super(new AntPathRequestMatcher("/login", "POST"));
	}

//	@Autowired
	public JwtLoginFilter(AuthenticationManager authenticationManager) {
		this();
		this.setAuthenticationManager(authenticationManager);
	}

	/**
	 * 认证用户
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		ObjectMapper om = new ObjectMapper();
		JwtAuthRequest user = null;
		try {
			user = om.readValue(request.getInputStream(), JwtAuthRequest.class);
		} catch (JsonMappingException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(user);

		String username = user.getUsername();
		String password = user.getPassword();

		return this.getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

	/**
	 * 认证成功
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		JwtUser u = (JwtUser) authResult.getPrincipal();
		String token = jwtTokenUtil.generateToken(u.getUsername());
		// String n = authResult.getName();
		response.addHeader("Authorization", "token=" + token);
	}
}
