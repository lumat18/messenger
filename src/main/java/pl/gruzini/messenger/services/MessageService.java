package pl.gruzini.messenger.services;

import org.springframework.stereotype.Service;
import pl.gruzini.messenger.dto.SendMessageDto;
import pl.gruzini.messenger.model.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final PresenceService presenceService;

    private List<Message> messages = new ArrayList<>();

    public MessageService(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    public Message postPublicMessage(final SendMessageDto messageDto, final String sessionId) {
        final Message message = new Message(messageDto.getText(), LocalDateTime.now(), presenceService.getUser(sessionId));
        messages.add(message);
        return message;
    }

    public List<Message> readAllPublicMessages() {
        return messages;
    }
}
