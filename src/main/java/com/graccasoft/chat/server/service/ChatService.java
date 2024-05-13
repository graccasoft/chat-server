package com.graccasoft.chat.server.service;

import com.graccasoft.chat.server.model.*;
import com.graccasoft.chat.server.repository.AppUserRepository;
import com.graccasoft.chat.server.repository.ChatMessageRepository;
import com.graccasoft.chat.server.repository.ChatRoomRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final AppUserRepository appUserRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ChatService(AppUserRepository appUserRepository,
                       ChatRoomRepository chatRoomRepository,
                       ChatMessageRepository chatMessageRepository) {
        this.appUserRepository = appUserRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    @CacheEvict(value = {"messages"}, allEntries = true)
    public ChatMessageDto sendMessage(SendMessageDto sendMessageDto, String username){
        //todo throw an exception if for some reason user not found
        AppUser appUser = appUserRepository.findByUsername(username).orElse(null);

        //todo use a mapper like mapstruct, also throw exception if room not found
        ChatRoom chatRoom = chatRoomRepository.findById(sendMessageDto.messageRoomId()).orElse(null);

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage(sendMessageDto.message());
        chatMessage.setAppUser(appUser);
        chatMessage.setChatRoom(chatRoom);

        return  messageToDto( chatMessageRepository.save(chatMessage) );
    }

    @Cacheable("messages")
    public List<ChatMessageDto> getRoomMessages(Long chatRoomId){
        return chatMessageRepository.findAllByChatRoom_Id(chatRoomId)
                .stream()
                .map(this::messageToDto)
                .toList();
    }

    @CacheEvict(value = {"messages"}, allEntries = true)
    public void deleteMessage(Long id){
        chatMessageRepository.deleteById(id);
    }

    //todo: move this to a seperate class, it violates SOLID SRP
    private ChatMessageDto messageToDto(ChatMessage chatMessage){
        return new ChatMessageDto(
                chatMessage.getId(),
                chatMessage.getAppUser().getUsername(),
                chatMessage.getMessage(),
                chatMessage.getSentDate()
        );
    }
}
