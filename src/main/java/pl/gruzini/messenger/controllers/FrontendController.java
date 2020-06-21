package pl.gruzini.messenger.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.gruzini.messenger.dto.SendMessageDto;
import pl.gruzini.messenger.services.MessageService;

@Controller
@Slf4j
public class FrontendController {

    private final MessageService messageService;

    public FrontendController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("time", System.currentTimeMillis());
        model.addAttribute("newMessage", new SendMessageDto());
        model.addAttribute("allMessages", messageService.readAllPublicMessages());
        return "index";
    }

    @PostMapping
    public String handleMessageInput(@ModelAttribute final SendMessageDto messageDto) {
        log.info("Message: " + messageDto);
        messageService.postPublicMessage(messageDto);
        return "redirect:/";
    }
}
