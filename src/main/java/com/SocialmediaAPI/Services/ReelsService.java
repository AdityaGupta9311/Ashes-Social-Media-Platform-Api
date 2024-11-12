package com.SocialmediaAPI.Services;

import java.util.List;

import com.SocialmediaAPI.models.Reels;
import com.SocialmediaAPI.models.User;

public interface ReelsService {

	public Reels createReel(Reels reels, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReels(Integer userId) throws Exception;
}
