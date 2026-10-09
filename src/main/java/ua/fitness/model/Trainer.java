package ua.fitness.model;

import ua.common.BaseEntity;
import ua.fitness.util.FitnessUtils;
import java.util.Objects;

public class Trainer extends BaseEntity {
    private final String trainerId;
    private String name;
    private String specialty;

    public Trainer(String trainerId, String name, String specialty) {
        super();
        FitnessUtils.validateNotBlank(trainerId, "trainerId");
        this.trainerId = trainerId.trim();
        setName(name);
        setSpecialty(specialty);
    }

    public String getTrainerId() { return trainerId; }
    public String getName() { return name; }
    public final void setName(String name) {
        FitnessUtils.validateNotBlank(name, "name");
        this.name = name.trim();
    }

    public String getSpecialty() { return specialty; }
    public final void setSpecialty(String specialty) {
        this.specialty = FitnessUtils.normalizeAndValidate(specialty, FitnessUtils.ALLOWED_SPECIALTIES, "specialty");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(trainerId, trainer.trainerId);
    }

    @Override
    public int hashCode() { return Objects.hash(trainerId); }

    @Override
    public String toString() {
        return "Trainer{trainerId='" + trainerId + "', name='" + name + "', specialty='" + specialty + "', createdAt=" + createdAt + "}";
    }
}