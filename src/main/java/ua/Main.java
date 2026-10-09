package ua;

import ua.fitness.model.Member;
import ua.fitness.model.Trainer;
import ua.fitness.model.MembershipPass;
import ua.fitness.model.WorkoutSession;
import ua.fitness.util.FitnessUtils;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));

        System.out.println("=== 1. Створення об'єкта конструктором та фабричним методом ===");
        Member member = new Member("1234567890", "Єгор Гуленкой", LocalDate.of(2000, 5, 15));
        Trainer trainer = new Trainer("TR-01", "Олександр Власенко", "yoga");
        
        MembershipPass pass = MembershipPass.of(
            "PASS-100", member, "premium",
            LocalDate.now().minusDays(5), LocalDate.now().plusDays(25), 1500.0
        );
        WorkoutSession session = WorkoutSession.of(trainer, pass, LocalDate.now(), 60);

        System.out.println("Член клубу: " + member);
        System.out.println("Абонемент: " + pass);

        System.out.println("\n=== 2. Нормалізація в дії ===");
        System.out.println("Введено 'yoga' -> Отримано спеціальність: " + trainer.getSpecialty());
        System.out.println("Введено 'premium' -> Отримано тип абонемента: " + pass.getType());

        System.out.println("\n=== 3. Три різні порушення правил валідації (try/catch) ===");
        try {
            new Member("999", "Іван", LocalDate.now().minusYears(10));
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 1 (Вік < 16): " + e.getMessage());
        }

        try {
            WorkoutSession.of(trainer, pass, LocalDate.now(), 200);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 2 (Тривалість > 180): " + e.getMessage());
        }

        try {
            new Trainer("TR-02", "  ", "GYM");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка 3 (Порожнє ім'я): " + e.getMessage());
        }

        System.out.println("\n=== 4. Спроба зіпсувати коректний об'єкт сеттером ===");
        try {
            pass.setPrice(-500.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Невдала зміна ціни: " + e.getMessage());
            System.out.println("Ціна залишилася: " + pass.getPrice());
        }

        System.out.println("\n=== 5. Порівняння об'єктів (==, equals, hashCode) ===");
        Member memberSameKey = new Member("1234567890", "Інше Ім'я", LocalDate.of(1995, 1, 1));
        Member memberDiffKey = new Member("0000000000", "Єгор Гуленкой", LocalDate.of(2000, 5, 15));

        System.out.println("member == memberSameKey: " + (member == memberSameKey));
        System.out.println("member.equals(memberSameKey): " + member.equals(memberSameKey));
        System.out.println("hashCode однакові: " + (member.hashCode() == memberSameKey.hashCode()));
        System.out.println("member.equals(memberDiffKey): " + member.equals(memberDiffKey));

        System.out.println("\n=== 6. Виклик обчислювальних методів ===");
        long days = FitnessUtils.passDurationDays(pass);
        double dailyPrice = FitnessUtils.pricePerDay(pass);
        System.out.println("Тривалість абонемента: " + days + " днів");
        System.out.println("Вартість за день: " + String.format("%.2f грн", dailyPrice));

        System.out.println("\n=== 7. Вивід усіх сутностей варіанта через toString() ===");
        System.out.println(member);
        System.out.println(trainer);
        System.out.println(pass);
        System.out.println(session);

        System.out.println("\n=== 8. Закоментований код з помилкою компіляції ===");
        // ValidationHelper.requireNotBlank("test", "field"); 
        // MembershipPass badPass = new MembershipPass(...);
    }
}