package ua.fitness.model;

import ua.common.BaseEntity;
import ua.fitness.util.FitnessUtils;
import java.time.LocalDate;
import java.util.Objects;

public class WorkoutSession extends BaseEntity {
    private final Trainer trainer;
    private final MembershipPass pass;
    private final LocalDate sessionDate;
    private int durationMinutes;

    private WorkoutSession(Trainer trainer, MembershipPass pass, LocalDate sessionDate, int durationMinutes) {
        super();
        FitnessUtils.validateNotNull(trainer, "trainer");
        FitnessUtils.validateNotNull(pass, "pass");
        FitnessUtils.validateDateInRange(sessionDate, pass.getStartDate(), pass.getEndDate(), "sessionDate");

        this.trainer = trainer;
        this.pass = pass;
        this.sessionDate = sessionDate;
        setDurationMinutes(durationMinutes);
    }

    public static WorkoutSession of(Trainer trainer, MembershipPass pass, LocalDate sessionDate, int durationMinutes) {
        return new WorkoutSession(trainer, pass, sessionDate, durationMinutes);
    }

    public Trainer getTrainer() { return trainer; }
    public MembershipPass getPass() { return pass; }
    public LocalDate getSessionDate() { return sessionDate; }
    public int getDurationMinutes() { return durationMinutes; }

    public final void setDurationMinutes(int durationMinutes) {
        FitnessUtils.validateInRange(durationMinutes, 30, 180, "durationMinutes");
        this.durationMinutes = durationMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutSession session = (WorkoutSession) o;
        return Objects.equals(trainer, session.trainer) && Objects.equals(pass, session.pass) && Objects.equals(sessionDate, session.sessionDate);
    }

    @Override
    public int hashCode() { return Objects.hash(trainer, pass, sessionDate); }

    @Override
    public String toString() {
        return "WorkoutSession{trainer=" + trainer.getName() + ", passCode=" + pass.getPassCode() + ", sessionDate=" + sessionDate + ", durationMinutes=" + durationMinutes + ", createdAt=" + createdAt + "}";
    }
}