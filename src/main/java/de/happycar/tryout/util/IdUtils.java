package de.happycar.tryout.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * User: Ne0t0N
 * Date: 28.05.2016
 */
public final class IdUtils {

    private static final String LOWER_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CHARS = LOWER_CHARS.toUpperCase();
    private static final String DIGITS = "0123456789";

    private static final String ALPHABET = LOWER_CHARS + UPPER_CHARS + DIGITS;
    private static final int ALPHABET_LENGTH = ALPHABET.length();
    private static final int ID_LENGTH = 6;

    private IdUtils() {
    }

    public static String generateId() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ID_LENGTH; i++) {
            int index = ThreadLocalRandom.current().nextInt(ALPHABET_LENGTH);
            builder.append(ALPHABET.charAt(index));
        }
        return builder.toString();
    }

}
