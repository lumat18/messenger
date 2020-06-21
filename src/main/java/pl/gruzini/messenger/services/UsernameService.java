package pl.gruzini.messenger.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@SessionScope
public class UsernameService {

    private static final String[] NAMES = {"kot", "pies"};
    private static final Random RANDOM = new Random();
    private static final List<String> availableUserNames = new LinkedList<>();
    private static int lastGenerated = 0;

    private String username = null;

    public String getUsername() {
        if (username == null) {

            username = generateNewUsername();
        }
        return username;
    }

    private static String generateNewUsername() {
        if (availableUserNames.size() == 0) {
            lastGenerated++;
            availableUserNames.addAll(Arrays.stream(NAMES)
                    .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1) + (lastGenerated == 1 ? "" : (" " + lastGenerated)))
                    .collect(Collectors.toList()));
        }
        final int randomIndex = RANDOM.nextInt(availableUserNames.size());
        return availableUserNames.remove(randomIndex);
    }
}
