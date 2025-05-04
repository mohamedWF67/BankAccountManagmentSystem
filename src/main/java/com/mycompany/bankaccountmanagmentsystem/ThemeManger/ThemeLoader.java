package com.mycompany.bankaccountmanagmentsystem.ThemeManger;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;


public class ThemeLoader {

    public static ArrayList<LookAndFeel> loadAllThemes() {
        ArrayList<LookAndFeel> themes = new ArrayList<>();

        themes.add(new FlatLightLaf());
        themes.add(new FlatDarkLaf());
        themes.add(new FlatIntelliJLaf());
        themes.add(new FlatDarculaLaf());
        themes.add(new FlatOneDarkIJTheme());
        themes.add(new FlatMacLightLaf());
        themes.add(new FlatMacDarkLaf());
        themes.add(new FlatAtomOneLightIJTheme());
        themes.add(new FlatAtomOneDarkIJTheme());
        themes.add(new FlatDraculaIJTheme());
        themes.add(new FlatGitHubDarkIJTheme());
        themes.add(new FlatGitHubIJTheme());
        themes.add(new FlatGruvboxDarkHardIJTheme());
        themes.add(new FlatGruvboxDarkMediumIJTheme());
        themes.add(new FlatGruvboxDarkSoftIJTheme());
        themes.add(new FlatMaterialDarkerIJTheme());
        themes.add(new FlatMaterialDeepOceanIJTheme());
        themes.add(new FlatMaterialLighterIJTheme());
        themes.add(new FlatMaterialOceanicIJTheme());
        themes.add(new FlatMaterialPalenightIJTheme());
        themes.add(new FlatMonokaiProIJTheme());
        themes.add(new FlatMoonlightIJTheme());
        themes.add(new FlatNightOwlIJTheme());
        themes.add(new FlatSolarizedDarkIJTheme());
        themes.add(new FlatSolarizedLightIJTheme());

        System.out.println("Working dir: " + System.getProperty("user.dir"));

        // 🔴 Add custom Pink theme (must be in your resource folder!)
        try (InputStream input = new FileInputStream("themes/PinkTheme.theme.json")) {
            LookAndFeel pinkLaf = IntelliJTheme.createLaf(input);
            themes.add(pinkLaf);
        } catch (Exception e) {
            System.err.println("Could not load Pink theme:");
            e.printStackTrace();
        }

        return themes;
    }


}

