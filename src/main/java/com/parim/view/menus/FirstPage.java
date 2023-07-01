package com.parim.view.menus;

import com.parim.view.MainFrame;
import com.parim.view.loaders.FontLoader;
import com.parim.view.swingObjects.ButtonCreator;
import com.parim.view.swingObjects.LabelCreator;

import javax.swing.*;

import static java.lang.System.exit;

public class FirstPage extends JPanel {
    private static final int buttonLabelGap = 300, buttonGap = 200;

    public FirstPage() {
        LabelCreator label = new LabelCreator("Please register or login to your account", FontLoader.titleFont);
        this.add(label);

        ButtonCreator registerButton = new ButtonCreator(buttonLabelGap, "Register", false);
        registerButton.addActionListener(e -> MainFrame.getInstance().setRegisterPage());
        this.add(registerButton);

        ButtonCreator loginButton = new ButtonCreator(buttonLabelGap + buttonGap, "Login", false);
        loginButton.addActionListener(e -> MainFrame.getInstance().setLoginPage());
        this.add(loginButton);

        ButtonCreator exitButton = new ButtonCreator(buttonLabelGap + buttonGap * 2, "Exit", false);
        exitButton.addActionListener(e -> exit(0));
        this.add(exitButton);

        this.setLayout(null);
    }
}