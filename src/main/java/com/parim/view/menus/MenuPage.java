package com.parim.view.menus;

import com.parim.view.MainFrame;
import com.parim.view.loaders.FontLoader;
import com.parim.view.swingObjects.ButtonCreator;
import com.parim.view.swingObjects.LabelCreator;

import javax.swing.*;
import java.awt.*;

public class MenuPage extends JPanel {
    private static final int buttonLabelGap = 250, buttonGap = 170;
    public MenuPage(){
        int width = Toolkit.getDefaultToolkit().getScreenSize().width, height = Toolkit.getDefaultToolkit().getScreenSize().height;
        LabelCreator label = new LabelCreator("Supper Mario Game!");
        this.add(label);

        ButtonCreator newGameButton = new ButtonCreator(width/2 - 100 - ButtonCreator.getNormalButtonWidth(), buttonLabelGap, "Start new game", false);
        newGameButton.addActionListener(e -> MainFrame.getInstance().setNewGamePage());
        this.add(newGameButton);

        ButtonCreator prevGameButton = new ButtonCreator(width/2 - 100 - ButtonCreator.getNormalButtonWidth(), buttonLabelGap + (buttonGap), "Continue previous games", FontLoader.font.deriveFont(35f), false);
        prevGameButton.addActionListener(e -> MainFrame.getInstance().setPreviousGamePage());
        this.add(prevGameButton);

        ButtonCreator leaderBoardButton = new ButtonCreator(buttonLabelGap + (buttonGap)*2, "Leaderboard", false);
        leaderBoardButton.addActionListener(e -> MainFrame.getInstance().setLeaderBoardPage());
        this.add(leaderBoardButton);

        ButtonCreator shopButton = new ButtonCreator(width/2 + 100, buttonLabelGap, "Shop", false);
        shopButton.addActionListener(e -> MainFrame.getInstance().setShopPage());
        this.add(shopButton);

        ButtonCreator profileButton = new ButtonCreator(width/2 + 100, buttonLabelGap + (buttonGap), "Profile", false);
        profileButton.addActionListener(e -> MainFrame.getInstance().setProfilePage());
        this.add(profileButton);

        ButtonCreator backButton = new ButtonCreator(buttonLabelGap + (buttonGap)*3-10, "<< Back <<", true);
        backButton.addActionListener(e -> MainFrame.getInstance().setFirstPage());
        this.add(backButton);

        this.setLayout(null);
    }
}
