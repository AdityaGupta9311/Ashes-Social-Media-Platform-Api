package com.SocialmediaAPI.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SocialmediaAPI.models.Chat;
import com.SocialmediaAPI.models.Message;
import com.SocialmediaAPI.models.User;
import com.SocialmediaAPI.repositry.ChatRepository;
import com.SocialmediaAPI.repositry.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private ChatService chatService;
	
	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {

		Chat chat = chatService.findChatById(chatId);
		Message message = new Message();
		
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		Message savedMessage = messageRepository.save(message);
		
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatMessages(Integer chatId) throws Exception {
		Chat chat = chatService.findChatById(chatId);

		return messageRepository.findByChatId(chatId);
	}

}
