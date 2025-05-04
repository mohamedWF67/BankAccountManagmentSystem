package com.mycompany.bankaccountmanagmentsystem.ThemeManger;

import javax.swing.*;
import java.awt.*;

public class ThemeUtils {
    public static void reloadLookAndFeel() {
        for (Frame frame : Frame.getFrames()) {
            SwingUtilities.updateComponentTreeUI(frame);
            frame.invalidate();
            frame.validate();
            frame.repaint();
        }
    }
}
