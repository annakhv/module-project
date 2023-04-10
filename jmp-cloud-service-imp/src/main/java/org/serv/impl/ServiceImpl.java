package org.serv.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.dto.dto.BankCard;
import org.dto.dto.Subscription;
import org.dto.dto.User;
import org.service.service.Service;

public class ServiceImpl implements Service {

    private static Map<User, Subscription> subscriptionMap = new HashMap<>();

    @Override
    public void subscribe(final BankCard card) {
        var subscription = new Subscription(card.getNumber(), LocalDate.now());
        subscriptionMap.put(card.getUser(), subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(final String cardNumber) {
        return subscriptionMap.values()
                .stream()
                .filter(subs -> subs.getBankCard().equals(cardNumber))
                .findAny();
    }

    @Override
    public List<User> getAllUsers() {
        return subscriptionMap.keySet().stream().distinct().toList();
    }
}
