package org.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankCard {
   private String number;
   private  User user;


}
