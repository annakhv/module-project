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

        var userAna = new User("Anna", "Smith", LocalDate.of(1995, Month.JULY, 12));
        var userGiorgi = new User("Giorgi", "Scholz", LocalDate.of(2000, Month.JANUARY, 12));
        var userTom= new User("Tom", "Connor", LocalDate.of(2015, Month.JANUARY, 12));
        var bankImpl = loadBank();
        var creditCard = bankImpl.createBankCard(userAna, BankCardType.CREDIT);
        System.out.println(creditCard);
        var debitCard = bankImpl.createBankCard(userAna, BankCardType.DEBIT);
        System.out.println(debitCard);
        var creditCardTom=bankImpl.createBankCard(userTom,BankCardType.CREDIT);
        System.out.println(creditCardTom);
        var debitCardGiorgi = bankImpl.createBankCard(userGiorgi, BankCardType.DEBIT);
        System.out.println(debitCardGiorgi);
        var serviceImpl = loadService();
        serviceImpl.subscribe(creditCard);
        serviceImpl.subscribe(debitCard);
        serviceImpl.subscribe(debitCardGiorgi);
        serviceImpl.subscribe(creditCardTom);
        serviceImpl.subscribe(creditCard); //subscribe second time same card
        var subscription = serviceImpl.getSubscriptionByBankCardNumber(creditCard.getNumber())
                .orElseThrow(()->new NoSubscriptionException("no subscription for bank card number: "+creditCard.getNumber()));
        System.out.println(subscription);
        var subscription2 = serviceImpl.getSubscriptionByBankCardNumber(debitCard.getNumber())
                .orElseThrow(()->new NoSubscriptionException("no subscription for bank card number: "+debitCard.getNumber()));
        System.out.println(subscription2);
        var subscription3=serviceImpl.getSubscriptionByBankCardNumber(debitCardGiorgi.getNumber())
                .orElseThrow(()->new NoSubscriptionException("no subscription for bank card number: "+debitCard.getNumber()));
        System.out.println(subscription3);
        var subscription4=serviceImpl.getSubscriptionByBankCardNumber(creditCardTom.getNumber())
                .orElseThrow(()->new NoSubscriptionException("no subscription for bank card number: "+creditCardTom.getNumber()));
        System.out.println(subscription4);
        List<User> users = serviceImpl.getAllUsers();
        System.out.println("users are "+users);
        var averegeAge=serviceImpl.getAverageUsersAge();
        System.out.println("averege users age is "+ averegeAge);
        var subsByCondition=serviceImpl.getAllSubscriptionsByCondition(subs->subs.getStartDate().isAfter(LocalDate.now().minusDays(1)));
        System.out.println("subscription by condition that it is subscribed after yesterday is "+subsByCondition);
        var isPayable=Service.isPayableUser(userAna);
        System.out.println("is ana payable user "+isPayable);
        var isTomPayable=Service.isPayableUser(userTom);
        System.out.println("is tom payable user "+isTomPayable);
    }

    private static Bank loadBank() {
        return ServiceLoader.load(Bank.class)
                .findFirst()
                .orElseThrow(() -> new NoImplementationFoundException("no implementation found for interface(abstract class) :" + Bank.class));
    }

    private static Service loadService() {
        return ServiceLoader.load(Service.class)
                .findFirst()
                .orElseThrow(() -> new NoImplementationFoundException("no implementation found for interface(abstract class) :" + Service.class));
    }
}