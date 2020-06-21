package pl.gruzini.messenger.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Message {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm:ss");

    private final String text;
    private final LocalDateTime timestamp;
    private final String username;

    public String getTimestamp() {
        return timestamp.format(DATE_TIME_FORMATTER);
    }
}
