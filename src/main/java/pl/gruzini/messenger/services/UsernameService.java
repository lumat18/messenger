package pl.gruzini.messenger.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Service
@SessionScope
public class UsernameService {

    private String username = null;

    public String getUsername() {
        if (username == null) {
            username = UUID.randomUUID().toString();
        }
        return username;
    }
}
