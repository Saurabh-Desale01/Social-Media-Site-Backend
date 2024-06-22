package com.example.Social.Media.Site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.Social.Media.Site.model.Comments;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.service.CommentService;
import com.example.Social.Media.Site.service.UserService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/comments/post/{postId}")
	public Comments createComments(@PathVariable Integer postId,
			@RequestBody Comments comments, 
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Comments createComment = commentService.createComments(comments, postId, user.getId());
		
		return createComment;
	}
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comments likeComments(@PathVariable Integer commentId, 
			@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		Comments likeComment = commentService.likeComments(commentId, user.getId());
		
		return likeComment;
	}
	
	@GetMapping("api/comments/{commentId}")
	public Comments getCommentById(@PathVariable Integer commentId) throws Exception {
		
		return commentService.findCommentById(commentId);
	}
}
