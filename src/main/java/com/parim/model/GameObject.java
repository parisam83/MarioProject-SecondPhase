package com.parim.model;

import com.parim.model.components.MarioObject;

import java.util.ArrayList;

public class GameObject {
    private ArrayList<LevelObject> levels;
    private int hearts = 3, score = 0, coins = 0;
    private String marioState = "MiniMario";
    private MarioObject mario = new MarioObject(0, 0, marioState);
    private LevelObject currentLevel;

    public GameObject(ArrayList<LevelObject> levels) {
        this.levels = levels;
        currentLevel = levels.get(0);
    }

    // Getters and Setters
    public ArrayList<LevelObject> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<LevelObject> levels) {
        this.levels = levels;
    }

    public int getHearts() {
        return hearts;
    }

    public void setHearts(int hearts) {
        this.hearts = hearts;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getMarioState() {
        return marioState;
    }

    public void setMarioState(String marioState) {
        this.marioState = marioState;
    }

    public MarioObject getMario() {
        return mario;
    }

    public void setMario(MarioObject mario) {
        this.mario = mario;
    }

    public LevelObject getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(LevelObject currentLevel) {
        this.currentLevel = currentLevel;
    }
}
