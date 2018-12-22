package com.lxb.demo.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lxb.demo.role.Role;

@Entity
@Table(name = "t_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username", "phone", "email" }) })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, max = 20, message = "昵称长度3~20")
	@Column(name = "nickname")
	private String nickname;

	@Column(name = "password")
	@Size(min = 5, message = "密码长度至少5位")
	private String password;

	@Size(min = 3, max = 20, message = "用户名长度3~20")
	@Column(name = "username")
	private String username;

	@Column(name = "email")
	@Email
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "enabled")
	private boolean enabled = true;

	@Column(name = "avatar")
	private String avatar;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_pwd_reset_date")
	private Date lastPasswordResetDate;

	/**
	 * 一个用户拥有多种角色 一个角色可以有多个用户 多对多
	 */
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class)
	@JoinTable(name = "t_user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<Role> roles;

	@JsonIgnore
	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@JsonIgnore
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", password=" + password + ", username=" + username
				+ ", email=" + email + ", phone=" + phone + ", enabled=" + enabled + ", avatar=" + avatar
				+ ", lastPasswordResetDate=" + lastPasswordResetDate + "]";
	}
}
