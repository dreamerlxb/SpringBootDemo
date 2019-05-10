package com.lxb.demo.controller;

import com.lxb.demo.pojo.User;
import com.lxb.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> findUserById(@PathVariable("id") long id) {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<Page<User>> findUsers(User u, PageRequest page) {
		Page<User> pu = userService.findUsers2(u, page);
		return ResponseEntity.ok(pu);
	}

	@PostMapping("/users")
	public ResponseEntity addUser(@Validated User u, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<ObjectError> errors = bindingResult.getAllErrors();
			return ResponseEntity.badRequest().body(errors);
		}
		return ResponseEntity.ok(userService.addUser(u));
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
