package com.lxb.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 测试用例
 * @author lxb
 *
 */
@RestController
public class HelloWorldController {

	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
}
