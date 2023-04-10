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

    private static Map<String, User> storage = new HashMap<>();
    private static final int CARD_NUMBER_LENGTH = 15;

    @Override
    public BankCard createBankCard(final User user, final BankCardType type) {
        var cardNumber = generateNumber();
        storage.put(cardNumber,user);
        return switch (type) {
            case CREDIT -> new CreditBankCard(cardNumber,user);
            case DEBIT -> new DebitBankCard(cardNumber,user);
        };
    }

    private String generateNumber() {
        var cardNumber = generate();
        System.out.println("cardNumber is" + cardNumber);
        while (true) {
            if (storage.containsKey(cardNumber)) { //checks that card number is not duplicated
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
        var cardNumber = new StringBuilder();
        while (cardNumber.length() < CARD_NUMBER_LENGTH) {
            cardNumber.append(randomIterator.nextInt());
        }
        return cardNumber.toString();
    }
}

