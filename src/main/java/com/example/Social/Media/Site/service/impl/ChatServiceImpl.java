package com.example.Social.Media.Site.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Social.Media.Site.model.Chat;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.repository.ChatRepository;
import com.example.Social.Media.Site.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Chat createChat(User ReqUser, User user2) {
		
		Chat isExists = chatRepository.findChatByUsersId(user2, ReqUser);
		
		if(isExists!=null) {
			return isExists;
		}else {
			Chat chat = new Chat();
			
			chat.getUsers().add(user2);
			chat.getUsers().add(ReqUser);
			chat.setTimestamp(LocalDateTime.now());
			
			return chatRepository.save(chat);
		}
		
	}

	@Override
	public Chat findChatById(Integer chatId) throws Exception {
		
		Optional<Chat> chat = chatRepository.findById(chatId);
		
		if(chat.isEmpty()) {
			throw new Exception("Chat not found with id "+chatId);
		}
		return chat.get();
	}

	@Override
	public List<Chat> findUsersChat(Integer userId) {
		
		return chatRepository.findByUsersId(userId);
	}

}
