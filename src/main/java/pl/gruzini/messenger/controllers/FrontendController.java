package pl.gruzini.messenger.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gruzini.messenger.dto.SendMessageDto;
import pl.gruzini.messenger.model.Message;
import pl.gruzini.messenger.services.MessageService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class FrontendController {

    private final MessageService messageService;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss");

    public FrontendController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("time", System.currentTimeMillis());
        model.addAttribute("newMessage", new SendMessageDto());
        model.addAttribute("allMessages", convertMessagesToText(messageService.readAllPublicMessages()));
        return "index";
    }

    private List<String> convertMessagesToText(final List<Message> messages) {
        return messages.stream()
                .map(message -> message.getTimestamp().format(DATE_TIME_FORMATTER) + " " + message.getUsername() + " : " + message.getText())
                .collect(Collectors.toList());
    }

    @PostMapping
    public String handleMessageInput(@ModelAttribute final SendMessageDto messageDto) {
        log.info("Message: " + messageDto);
        messageService.postPublicMessage(messageDto);
        return "redirect:/";
    }
}
