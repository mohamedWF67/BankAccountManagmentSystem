/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccountmanagmentsystem;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author abrar
 */
public class Card implements Serializable {
    private int cardNum;
    private String cardHolderName;
    private int CVV;
    private int expiryMonth;
    private int expiryYear;
    private int cardPIN;
    private boolean isActive;

    // Constructor
    public Card(String cardHolderName,int Pin) {
        Random random = new Random();
        this.cardNum = 100000000 + random.nextInt(900000000); // 9-digit card number
        this.cardHolderName = cardHolderName;
        this.CVV = 100 + random.nextInt(900); // 3-digit CVV
        this.expiryMonth = 1 + random.nextInt(12); // Month between 1 and 12
        this.expiryYear = LocalDate.now().getYear() + 1 + random.nextInt(5); // Year between next year and next 5 years
        this.cardPIN = Pin; // 4-digit PIN
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

    public int getCardNum() {
        return cardNum;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public int getCVV() {
        return CVV;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public int getCardPIN() {
        return cardPIN;
    }

    public boolean isActive() {
        return isActive;
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

    public boolean isDeactivated() {
        return !isActive;
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
