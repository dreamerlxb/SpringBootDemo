package com.lxb.demo.pojo.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lxb.demo.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class UserSpec implements Specification<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public UserSpec(User user) {
		super();
		this.user = user;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if (StringUtils.isNotBlank(user.getUsername())) {
			predicates.add(cb.like(root.get("username"), "%" + user.getUsername() + "%"));
		}
		if (StringUtils.isNotBlank(user.getPhone())) {
			predicates.add(cb.like(root.get("phone"), "%" + user.getPhone() + "%"));
		}
		if (StringUtils.isNotBlank(user.getEmail())) {
			predicates.add(cb.like(root.get("email"), "%" + user.getEmail() + "%"));
		}
		query.where(predicates.toArray(new Predicate[0]));
		return null;
	}
}
