package pl.gruzini.messenger.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import pl.gruzini.messenger.dto.SendMessageDto;
import pl.gruzini.messenger.model.Message;
import pl.gruzini.messenger.services.MessageService;

@Controller
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/publish-message")
    @SendTo("/topic/all-messages")
    public Message greeting(@Payload SendMessageDto message, MessageHeaderAccessor messageHeaderAccessor) throws Exception {
        final String sessionId = ((StompHeaderAccessor) messageHeaderAccessor).getSessionId();
        return messageService.postPublicMessage(message, sessionId);
    }
}
