/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccountmanagmentsystem;

import java.util.Date;

/**
 *
 * @author abrar
 */
  

public class Transaction {
    // Fields
    private int transactionID;
    private int transactionType; // 1 = Deposit, 2 = Withdrawal, 3 = Transfer
    private Account senderAccount;
    private Account receiverAccount;
    private int amount;
    private Date timestamp;

    // Constructor for Transfer (with receiver account)
    public Transaction(int transactionID, int transactionType, int amount, Account receiverAccount) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.receiverAccount = receiverAccount;
        this.timestamp = new Date();
    }

    // Constructor for Deposit or Withdrawal (no receiver)
    public Transaction(int transactionID, int transactionType, int amount) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = new Date();
    }

    // Getters
    public int getTransactionID() {
        return transactionID;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public int getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    // Set sender account if needed
    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    // Methods
    public String getTransactionDetails() {
        return "Transaction ID: " + transactionID +
               "\nType: " + getTypeName() +
               "\nAmount: " + amount +
               "\nTimestamp: " + timestamp;
    }

    public boolean withdrawMoney(double amount) {
        if (senderAccount != null && senderAccount.getBalance() >= amount) {
            senderAccount.withdraw(amount);
            System.out.println("Withdrawn $" + amount + " from account.");
            return true;
        }
        System.out.println("Withdrawal failed: insufficient balance.");
        return false;
    }

    public boolean depositMoney(double amount) {
        if (senderAccount != null) {
            senderAccount.deposit(amount);
            System.out.println("Deposited $" + amount + " to account.");
            return true;
        }
        System.out.println("Deposit failed: sender account not set.");
        return false;
    }

    public boolean transferMoney(double amount, Account receiverAccount) {
        if (senderAccount != null && receiverAccount != null && senderAccount.getBalance() >= amount) {
            senderAccount.withdraw(amount);
            receiverAccount.deposit(amount);
            System.out.println("Transferred $" + amount + " to receiver account.");
            return true;
        }
        System.out.println("Transfer failed.");
        return false;
    }

    public boolean generateReport() {
        System.out.println("==== Transaction Report ====");
        System.out.println(getTransactionDetails());
        return true;
    }

    private String getTypeName() {
        return switch (transactionType) {
            case 1 -> "Deposit";
            case 2 -> "Withdrawal";
            case 3 -> "Transfer";
            default -> "Unknown";
        };
    }
}
