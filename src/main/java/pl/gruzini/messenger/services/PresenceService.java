package pl.gruzini.messenger.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.gruzini.messenger.model.User;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class PresenceService {

    private List<User> activeUsers = new LinkedList<>();

    public void userLoggedIn(final String sessionId) {

    }

    public void userLoggedOut(final String sessionId) {

    }
}
