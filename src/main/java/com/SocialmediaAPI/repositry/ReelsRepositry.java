package com.SocialmediaAPI.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SocialmediaAPI.models.Reels;

@Repository
public interface ReelsRepositry extends JpaRepository<Reels, Integer> {
	
	public List<Reels> findByUserId(Integer userId);

}
