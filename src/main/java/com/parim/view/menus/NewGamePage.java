package com.parim.view.menus;

import com.parim.access.GameAccess;
import com.parim.controller.GameController;
import com.parim.model.user.User;
import com.parim.view.menus.saveGame.SaveGamePage;
import com.parim.view.swingObjects.LabelCreator;

public class NewGamePage extends SaveGamePage {
    public NewGamePage(User user) {
        super(user);
        for (int i = 0; i < buttons.size(); i++){
            final int index = i;
            buttons.get(i).addActionListener(e -> {
                user.addRemoveGame(index, new GameAccess().loadGame());
                GameController.setCheckIfStart(true);
            });
        }
    }

    @Override
    public void setLabel() {
        label = new LabelCreator("Choose where to store this game...");
    }
}
