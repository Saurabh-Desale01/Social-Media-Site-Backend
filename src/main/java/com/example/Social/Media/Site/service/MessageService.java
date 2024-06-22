package com.example.Social.Media.Site.service;

import java.util.List;

import com.example.Social.Media.Site.model.Message;
import com.example.Social.Media.Site.model.User;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message message) throws Exception;
	
	public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
