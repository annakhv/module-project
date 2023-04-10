package org.service.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.dto.dto.BankCard;
import org.dto.dto.Subscription;
import org.dto.dto.User;

public interface Service {

    void subscribe(BankCard card);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> subscriptionPredicate);

    default double getAverageUsersAge() {
        return getAllUsers().stream()
                .map(user -> calculateAge(user.getBirthday()))
                .mapToLong(Long::longValue)
                .average()
                .orElse(0.0);
    }

    static boolean isPayableUser(User user) {
        return calculateAge(user.getBirthday()) >= 18l;
    }

    private static long calculateAge(LocalDate date) {
        return ChronoUnit.YEARS.between(date, LocalDate.now());
    }
}
