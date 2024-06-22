package com.example.Social.Media.Site.service;

import com.example.Social.Media.Site.model.Comments;

public interface CommentService {

	public Comments createComments(Comments comments, Integer PostId, Integer userId) throws Exception;
	
	public Comments findCommentById(Integer commentId) throws Exception;
	
	public Comments likeComments(Integer commentId, Integer userId) throws Exception;
}
