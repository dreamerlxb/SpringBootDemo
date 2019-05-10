package com.lxb.demo.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lxb.demo.pojo.User;
import com.lxb.demo.pojo.spec.UserSpec;
import com.lxb.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

	private final JdbcTemplate jdbcTemplate;

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final UserRepository userRepository;

	@Autowired
	public UserService(JdbcTemplate jdbcTemplate,
					   NamedParameterJdbcTemplate namedParameterJdbcTemplate,
					   UserRepository userRepository) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.userRepository = userRepository;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	public List<User> findUsers(User u) {
//		StringBuilder sqlSb = new StringBuilder("SELECT * FROM users ");
//		Map<String, Object> paramMap = new HashMap<>();
//		if (!StringUtils.isEmpty(u.getUsername())) {
//			sqlSb.append("AND name LIKE '%' :username '%' ");
//			paramMap.put("username", u.getUsername());
//		}
//		if (!StringUtils.isEmpty(u.getNickname())) {
//			sqlSb.append("AND nickname LIKE '%' :nickname '%' ");
//			paramMap.put("nickname", u.getNickname());
//		}
//		if (!StringUtils.isEmpty(u.getEmail())) {
//			sqlSb.append("AND email LIKE '%' :email '%' ");
//			paramMap.put("email", u.getEmail());
//		}
//		String sql = sqlSb.toString().replaceFirst("AND", "WHERE");
//		return namedParameterJdbcTemplate.query(sql, paramMap, new RowMapper<User>() {
//			@Override
//			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//				User u = new User();
//				u.setId(rs.getLong("id"));
//				u.setEmail(rs.getString("email"));
//				u.setUsername(rs.getString("username"));
//				u.setNickname(rs.getString("nickname"));
//				return u;
//			}
//		});
//	}
	
	public List<User> findUsers2(User u) {
		return userRepository.findAll(new UserSpec(u));
	}
	
	public Page<User> findUsers2(User u, Pageable page) {
		return userRepository.findAll(new UserSpec(u), page);
	}
	

	/**
	 * 根据ID获取User
	 * 
	 * @param id
	 * @return 用户信息
	 */
	public User findById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	/**
	 * 添加用户信息
	 * 
	 * @param u
	 * @return 如果返回值>0，说明添加成功，否则说明添加失败
	 */
	public User addUser(User u) {
		u.setPassword(passwordEncoder().encode(u.getPassword()));
		return userRepository.save(u);
	}

	/**
	 * 
	 * @param u {@link User}
	 * @return saved user
	 */
	public User saveUser2(User u) {
		u.setPassword(passwordEncoder().encode(u.getPassword()));
		return userRepository.save(u);
	}

	/**
	 * 根据ID删除用户信息
	 * 
	 * @param id
	 * @return {@link User}
	 */
	public int deleteUserById(long id) {
		userRepository.deleteById(id);
//		String sql = "DELETE FROM users WHERE id = ?";
//		return jdbcTemplate.update(sql, id);
		return 1;
	}
	
	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

	/**
	 * 批量删除用户信息
	 * 
	 * @param ids
	 *            要删除的用户的id
	 * @return
	 */
	public int[] deleteMultiUser(long[] ids) {
		String sql = "DELETE FROM users WHERE id = ?";

		return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, ids[i]);
			}

			@Override
			public int getBatchSize() {
				return ids.length;
			}
		});
	}

	/**
	 * 更新用户信息
	 * 
	 * @param u
	 * @return
	 */
	public int updateUser(User u) {
		StringBuilder sqlSb = new StringBuilder("UPDATE users ");
		Map<String, Object> paramMap = new HashMap<>();
		if (!StringUtils.isEmpty(u.getUsername())) {
			sqlSb.append(", username = :username ");
			paramMap.put("username", u.getUsername());
		}
		if (!StringUtils.isEmpty(u.getNickname())) {
			sqlSb.append(", nickname = :nickname ");
			paramMap.put("nickname", u.getNickname());
		}
		if (!StringUtils.isEmpty(u.getEmail())) {
			sqlSb.append(", email = :email ");
			paramMap.put("email", u.getEmail());
		}
		sqlSb.append("WHERE id = :id ");
		paramMap.put("id", u.getId());
		String sql = sqlSb.toString().replaceFirst(",", "SET");
		System.out.println(sql);
		return namedParameterJdbcTemplate.update(sql, paramMap);
	}

//	/**
//	 * 更新或者删除用户，如果存在ID，则更新，否则新增
//	 * 
//	 * @param u
//	 * @return
//	 */
//	public int saveUser(User u) {
//		if (u.getId() > 0) {
//			// 说明存在用户ID，那么更新用户
//			return updateUser(u);
//		} else {
//			// 说明没有用户ID，那么添加用户
//			return addUser(u);
//		}
//	}
}
