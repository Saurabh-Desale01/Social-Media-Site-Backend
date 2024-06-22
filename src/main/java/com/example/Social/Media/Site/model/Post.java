package com.example.Social.Media.Site.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	private String caption;
	private String image;
	private String video;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private List<User> liked=new ArrayList<>();
	
	private LocalDateTime createdAt;
	
	@OneToMany
	private List<Comments> comments = new ArrayList<Comments>();

}
