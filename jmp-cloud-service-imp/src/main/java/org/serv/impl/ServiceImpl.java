package org.serv.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.dto.dto.BankCard;
import org.dto.dto.Subscription;
import org.dto.dto.User;
import org.service.service.Service;

public class ServiceImpl implements Service {

    private static Map<Subscription, User> subscriptionMap = new HashMap<>();

    @Override
    public void subscribe(final BankCard card) {
        var subscription = new Subscription(card.getNumber(), LocalDate.now());
        subscriptionMap.put(subscription,card.getUser());
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(final String cardNumber) {
        return subscriptionMap.keySet()
                .stream()
                .filter(subs -> subs.getBankCard().equals(cardNumber))
                .findAny();
    }

    @Override
    public List<User> getAllUsers() {
        return subscriptionMap.values()
                .stream()
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(final Predicate<Subscription> subscriptionPredicate) {
        return subscriptionMap.keySet()
                .stream()
                .filter(subscriptionPredicate)
                .collect(Collectors.toUnmodifiableList());
    }
}
