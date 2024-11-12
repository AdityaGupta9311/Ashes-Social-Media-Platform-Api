package com.SocialmediaAPI.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SocialmediaAPI.models.Post;

public interface PostRepositry extends JpaRepository<Post, Integer> {
	
	@Query("select p from Post p where p.user.id=:userId")
	List<Post> findPostByUserId(Integer userId);
	

}
