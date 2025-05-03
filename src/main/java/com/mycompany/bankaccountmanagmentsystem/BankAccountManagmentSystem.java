/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bankaccountmanagmentsystem;

import com.mycompany.bankaccountmanagmentsystem.Auth.AuthManagerUI;

/**
 *
 * @author mohamed waleed
 */
public class BankAccountManagmentSystem {

    public static void main(String[] args) {
        Account account = new Account(1500);
        DataHandler.createAccount(account);
        DataHandler.getAccount(account.getCCN());
        account.addCard(new Card("Waleed Farouk Mahdy",1234));

        Account account2 = new Account(2500);
        DataHandler.createAccount(account2);
        DataHandler.getAccount(account2.getCCN());
        account2.addCard(new Card("Abrar Mansour",1234));

        account2.transferMoney(1234,1500, account.getCCN());
        account.transferMoney(1234,120, account2.getCCN());
        account.depositMoney(1234,1200);
        account2.depositMoney(1234,1200);
        new AuthManagerUI();
    }
}






    