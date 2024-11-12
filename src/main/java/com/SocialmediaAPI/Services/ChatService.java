package com.SocialmediaAPI.Services;

import java.util.List;

import com.SocialmediaAPI.models.Chat;
import com.SocialmediaAPI.models.User;

public interface ChatService {
	
	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);

}
