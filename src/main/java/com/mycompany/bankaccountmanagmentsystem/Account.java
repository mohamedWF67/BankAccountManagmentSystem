
package com.mycompany.bankaccountmanagmentsystem;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 
 * @author abrar
 */


public class Account {
    private int CCN; // Customer Account Number
    private Card card;
    private double balance; 
    private boolean isFrozen;

    
    public Account() {
        this.CCN = generateRandomCCN();
        this.balance = 0.0;
        this.isFrozen = false;
    }

    public Account(double initialBalance) {
        this();
        this.balance = initialBalance;
    }

    private int generateRandomCCN() {
        return UUID.randomUUID().toString().hashCode();
    }

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

    public boolean withdrawMoney(int pin, int amount) {
        return withdrawMoney(pin, amount, false);
    }

    // Account Operations - amount parameters as int, balance as double
    public boolean withdrawMoney(int pin, int amount,boolean internal) {
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
            System.out.printf("Withdrawn $%d from account %d. New balance: $%.2f%n", 
                            amount, CCN, balance);
            return true;
        } else {
            System.out.println("Insufficient funds");
            return false;
        }
    }

    public boolean depositMoney(int pin, int amount) {
        return depositMoney(pin, amount, false);
    }

    public boolean depositMoney(int pin, int amount,boolean internal) {
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
            System.out.printf("Deposited $%d to account %d. New balance: $%.2f%n",
                    amount, CCN, balance);
            if (!internal) {
                Transaction transaction = new Transaction(1,this,amount);
                DataHandler.addTransaction(transaction);
            }
            return true;
    }

    public boolean transferMoney(int pin, int amount, int targetAccount) {
        if (!withdrawMoney(pin, amount,true)) {
            return false;
        }
        if (!DataHandler.getAccount(targetAccount).depositMoney(pin, amount,true)) {
            return false;
        }
        Transaction transaction = new Transaction(3,this,DataHandler.getAccount(targetAccount),amount);
        DataHandler.addTransaction(transaction);
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