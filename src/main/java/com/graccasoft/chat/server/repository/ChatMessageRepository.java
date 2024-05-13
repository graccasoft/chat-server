package com.graccasoft.chat.server.repository;

import com.graccasoft.chat.server.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findAllByChatRoom_Id(Long chatRoomId);
}