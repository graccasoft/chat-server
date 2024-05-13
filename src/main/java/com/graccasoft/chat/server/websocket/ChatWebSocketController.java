package com.graccasoft.chat.server.websocket;

import com.graccasoft.chat.server.model.ChatMessageDto;
import com.graccasoft.chat.server.model.SendMessageDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {
    @MessageMapping
    @SendTo("/topic/room-1")
    public ChatMessageDto greeting(SendMessageDto message) throws Exception {
        //todo implement sockets
        return null;
    }
}
