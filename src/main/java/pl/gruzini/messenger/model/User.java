package pl.gruzini.messenger.model;

import lombok.Data;

import java.util.Random;

@Data
public class User {
    private final String name;
    private final String colorCode;

    public User(String name) {
        this.name = name;
        final Random random = new Random(this.name.hashCode());
        final int randomNumber = random.nextInt(0xffffff + 1);
        this.colorCode = String.format("#%06x", randomNumber);
    }
}
