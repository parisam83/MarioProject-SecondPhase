package com.parim.view.menus.login;

import com.parim.event.UserFormEvent;
import com.parim.listener.LoginListener;
import com.parim.view.loaders.FontLoader;
import com.parim.view.menus.login.AccountPage;
import com.parim.view.swingObjects.LabelCreator;


public class LoginPage extends AccountPage {
    @Override
    public void setLabel() {
        label = new LabelCreator("Login to your account", FontLoader.titleFont);
        this.add(label);
    }

    @Override
    public void listener(UserFormEvent userFormEvent) {
        if (!super.isDefaultValue())
            new LoginListener().eventOccurred(userFormEvent);
    }
}
