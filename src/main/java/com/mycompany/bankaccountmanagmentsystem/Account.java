/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccountmanagmentsystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 *

 * @author abrar
 */


public class Account implements Serializable {
    private int CCN; // Customer Account Number
    private Card card;
    private double balance;
    private boolean isFrozen;

    // Constructors
    public Account() {
        this.CCN = generateRandomCCN();
        this.balance = 0.0;
        this.isFrozen = false;
    }

    public Account(int pin,double initialBalance) {
        this();
        this.balance = initialBalance;
        addCard(new Card("Waleed Farouk Mahdy",pin));
    }

    private int generateRandomCCN() {
        return UUID.randomUUID().toString().hashCode();
    }

    // Getters and Setters
    public int getCCN() {
        return CCN;
    }

    public void setCCN(int CCN) {
        this.CCN = CCN;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Card getCard() {
        return card;
    }
    public ArrayList<Card> getLinkedCards() {
        ArrayList<Card> cards = new ArrayList<>();
        if (this.card != null) {
            cards.add(this.card);
        }
        return cards;
    }

    // Card Management
    public void addCard(Card card) {
        if (this.card != null) {
            System.out.println("Account already has a linked card");
            return;
        }
        this.card = card;
        System.out.println("Card successfully linked to account " + CCN);
    }

    public boolean withdrawMoney(int pin, double amount) {
        return withdrawMoney(pin, amount, false);
    }

    // Account Operations - amount parameters as int, balance as double
    public boolean withdrawMoney(int pin, double amount,boolean internal) {
        if (isFrozen) {
            System.out.println("Account is frozen");
            return false;
        }

        if (card == null || !card.checkPIN(pin)) {
            System.out.println("Invalid card or PIN");
            return false;
        }

        if (card.isExpired() || card.isDeactivated()) {
            System.out.println("Card deactivated or expired");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive");
            return false;
        }

        if (balance >= amount) {
            balance -= amount;
            if (!internal) {
                Transaction transaction = new Transaction(2,this,amount);
                DataHandler.addTransaction(transaction);
            }
            System.out.printf("Withdrawn $%f from account %d. New balance: $%.2f%n",
                    amount, CCN, balance);
            return true;
        } else {
            System.out.println("Insufficient funds");
            return false;
        }
    }

    public boolean depositMoney(int pin, double amount) {
        return depositMoney(pin, amount, false);
    }

    public boolean depositMoney(int pin, double amount,boolean internal) {
        if (isFrozen) {
            System.out.println("Account is frozen");
            return false;
        }

        if (card == null || !card.checkPIN(pin)) {
            System.out.println("Invalid card or PIN");
            return false;
        }

        if (card.isExpired() || card.isDeactivated()) {
            System.out.println("Card deactivated or expired");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive");
            return false;
        }

        balance += amount;
        System.out.printf("Deposited $%f to account %d. New balance: $%.2f%n",
                amount, CCN, balance);
        if (!internal) {
            Transaction transaction = new Transaction(1,this,amount);
            DataHandler.addTransaction(transaction);
        }
        return true;
    }

    public boolean depositMoneyToAccount(double amount,boolean internal) {
        if (isFrozen) {
            System.out.println("Account is frozen");
            return false;
        }

        if (card == null) {
            System.out.println("Invalid card");
            return false;
        }

        if (card.isExpired() || card.isDeactivated()) {
            System.out.println("Card deactivated or expired");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive");
            return false;
        }

        balance += amount;
        System.out.printf("Deposited $%f to account %d. New balance: $%.2f%n",
                amount, CCN, balance);
        if (!internal) {
            Transaction transaction = new Transaction(1,this,amount);
            DataHandler.addTransaction(transaction);
        }
        return true;
    }

    public boolean transferMoney(int pin, double amount, int targetAccount) {
        Account target = DataHandler.getAccount(targetAccount);
        if (target == null) {
            System.out.println("Account not found");
            return false;
        }
        if (!withdrawMoney(pin, amount,true)) {
            return false;
        }
        if (!target.depositMoneyToAccount( amount,true)) {
            return false;
        }
        Transaction transaction = new Transaction(3,this,DataHandler.getAccount(targetAccount),amount);
        DataHandler.addTransaction(transaction);
        System.out.printf("Transferred $%f from account %d to account %d%n",
                amount, CCN, targetAccount);
        return true;
    }

    public void freezeAccount() {
        isFrozen = true;
        System.out.println("Account " + CCN + " has been frozen");
    }

    public void unfreezeAccount() {
        isFrozen = false;
        System.out.println("Account " + CCN + " has been unfrozen");
    }

    public boolean checkBalance(int pin, double expectedBalance) {
        if (card == null || !card.checkPIN(pin)) {
            System.out.println("Invalid card or PIN");
            return false;
        }
        boolean matches = Math.abs(balance - expectedBalance) < 0.01;
        System.out.printf("Balance %s: Actual $%.2f vs Expected $%.2f%n",
                matches ? "matches" : "doesn't match", balance, expectedBalance);
        return matches;
    }

    public boolean confirmationMessage() {
        System.out.println("Transaction confirmed for account " + CCN);
        return true;
    }

    @Override
    public String toString() {
        return String.format("Account[CCN:%d, Balance:$%.2f, Frozen:%s, Card:%s]",
                CCN, balance, isFrozen, (card != null ? "Attached" : "None"));
    }

    boolean isFrozen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void deposit(double amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void withdraw(double amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}