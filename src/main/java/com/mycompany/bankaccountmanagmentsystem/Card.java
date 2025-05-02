    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccountmanagmentsystem;
import java.time.LocalDate;
/**
 *
 * @author abrar
 */


public class Card {
    private int cardNumber;
    private String cardHolderName;
    private int cvv;
    private int expiryMonth;
    private int expiryYear;
    private int pin;
    private boolean isActive;

    public Card(int cardNumber, String cardHolderName, int cvv,
                int expiryMonth, int expiryYear, int pin) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.pin = pin;
        this.isActive = true;
    }

    public boolean checkPIN(int inputPIN) {
        return this.pin == inputPIN && isActive && !isExpired();
    }

    public boolean isExpired() {
        LocalDate now = LocalDate.now();
        LocalDate expiryDate = LocalDate.of(expiryYear, expiryMonth, 1)
                                        .plusMonths(1)
                                        .minusDays(1);
        return now.isAfter(expiryDate);
    }

    public void deactivate() {
        isActive = false;
        System.out.println("Card has been deactivated.");
    }

    public void activate() {
        isActive = true;
        System.out.println("Card has been activated.");
    }

    public String getCardInfo() {
        return String.format(
            "Card Number: **** **** **** %04d\nHolder: %s\nExpiry: %02d/%d",
            cardNumber % 10000, cardHolderName, expiryMonth, expiryYear
        );
    }
}
