package com.parim.view.swingObjects;

import com.parim.model.user.Character;
import com.parim.view.loaders.FontLoader;
import com.parim.view.loaders.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class ShopButtonCreator extends JButton {
    private static final int smallButtonSize = 250, bigButtonSize = 500;
    private static final int smallIconSize = smallButtonSize - 60, bigIconSize = bigButtonSize - 100;
    private int x, y, buttonSize;
    private Character character;
    private boolean bought;

    public ShopButtonCreator(int index, boolean bought, Character character){
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.bought = bought;
        this.character = character;
        buttonSize = index == 0 ? bigButtonSize : smallButtonSize;

        if (index == 0) {
            x = width/ 2 - bigButtonSize - 100;
            y = height/ 2 - bigButtonSize / 2;
        }
        else if (index > 0){
            x = width/2 + 100;
            y = height/2 - smallButtonSize;
            if (index > 2) x += smallButtonSize;
            if (index % 2 == 0) y += smallButtonSize;
        }

        createShopButton();
        this.setFont(buttonSize == bigButtonSize ? FontLoader.buttonFont : FontLoader.font.deriveFont(32f));
        this.setBounds(x, y, buttonSize, buttonSize);
        this.setFocusable(false);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.BOTTOM);
    }

    public void createShopButton(){
        this.setIcon(new ImageIcon((buttonSize == smallButtonSize) ? ImageLoader.getInstance().LoadSmallCharacter(character.name()) : ImageLoader.getInstance().LoadBigCharacter(character.name())));
        this.setText(character.name() + " :: " + (bought ? " bought" : character.getPrice()));
    }

    public static void swapButtons(ShopButtonCreator prevBig, ShopButtonCreator prevSmall){
        Character bigCharacter = prevBig.getCharacter();
        Character smallCharacter = prevSmall.getCharacter();

        prevBig.setBought(true);
        prevSmall.setBought(true);

        prevSmall.setCharacter(bigCharacter);
        prevBig.setCharacter(smallCharacter);

        prevBig.createShopButton();
        prevSmall.createShopButton();
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public boolean isBought() {
        return bought;
    }
}
