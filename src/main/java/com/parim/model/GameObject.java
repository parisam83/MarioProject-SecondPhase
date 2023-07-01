package com.parim.model;

import com.parim.model.components.mario.MarioObject;
import com.parim.model.components.mario.MarioState;

import java.util.ArrayList;

public class GameObject {
    private ArrayList<LevelObject> levels;
    private int hearts = 3, score = 0, coins = 0;
    private MarioState marioState = MarioState.MINI;
    private MarioObject mario = new MarioObject(1, 0);
    private LevelObject currentLevel;
    private SectionObject currentSection;


    public GameObject(ArrayList<LevelObject> levels) {
        this.levels = levels;
        currentLevel = levels.get(0);
        currentSection = currentLevel.getSections().get(0);
    }

    public void addScore(int x){
        score += x;
    }
    public void addCoins(int x){
        coins += x;
    }
    public MarioObject resetMario(){
        hearts--;
        return mario = new MarioObject(1, 0);
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

    public MarioState getMarioState() {
        return marioState;
    }

    public void setMarioState(MarioState marioState) {
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

    public SectionObject getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(SectionObject currentSection) {
        this.currentSection = currentSection;
    }

    public String calculateGameState() {
        int level = levels.indexOf(currentLevel) + 1;
        int section = currentLevel.getSections().indexOf(currentSection) + 1;
        return "" + level + "-" + section;
    }

    public SectionObject getNextSection(){
        boolean returnThis = false;
        for (SectionObject section : currentLevel.getSections()) {
            if (returnThis)
                return section;
            if (section == currentSection)
                returnThis = true;
        }
        return null;
    }

    public LevelObject getNextLevel(){
        boolean returnThis = false;
        for (LevelObject level : levels){
            if (returnThis)
                return level;
            if (level == currentLevel)
                returnThis = true;
        }
        return null;
    }
}
