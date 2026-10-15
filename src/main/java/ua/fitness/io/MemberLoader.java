package ua.fitness.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.fitness.exception.InvalidDataException;
import ua.fitness.model.Member;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MemberLoader {

    private static final Logger log = LoggerFactory.getLogger(MemberLoader.class);

    private MemberLoader() {}

    public static List<Member> loadMembers(String filePath) {
        List<Member> members = new ArrayList<>();
        log.info("Starting CSV data loading from file: {}", filePath);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String header = reader.readLine(); // Пропускаємо заголовок
            if (header == null) {
                log.warn("File {} is completely empty", filePath);
                return members;
            }

            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    Member member = parseMemberLine(line, lineNumber);
                    members.add(member);
                } catch (InvalidDataException e) {
                    // Логуємо некоректний рядок і продовжуємо читання
                    log.warn("Skipping invalid line #{}: {}", lineNumber, e.getMessage());
                }
            }

            log.info("Successfully loaded {} valid members from {}", members.size(), filePath);

        } catch (FileNotFoundException e) {
            // Відсутній файл НЕ зупиняє програму
            log.error("File not found: {}. Returning empty list.", filePath, e);
        } catch (IOException e) {
            log.error("I/O error occurred while reading file: {}", filePath, e);
        } finally {
            // Демонстрація обов'язкового блоку finally
            if (reader != null) {
                try {
                    reader.close();
                    log.info("BufferedReader successfully closed in finally block");
                } catch (IOException e) {
                    log.error("Failed to close reader in finally block", e);
                }
            }
        }

        return members;
    }

    public static Member parseMemberLine(String line, int lineNumber) throws InvalidDataException {
        String[] parts = line.split(",");
        if (parts.length < 3) {
            throw new InvalidDataException("Line " + lineNumber + ": insufficient columns (expected 3, got " + parts.length + ")");
        }

        String taxId = parts[0].trim();
        String fullName = parts[1].trim();
        String birthDateStr = parts[2].trim();

        try {
            LocalDate birthDate = LocalDate.parse(birthDateStr);
            return new Member(taxId, fullName, birthDate);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            // Multi-catch: обробка помилок парсингу дати та некоректного віку з компактного конструктора
            throw new InvalidDataException("Line " + lineNumber + ": invalid data format or constraints - " + e.getMessage(), e);
        }
    }
}