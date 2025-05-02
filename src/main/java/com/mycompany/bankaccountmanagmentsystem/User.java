/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankaccountmanagmentsystem;
import java.io.*;
import java.util.*;


public class User {
    private int nationalID;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String password;

    public User(int id, String fname, String mail, String phonenum, String add, String pword) {
        nationalID=id;
        fullname=fname;
        email=mail;
        phone= phonenum;
        address=add;
        password=pword;
    }

    public String getDetails() {
        return "Name: " + fullname + ", Email: " + email + ", Phone: " + phone + ", Address: " + address;
    }

    public void updateContactInfo(String newPhone) {
       phone = newPhone;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }
    public int getNationalID() {
    return nationalID;
    }

    public String getPassword() {
    return password;
    }

}
