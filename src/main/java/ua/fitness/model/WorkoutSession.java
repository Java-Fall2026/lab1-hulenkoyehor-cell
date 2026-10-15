package ua.fitness.model;

import ua.fitness.util.ValidationHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record WorkoutSession(Trainer trainer, MembershipPass pass, LocalDate sessionDate, int durationMinutes, LocalDateTime createdAt) {

    public WorkoutSession {
        ValidationHelper.requireNotNull(trainer, "trainer");
        ValidationHelper.requireNotNull(pass, "pass");
        ValidationHelper.requireNotNull(sessionDate, "sessionDate");
        ValidationHelper.requireDateInRange(sessionDate, pass.getStartDate(), pass.getEndDate(), "sessionDate");
        ValidationHelper.requireInRange(durationMinutes, 30, 180, "durationMinutes");
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public static WorkoutSession of(Trainer trainer, MembershipPass pass, LocalDate sessionDate, int durationMinutes) {
        return new WorkoutSession(trainer, pass, sessionDate, durationMinutes, LocalDateTime.now());
    }
}