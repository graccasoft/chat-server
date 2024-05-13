package com.graccasoft.chat.server.repository;

import com.graccasoft.chat.server.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}