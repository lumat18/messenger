package pl.gruzini.messenger.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.gruzini.messenger.dto.SendMessageDto;
import pl.gruzini.messenger.services.MessageService;
import pl.gruzini.messenger.services.UsernameService;

@Controller
@Slf4j
public class FrontendController {

    private final UsernameService usernameService;
    private final MessageService messageService;

    public FrontendController(UsernameService usernameService, MessageService messageService) {
        this.usernameService = usernameService;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("time", System.currentTimeMillis());
        model.addAttribute("newMessage", new SendMessageDto());
        model.addAttribute("allMessages", messageService.readAllPublicMessages());
        model.addAttribute("username", usernameService.getUsername());
        return "index";
    }
}
