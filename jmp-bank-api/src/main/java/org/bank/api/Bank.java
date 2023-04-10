package org.bank.api;

import org.dto.dto.BankCard;
import org.dto.dto.BankCardType;
import org.dto.dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType type);

}
