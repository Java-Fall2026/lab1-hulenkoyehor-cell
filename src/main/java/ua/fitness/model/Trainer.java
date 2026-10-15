package ua.fitness.model;

import ua.fitness.util.ValidationHelper;

import java.time.LocalDateTime;

public record Trainer(String trainerId, String name, Specialty specialty, LocalDateTime createdAt) {

    public Trainer {
        ValidationHelper.requireNotBlank(trainerId, "trainerId");
        ValidationHelper.requireNotBlank(name, "name");
        ValidationHelper.requireNotNull(specialty, "specialty");
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Trainer(String trainerId, String name, Specialty specialty) {
        this(trainerId, name, specialty, LocalDateTime.now());
    }
}