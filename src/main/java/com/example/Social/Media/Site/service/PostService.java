package com.example.Social.Media.Site.service;

import java.util.List;

import com.example.Social.Media.Site.model.Post;

public interface PostService {

	Post createPost(Post post, Integer userId) throws Exception;
	
	String deletePost(Integer postId, Integer userId) throws Exception;
	
	List<Post> findPostByUserId(Integer userId);
	
	Post findPostByPostId(Integer postId) throws Exception;
	
	List<Post> findAllPost();
	
	Post savedPost(Integer userId, Integer postId) throws Exception;
	
	Post likePost(Integer userId, Integer postId) throws Exception;
}
