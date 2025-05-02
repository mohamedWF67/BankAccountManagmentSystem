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
    private int accountNumber;
    private Card card;
    private double balance;
    private boolean isFrozen;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.isFrozen = false;
    }

    public void linkCard(Card card) {
        if (this.card != null) {
            System.out.println("Card already linked.");
        } else {
            this.card = card;
            System.out.println("Card linked successfully.");
        }
    }

    public boolean deposit(int pin, double amount) {
        if (!canTransact(pin, amount)) return false;
        balance += amount;
        System.out.printf("Deposited $%.2f. New Balance: $%.2f%n", amount, balance);
        return true;
    }

    public boolean withdraw(int pin, double amount) {
        if (!canTransact(pin, amount)) return false;
        if (balance >= amount) {
            balance -= amount;
            System.out.printf("Withdrawn $%.2f. New Balance: $%.2f%n", amount, balance);
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    public boolean transferTo(Account target, int pin, double amount) {
        if (withdraw(pin, amount)) {
            target.balance += amount;
            System.out.printf("Transferred $%.2f to account %d.%n", amount, target.accountNumber);
            return true;
        }
        return false;
    }

    private boolean canTransact(int pin, double amount) {
        if (isFrozen) {
            System.out.println("Account is frozen.");
            return false;
        }
        if (card == null || !card.checkPIN(pin)) {
            System.out.println("Invalid card or PIN.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return false;
        }
        return true;
    }

    public void freeze() {
        isFrozen = true;
        System.out.println("Account has been frozen.");
    }

    public void unfreeze() {
        isFrozen = false;
        System.out.println("Account has been unfrozen.");
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return String.format("Account #%d | Balance: $%.2f | Frozen: %s",
                             accountNumber, balance, isFrozen ? "Yes" : "No");
    }
}
