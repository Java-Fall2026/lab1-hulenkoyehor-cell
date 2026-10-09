package ua.fitness.util;

import java.util.Locale;

class FormatHelper {
    private FormatHelper() {}

    static String normalize(String value) {
        return value == null ? "" : value.trim().toUpperCase(Locale.ROOT);
    }
}