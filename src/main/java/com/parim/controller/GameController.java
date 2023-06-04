package com.parim.controller;

import com.parim.access.GameAccess;
import com.parim.model.GameObject;
import com.parim.model.components.MarioObject;
import com.parim.model.components.TileObject;
import com.parim.view.GamePanel;
import com.parim.view.MainFrame;
import com.parim.view.PausePanel;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameController {
    private static GameController instance = new GameController(new MainFrame().getInstance());
    private boolean gameFinished = false;
    private MainFrame mainFrame;
    private GamePanel gamePanel = new GamePanel();
    private PausePanel pausePanel = new PausePanel();
    private GameObject gameObject = new GameAccess().loadGame();
    private MarioObject marioObject = gameObject.getMario();
    private ArrayList<TileObject> allTiles = new ArrayList<TileObject>();
    private Set<KeyEvent> pressedKeys = new HashSet<>();

    public GameController(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        this.mainFrame.setContentPane(gamePanel);
    }

    public void run(){
        while (!gameFinished) {
            sleep();
            updateVelocity();
            move();
            gamePanel.requestFocus();
            mainFrame.requestFocus();
            mainFrame.repaint();
            mainFrame.revalidate();
        }
    }

    public void updateVelocity(){
        keyPressed();
        keyReleased();
    }

    public void move(){
        marioObject.move();
        for (TileObject tile : allTiles)
            tile.move();
    }
    public void sleep(){
        try {
            Thread.sleep(1000 / 60);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public GameObject loadGame(){
        return gameObject;
    }
    public static GameController getInstance(){
        return instance;
    }

    private void keyPressed() {
        boolean left = false, right = false, up = false, down = false, space = false, escape = false;
        for (KeyEvent e : pressedKeys) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
            if (e.getKeyCode() == KeyEvent.VK_UP) up = true;
            if (e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
            if (e.getKeyCode() == KeyEvent.VK_SPACE) space = true;
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) escape = true;
        }

        if (right) marioObject.setXVelocity(marioObject.getSpeed());
        if (left) marioObject.setXVelocity(-marioObject.getSpeed());
        if (up) marioObject.setYVelocity(-marioObject.getSpeed());
        if (down) marioObject.setType("MEGA_MINI");
        if (space) marioObject.setXVelocity(marioObject.getSpeed());
        if (escape){
            gameFinished = true;
            mainFrame.setContentPane(pausePanel);
        }

        for (TileObject tileObject : allTiles)
            tileObject.setXVelocity(-marioObject.getXVelocity());
    }

    private void keyReleased() {
        boolean left = false, right = false, up = false, down = false, space = false, escape = false;
        for (KeyEvent e : pressedKeys) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) left = true;
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = true;
            if (e.getKeyCode() == KeyEvent.VK_UP) up = true;
            if (e.getKeyCode() == KeyEvent.VK_DOWN) down = true;
            if (e.getKeyCode() == KeyEvent.VK_SPACE) space = true;
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) escape = true;
        }

        if (!right && !left) marioObject.setXVelocity(0);
        if (!down) marioObject.setType("SITDOWN");

        for (TileObject tileObject : allTiles)
            tileObject.setXVelocity(-marioObject.getXVelocity());
    }

    public void addPressedKey(KeyEvent e){
        pressedKeys.add(e);
    }
    public void removePressedKey(KeyEvent e){
        pressedKeys.remove(e);
    }
}
