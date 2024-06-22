package com.example.Social.Media.Site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.repository.UserRepository;
import com.example.Social.Media.Site.response.AuthResponse;
import com.example.Social.Media.Site.response.LoginRequest;
import com.example.Social.Media.Site.security.JwtProvider;
import com.example.Social.Media.Site.service.UserService;
import com.example.Social.Media.Site.service.impl.CustomeUserDetailService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEndcoder;
	
	@Autowired
	private CustomeUserDetailService customeUser;
	

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signup")
	public AuthResponse signUp(@RequestBody User user) throws Exception{
		
		User oldUser = userRepository.findByEmail(user.getEmail());
		
		if(oldUser!=null) {
			throw new Exception("Email already exists ");
		}
		
		User newUser = new User();
		
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setGender(user.getGender());
		newUser.setPassword(passwordEndcoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(newUser);
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(savedUser.getEmail(), user.getPassword()));
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token, "Register Successfully...");
		
		return res;
	}
	
	@PostMapping("/signin")
	public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {
		 
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token, "Login Successfully...");
		
		return res;
	}
	
	public Authentication authentication(String email, String password) {
		
		UserDetails userDetails = customeUser.loadUserByUsername(email);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid username");
		}
		
		if(!passwordEndcoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Wrong password");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
