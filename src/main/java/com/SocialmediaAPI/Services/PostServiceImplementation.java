package com.SocialmediaAPI.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SocialmediaAPI.models.Post;
import com.SocialmediaAPI.models.User;
import com.SocialmediaAPI.repositry.PostRepositry;
import com.SocialmediaAPI.repositry.UserRepositry;

@Service
public class PostServiceImplementation implements PostService {

	@Autowired
	PostRepositry postRepositry;

	@Autowired
	UserService userService;

	@Autowired
	UserRepositry userRepositry;

	@Override
	public Post createnewPost(Post post, Integer userId) throws Exception {

		User user = userService.findUserById(userId);

		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setUser(user);
		
		Post p1 = postRepositry.save(newPost);
		return p1;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {

		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if (post.getUser().getId() != user.getId()) {
			throw new Exception("You can't delete another user post");
		}
		postRepositry.delete(post);
		return "Post delete successfully";
	}

	@Override
	public List<Post> finPostByUserId(Integer userId) {

		return postRepositry.findPostByUserId(userId);
	}

	@Override
	public Post findPostById(Integer postId) throws Exception {

		Optional<Post> opt = postRepositry.findById(postId);
		if (opt.isEmpty()) {
			throw new Exception("Post not found with id" + postId);
		}
		return opt.get();
	}

	@Override
	public List<Post> findAllPost() {
		return postRepositry.findAll();
	}

	@Override
	public Post savedPost(Integer postId, Integer userId) throws Exception {

		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if (user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		} else
			user.getSavedPost().add(post);
		userRepositry.save(user);

		return post;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) throws Exception {

		Post post = findPostById(postId);
		User user = userService.findUserById(userId);

		if (post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		} else {
			post.getLiked().add(user);
		}
		return postRepositry.save(post);
	}

}
