package ua;

import ua.fitness.model.Member;
import ua.fitness.model.PassType;
import ua.fitness.model.Specialty;
import ua.fitness.model.Trainer;
import ua.fitness.util.FitnessUtils;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));

        System.out.println("=== 1. Створення record-об'єкта та звернення до компонентів ===");
        Member member = new Member("1234567890", "Єгор Гуленкой", LocalDate.of(2000, 5, 15));
        Trainer trainer = new Trainer("TR-01", "Олександр Власенко", Specialty.YOGA);
        System.out.println("ПІБ члена (через аксесор record): " + member.fullName());
        System.out.println("ІПН члена (через аксесор record): " + member.taxId());
        System.out.println("Спеціальність тренера (через аксесор record): " + trainer.specialty());

        System.out.println("\n=== 2. Enum у роботі: values(), власне поле та valueOf з try/catch ===");
        System.out.println("Перелік усіх типів абонементів (values() та getPriceFactor()):");
        for (PassType type : PassType.values()) {
            System.out.println(" - " + type + " (коефіцієнт ціни: " + type.getPriceFactor() + ")");
        }

        try {
            System.out.println("\nСпроба зчитати неіснуючий enum через valueOf('UNKNOWN'):");
            System.out.println(PassType.valueOf("UNKNOWN"));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено виняток для невідомого enum: " + e.getMessage());
        }

        System.out.println("\n=== 3. Демонстрація обох switch-виразів ===");
        PassType passType = PassType.PREMIUM;
        String passDesc = FitnessUtils.getPassDescription(passType);
        System.out.println("Switch 1 (по PassType): " + passType + " -> " + passDesc);

        Specialty specialty = Specialty.CROSSFIT;
        double bonus = FitnessUtils.calculateTrainerBonusMultiplier(specialty);
        System.out.println("Switch 2 (по Specialty): " + specialty + " -> коефіцієнт бонусу: " + bonus);

        System.out.println("\n=== 4. Record із невалідними даними (компактний конструктор) ===");
        try {
            new Member("999", "Іван", LocalDate.now().minusYears(10));
        } catch (IllegalArgumentException e) {
            System.out.println("Перехоплено виняток з компактного конструктора: " + e.getMessage());
        }

        System.out.println("\n=== 5. Порівняння двох однакових record-об'єктів (equals та hashCode) ===");
        Member member1 = new Member("1234567890", "Єгор Гуленкой", LocalDate.of(2000, 5, 15), member.createdAt());
        Member member2 = new Member("1234567890", "Єгор Гуленкой", LocalDate.of(2000, 5, 15), member.createdAt());

        System.out.println("member1.equals(member2): " + member1.equals(member2));
        System.out.println("hashCode однакові: " + (member1.hashCode() == member2.hashCode()));

        System.out.println("\n=== 6. Вивід record через згенерований toString() ===");
        System.out.println("Record Member toString(): " + member);
        System.out.println("Record Trainer toString(): " + trainer);
    }
}