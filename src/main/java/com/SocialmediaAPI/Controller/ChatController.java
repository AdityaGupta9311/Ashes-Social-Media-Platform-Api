package com.SocialmediaAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.SocialmediaAPI.Services.ChatService;
import com.SocialmediaAPI.Services.UserService;
import com.SocialmediaAPI.models.Chat;
import com.SocialmediaAPI.models.User;
import com.SocialmediaAPI.request.CreateChatRequest;

@RestController
public class ChatController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/api/chats")
	public Chat createChat(@RequestBody CreateChatRequest req, @RequestHeader("Authorization") String jwt) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		User user2 = userService.findUserById(req.getUserId());
		Chat chat = chatService.createChat(reqUser, user2);

		return chat;
	}
	
	@GetMapping("/api/chats")
	public List<Chat> findUserChat(@RequestHeader("Authorization") String jwt) {

		User user = userService.findUserByJwt(jwt);
		List<Chat> chat = chatService.findUsersChat(user.getId());
		return chat;
	}
}
