package com.parim.view;

import javax.swing.*;

public class MainFrame extends JFrame {
    private static MainFrame instance;

    public MainFrame(){
        if (instance != null) return;
        instance = this;

        this.setTitle("Super Mario Game!");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocation(0, 0);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static MainFrame getInstance() {
        if (instance == null) instance = new MainFrame();
        return instance;
    }
}
