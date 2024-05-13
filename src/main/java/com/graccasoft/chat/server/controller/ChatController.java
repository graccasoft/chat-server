package com.graccasoft.chat.server.controller;

import com.graccasoft.chat.server.model.ChatMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("chat-room")
public class ChatController {

    List<ChatMessage> getRoomMessages(){
        return null;
    }

    ChatMessage sendMessage(){
        return null;
    }

    void deleteMessage(){

    }
}
