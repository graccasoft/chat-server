package com.graccasoft.chat.server.model;

import java.time.Instant;

public record ChatMessageDto(
        Long id,
        String author,
        String message,
        Instant sentAt
) {
}
