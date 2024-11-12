package com.SocialmediaAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.SocialmediaAPI.Services.MessageService;
import com.SocialmediaAPI.Services.UserService;
import com.SocialmediaAPI.models.Message;
import com.SocialmediaAPI.models.User;

@RestController
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/messages/chat/{chatId}")
	public Message createMessage(@RequestBody Message req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer chatId) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		Message message = messageService.createMessage(reqUser, chatId, req);
		return message;

	}


	@GetMapping("/api/messages/chat/{chatId}")
	public List<Message> findChatsMessage(@RequestBody Message req,
			@RequestHeader("Authorization") String jwt,
			@PathVariable Integer chatId) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		List<Message> message = messageService.findChatMessages(chatId);
		return message;

	}
}
