package com.mycompany.bankaccountmanagmentsystem.ThemeManger;


import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import javax.swing.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class ThemeManager {

    static Preferences prefs = Preferences.userRoot().node("myAppSettings");
    static ArrayList<LookAndFeel> themes = new ArrayList<>();

    public static ArrayList<LookAndFeel> getFlatLafThemes() {
        return themes;
    }

    public static void InitThemeManager() {
        themes = ThemeLoader.loadAllThemes();
        LoadTheme();
    }

    private static void SetDefaultTheme() {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    public static void CloseThemeManager() {
        SaveTheme();
    }
    public static void SaveTheme() {
        prefs.put("theme",UIManager.getLookAndFeel().getName());
        System.out.println("💾 Saved theme as " + prefs.get("theme","Failed"));
    }

    public static void LoadTheme() {
        String theme = prefs.get("theme",null);
        LookAndFeel lookAndFeel = GetTheme(theme);
        if (lookAndFeel != null) {
            try {
                UIManager.setLookAndFeel(lookAndFeel);
                System.out.println("🎨 Loaded theme as " + UIManager.getLookAndFeel().getName());
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.err.println("⚠️ Loaded DefaultTheme ");
            SetDefaultTheme();
        }
    }

    private static LookAndFeel GetTheme(String NewTheme) {
        for (LookAndFeel theme : getFlatLafThemes()) {
            if (theme.getName().equals(NewTheme)) {
                return theme;
            }
        }
        return null;
    }

}
