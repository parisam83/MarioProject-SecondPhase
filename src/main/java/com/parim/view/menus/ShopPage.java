package com.parim.view.menus;

import com.parim.event.ShopEvent;
import com.parim.listener.ShopListener;
import com.parim.model.user.Character;
import com.parim.model.user.User;
import com.parim.view.MainFrame;
import com.parim.view.loaders.FontLoader;
import com.parim.view.swingObjects.ButtonCreator;
import com.parim.view.swingObjects.LabelCreator;
import com.parim.view.swingObjects.ShopButtonCreator;

import javax.swing.*;
import java.awt.*;

public class ShopPage extends JPanel {
    private final User user;
    private LabelCreator coins;
    private ShopButtonCreator[] shopButtons = new ShopButtonCreator[5];

    public ShopPage(User user) {
        this.user = user;

        LabelCreator label = new LabelCreator("Shop!");
        this.add(label);

        ButtonCreator backButton = new ButtonCreator(Toolkit.getDefaultToolkit().getScreenSize().width/ 2 - ButtonCreator.getNextButtonWidth() / 2, 290 + 180 * 7 / 2 - 50, ButtonCreator.getNextButtonWidth(), ButtonCreator.getNextButtonHeight(), "<< Back <<", true);
        backButton.addActionListener(e -> MainFrame.getInstance().setMenuPage());
        this.add(backButton);

        coins = new LabelCreator(5, 10, "Coins: " + user.getCoins(), FontLoader.font.deriveFont(35f));
        this.add(coins);

        shopButtons[0] = new ShopButtonCreator(0, true, user.getCurrentCharacter());
        this.add(shopButtons[0]);

        int index = 1;
        for (Character character : Character.values())
            if (character != user.getCurrentCharacter()) {
                shopButtons[index] = new ShopButtonCreator(index, user.getCharacters().contains(character), character);
                int finalIndex = index;
                shopButtons[index].addActionListener(e -> setButtons(finalIndex, new ShopListener().eventOccurred(new ShopEvent(user, shopButtons[finalIndex].getCharacter()))));
                this.add(shopButtons[index]);
                index++;
            }

        this.setLayout(null);
    }

    public void setButtons(int smallIndex, String message) {
        coins.setText("Coins: " + user.getCoins());
        if (!message.contains("successfully")) return;
        ShopButtonCreator.swapButtons(shopButtons[0], shopButtons[smallIndex]);
    }
}
