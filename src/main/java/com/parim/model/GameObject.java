package com.parim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parim.model.components.MarioObject;

import java.util.ArrayList;

public class GameObject {
    private ArrayList<LevelObject> levels;
    private int hearts = 3, score = 0, coins = 0;
    private String marioState = "MINI";
    private MarioObject mario = new MarioObject(1, 0);
    private LevelObject currentLevel;
    private SectionObject currentSection;
    @JsonIgnore
    private int time = 0;

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

    public void updateTime(){
        time++;
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

    public SectionObject getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(SectionObject currentSection) {
        this.currentSection = currentSection;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String calculateGameState() {
        int level = levels.indexOf(currentLevel) + 1;
        int section = currentLevel.getSections().indexOf(currentSection) + 1;
        return "" + level + "-" + section;
    }
}
