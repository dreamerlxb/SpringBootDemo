package com.lxb.demo.service.impl;

import com.lxb.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lxb.demo.controller.UsernameAlreadyExistsException;
import com.lxb.demo.repository.UserRepository;
import com.lxb.demo.security.JwtTokenUtil;
import com.lxb.demo.pojo.JwtUser;
import com.lxb.demo.pojo.User;

import java.util.Date;

//@Service
public class AuthServiceImpl implements AuthService {

//	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${jwt.tokenHead}")
	private String tokenHead;
//
//	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public User register(User u) throws UsernameAlreadyExistsException {
		final String username = u.getUsername();
		// 判断用户名是否存在，如果已经存在，那么直接抛异常
		if (findByUsername(username) != null) {
			throw new UsernameAlreadyExistsException("该用户名已经被注册");
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		final String rawPassword = u.getPassword();
		u.setPassword(encoder.encode(rawPassword));
		u.setLastPasswordResetDate(new Date());
		// 如果注册用户成功
		return userRepository.save(u);
	}
	
	public User findByUsername(final String username) {
//		if (StringUtils.isNotBlank(username)) {
//			return null;
//		}
//
//		return userRepository2.findOne((Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//			List<Predicate> predicates = new ArrayList<>();
//			predicates.add(cb.equal(root.get("username"), username));
//			query.where(predicates.toArray(new Predicate[0]));
//			return null;
//		}).orElse(null);

		return userRepository.findOneByUsername(username).orElse(null);
		
//		return userRepository2.findOne(new Specification<User>() {
//			@Override
//			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				List<Predicate> predicates = new ArrayList<>();
//				predicates.add(cb.equal(root.get("username"), username));
//				query.where(predicates.toArray(new Predicate[predicates.size()]));
//				return null;
//			}
//		}).get();
	}

	@Override
	public String login(String username, String password) {
		UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
		// Perform the security
		Authentication authentication = authenticationManager.authenticate(upToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		return jwtTokenUtil.generateToken(userDetails);
	}

	@Override
	public String refresh(String oldToken) {
		final String token = oldToken.substring(tokenHead.length());
		String username = jwtTokenUtil.getUsernameFromToken(token);
		JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
		if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			return jwtTokenUtil.refreshToken(token);
		}
		return null;
	}
}