package com.mycompany.bankaccountmanagmentsystem;




public class AuthManagerUI {
    private Authentication auth;

    public AuthManagerUI(Authentication auth) {
        this.auth = auth;
    }

    public void viewLogin(int nationalID, String password) {
        if (auth.validateLogin(nationalID, password)) {
            System.out.println("Login successful!");
        } else {
            showError("Login failed. Invalid credentials.");
        }
    }

    
public void viewRegister() {

}

    public void showError(String message) {
        System.err.println("Error: " + message);
    }
}

