package com.graccasoft.chat.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graccasoft.chat.server.model.SendMessageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChatControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnMessages() throws Exception {
        mockMvc.perform(
                get("/v1/chat-room/1")
                        .with(user("test-user"))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].message").value("Where is the cheapest tackle shop"));
    }

    @Test
    void shouldSendMessage() throws Exception {
        SendMessageDto sendMessageDto = new SendMessageDto(1l, "test message");

        mockMvc.perform(
                        post("/v1/chat-room/1/message")
                                .with(user("angler"))
                                .content(asJsonString(sendMessageDto))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("test message"));
    }

    @Test
    void shouldNotSendMessageIfNotLoggedIn() throws Exception {
        SendMessageDto sendMessageDto = new SendMessageDto(1l, "test message");

        mockMvc.perform(
                        post("/v1/chat-room/1/message")
                                .content(asJsonString(sendMessageDto))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isUnauthorized());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}