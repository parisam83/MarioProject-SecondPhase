package com.parim;

import com.parim.controller.GameController;
import com.parim.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        new MainFrame().setFirstPage();
        GameController.getInstance().checkStart();
    }
}