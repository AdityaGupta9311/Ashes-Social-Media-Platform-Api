package com.SocialmediaAPI.Services;

import com.SocialmediaAPI.models.Comment;

public interface CommentService {

	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception;
	
	public Comment findByCommentId(Integer commentId) throws Exception;
	
	public Comment likeComment(Integer commentId, Integer userId) throws Exception;
}
