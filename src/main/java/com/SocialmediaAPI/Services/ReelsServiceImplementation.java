package com.SocialmediaAPI.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SocialmediaAPI.models.Reels;
import com.SocialmediaAPI.models.User;
import com.SocialmediaAPI.repositry.ReelsRepositry;

@Service
public class ReelsServiceImplementation implements ReelsService {

	@Autowired
	private ReelsRepositry reelsRepositry;

	@Autowired
	private UserService userService;

	@Override
	public Reels createReel(Reels reels, User user) {

		Reels createReels = new Reels();
		createReels.setTitle(reels.getTitle());
		createReels.setUser(user);
		createReels.setVideo(reels.getVideo());
		return reelsRepositry.save(createReels);
	}

	@Override
	public List<Reels> findAllReels() {
		// TODO Auto-generated method stub
		return reelsRepositry.findAll();
	}

	@Override
	public List<Reels> findUserReels(Integer userId) throws Exception {

		userService.findUserById(userId);
		return reelsRepositry.findByUserId(userId);
	}

}
