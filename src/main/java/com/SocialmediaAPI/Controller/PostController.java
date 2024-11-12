package com.SocialmediaAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.SocialmediaAPI.Services.PostService;
import com.SocialmediaAPI.Services.UserServiceImplementation;
import com.SocialmediaAPI.models.Post;
import com.SocialmediaAPI.models.User;
import com.SocialmediaAPI.response.ApiResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class PostController {

	@Autowired
	PostService postService;

	@Autowired
	UserServiceImplementation userServiceImplementation;

	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt)
			throws Exception {

		User reqUser = userServiceImplementation.findUserByJwt(jwt);

		Post createdPost = postService.createnewPost(post, reqUser.getId());

		return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User reqUser = userServiceImplementation.findUserByJwt(jwt);
		String message = postService.deletePost(postId, reqUser.getId());
		ApiResponse res = new ApiResponse(message, true);

		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);

	}

	@GetMapping("/api/posts/{postId}")
	public ResponseEntity<Post> findPostByIdHnadler(@PathVariable Integer postId) throws Exception {

		Post post = postService.findPostById(postId);
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}

	@GetMapping("/api/posts/user/{userId}")
	public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId) {

		List<Post> posts = postService.finPostByUserId(userId);
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);

	}

	@GetMapping("/api/posts")
	public ResponseEntity<List<Post>> findAllPost() {

		List<Post> posts = postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);

	}

	@PutMapping("/api/posts/save/{postId}")
	public ResponseEntity<Post> savedPostHnadler(@PathVariable Integer postId,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User reqUser = userServiceImplementation.findUserByJwt(jwt);
		Post post = postService.savedPost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}

	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<Post> likePostHnadler(@PathVariable Integer postId,
			@RequestHeader("Authorization") String jwt) throws Exception {

		User reqUser = userServiceImplementation.findUserByJwt(jwt);
		Post post = postService.likePost(postId, reqUser.getId());
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
//9121c385-e57e-4762-ba18-c40e05630e7c
//6ebbc834-ed64-4b2c-93f3-fce567bfc01f	
}
