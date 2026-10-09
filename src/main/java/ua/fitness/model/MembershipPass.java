package ua.fitness.model;

import ua.common.BaseEntity;
import ua.fitness.util.FitnessUtils;
import java.time.LocalDate;
import java.util.Objects;

public class MembershipPass extends BaseEntity {
    private final String passCode;
    private final Member member;
    private String type;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private double price;

    private MembershipPass(String passCode, Member member, String type, LocalDate startDate, LocalDate endDate, double price) {
        super();
        FitnessUtils.validateNotBlank(passCode, "passCode");
        FitnessUtils.validateNotNull(member, "member");
        FitnessUtils.validateNotNull(startDate, "startDate");
        FitnessUtils.validateDateAfter(endDate, startDate, "endDate");

        this.passCode = passCode.trim();
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
        setType(type);
        setPrice(price);
    }

    public static MembershipPass of(String passCode, Member member, String type, LocalDate startDate, LocalDate endDate, double price) {
        return new MembershipPass(passCode, member, type, startDate, endDate, price);
    }

    public String getPassCode() { return passCode; }
    public Member getMember() { return member; }
    public String getType() { return type; }
    public final void setType(String type) {
        this.type = FitnessUtils.normalizeAndValidate(type, FitnessUtils.ALLOWED_PASS_TYPES, "type");
    }

    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getPrice() { return price; }
    public final void setPrice(double price) {
        FitnessUtils.validatePositive(price, "price");
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MembershipPass that = (MembershipPass) o;
        return Objects.equals(passCode, that.passCode);
    }

    @Override
    public int hashCode() { return Objects.hash(passCode); }

    @Override
    public String toString() {
        return "MembershipPass{passCode='" + passCode + "', member=" + member.getFullName() + ", type='" + type + "', startDate=" + startDate + ", endDate=" + endDate + ", price=" + price + ", createdAt=" + createdAt + "}";
    }
}