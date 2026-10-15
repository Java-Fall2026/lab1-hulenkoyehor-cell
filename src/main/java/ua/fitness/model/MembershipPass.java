package ua.fitness.model;

import ua.fitness.util.ValidationHelper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MembershipPass {

    private final String passCode;
    private final Member member;
    private final PassType type;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private double price;
    private final LocalDateTime createdAt;

    private MembershipPass(String passCode, Member member, PassType type, LocalDate startDate, LocalDate endDate, double price, LocalDateTime createdAt) {
        ValidationHelper.requireNotBlank(passCode, "passCode");
        ValidationHelper.requireNotNull(member, "member");
        ValidationHelper.requireNotNull(type, "type");
        ValidationHelper.requireNotNull(startDate, "startDate");
        ValidationHelper.requireNotNull(endDate, "endDate");
        ValidationHelper.requireStrictlyAfter(endDate, startDate, "endDate");
        ValidationHelper.requirePositive(price, "price");

        this.passCode = passCode;
        this.member = member;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
    }

    public static MembershipPass of(String passCode, Member member, PassType type, LocalDate startDate, LocalDate endDate, double price) {
        return new MembershipPass(passCode, member, type, startDate, endDate, price, LocalDateTime.now());
    }

    public String getPassCode() {
        return passCode;
    }

    public Member getMember() {
        return member;
    }

    public PassType getType() {
        return type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        ValidationHelper.requirePositive(price, "price");
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "MembershipPass{" +
                "passCode='" + passCode + '\'' +
                ", member=" + member.fullName() +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}