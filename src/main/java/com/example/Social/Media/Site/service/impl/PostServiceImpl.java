package com.example.Social.Media.Site.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Social.Media.Site.model.Post;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.repository.PostRepository;
import com.example.Social.Media.Site.repository.UserRepository;
import com.example.Social.Media.Site.service.PostService;
import com.example.Social.Media.Site.service.UserService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Post createPost(Post post, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setCreatedAt(LocalDateTime.now());
		newPost.setVideo(post.getVideo());
		newPost.setUser(user);
		
		return postRepository.save(newPost);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		
		Post post = findPostByPostId(postId);
		User user = userService.findUserById(userId);
		
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("You can't delete another user post");
		}
		
		postRepository.deleteById(postId);
		
		return "Post deleted successfully!!!";
		
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public Post findPostByPostId(Integer postId) throws Exception {

		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isPresent()) {
			return post.get();
		}
		
		throw new Exception("Post not exit with id "+ postId);
	}

	@Override
	public List<Post> findAllPost() {

		return postRepository.findAll();
	}

	@Override
	public Post savedPost(Integer userId, Integer postId) throws Exception {
		
		Post post = findPostByPostId(postId);
		User user = userService.findUserById(userId);
		
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}else {
			user.getSavedPost().add(post);
		}
		userRepository.save(user);
		return post;
	}

	@Override
	public Post likePost(Integer userId, Integer postId) throws Exception {
		
		Post post = findPostByPostId(postId);
		User user = userService.findUserById(userId);
		
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}else {
			post.getLiked().add(user);
		}
		//post.getLiked().add(user);
		
		return postRepository.save(post);
	}

}
