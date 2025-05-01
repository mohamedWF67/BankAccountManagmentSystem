/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccountmanagmentsystem;
/**
 *
 
 * @author abrar
 */


public class Account {
    private int CCN; // Customer Account Number
    private Card card;
    private double balance; 
    private boolean isFrozen;

    // Constructors
    public Account() {
        this.balance = 0.0;
        this.isFrozen = false;
    }

    public Account(double initialBalance) {
        this();
        this.balance = initialBalance;
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

    // Card Management
    public void addCard(Card card) {
        if (this.card != null) {
            System.out.println("Account already has a linked card");
            return;
        }
        this.card = card;
        System.out.println("Card successfully linked to account " + CCN);
    }

    // Account Operations - amount parameters as int, balance as double
    public boolean withdrawMoney(int pin, int amount) {
        if (isFrozen) {
            System.out.println("Account is frozen");
            return false;
        }

        if (card == null || !card.checkPIN(pin)) {
            System.out.println("Invalid card or PIN");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive");
            return false;
        }

        if (balance >= amount) {
            balance -= amount;
            System.out.printf("Withdrawn $%d from account %d. New balance: $%.2f%n", 
                            amount, CCN, balance);
            return true;
        } else {
            System.out.println("Insufficient funds");
            return false;
        }
    }

    public boolean depositMoney(int pin, int amount) {
        if (isFrozen) {
            System.out.println("Account is frozen");
            return false;
        }

        if (card == null || !card.checkPIN(pin)) {
            System.out.println("Invalid card or PIN");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Amount must be positive");
            return false;
        }

        balance += amount;
        System.out.printf("Deposited $%d to account %d. New balance: $%.2f%n", 
                         amount, CCN, balance);
        return true;
    }

    public boolean transferMoney(int pin, int amount, int targetAccount) {
        if (!withdrawMoney(pin, amount)) {
            return false;
        }
        System.out.printf("Transferred $%d from account %d to account %d%n", 
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