package ua.fitness.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

class ValidationHelper {
    private ValidationHelper() {}

    static void requireNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }
    }

    static void requireNotBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }

    static void requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be strictly greater than 0, got: " + value);
        }
    }

    static void requireInRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max + ", got: " + value);
        }
    }

    static void requireAtLeastAge(LocalDate birthDate, int minAge, String fieldName) {
        requireNotNull(birthDate, fieldName);
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < minAge) {
            throw new IllegalArgumentException(fieldName + " must indicate age at least " + minAge + " years, got age: " + age);
        }
    }

    static void requireDateAfter(LocalDate endDate, LocalDate startDate, String fieldName) {
        requireNotNull(startDate, "startDate");
        requireNotNull(endDate, fieldName);
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException(fieldName + " must be strictly after " + startDate + ", got: " + endDate);
        }
    }

    static void requireDateInRange(LocalDate date, LocalDate start, LocalDate end, String fieldName) {
        requireNotNull(date, fieldName);
        requireNotNull(start, "startDate");
        requireNotNull(end, "endDate");
        if (date.isBefore(start) || date.isAfter(end)) {
            throw new IllegalArgumentException(fieldName + " (" + date + ") must be within range [" + start + " .. " + end + "]");
        }
    }

    static void requireInList(String value, String[] allowedValues, String fieldName) {
        requireNotBlank(value, fieldName);
        boolean contains = Arrays.asList(allowedValues).contains(value);
        if (!contains) {
            throw new IllegalArgumentException(fieldName + " must be one of " + Arrays.toString(allowedValues) + ", got: " + value);
        }
    }
}