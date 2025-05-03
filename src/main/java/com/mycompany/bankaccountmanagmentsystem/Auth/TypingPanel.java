package com.mycompany.bankaccountmanagmentsystem.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypingPanel extends JPanel {
    private String fullText = "✨ Welcome to El7ramy ✨";
    private StringBuilder currentText = new StringBuilder();
    private int charIndex = 0;
    private Timer timer;

    public TypingPanel() {
        setFont(new Font("SansSerif", Font.BOLD, 24));
        setForeground(UIManager.getColor("Label.foreground"));
        setBackground(UIManager.getColor("Label.background"));

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (charIndex < fullText.length()) {
                    currentText.append(fullText.charAt(charIndex));
                    charIndex++;
                    repaint();
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(getFont());
        g.setColor(getForeground());
        g.drawString(currentText.toString(), 50, 30);
    }
}
