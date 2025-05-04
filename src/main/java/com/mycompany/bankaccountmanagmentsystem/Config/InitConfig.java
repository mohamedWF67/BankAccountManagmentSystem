package com.mycompany.bankaccountmanagmentsystem.Config;

import com.mycompany.bankaccountmanagmentsystem.Auth.AuthManagerUI;
import com.mycompany.bankaccountmanagmentsystem.DataHandler;
import com.mycompany.bankaccountmanagmentsystem.ThemeManger.ThemeManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class InitConfig {
    public static final InitConfig INSTANCE;
    public static final boolean DEVMODE = true;

    static {
        try {
            INSTANCE = new InitConfig();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private InitConfig() throws FileNotFoundException {
        System.out.println("🔧 InitConfig started!");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> onClose()));

        ThemeManager.InitThemeManager();

        DataHandler.loadAllData();
        new AuthManagerUI();
    }

    private void onClose(){
        ThemeManager.CloseThemeManager();

        DataHandler.saveAllData();

        System.out.println("👋 Good bye..");
    }
}
