package com.mycompany.bankaccountmanagmentsystem.Config;

import com.formdev.flatlaf.FlatLaf;
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

        FlatLightLaf.setup();
        FlatLaf.setUseNativeWindowDecorations(false);
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        UIManager.put("TitlePane.background", new Color(255, 192, 203));  // Light pink
        UIManager.put("TitlePane.foreground", new Color(136, 14, 79));    // Dark pink (text)
        UIManager.put("TitlePane.unifiedBackground", true);

        Color foreground = new Color(136, 14, 79);  // Dark pink
        Color accent = new Color(233, 30, 99);      // Strong pink

        UIManager.put("Component.foreground", foreground);
        UIManager.put("Label.foreground", foreground);
        UIManager.put("TitlePane.foreground", foreground);
        UIManager.put("Component.focusColor", accent);
        UIManager.put("TextComponent.selectionBackground", accent);

        // 🌸 Define the pink palette
        Color softPink     = new Color(255, 240, 245); // general background
        Color deepPink     = new Color(136, 14, 79);   // main foreground
        Color accentPink   = new Color(233, 30, 99);   // focus & selection
        Color buttonPink   = new Color(248, 187, 208); // button background
        Color borderPink   = new Color(240, 98, 146);  // border & shadows
        Color lightPink    = new Color(255, 228, 236); // text fields

        // 🧠 Component styling
        UIManager.put("Component.arc", 25);
        UIManager.put("Button.arc", 999);
        UIManager.put("TextComponent.arc", 20);
        UIManager.put("Component.focusWidth", 2);

        // 🌺 Universal pink theme
        UIManager.put("Component.background", softPink);
        UIManager.put("Panel.background", softPink);
        UIManager.put("Viewport.background", softPink);
        UIManager.put("ScrollPane.background", softPink);
        UIManager.put("Label.foreground", deepPink);
        UIManager.put("Component.foreground", deepPink);
        UIManager.put("Button.background", buttonPink);
        UIManager.put("Button.foreground", deepPink);
        UIManager.put("ToggleButton.background", buttonPink);
        UIManager.put("ToggleButton.foreground", deepPink);
        UIManager.put("TextField.background", lightPink);
        UIManager.put("TextField.foreground", deepPink);
        UIManager.put("PasswordField.background", lightPink);
        UIManager.put("PasswordField.foreground", deepPink);
        UIManager.put("FormattedTextField.background", lightPink);
        UIManager.put("FormattedTextField.foreground", deepPink);
        UIManager.put("EditorPane.background", lightPink);
        UIManager.put("EditorPane.foreground", deepPink);
        UIManager.put("CheckBox.background", softPink);
        UIManager.put("CheckBox.foreground", deepPink);
        UIManager.put("RadioButton.background", softPink);
        UIManager.put("RadioButton.foreground", deepPink);
        UIManager.put("ComboBox.background", lightPink);
        UIManager.put("ComboBox.foreground", deepPink);
        UIManager.put("ComboBox.selectionBackground", accentPink);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("List.background", lightPink);
        UIManager.put("List.foreground", deepPink);
        UIManager.put("Table.background", softPink);
        UIManager.put("Table.foreground", deepPink);
        UIManager.put("Tree.background", softPink);
        UIManager.put("Tree.foreground", deepPink);
        UIManager.put("ScrollBar.thumb", buttonPink);
        UIManager.put("ScrollBar.track", softPink);
        UIManager.put("A", softPink);

        // ✨ Accent & selection
        UIManager.put("Component.focusColor", accentPink);
        UIManager.put("Component.borderColor", borderPink);
        UIManager.put("TextComponent.selectionBackground", accentPink);
        UIManager.put("TextComponent.selectionForeground", Color.WHITE);
        UIManager.put("CheckBox.icon.selectionColor", accentPink);
        UIManager.put("RadioButton.icon.selectionColor", accentPink);
        UIManager.put("ToggleButton.focusColor", accentPink);
        UIManager.put("Button.focusColor", accentPink);

        new AuthManagerUI();
    }

    private void onClose(){
        ThemeManager.CloseThemeManager();

        DataHandler.saveAllData();

        System.out.println("👋 Good bye..");
    }
}
