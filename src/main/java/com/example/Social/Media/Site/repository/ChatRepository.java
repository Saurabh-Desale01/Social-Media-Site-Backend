package com.example.Social.Media.Site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Social.Media.Site.model.Chat;
import com.example.Social.Media.Site.model.User;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{

	public List<Chat> findByUsersId(Integer userId);
	
	@Query("select c from Chat c where :user Member of c.users And :reqUser Member of c.users")
	public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
}
