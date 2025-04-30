
package com.mycompany.bankaccountmanagmentsystem;

import java.io.*;
import java.util.*;

public class Authentication {
    private static final String FILE_PATH = "users.txt";

    public boolean validateLogin(int nationalID, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) continue;

                int storedID = Integer.parseInt(parts[0]);
                String storedPassword = parts[1];

                if (storedID == nationalID && storedPassword.equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user file: " + e.getMessage());
        }
        return false;
    }

    public void createClient(User user) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(user.getNationalID() + "," + user.getPassword());
        } catch (IOException e) {
            System.err.println("Error writing to user file: " + e.getMessage());
        }
    }
    public boolean isValidPassword(String password) {
    boolean hasUpper = false;
    boolean hasDigit = false;

    for (char c : password.toCharArray()) {
        if (Character.isUpperCase(c)) hasUpper = true;
        if (Character.isDigit(c)) hasDigit = true;
    }

    return hasUpper && hasDigit;
    }

}
