package com.example.Social.Media.Site.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Social.Media.Site.model.Chat;
import com.example.Social.Media.Site.model.Message;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.repository.ChatRepository;
import com.example.Social.Media.Site.repository.MessageRepository;
import com.example.Social.Media.Site.service.ChatService;
import com.example.Social.Media.Site.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Message createMessage(User user, Integer chatId, Message message) throws Exception {
		
		Chat chat = chatService.findChatById(chatId);
		
		Message req = new Message();
		
		req.setChat(chat);
		req.setContent(message.getContent());
		req.setImage(message.getImage());
		req.setUser(user);
		req.setTimestamp(LocalDateTime.now());
		
		Message savedMessage = messageRepository.save(req);
		
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
		
		Chat chat = chatService.findChatById(chatId);
		
		return messageRepository.findByChatId(chatId);
	}

}
