package org.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankCard {
   private String number;
   private  User user;


   public boolean isCreditCard() {
        if (this instanceof CreditBankCard) {
            return true;
        }
        return false;
    }


    public boolean isDebitCard() {
        if (this instanceof DebitBankCard) {
            return true;
        }
        return false;
    }


}
