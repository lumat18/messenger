package pl.gruzini.messenger.services;

import org.springframework.stereotype.Service;
import pl.gruzini.messenger.dto.SendMessageDto;
import pl.gruzini.messenger.model.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<Message> messages = new ArrayList<>();

    private final UsernameService usernameService;

    public MessageService(UsernameService usernameService) {
        this.usernameService = usernameService;
    }

    public void postPublicMessage(final SendMessageDto messageDto) {
        messages.add(new Message(messageDto.getText(), LocalDateTime.now(), usernameService.getUsername()));
    }

    public List<Message> readAllPublicMessages() {
        return messages;
    }
}