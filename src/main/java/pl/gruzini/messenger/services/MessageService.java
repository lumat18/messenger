package pl.gruzini.messenger.services;

import org.springframework.stereotype.Service;
import pl.gruzini.messenger.dto.SendMessageDto;
import pl.gruzini.messenger.model.Message;
import pl.gruzini.messenger.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<Message> messages = new ArrayList<>();

    public Message postPublicMessage(final SendMessageDto messageDto, final String sessionId) {
        final Message message = new Message(messageDto.getText(), LocalDateTime.now(), new User(sessionId));
        messages.add(message);
        return message;
    }

    public List<Message> readAllPublicMessages() {
        return messages;
    }
}
