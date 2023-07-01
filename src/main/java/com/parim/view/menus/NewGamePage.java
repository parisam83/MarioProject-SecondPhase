package com.parim.view.menus;

import com.parim.access.GameAccess;
import com.parim.model.GameObject;
import com.parim.model.user.User;
import com.parim.view.MainFrame;
import com.parim.view.menus.saveGame.SaveGamePage;
import com.parim.view.swingObjects.LabelCreator;

public class NewGamePage extends SaveGamePage {
    public NewGamePage(User user) {
        super(user);
        for (int i = 0; i < buttons.size(); i++){
            final int index = i;
            buttons.get(i).addActionListener(e -> {
                user.addRemoveGame(index, new GameAccess().loadGame());
                // MainFrame.getInstance().setGamePage(user.GetLastAddedGame());
            });
        }
    }

    @Override
    public void setLabel() {
        label = new LabelCreator("Choose where to store this game...");
    }
}
