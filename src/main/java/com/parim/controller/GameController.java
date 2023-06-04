package com.parim.controller;

import com.parim.model.SectionObject;
import com.parim.view.MainFrame;

public class GameController {
    private static GameController instance = new GameController(new MainFrame().getInstance());
    private MainFrame mainFrame;

    public GameController(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    public SectionObject loadGame(){

        return null;
    }
    public void run(){
        mainFrame.repaint();
        mainFrame.revalidate();
    }

    public static GameController getInstance(){
        return instance;
    }
}
