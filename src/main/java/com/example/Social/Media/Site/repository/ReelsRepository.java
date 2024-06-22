package com.example.Social.Media.Site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Social.Media.Site.model.Reels;

@Repository
public interface ReelsRepository extends JpaRepository<Reels, Integer>{

	public List<Reels> findByUserId(Integer userId);
}
