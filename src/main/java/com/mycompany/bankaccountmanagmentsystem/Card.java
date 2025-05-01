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
    private int cardNum;
    private String cardHolderName;
    private int CVV;
    private int expiryMonth;
    private int expiryYear;
    private int cardPIN;
    private boolean isActive;

    // Constructor
    public Card(int cardNum, String cardHolderName, int CVV, 
                int expiryMonth, int expiryYear, int cardPIN) {
        this.cardNum = cardNum;
        this.cardHolderName = cardHolderName;
        this.CVV = CVV;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cardPIN = cardPIN;
        this.isActive = true;
    }

    // Getters
    public String getCardInfo() {
        return String.format(
            "Card Number: %d\nHolder: %s\nExpiry: %02d/%d\nCVV: %03d",
            cardNum, cardHolderName, expiryMonth, expiryYear, CVV
        );
    }

    // Setters
    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public void setCardPIN(int cardPIN) {
        this.cardPIN = cardPIN;
    }

    // Card Operations
    public boolean validateCard(int cardNum, int CVV, int expiryMonth, int expiryYear, int PIN) {
        return this.cardNum == cardNum &&
               this.CVV == CVV &&
               this.expiryMonth == expiryMonth &&
               this.expiryYear == expiryYear &&
               this.cardPIN == PIN &&
               isActive &&
               !isExpired();
    }

    public boolean checkPIN(int PIN) {
        return this.cardPIN == PIN && isActive && !isExpired();
    }

    public boolean isExpired() {
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = LocalDate.of(expiryYear, expiryMonth, 1);
        return currentDate.isAfter(expiryDate);
    }

    public void deactivateCard() {
        isActive = false;
        System.out.println("Card has been deactivated");
    }

    public void activateCard() {
        isActive = true;
        System.out.println("Card has been activated");
    }
}
