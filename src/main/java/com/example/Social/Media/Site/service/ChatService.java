package com.example.Social.Media.Site.service;

import java.util.List;

import com.example.Social.Media.Site.model.Chat;
import com.example.Social.Media.Site.model.User;

public interface ChatService {

	public Chat createChat(User ReqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
	
}
