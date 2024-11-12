package com.SocialmediaAPI.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SocialmediaAPI.config.JwtProvider;
import com.SocialmediaAPI.models.User;
import com.SocialmediaAPI.repositry.UserRepositry;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepositry userRepositry;

	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());

		User saveUser = userRepositry.save(newUser);

		return saveUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
		Optional<User> u1 = userRepositry.findById(userId);

		if (u1.isPresent()) {
			return u1.get();
		}
		throw new Exception("User not exist with this " + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepositry.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws Exception {

		User reqUser = findUserById(reqUserId);
		User user2 = findUserById(userId2);

		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowing().add(user2.getId());

		userRepositry.save(reqUser);
		userRepositry.save(user2);

		return reqUser;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {

		Optional<User> u1 = userRepositry.findById(userId);
		if (u1.isEmpty()) {
			throw new Exception("User not exist with this " + userId);
		}

		User oldUser = u1.get();

		if (user.getFirstname() != null) {
			oldUser.setFirstname(user.getFirstname());
		}
		if (user.getLastname() != null) {
			oldUser.setLastname(user.getLastname());
		}
		if (user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender() != null) {
			oldUser.setGender(user.getGender());
		}

		User updateUser = userRepositry.save(oldUser);
		return updateUser;
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepositry.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {

		String email = JwtProvider.getEmailFromJwtToken(jwt);
		
		User user = userRepositry.findByEmail(email);
		
		return user;
	}

}
