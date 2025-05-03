package com.mycompany.bankaccountmanagmentsystem.Auth;

import com.mycompany.bankaccountmanagmentsystem.User;

class logCounter {
    private User user;
    private int logCounter = 0;

    logCounter(User user) {
        this.user = user;
        logCounter++;
    }

    public int getLogCounter() {
        return logCounter;
    }

    public void setLogCounter(int logCounter) {
        this.logCounter = logCounter;
    }

    public void incrementLogCounter() {
        logCounter++;
    }

    public void decrementLogCounter() {
        logCounter--;
    }

    public void resetLogCounter() {
        logCounter = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}