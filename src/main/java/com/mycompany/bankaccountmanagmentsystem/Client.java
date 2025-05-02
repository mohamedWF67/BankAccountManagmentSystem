package com.mycompany.bankaccountmanagmentsystem;

import java.util.ArrayList;

public class Client extends User {
    private int clientID;
    private ArrayList<Account> accounts;

    public Client(int nationalID, String fullname, String email, String phone, String address, String password, ArrayList<Account> accounts) {
        super(nationalID, fullname, email, phone, address, password);
        this.accounts = accounts != null ? accounts : new ArrayList<>();
    }

    public String getClientInfo() {
        return "Client ID: " + clientID + " | " + getDetails();
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public int setClientID(int id) {
        this.clientID = id;
        return this.clientID;
    }

    public String setAdd(String newAddress) {
        // Assuming address is inherited and private, we need a setter in User (or do it here if protected)
        super.setAddress(newAddress);
        return newAddress;
    }

    public void addAccount(double initialBalance) {
        Account newAccount = new Account(initialBalance);
        accounts.add(newAccount);
    }

    public ArrayList<Card> getLinkedCards() {
        ArrayList<Card> allCards = new ArrayList<>();
        for (Account acc : accounts) {
            allCards.addAll(acc.getLinkedCards());
        }
        return allCards;
    }

    public CustomerServiceRequest submitRequest(Client client, String subject, String message) {
        return new CustomerServiceRequest(client, subject, message);
    }

    public void closeRequest(CustomerServiceRequest req) {
        req.close();
    }

    public void addCommentToRequest(String comment, String author) {
        // Assuming you store and manage a current or selected request elsewhere
        // You could pass a request as a parameter
        System.err.println("No request object passed. Please modify the method to include a request reference.");
    }
}
