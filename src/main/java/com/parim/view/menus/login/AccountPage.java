package com.parim.view.menus.login;

import com.parim.event.UserFormEvent;
import com.parim.view.MainFrame;
import com.parim.view.swingObjects.ButtonCreator;
import com.parim.view.swingObjects.LabelCreator;
import com.parim.view.swingObjects.TextFieldCreator;

import javax.swing.*;
import java.awt.*;

public abstract class AccountPage extends JPanel {
    private static final int textFieldLabelGap = 300, textFieldGap = 200;
    protected LabelCreator label;
    protected TextFieldCreator username, password;
    public AccountPage(){
        setLabel();
        this.add(label);

        username = new TextFieldCreator(textFieldLabelGap, "username");
        this.add(username);

        password = new TextFieldCreator(textFieldLabelGap + textFieldGap, "password");
        this.add(password);

        ButtonCreator nextButton = new ButtonCreator(Toolkit.getDefaultToolkit().getScreenSize().width/2 + 300, textFieldLabelGap + textFieldGap*7/3, ButtonCreator.getNextButtonWidth(), ButtonCreator.getNextButtonHeight(), ">> Next >>", true);
        nextButton.addActionListener(e -> listener(new UserFormEvent(username.getText(), password.getText())));
        this.add(nextButton);

        ButtonCreator backButton = new ButtonCreator(Toolkit.getDefaultToolkit().getScreenSize().width/2 - 300 - ButtonCreator.getNextButtonWidth(), textFieldLabelGap + textFieldGap*7/3, ButtonCreator.getNextButtonWidth(), ButtonCreator.getNextButtonHeight(), "<< Back <<", true);
        backButton.addActionListener(e -> MainFrame.getInstance().setFirstPage());
        this.add(backButton);

        this.setLayout(null);
    }

    public boolean isDefaultValue(){
        if (username.getText().equals("username") && password.getText().equals("password")){
            MainFrame.getInstance().defaultValueInputError();
            return true;
        }
        else if (username.getText().equals("") || password.getText().equals("")) {
            MainFrame.getInstance().emptyValueInputError();
            return true;
        }
        return false;
    }
    public abstract void setLabel();
    public abstract void listener(UserFormEvent userFormEvent);
}