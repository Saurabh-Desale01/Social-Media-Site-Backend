package com.example.Social.Media.Site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Social.Media.Site.model.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer>{

}
