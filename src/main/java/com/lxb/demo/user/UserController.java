package com.lxb.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/users/{id}")
	public User findUserById(@PathVariable("id") long id) {
		return userService.findById(id);
	}

	@GetMapping("/users")
	public Page<User> findUsers(User u, PageRequest page) {
		return userService.findUsers2(u, page);
	}

	@PostMapping("/users")
	public User addUser(@Validated User u, BindingResult bindingResult) {
		return userService.addUser(u);
	}

	@PutMapping("/users")
	public int update(@Validated User u, BindingResult bindingResult) {
		return userService.updateUser(u);
	}

	@DeleteMapping("/users/{id}")
	public void delUserById(@PathVariable("id") long id) {
		userService.deleteById(id);
	}

	@DeleteMapping("/users")
	public int[] delMultiUser(long[] ids) {
		return userService.deleteMultiUser(ids);
	}
}
