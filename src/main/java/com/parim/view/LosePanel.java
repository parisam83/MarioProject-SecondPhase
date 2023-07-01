package com.parim.view;

import com.parim.view.loaders.FontLoader;
import com.parim.view.swingObjects.LabelCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LosePanel extends JPanel {
    public LosePanel(){
        LabelCreator sorryLabel = new LabelCreator(Toolkit.getDefaultToolkit().getScreenSize().height/2 - 75, "Sorry :(", FontLoader.titleFont);
        sorryLabel.setForeground(new Color(199, 0,57)); //  195, 55, 49
        this.add(sorryLabel);

        LabelCreator loseLabel = new LabelCreator(Toolkit.getDefaultToolkit().getScreenSize().height/2 + 10, "You lost the game.", FontLoader.titleFont);
        loseLabel.setForeground(new Color(30, 157, 171));
        this.add(loseLabel);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
            }
        });
        this.setLayout(null);
    }
}
