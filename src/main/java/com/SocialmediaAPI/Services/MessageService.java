package com.SocialmediaAPI.Services;

import java.util.List;

import com.SocialmediaAPI.models.Chat;
import com.SocialmediaAPI.models.Message;
import com.SocialmediaAPI.models.User;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	
	public List<Message> findChatMessages(Integer chatId)throws Exception;
}
