package com.example.Social.Media.Site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Social.Media.Site.model.Post;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.response.ApiResponse;
import com.example.Social.Media.Site.service.PostService;
import com.example.Social.Media.Site.service.UserService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/users/")
	public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		Post createPost =  postService.createPost(post, reqUser.getId());
		
		return new ResponseEntity<>(createPost, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{postId}/")
	public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		String message = postService.deletePost(reqUser.getId(), postId);
		ApiResponse api = new ApiResponse(message, true);
		return new ResponseEntity<ApiResponse>(api, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Integer userId){
		
		List<Post> getById = postService.findPostByUserId(userId);
		return new ResponseEntity<>(getById, HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<Post> findPostByPostId(@PathVariable Integer postId) throws Exception {
		Post getByPostId = postService.findPostByPostId(postId);
		return new ResponseEntity<>(getByPostId, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Post>> findAllPosts(){
		List<Post> allPost = postService.findAllPost();
		return new ResponseEntity<>(allPost, HttpStatus.OK);
	}
	
	@PutMapping("/saved/{postId}/")
	public ResponseEntity<Post> savedPost(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		Post savePost= postService.savedPost(reqUser.getId(), postId);
		return new ResponseEntity<>(savePost, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/like/{postId}/")
	public ResponseEntity<Post> likedPost(@RequestHeader("Authorization") String jwt, @PathVariable Integer postId) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		Post savePost= postService.likePost(reqUser.getId(), postId);
		return new ResponseEntity<>(savePost, HttpStatus.ACCEPTED);
	}
	

}
