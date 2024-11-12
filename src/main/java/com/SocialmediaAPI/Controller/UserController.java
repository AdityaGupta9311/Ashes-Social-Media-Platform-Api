package com.SocialmediaAPI.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SocialmediaAPI.Services.UserService;
import com.SocialmediaAPI.Services.UserServiceImplementation;
import com.SocialmediaAPI.models.User;
import com.SocialmediaAPI.repositry.UserRepositry;

@RestController
public class UserController {

	@Autowired
	UserRepositry userRepositry;

	@Autowired
	UserServiceImplementation userServiceImplementation;
	
	@Autowired
	UserService userService;

	@GetMapping("/api/users")
	public List<User> getUsers() {

		List<User> users = userRepositry.findAll();

		return users;
	}

	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") int id) throws Exception {
		User u1 = userServiceImplementation.findUserById(id);
		return u1;
	}

	@PutMapping("/api/users")
	public User updateUser(@RequestBody User user, @RequestHeader("Authorization") String jwt) throws Exception {

		User reqUser = userServiceImplementation.findUserByJwt(jwt);
		User u1 = userServiceImplementation.updateUser(user, reqUser.getId());

		return u1;
	}

	@PutMapping("/api/users/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId2)
			throws Exception {

		User reqUser = userServiceImplementation.findUserByJwt(jwt);
		User u1 = userServiceImplementation.followUser(reqUser.getId(), userId2);

		return u1;
	}

	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query) {

		List<User> u1 = userService.searchUser(query);
		return u1;
	}

	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		User user = userServiceImplementation.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}
}
