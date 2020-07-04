package pl.gruzini.messenger.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import pl.gruzini.messenger.services.PresenceService;

@Component
@Slf4j
public class PresenceEventListener {

    private final PresenceService presenceService;

    public PresenceEventListener(PresenceService presenceService) {
        this.presenceService = presenceService;
    }

    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        final SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        final String sessionId = headers.getSessionId();
        log.info("Connected! SessionID: " + sessionId);
        presenceService.userLoggedIn(sessionId);
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        final SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
        final String sessionId = headers.getSessionId();
        log.info("Disconnected! SessionID: " + sessionId);
        presenceService.userLoggedOut(sessionId);
    }

}
