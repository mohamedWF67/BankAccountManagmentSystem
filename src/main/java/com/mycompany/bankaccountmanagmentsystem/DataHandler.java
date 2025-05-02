package com.mycompany.bankaccountmanagmentsystem;

import java.util.ArrayList;

public class DataHandler {
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    // Create
    public static void createAccount(Account account) {
        accounts.add(account);
        System.out.println("Account created: " + account);
    }

    // Read
    public static Account getAccount(int ccn) {
        for (Account acc : accounts) {
            if (acc.getCCN() == ccn) {
                return acc;
            }
        }
        System.out.println("Account not found with CCN: " + ccn);
        return null;
    }

    // Update
    public static boolean updateAccount(int ccn, double newBalance, boolean freezeStatus) {
        Account acc = getAccount(ccn);
        if (acc != null) {
            acc.setBalance(newBalance);
            if (freezeStatus) {
                acc.freezeAccount();
            } else {
                acc.unfreezeAccount();
            }
            System.out.println("Account updated: " + acc);
            return true;
        }
        return false;
    }

    // Delete
    public static boolean deleteAccount(int ccn) {
        Account acc = getAccount(ccn);
        if (acc != null) {
            accounts.remove(acc);
            System.out.println("Account deleted: " + acc);
            return true;
        }
        return false;
    }

    // List All
    public static void listAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            for (Account acc : accounts) {
                System.out.println(acc);
            }
        }
    }

    // Create
    public static void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("Transaction added: " + transaction);
    }

    // Read
    public static Transaction getTransactionById(int transactionID) {
        Transaction transaction = transactions.stream()
                .filter(t -> t.getTransactionID() == transactionID)
                .findFirst().orElse(null);
        return transaction;
    }

    // List all transactions
    public static void listAllTransactions() {
        transactions.forEach(System.out::println);
    }
}
