package pl.gruzini.messenger.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.gruzini.messenger.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PresenceService {

    private Map<String, User> activeUsers = new HashMap<>();

    private final UsernameService usernameService;

    public PresenceService(UsernameService usernameService) {
        this.usernameService = usernameService;
    }

    public void userLoggedIn(final String sessionId) {
        final String username = usernameService.generateNewUsername();
        final User user = new User(username, sessionId);
        activeUsers.put(sessionId, user);
    }

    public void userLoggedOut(final String sessionId) {
        activeUsers.remove(sessionId);
    }

    public User getUser(final String sessionId) {
        return activeUsers.get(sessionId);
    }

    public Collection<User> getAllActiveUsers() {
        return activeUsers.values();
    }
}
