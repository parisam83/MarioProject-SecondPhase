package com.parim.listener;

import com.parim.controller.GameController;
import com.parim.controller.UserController;
import com.parim.event.UserFormEvent;
import com.parim.model.user.User;
import com.parim.view.MainFrame;

public class LoginListener implements FormListener{
    @Override
    public void eventOccurred(UserFormEvent userFormEvent) {
        User user = new UserController(userFormEvent).loginUser();
        if (user == null)
            MainFrame.getInstance().unsuccessfulLoginError();
        else{
            new GameController(user);
            MainFrame.getInstance().setMenuPage();
        }
    }
}
