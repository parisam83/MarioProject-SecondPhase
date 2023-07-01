package com.parim.view.menus.saveGame;

import com.parim.model.GameObject;
import com.parim.model.user.User;
import com.parim.view.MainFrame;
import com.parim.view.swingObjects.ButtonCreator;
import com.parim.view.swingObjects.LabelCreator;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public abstract class SaveGamePage extends JPanel {
    private static final int buttonLabelGap = 300, buttonGap = 180;
    protected LabelCreator label;
    protected ArrayList<GameObject> savedGames;
    protected ArrayList<ButtonCreator> buttons = new ArrayList<ButtonCreator>(3){{add(null); add(null); add(null);}};
    public SaveGamePage(User user){
        savedGames = user.getSavedGames();
        setLabel();
        this.add(label);

        for (int i = 0; i < savedGames.size(); i++) {
            ButtonCreator button = new ButtonCreator(buttonLabelGap + buttonGap * i, savedGames.get(i).calculateGameState(), false);
            buttons.set(i, button);
            this.add(button);
        }
        for (int i = savedGames.size(); i < 3; i++) {
            ButtonCreator button = new ButtonCreator(buttonLabelGap + buttonGap * i, "----", false);
            buttons.set(i, button);
            this.add(button);
        }

        this.addKeyListener(new AL());
        this.setLayout(null);
    }

    public abstract void setLabel();
    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                MainFrame.getInstance().setMenuPage();
        }
    }
}
