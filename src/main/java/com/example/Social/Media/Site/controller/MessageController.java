package com.example.Social.Media.Site.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.Social.Media.Site.model.Message;
import com.example.Social.Media.Site.model.User;
import com.example.Social.Media.Site.service.MessageService;
import com.example.Social.Media.Site.service.UserService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/messages/chat/{chatId}")
	public Message chatMessage(@RequestBody Message message,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer chatId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		return messageService.createMessage(user, chatId, message);
		
	}
	
	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> findChatMessage(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer chatId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		
		return messageService.findChatsMessages(chatId);
		
	}
}
