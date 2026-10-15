package ua.fitness.util;

import java.time.LocalDate;
import java.time.Period;

public class ValidationHelper {

    private ValidationHelper() {}

    public static void requireNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }
    }

    public static void requireNotBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
    }

    public static void requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be strictly positive, got: " + value);
        }
    }

    public static void requireInRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(fieldName + " must be between " + min + " and " + max + ", got: " + value);
        }
    }

    public static void requireMinAge(LocalDate birthDate, int minAge, String fieldName) {
        requireNotNull(birthDate, fieldName);
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < minAge) {
            throw new IllegalArgumentException(fieldName + " must indicate age at least " + minAge + " years, got age: " + age);
        }
    }

    public static void requireStrictlyAfter(LocalDate endDate, LocalDate startDate, String fieldName) {
        requireNotNull(startDate, "startDate");
        requireNotNull(endDate, fieldName);
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException(fieldName + " must be strictly after " + startDate + ", got: " + endDate);
        }
    }

    public static void requireDateInRange(LocalDate date, LocalDate start, LocalDate end, String fieldName) {
        requireNotNull(date, fieldName);
        requireNotNull(start, "startDate");
        requireNotNull(end, "endDate");
        if (date.isBefore(start) || date.isAfter(end)) {
            throw new IllegalArgumentException(fieldName + " (" + date + ") must be within range [" + start + " .. " + end + "]");
        }
    }
}