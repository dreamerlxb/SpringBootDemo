package com.lxb.demo.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/users/{id}")
	public User findUserById(@PathVariable("id") long id) {
		User u = userService.findUserById(id);
		return u;
	}
	
	@GetMapping("/users")
	public List<User> findUsers(User u) {
		return userService.findUsers(u);
	}

//	@PostMapping("/users")
//	public int addUser(User u) {
//		return userService.addUser(u);
//	}
	
	@PutMapping("/users")
	public int update(User u) {
		return userService.updateUser(u);
	}
	
	@DeleteMapping("/users/{id}")
	public int delUserById(@PathVariable("id") long id) {
		return userService.deleteUserById(id);
	}
	
	@DeleteMapping("/users")
	public int[] delMultiUser(long [] ids) {
		System.out.println(ids);
		return userService.deleteMultiUser(ids);
	}
}
