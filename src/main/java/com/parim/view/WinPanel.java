package com.parim.view;

import com.parim.view.loaders.FontLoader;
import com.parim.view.swingObjects.LabelCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WinPanel extends JPanel {
    public WinPanel() {
        LabelCreator congratsLabel = new LabelCreator(Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 100, "Congratulations!", FontLoader.titleFont);
        congratsLabel.setForeground(new Color(44, 173, 38));
        this.add(congratsLabel);

        LabelCreator wonLabel = new LabelCreator(Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 10, "You won the game.", FontLoader.titleFont);
        wonLabel.setForeground(new Color(30, 157, 171));
        this.add(wonLabel);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }
        });
        this.setLayout(null);
        // this.setPreferredSize(MainFrame.getScreenSize());
    }
}
