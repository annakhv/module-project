package org.cloud.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bank.api.Bank;
import org.dto.dto.BankCard;
import org.dto.dto.BankCardType;
import org.dto.dto.CreditBankCard;
import org.dto.dto.DebitBankCard;
import org.dto.dto.User;

public class CloudBank implements Bank {

    public static Map<User, String> storage = new HashMap<>();

    @Override
    public BankCard createBankCard(final User user, final BankCardType type) {
        return switch (type) {

            case CREDIT -> new CreditBankCard(generateNumber(),user);
            case DEBIT -> new DebitBankCard(generateNumber(),user);
        };
    }

    private String generateNumber() {
        String cardNumber = generate();
        System.out.println("cardNumber is" + cardNumber);
        while (true) {
            if (storage.containsValue(cardNumber)) {
                cardNumber = generate();
            } else {
                return cardNumber;
            }
        }
    }

    private String generate() {
        var random = new Random();
        var randomIterator = random.ints(0, 10)
                .iterator();
        StringBuilder cardNumber = new StringBuilder();
        while (cardNumber.length() < 10) {
            cardNumber.append(randomIterator.nextInt());
        }
        return cardNumber.toString();
    }
}

