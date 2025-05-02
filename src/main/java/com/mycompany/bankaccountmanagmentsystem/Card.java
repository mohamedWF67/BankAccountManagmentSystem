package com.mycompany.bankaccountmanagmentsystem;


import java.time.LocalDate;

public class Card {
    private int cardNum;
    private String cardHolderName;
    private int CVV;
    private int expiryMonth;
    private int expiryYear;
    private int cardPIN;

    public Card() {
    }

    public String getCardInfo() {
        return "Card Holder: " + cardHolderName + ", Card Number: " + cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public void setCardHolderName(String name) {
        this.cardHolderName = name;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public void setExpiryMonth(int month) {
        this.expiryMonth = month;
    }

    public void setExpiryYear(int year) {
        this.expiryYear = year;
    }

    public void setCardPIN(int pin) {
        this.cardPIN = pin;
    }

    public boolean validateCard(int cardNum, int pin, int CVV, int month, int year) {
        if (this.cardNum == cardNum &&
            this.cardPIN == pin &&
            this.CVV == CVV &&
            this.expiryMonth == month &&
            this.expiryYear == year) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPIN(int inputPIN) {
        if (this.cardPIN == inputPIN) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExpired() {
        LocalDate today = LocalDate.now();
        if (expiryYear < today.getYear()) {
            return true;
        } else if (expiryYear == today.getYear() && expiryMonth < today.getMonthValue()) {
            return true;
        } else {
            return false;
        }
    }

    public void deactivateCard(Card card) {
        card.cardNum = 0;
        card.cardHolderName = "";
        card.CVV = 0;
        card.expiryMonth = 0;
        card.expiryYear = 0;
        card.cardPIN = 0;
    }
}