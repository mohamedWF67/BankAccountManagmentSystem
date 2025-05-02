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
    public static final int DEPOSIT = 1;
    public static final int WITHDRAW = 2;
    public static final int TRANSFER = 3;

    private static final double DAILY_DEPOSIT_LIMIT = 10000;
    private static final double DAILY_WITHDRAW_LIMIT = 5000;
    private static final double DAILY_TRANSFER_LIMIT = 25000;
    private static final double MIN_BALANCE = 100;

    private int id;
    private int type;
    private Account sender;
    private Account receiver;
    private double amount;
    private Date date;
    private String status;
    private String message;

    // Getters (placed first)
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
    
    public double getAmount() { 
        return amount; 
    }
    
    public Date getTimestamp() { 
        Date timestamp = null;
        zzzzzzreturn timestamp; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public String getReasonCode() { 
        return reasonCode; 
    }

    // Constructors
    public Transaction(int transactionID, int transactionType, Account senderAccount) {
        this(transactionID, transactionType, senderAccount, null, 0.0);
    }

    public Transaction(int transactionID, int transactionType, Account senderAccount, 
                      Account receiverAccount, double amount) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.date = new Date();
        this.status = "PENDING";
    }

    public boolean process(int pin) {
        switch (type) {
            case DEPOSIT -> {
                if (amount > DAILY_DEPOSIT_LIMIT) return fail("Deposit limit exceeded");
                if (sender.deposit(pin, amount)) return success("Deposit successful");
            }
            case WITHDRAW -> {
                if (amount > DAILY_WITHDRAW_LIMIT) return fail("Withdraw limit exceeded");
                if (sender.getBalance() - amount < MIN_BALANCE) return fail("Below minimum balance");
                if (sender.withdraw(pin, amount)) return success("Withdrawal successful");
            }
            case TRANSFER -> {
                if (receiver == null) return fail("Receiver not specified");
                if (amount > DAILY_TRANSFER_LIMIT) return fail("Transfer limit exceeded");
                if (sender.getBalance() - amount < MIN_BALANCE) return fail("Below minimum balance");
                if (sender.transferTo(receiver, pin, amount)) return success("Transfer successful");
            }
        }
        return fail("Transaction failed");
    }

    private boolean success(String msg) {
        status = "COMPLETED";
        message = msg;
        System.out.println("[TXN " + id + "] " + msg);
        return true;
    }

    private boolean fail(String msg) {
        status = "FAILED";
        message = msg;
        System.out.println("[TXN " + id + "] Failed: " + msg);
        return false;
    }

    @Override
    public String toString() {
        return String.format("Transaction #%d | Type: %s | Amount: %.2f | Status: %s",
                id, getTypeName(), amount, status);
    }

    private String getTypeName() {
        return switch (type) {
            case DEPOSIT -> "Deposit";
            case WITHDRAW -> "Withdraw";
            case TRANSFER -> "Transfer";
            default -> "Unknown";
        };
    }
}

    