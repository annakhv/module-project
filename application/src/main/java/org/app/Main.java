package org.app;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ServiceLoader;

import org.bank.api.Bank;
import org.dto.dto.BankCardType;
import org.dto.dto.User;
import org.service.service.Service;

public class Main {
    public static void main(String[] args) {

        var userAna = new User("Anna", "Smith", LocalDate.of(1999, Month.APRIL, 12));
        var bankImpl = ServiceLoader.load(Bank.class)
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
        var creditCard = bankImpl.createBankCard(userAna, BankCardType.CREDIT);
        var debitCard = bankImpl.createBankCard(userAna, BankCardType.DEBIT);
        var serviceImpl = ServiceLoader.load(Service.class)
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
        serviceImpl.subscribe(creditCard);
        serviceImpl.subscribe(debitCard);
        var subscription = serviceImpl.getSubscriptionByBankCardNumber(creditCard.getNumber());
        System.out.println(subscription);
        var subscription2 = serviceImpl.getSubscriptionByBankCardNumber(debitCard.getNumber());
        System.out.println(subscription2);
        List<User> users = serviceImpl.getAllUsers();
        System.out.println(users);
    }
}