/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccountmanagmentsystem;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author abrar
 */
public class Transaction {
    // Constants
    public static final int DEPOSIT = 1;
    public static final int WITHDRAWAL = 2;
    public static final int TRANSFER = 3;

    // Transaction limits
    private static final int DAILY_DEPOSIT_LIMIT = 10000;//DAILY_DEPOSIT_LIMIT
    private static final int DAILY_WITHDRAWAL_LIMIT = 5000;//DAILY_WITHDRAWAL_LIMIT
    private static final int DAILY_TRANSFER_LIMIT = 25000;//DAILY_TRANSFER_LIMIT
    private static final int MINIMUM_BALANCE = 100;//MINIMUM_BALANCE

    // Fields
    private int transactionID;
    private int transactionType;
    private Account senderAccount;
    private Account receiverAccount;
    private double amount;
    private Date timestamp;
    private String status;
    private String reasonCode;

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
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    // Constructors
    public Transaction( int transactionType, Account senderAccount,double amount) {
        this(transactionType, senderAccount, null, amount);
    }

    public Transaction(int transactionType, Account senderAccount,
                       Account receiverAccount, double amount) {
        this.transactionID = UUID.randomUUID().toString().hashCode();
        this.transactionType = transactionType;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
        this.timestamp = new Date();
        this.status = "Success";
        this.reasonCode = "";
    }

    // Core business methods
    public boolean processTransaction() {
        if (senderAccount == null || senderAccount.isFrozen()) {
            setFailure("Invalid or frozen sender account");
            return false;
        }

        switch (transactionType) {
            case DEPOSIT -> {
                return processDeposit();
            }
            case WITHDRAWAL -> {
                return processWithdrawal();
            }
            case TRANSFER -> {
                return processTransfer();
            }
            default -> {
                setFailure("Invalid transaction type");
                return false;
            }
        }
    }

    private boolean processDeposit() {
        if (amount <= 0) {
            setFailure("Deposit amount must be positive");
            return false;
        }

        if (amount > DAILY_DEPOSIT_LIMIT) {
            setFailure("Exceeds daily deposit limit of " + DAILY_DEPOSIT_LIMIT);
            return false;
        }

        senderAccount.deposit(amount);
        setSuccess("Deposit processed");
        return true;
    }

    private boolean processWithdrawal() {
        if (amount <= 0) {
            setFailure("Withdrawal amount must be positive");
            return false;
        }

        if (amount > DAILY_WITHDRAWAL_LIMIT) {
            setFailure("Exceeds daily withdrawal limit of " + DAILY_WITHDRAWAL_LIMIT);
            return false;
        }

        double availableBalance = senderAccount.getBalance() - MINIMUM_BALANCE;
        if (amount > availableBalance) {
            setFailure("Insufficient funds. Available: " + availableBalance);
            return false;
        }

        senderAccount.withdraw(amount);
        setSuccess("Withdrawal processed");
        return true;
    }

    private boolean processTransfer() {
        if (receiverAccount == null || receiverAccount.isFrozen()) {
            setFailure("Invalid or frozen recipient account");
            return false;
        }

        if (amount <= 0) {
            setFailure("Transfer amount must be positive");
            return false;
        }

        if (amount > DAILY_TRANSFER_LIMIT) {
            setFailure("Exceeds daily transfer limit of " + DAILY_TRANSFER_LIMIT);
            return false;
        }

        double availableBalance = senderAccount.getBalance() - MINIMUM_BALANCE;
        if (amount > availableBalance) {
            setFailure("Insufficient funds. Available: " + availableBalance);
            return false;
        }

        senderAccount.withdraw(amount);
        receiverAccount.deposit(amount);
        setSuccess("Transfer processed");
        return true;
    }

    // Helper methods
    private void setSuccess(String message) {
        this.status = "COMPLETED";
        this.reasonCode = "SUCCESS";
        logTransaction(message);
    }

    private void setFailure(String reason) {
        this.status = "FAILED";
        this.reasonCode = reason.replaceAll(" ", "_").toUpperCase();
        logTransaction("Failed: " + reason);
    }

    private void logTransaction(String message) {
        System.out.println("[TXN-" + transactionID + "] " + message);
    }

    @Override
    public String toString() {
        return String.format(
                "Transaction[ID:%d, Type:%s, Amount:%.2f, Status:%s, Reason:%s]",
                transactionID, getTypeName(), amount, status, reasonCode
        );
    }

    private String getTypeName() {
        return switch (transactionType) {
            case DEPOSIT -> "DEPOSIT";
            case WITHDRAWAL -> "WITHDRAWAL";
            case TRANSFER -> "TRANSFER";
            default -> "UNKNOWN";
        };
    }
}