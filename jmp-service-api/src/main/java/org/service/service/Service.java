package org.service.service;

import java.util.List;
import java.util.Optional;

import org.dto.dto.BankCard;
import org.dto.dto.Subscription;
import org.dto.dto.User;

public interface Service {

    void subscribe(BankCard card);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();
}
