package com.parim.controller;

import com.parim.access.GameAccess;
import com.parim.model.GameObject;
import com.parim.model.LevelObject;
import com.parim.model.SectionObject;
import com.parim.model.components.MarioObject;
import com.parim.model.components.TileObject;
import com.parim.model.interfaces.Movable;
import com.parim.view.GamePanel;
import com.parim.view.MainFrame;
import com.parim.view.PausePanel;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameController {
    private static GameController instance;
    private boolean gameFinished = false;
    private MainFrame mainFrame;
    private GamePanel gamePanel;
    private PausePanel pausePanel = new PausePanel();
    private GameObject gameObject = new GameAccess().loadGame();
    private MarioObject marioObject = gameObject.getMario();
    private ArrayList<TileObject> allTiles = new ArrayList<>();
    private Set<Integer> pressedKeys = new HashSet<>();

    public GameController(){
    }
    public GameController(MainFrame mainFrame){
        if (instance != null) return;
        instance = this;

        setAllTiles();
        this.mainFrame = mainFrame;
        this.mainFrame.setContentPane(gamePanel = new GamePanel());
        gamePanel.requestFocus();
    }

    public void run(){
        while (!gameFinished) {
            sleep();
            updateVelocity();
            move();
            gamePanel.requestFocus();
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
            if (tile instanceof Movable)
                ((Movable) tile).move();
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
        if (instance == null)  instance = new GameController(new MainFrame());
        return instance;
    }

    private void keyPressed() {
        boolean left = false, right = false, up = false, down = false, space = false, escape = false;
        for (Integer e : pressedKeys) {
            if (e == KeyEvent.VK_LEFT) left = true;
            if (e == KeyEvent.VK_RIGHT) right = true;
            if (e == KeyEvent.VK_UP) up = true;
            if (e == KeyEvent.VK_DOWN) down = true;
            if (e == KeyEvent.VK_SPACE) space = true;
            if (e == KeyEvent.VK_ESCAPE) escape = true;
        }

        if (right) marioObject.setXVelocity(marioObject.getSpeed());
        if (left) marioObject.setXVelocity(-marioObject.getSpeed());
        if (up) marioObject.setYVelocity(-marioObject.getSpeed());
        // if (down) marioObject.setType(marioObject.getType() + "SITDOWN");
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
        for (Integer e : pressedKeys) {
            if (e == KeyEvent.VK_LEFT) left = true;
            if (e == KeyEvent.VK_RIGHT) right = true;
            if (e == KeyEvent.VK_UP) up = true;
            if (e == KeyEvent.VK_DOWN) down = true;
            if (e == KeyEvent.VK_SPACE) space = true;
            if (e == KeyEvent.VK_ESCAPE) escape = true;
        }

        if (!right && !left) marioObject.setXVelocity(0);
        // if (!down) marioObject.setType(marioObject.getType().replace("_SITDOWN", ""));

//        for (TileObject tileObject : allTiles)
//            tileObject.setXVelocity(-marioObject.getXVelocity());
    }

    public void addPressedKey(Integer e){
        pressedKeys.add(e);
    }
    public void removePressedKey(Integer e){
        pressedKeys.remove(e);
    }
    private void setAllTiles() {
        for (LevelObject levelObject : gameObject.getLevels())
            for (SectionObject sectionObject : levelObject.getSections()){
                if (sectionObject.getBlocks() != null) allTiles.addAll(sectionObject.getBlocks());
                if (sectionObject.getEnemies() != null) allTiles.addAll(sectionObject.getEnemies());
                if (sectionObject.getPipes() != null) allTiles.addAll(sectionObject.getPipes());
            }
    }
}
