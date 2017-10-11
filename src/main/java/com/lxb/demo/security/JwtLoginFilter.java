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
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxb.demo.user.User;

@Component
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	protected JwtLoginFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}
	@Autowired
	public JwtLoginFilter(AuthenticationManager authenticationManager) {
		this(new AntPathRequestMatcher("/login", "POST"));
		this.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		String username = user.getUsername();
		String password = user.getPassword();

		return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		JwtUser u = (JwtUser) authResult.getPrincipal();
		String token = jwtTokenUtil.generateToken(u.getUsername());
		//String n = authResult.getName();
		response.addHeader("Authorization", "token=" + token);
	}
}
