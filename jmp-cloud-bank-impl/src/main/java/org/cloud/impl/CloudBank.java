package org.cloud.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.BiFunction;

import org.bank.api.Bank;
import org.dto.dto.BankCard;
import org.dto.dto.BankCardType;
import org.dto.dto.CreditBankCard;
import org.dto.dto.DebitBankCard;
import org.dto.dto.User;

public class CloudBank implements Bank {

    private Map<String, User> storage;  //each user can have several cards
    private Map<BankCardType, BiFunction<String, User, BankCard>> bankCards;

    {
        storage = new HashMap<>();
        bankCards = new HashMap<>();
        bankCards.put(BankCardType.CREDIT, CreditBankCard::new);
        bankCards.put(BankCardType.DEBIT, DebitBankCard::new);
    }

    private static final int CARD_NUMBER_LENGTH = 15;

    @Override
    public BankCard createBankCard(final User user, final BankCardType type) {
        var cardNumber = generateNumber();
        var bankCard = bankCards.getOrDefault(type, DebitBankCard::new).apply(cardNumber, user);
        storage.put(bankCard.getNumber(), user);
        return bankCard;
    }

    private String generateNumber() {
        var cardNumber = generate();
        while (true) {
            if (storage.containsKey(cardNumber)) { //checks that card number is not duplicated and thus replacing existing one
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

