package com.graccasoft.chat.server.model;

public record SendMessageDto(
        Long messageRoomId,
        String message
) {
}
