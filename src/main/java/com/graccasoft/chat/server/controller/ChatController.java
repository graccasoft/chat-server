package com.graccasoft.chat.server.controller;

import com.graccasoft.chat.server.model.ChatMessage;
import com.graccasoft.chat.server.model.SendMessageDto;
import com.graccasoft.chat.server.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/chat-room")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("{roomId}")
    List<ChatMessage> getRoomMessages(@PathVariable Long roomId) {
        return chatService.getRoomMessages(roomId);
    }

    @PostMapping("{roomId}")
    ResponseEntity<ChatMessage> sendMessage(
            @PathVariable Long roomId,
            @RequestBody SendMessageDto sendMessageDto,
            Authentication authentication) {
        ChatMessage message = chatService.sendMessage(sendMessageDto, authentication.getName());
        return ResponseEntity.created(URI.create("v1//chat-room/" + roomId + "/message" + message.getId()))
                .body(message);
    }

    @DeleteMapping("{roomId}/message/{id}")
    void deleteMessage(@PathVariable Long roomId, @PathVariable Long id) {
        chatService.deleteMessage(id);
    }
}
