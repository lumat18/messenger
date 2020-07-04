package pl.gruzini.messenger.model;

import lombok.Data;

import java.util.Random;

@Data
public class User {
    private final String name;
    private final String colorCode;
    private final String sessionId;

    public User(String name, String sessionId) {
        this.name = name;
        this.sessionId = sessionId;
        final Random random = new Random(this.name.hashCode());
        final int randomNumber = random.nextInt(0xffffff + 1);
        this.colorCode = String.format("#%06x", randomNumber);
    }
}
