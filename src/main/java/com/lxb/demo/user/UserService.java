package com.lxb.demo.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<User> findUsers(User u) {
		StringBuilder sqlSb = new StringBuilder("SELECT * FROM users ");
		Map<String, Object> paramMap = new HashMap<>();
		if (!StringUtils.isEmpty(u.getUsername())) {
			sqlSb.append("AND name LIKE '%:username%' ");
			paramMap.put("username", u.getUsername());
		}
		if (!StringUtils.isEmpty(u.getNickname())) {
			sqlSb.append("AND nickname LIKE '%:nickname%' ");
			paramMap.put("nickname", u.getNickname());
		}
		if (!StringUtils.isEmpty(u.getEmail())) {
			sqlSb.append("AND email LIKE '%:email%' ");
			paramMap.put("email", u.getEmail());
		}
		String sql = sqlSb.toString().replaceFirst("AND", "WHERE");
		return namedParameterJdbcTemplate.query(sql, paramMap,
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User u = new User();
						u.setId(rs.getLong("id"));
						u.setEmail(rs.getString("email"));
						u.setUsername(rs.getString("username"));
						u.setNickname(rs.getString("nickname"));
						return u;
					}
				});
	}

	/**
	 * 根据ID获取User
	 * 
	 * @param id
	 * @return 用户信息
	 */
	public User findUserById(long id) {
		String sql = "SELECT id, username, email, nickname FROM users WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setId(rs.getLong("id"));
				u.setEmail(rs.getString("email"));
				u.setUsername(rs.getString("username"));
				u.setNickname(rs.getString("nickname"));
				return u;
			}
		});
	}

	/**
	 * 添加用户信息
	 * 
	 * @param u
	 * @return 如果返回值>0，说明添加成功，否则说明添加失败
	 */
	public int addUser(User u) {
		String sql = "INSERT INTO users (username, email, nickname)" + " values (?,?,?)";
		Object args[] = { u.getUsername(), u.getEmail(), u.getNickname() };
		// result > 0 成功 否则 失败
		int result = jdbcTemplate.update(sql, args);
		return result;
	}

	/**
	 * 根据ID删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	public int deleteUserById(long id) {
		String sql = "DELETE FROM users WHERE id = ?";
		return jdbcTemplate.update(sql, id);
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
		int[] results = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, ids[i]);
			}

			@Override
			public int getBatchSize() {
				return ids.length;
			}
		});
		return results;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param id
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

	/**
	 * 更新或者删除用户，如果存在ID，则更新，否则新增
	 * 
	 * @param u
	 * @return
	 */
	public int saveUser(User u) {
		if (u.getId() > 0) {
			// 说明存在用户ID，那么更新用户
			return updateUser(u);
		} else {
			// 说明没有用户ID，那么添加用户
			return addUser(u);
		}
	}
}
