package com.graccasoft.chat.server.websocket;

import com.graccasoft.chat.server.model.ChatMessageDto;
import com.graccasoft.chat.server.model.SendMessageDto;
import com.graccasoft.chat.server.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    private final ChatService chatService;

    public ChatWebSocketController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping
    @SendTo("/topic/room-1")
    public ChatMessageDto greeting(SendMessageDto message, Authentication authentication) throws Exception {
        return chatService.sendMessage(message, authentication.getName());
    }
}
