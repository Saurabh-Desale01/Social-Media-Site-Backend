package com.example.Social.Media.Site.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Social.Media.Site.model.Comments;
import com.example.Social.Media.Site.model.Post;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.repository.CommentRepository;
import com.example.Social.Media.Site.repository.PostRepository;
import com.example.Social.Media.Site.service.CommentService;
import com.example.Social.Media.Site.service.PostService;
import com.example.Social.Media.Site.service.UserService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Comments createComments(Comments comments, Integer PostId, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		
		Post post = postService.findPostByPostId(PostId);
		
		comments.setUser(user);
		comments.setContext(comments.getContext());
		comments.setCreatedAt(LocalDateTime.now());
		
		Comments savedComments = commentRepository.save(comments);
		
		post.getComments().add(savedComments);
		
		postRepository.save(post);
		
		return savedComments;
	}

	@Override
	public Comments findCommentById(Integer commentId) throws Exception{

		Optional<Comments> opt = commentRepository.findById(commentId);
		
		if(opt.isEmpty()) {
			throw new Exception("Comment not exist ");
		}
		return opt.get();
	}

	@Override
	public Comments likeComments(Integer commentId, Integer userId) throws Exception {

		Comments comments = findCommentById(commentId);
		User user = userService.findUserById(userId);
		
		if(!comments.getLiked().contains(user)) {
			comments.getLiked().add(user);
		}else {
			comments.getLiked().remove(user);
		}
		
		return commentRepository.save(comments);
	}

	
}
