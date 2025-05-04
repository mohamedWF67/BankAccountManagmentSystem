package com.mycompany.bankaccountmanagmentsystem;

import java.io.*;
import java.util.ArrayList;

public class DataHandler {
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void setClients(ArrayList<Client> clients) {
        DataHandler.clients = clients;
    }

    public static void setAccounts(ArrayList<Account> accounts) {
        DataHandler.accounts = accounts;
    }

    public static void setTransactions(ArrayList<Transaction> transactions) {
        DataHandler.transactions = transactions;
    }

    // CREATE
    public static void createClient(Client client) {
        clients.add(client);
    }

    // READ
    public static Client getClientByEmail(int clientID) {
        for (Client c : clients) {
            if (c.setClientID(clientID) == clientID) {
                return c;
            }
        }
        return null;
    }

    public static Client getClientByEmail(String email) {
        for (Client c : clients) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public static ArrayList<Client> getAllClients() {
        return clients;
    }

    // UPDATE
    public static boolean updateClientAddress(int clientID, String newAddress) {
        Client c = getClientByEmail(clientID);
        if (c != null) {
            c.setAdd(newAddress);
            return true;
        }
        return false;
    }

    // DELETE
    public static boolean deleteClient(int clientID) {
        Client c = getClientByEmail(clientID);
        if (c != null) {
            return clients.remove(c);
        }
        return false;
    }

    // Create
    public static void createAccount(Account account) {
        accounts.add(account);
        System.out.println("Account created: " + account);
    }

    public static void addAccount(Account account) {
        accounts.add(account);
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

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public static void loadAllData() {
        try {
            ArrayList<Account> accountsData = (ArrayList<Account>) readObjectFromFile("Accounts.txt");
            if (accountsData != null) {
                setAccounts(accountsData);
            }
            ArrayList<Transaction> transactionsData = (ArrayList<Transaction>) readObjectFromFile("Transactions.txt");
            if (transactionsData != null) {
                setTransactions(transactionsData);
            }
            ArrayList<Client> loadedClients = loadClients("clients.dat");
            loadedClients.forEach(System.out::println);
            for (Client c : loadedClients) {
                System.out.println(c.getClientInfo());
            }
            if (loadedClients != null) {
                setClients(loadedClients);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveObjectToFile(String filename,ArrayList<?>values) {
        try {
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(values);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("Saved to " + filename);
    }

    public static ArrayList<?> readObjectFromFile(String filename) {
        ArrayList<?>List = new ArrayList<>();
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return null;
            }
            FileInputStream fis = new FileInputStream(file);

            ObjectInputStream ois = new ObjectInputStream(fis);
            List = (ArrayList<?>) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //System.out.println("Read from "+filename);
        return List;
    }

    public static void saveAllData() {
        saveObjectToFile("Accounts.txt", accounts);
        saveClients(clients, "clients.dat");
        saveObjectToFile("Transactions.txt", transactions);
    }

    public static void saveClients(ArrayList<Client> clients, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(clients);
            System.out.println("Clients saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving clients:");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Client> loadClients(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ArrayList<Client>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading clients:");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
