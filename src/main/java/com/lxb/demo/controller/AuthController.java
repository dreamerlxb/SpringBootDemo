package com.lxb.demo.controller;

import com.lxb.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lxb.demo.security.JwtAuthRequest;
import com.lxb.demo.security.JwtAuthResponse;
import com.lxb.demo.pojo.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * token
 * 
 * @author lxb
 *
 */

@RestController
public class AuthController {
	@Value("${jwt.header}")
	private String tokenHeader;

	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	/**
	 * 认证用户
	 * 
	 * @param authenticationRequest
	 * @return
	 * @throws AuthenticationException
	 */
	@RequestMapping(value = { "auth", "login" }, method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthRequest authenticationRequest)
			throws AuthenticationException {
		final String token = authService.login(authenticationRequest.getUsername(),
				authenticationRequest.getPassword());
		// Return the token
		return ResponseEntity.ok(new JwtAuthResponse(token));
	}

	/**
	 * 刷新token
	 * 
	 * @param request
	 * @return
	 * @throws AuthenticationException
	 */
	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request)
			throws AuthenticationException {
		String token = request.getHeader(tokenHeader);
		String refreshedToken = authService.refresh(token);
		if (refreshedToken == null) {
			return ResponseEntity.badRequest().body(null);
		} else {
			return ResponseEntity.ok(new JwtAuthResponse(refreshedToken));
		}
	}

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 * @throws AuthenticationException
	 */
	@PostMapping(value = "auth/register")
	public User register( User user) throws AuthenticationException {
		System.out.println(user);
		return authService.register(user);
	}
}
