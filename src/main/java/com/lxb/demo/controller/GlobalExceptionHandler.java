package com.lxb.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * 
 * @author lxb
 *
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	/**
	 * 注册时用户名已经存在
	 * 
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(value = UsernameAlreadyExistsException.class)
	public String usernameAlreadyExists(HttpServletRequest request, Exception exception) {
		exception.printStackTrace();
		System.out.println("我报错了：" + exception.getLocalizedMessage());
		System.out.println("我报错了：" + exception.getCause());
		System.out.println("我报错了：" + exception.getSuppressed());
		System.out.println("我报错了：" + exception.getMessage());
		System.out.println("我报错了：" + exception.getStackTrace());
		return exception.getMessage();
	}
}
