package com.mycompany.bankaccountmanagmentsystem.Config;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.bankaccountmanagmentsystem.Auth.AuthManagerUI;
import com.mycompany.bankaccountmanagmentsystem.DataHandler;
import com.mycompany.bankaccountmanagmentsystem.ThemeManger.ThemeManager;

import javax.swing.*;
import java.awt.*;
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

// 🌸 Base pinks
        Color background = new Color(255, 240, 245); // Soft pink
        Color foreground = new Color(136, 14, 79);   // Bold pink (text)
        Color accent     = new Color(233, 30, 99);   // Accent pink

        // 🧠 Core component shapes
        UIManager.put("Component.arc", 20);
        UIManager.put("Button.arc", 999);
        UIManager.put("TextComponent.arc", 15);
        UIManager.put("Component.focusWidth", 2);

        // 🎨 Global colors
        UIManager.put("Panel.background", background);
        UIManager.put("Component.background", background);
        UIManager.put("Viewport.background", background);
        UIManager.put("ScrollPane.background", background);
        UIManager.put("TextField.background", background);
        UIManager.put("TextField.foreground", foreground);
        UIManager.put("PasswordField.background", background);
        UIManager.put("PasswordField.foreground", foreground);
        UIManager.put("Label.foreground", foreground);
        UIManager.put("Button.background", new Color(248, 187, 208)); // Light pink
        UIManager.put("Button.foreground", foreground);
        UIManager.put("CheckBox.background", background);
        UIManager.put("CheckBox.foreground", foreground);
        UIManager.put("RadioButton.background", background);
        UIManager.put("RadioButton.foreground", foreground);
        UIManager.put("ComboBox.background", background);
        UIManager.put("ComboBox.foreground", foreground);

        // ✨ Accent-like behavior
        UIManager.put("Component.focusColor", accent);
        UIManager.put("Component.borderColor", accent.darker());
        UIManager.put("TextComponent.selectionBackground", accent);
        UIManager.put("TextComponent.selectionForeground", Color.WHITE);
        UIManager.put("CheckBox.icon.selectionColor", accent);
        UIManager.put("RadioButton.icon.selectionColor", accent);
        UIManager.put("ComboBox.selectionBackground", accent);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("ScrollBar.thumb", new Color(248, 187, 208));
        FlatLightLaf.setup();

        new AuthManagerUI();
    }

    private void onClose(){
        ThemeManager.CloseThemeManager();

        DataHandler.saveAllData();

        System.out.println("👋 Good bye..");
    }
}
