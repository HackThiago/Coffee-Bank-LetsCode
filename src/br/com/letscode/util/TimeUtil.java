package br.com.letscode.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String now() {
        LocalDateTime now = LocalDateTime.now();
        return DEFAULT_FORMATTER.format(now);
    }
}
