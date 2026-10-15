package ua.fitness.model;

import ua.fitness.util.ValidationHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Immutable domain record representing a gym member.
 */
public record Member(String taxId, String fullName, LocalDate birthDate, LocalDateTime createdAt) {

    public Member {
        ValidationHelper.requireNotBlank(taxId, "taxId");
        ValidationHelper.requireNotBlank(fullName, "fullName");
        ValidationHelper.requireMinAge(birthDate, 16, "birthDate");
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public Member(String taxId, String fullName, LocalDate birthDate) {
        this(taxId, fullName, birthDate, LocalDateTime.now());
    }
}