package ua.fitness.util;

import ua.fitness.model.MembershipPass;
import ua.fitness.model.PassType;
import ua.fitness.model.Specialty;

import java.time.temporal.ChronoUnit;

public class FitnessUtils {

    private FitnessUtils() {
    }

    public static long passDurationDays(MembershipPass pass) {
        ValidationHelper.requireNotNull(pass, "pass");
        return ChronoUnit.DAYS.between(pass.getStartDate(), pass.getEndDate()) + 1;
    }

    public static double pricePerDay(MembershipPass pass) {
        ValidationHelper.requireNotNull(pass, "pass");
        long days = passDurationDays(pass);
        return pass.getPrice() / days;
    }

    public static String getPassDescription(PassType type) {
        ValidationHelper.requireNotNull(type, "type");
        return switch (type) {
            case STANDARD -> "Стандартний доступ до тренажерного залу";
            case STUDENT -> "Студентський абонемент зі знижкою";
            case PREMIUM -> "Преміум доступ включає басейн та групові заняття";
            case VIP -> "VIP доступ із персональним шафчиком та пріоритетом";
        };
    }

    public static double calculateTrainerBonusMultiplier(Specialty specialty) {
        ValidationHelper.requireNotNull(specialty, "specialty");
        return switch (specialty) {
            case YOGA, PILATES -> 1.15;
            case CROSSFIT -> 1.30;
            case GYM -> 1.05;
        };
    }
}