package com.SocialmediaAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.SocialmediaAPI.Services.ReelsService;
import com.SocialmediaAPI.Services.UserService;
import com.SocialmediaAPI.models.Reels;
import com.SocialmediaAPI.models.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
public class ReelsController {
	
	@Autowired
	private ReelsService reelsService;

	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reels, @RequestHeader("Authorization") String jwt) {

		User reqUser = userService.findUserByJwt(jwt);

		Reels createdReels = reelsService.createReel(reels, reqUser);
		return createdReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {

		List<Reels> reels = reelsService.findAllReels();
		return reels;
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUserReel(@PathVariable Integer userId) throws Exception {

		List<Reels> useReels = reelsService.findUserReels(userId);
		return useReels;
	}
	
}
