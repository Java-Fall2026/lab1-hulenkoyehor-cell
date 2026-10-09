package ua.fitness.util;

import ua.fitness.model.MembershipPass;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FitnessUtils {
    public static final String[] ALLOWED_SPECIALTIES = {"YOGA", "CROSSFIT", "PILATES", "GYM"};
    public static final String[] ALLOWED_PASS_TYPES = {"STANDARD", "PREMIUM", "VIP", "STUDENT"};

    private FitnessUtils() {}

    public static void validateNotNull(Object obj, String fieldName) { ValidationHelper.requireNotNull(obj, fieldName); }
    public static void validateNotBlank(String value, String fieldName) { ValidationHelper.requireNotBlank(value, fieldName); }
    public static void validatePositive(double value, String fieldName) { ValidationHelper.requirePositive(value, fieldName); }
    public static void validateInRange(int value, int min, int max, String fieldName) { ValidationHelper.requireInRange(value, min, max, fieldName); }
    public static void validateAtLeastAge(LocalDate birthDate, int minAge, String fieldName) { ValidationHelper.requireAtLeastAge(birthDate, minAge, fieldName); }
    public static void validateDateAfter(LocalDate endDate, LocalDate startDate, String fieldName) { ValidationHelper.requireDateAfter(endDate, startDate, fieldName); }
    public static void validateDateInRange(LocalDate date, LocalDate start, LocalDate end, String fieldName) { ValidationHelper.requireDateInRange(date, start, end, fieldName); }

    public static String normalizeAndValidate(String value, String[] allowedValues, String fieldName) {
        String normalized = FormatHelper.normalize(value);
        ValidationHelper.requireInList(normalized, allowedValues, fieldName);
        return normalized;
    }

    public static long passDurationDays(MembershipPass pass) {
        validateNotNull(pass, "pass");
        return ChronoUnit.DAYS.between(pass.getStartDate(), pass.getEndDate()) + 1;
    }

    public static double pricePerDay(MembershipPass pass) {
        long days = passDurationDays(pass);
        return pass.getPrice() / days;
    }
}