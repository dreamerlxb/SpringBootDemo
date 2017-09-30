package com.lxb.demo.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lxb.demo.user.User;

@Repository
public class RoleRepository {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Role> findRolesByUser(User u) {
		String sql = "SELECT a.id AS 'id', a.name AS 'name' FROM roles AS a LEFT OUTER JOIN user_roles AS b ON a.id = b.role_id WHERE b.user_id = :userId";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", u.getId());
		return namedParameterJdbcTemplate.queryForList(sql, paramMap, Role.class);
	}

	public int insert(Role role) {
		BeanPropertySqlParameterSource bpsp = new BeanPropertySqlParameterSource(role);
		String sql = "INSERT INTO roles (name) values (:name)";
		return namedParameterJdbcTemplate.update(sql, bpsp);
	}
	//	为用户添加一个普通角色
	public int addRoleForUser(User tmpUser) {
		// TODO Auto-generated method stub
		return 0;
	}
}
