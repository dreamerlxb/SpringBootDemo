package com.lxb.demo.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public User findByUsername(final String username) {
		String sql = "SELECT * FROM users WHERE username = :username ";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("username", username);
		
		return namedParameterJdbcTemplate.query(sql, paramMap, new ResultSetExtractor<User>() {
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					User u = new User();
					u.setId(rs.getLong("id"));
					u.setEmail(rs.getString("email"));
					u.setUsername(rs.getString("username"));
					u.setNickname(rs.getString("nickname"));
					u.setPassword(rs.getString("password"));
					u.setLastPasswordResetDate(rs.getDate("last_pwd_reset_date"));
					return u;
				}
				return null;
			}
		});
	}

	public int insert(User u) {
		BeanPropertySqlParameterSource bpsp = new BeanPropertySqlParameterSource(u);
		String sql = "INSERT INTO users (username, email, nickname, password)" +
						" values (:username, :email, :nickname, :password)";
		return namedParameterJdbcTemplate.update(sql, bpsp);
		// result > 0 成功, 否则失败
	}
}
