package com.parim.view.menus;

import com.parim.model.user.User;
import com.parim.view.MainFrame;
import com.parim.view.loaders.FontLoader;
import com.parim.view.swingObjects.ButtonCreator;
import com.parim.view.swingObjects.LabelCreator;

import javax.swing.*;
import java.awt.*;

public class ProfilePage extends JPanel {
    private static final int buttonLabelGap = 350, buttonGap = 80;
    public ProfilePage(User user){
        LabelCreator label = new LabelCreator(200, "Profile page!");
        this.add(label);

        // Username
        LabelCreator usernameLabel = new LabelCreator(buttonLabelGap, "Username: " + user.getUsername(), FontLoader.buttonFont);
        this.add(usernameLabel);

        // Character
        LabelCreator characterLabel = new LabelCreator(buttonLabelGap + buttonGap, "Character: " + user.getCurrentCharacter().toString(), FontLoader.buttonFont);
        this.add(characterLabel);

        // MaxScore
        LabelCreator maxScoreLabel = new LabelCreator(buttonLabelGap + buttonGap*2, "Max Score: " + String.valueOf(user.getMaxScore()), FontLoader.buttonFont);
        this.add(maxScoreLabel);

        ButtonCreator backButton = new ButtonCreator(Toolkit.getDefaultToolkit().getScreenSize().width/2 - ButtonCreator.getNextButtonWidth()/2, 230 + 180*7/3, ButtonCreator.getNextButtonWidth(), ButtonCreator.getNextButtonHeight(), "<< Back <<", true);
        backButton.addActionListener(e -> MainFrame.getInstance().setMenuPage());
        this.add(backButton);

        this.setLayout(null);
    }
}
