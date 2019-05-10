package com.lxb.demo.service;

import com.lxb.demo.controller.UsernameAlreadyExistsException;
import com.lxb.demo.pojo.User;

public interface AuthService {
	/**
	 * 注册用户
	 * @param userToAdd
	 * @return
	 */
    User register(User userToAdd) throws UsernameAlreadyExistsException;
    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);
    /**
     * 刷新token
     * @param oldToken
     * @return
     */
    String refresh(String oldToken);
}
