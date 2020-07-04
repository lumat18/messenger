package pl.gruzini.messenger.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        model.addAttribute("allMessages", messageService.readAllPublicMessages());
        return "index";
    }
}
