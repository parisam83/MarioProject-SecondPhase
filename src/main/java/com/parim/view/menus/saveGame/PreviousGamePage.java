package com.parim.view.menus.saveGame;

import com.parim.model.user.User;
import com.parim.view.MainFrame;
import com.parim.view.swingObjects.LabelCreator;

public class PreviousGamePage extends SaveGamePage{
    public PreviousGamePage(User user) {
        super(user);
        for (int i = 0; i < buttons.size(); i++){
            final int index = i;
            if (i < savedGames.size()) {
                // TODO
                // buttons.get(i).addActionListener(e -> MainFrame.getInstance().setGamePage(savedGames.get(index)));
            }
            else
                buttons.get(i).setEnabled(false);
        }
    }

    @Override
    public void setLabel() {
        label = new LabelCreator("Choose one of the games...");
    }
}